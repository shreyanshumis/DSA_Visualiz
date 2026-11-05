"""Build the three offline teaching views from the controlled lesson source subset.

The small parser deliberately rejects unsupported constructs. Source locations are
retained so each displayed language can follow the same algorithm trace.
"""
import json, re, pathlib

ROOT = pathlib.Path(__file__).parent

class Parser:
    def __init__(self, source):
        self.source = source
        pattern = r'''\s+|'(?:\\.|[^'\\])*'|"(?:\\.|[^"\\])*"|\d+(?:\.\d+)?|[A-Za-z_$][\w$]*|===|!==|=>|\*\*|\.\.\.|\+\+|--|<=|>=|&&|\|\||\+=|-=|\*=|/=|[^\s]'''
        self.tokens = [(m.group(), source.count('\n',0,m.start())+1) for m in re.finditer(pattern,source) if not m.group().isspace()]
        self.i = 0
    def peek(self, offset=0): return self.tokens[self.i+offset][0] if self.i+offset < len(self.tokens) else 'EOF'
    def pop(self, want=None):
        token, line = self.tokens[self.i]; self.i += 1
        if want is not None and token != want: raise ValueError(f'Expected {want}, got {token} on {line}')
        return token
    def eat(self, token):
        if self.peek() == token: self.pop(); return True
        return False
    def expr(self, minimum=0):
        token=self.pop()
        if token in ('!', '-', '+', '...'):
            left=('unary',token,self.expr(14))
        elif token=='new':
            left=('new',self.expr(16))
        elif token=='(':
            args=[]
            if self.peek()!=')':
                args.append(self.expr())
                while self.eat(','): args.append(self.expr())
            self.pop(')'); left=args[0] if len(args)==1 else ('tuple',args)
        elif token=='[':
            items=[]
            if self.peek()!=']':
                items.append(self.expr())
                while self.eat(','): items.append(self.expr())
            self.pop(']'); left=('array',items)
        elif token=='{':
            items=[]
            while self.peek()!='}':
                key=self.pop(); value=self.expr() if self.eat(':') else ('id',key)
                items.append((key,value))
                if not self.eat(','): break
            self.pop('}'); left=('object',items)
        elif token[0] in "'\"": left=('str',token[1:-1])
        elif re.fullmatch(r'\d+(?:\.\d+)?',token): left=('num',token)
        else: left=('id',token)
        precedence={'=':1,'+=':1,'-=':1,'*=':1,'/=':1,'=>':1,'?':2,'||':3,'&&':4,'===':7,'!==':7,'<':8,'>':8,'<=':8,'>=':8,'+':10,'-':10,'*':11,'/':11,'%':11,'**':13}
        while True:
            op=self.peek()
            if op in ('(','.','[','++','--') and 17>=minimum:
                self.pop()
                if op=='.': left=('member',left,self.pop())
                elif op=='[':
                    index=self.expr(); self.pop(']'); left=('index',left,index)
                elif op=='(':
                    args=[]
                    if self.peek()!=')':
                        args.append(self.expr())
                        while self.eat(','): args.append(self.expr())
                    self.pop(')'); left=('call',left,args)
                else: left=('post',op,left)
                continue
            power=precedence.get(op,-1)
            if power<minimum: break
            self.pop()
            if op=='?':
                yes=self.expr(); self.pop(':'); no=self.expr(power)
                left=('ternary',left,yes,no)
            else:
                right=self.expr(power if op in ('=','+=','-=','*=','/=','=>','**') else power+1)
                left=('binary',op,left,right)
        return left
    def statement(self):
        line=self.tokens[self.i][1]; token=self.peek()
        if self.eat('{'):
            out=[]
            while self.peek()!='}': out.append(self.statement())
            self.pop('}'); return ('block',line,out)
        if token in ('let','const'):
            self.pop(); values=[]
            while True:
                name=self.pop(); self.pop('='); values.append((name,self.expr(2)))
                if not self.eat(','): break
            self.eat(';'); return ('decl',line,values)
        if token=='if':
            self.pop(); self.pop('('); cond=self.expr(); self.pop(')'); yes=self.statement(); no=self.statement() if self.eat('else') else None
            return ('if',line,cond,yes,no)
        if token=='while':
            self.pop(); self.pop('('); cond=self.expr(); self.pop(')'); return ('while',line,cond,self.statement())
        if token=='do':
            self.pop(); body=self.statement(); self.pop('while'); self.pop('('); cond=self.expr(); self.pop(')'); self.eat(';'); return ('do',line,cond,body)
        if token=='for':
            self.pop(); self.pop('(')
            if self.peek(2)=='of':
                self.pop(); name=self.pop(); self.pop('of'); collection=self.expr(); self.pop(')'); return ('foreach',line,name,collection,self.statement())
            init=self.statement(); cond=self.expr(); self.pop(';'); update=self.expr(); self.pop(')'); return ('for',line,init,cond,update,self.statement())
        if token=='function':
            self.pop(); name=self.pop(); self.pop('('); args=[]
            while self.peek()!=')':
                args.append(self.pop())
                if not self.eat(','): break
            self.pop(')'); return ('helper',line,name,args,self.statement())
        if token=='return':
            self.pop(); value=self.expr(); self.eat(';'); return ('return',line,value)
        if token in ('break','continue'):
            self.pop(); self.eat(';'); return (token,line)
        value=self.expr(); self.eat(';'); return ('expr',line,value)
    def parse(self):
        self.pop('function'); self.pop(); self.pop('(')
        depth=1
        while depth:
            t=self.pop(); depth += (t=='(')-(t==')')
        return self.statement()

class Emitter:
    def __init__(self, lesson, lang):
        self.lesson=lesson; self.lang=lang; self.lines=[]; self.map={}; self.indent=0; self.loops=[]; self.helpers=[]
        self.types={k:('String[]' if k=='words' else 'int[][]' if isinstance(v,list) and v and isinstance(v[0],list) else 'int[]' if isinstance(v,list) else 'String' if isinstance(v,str) else 'int') for k,v in lesson['example'].items()}
        self.types.update({'next':'int[]','out':'int[]','result':'List<Object>','freq':'Map<Object, Integer>','need':'Map<Object, Integer>','seen':'Map<Object, Integer>','last':'Map<Object, Integer>'})
    def line(self,text,source):
        self.lines.append('    '*self.indent+text); self.map.setdefault(str(source),[]).append(len(self.lines))
    def typ(self,e):
        kind=e[0]
        if kind=='call' and e[1][0]=='id' and e[1][1] in self.lesson.get('helperTypes',{}):return self.lesson['helperTypes'][e[1][1]]['return']
        if kind=='num' and '.' in e[1]:return 'double'
        if kind=='id': return self.types.get(e[1],'int')
        if kind=='str': return 'char' if len(e[1])==1 else 'String'
        if kind=='array': return 'List<Object>'
        if kind=='index': return 'int[]' if self.typ(e[1])=='int[][]' else 'char' if self.typ(e[1]) in ('String','char[]') else 'String' if self.typ(e[1])=='String[]' else 'int'
        if kind=='ternary': return 'Character' if e[3]==('id','null') and self.typ(e[2])=='char' else self.typ(e[2])
        if kind=='binary' and e[1]=='||' and e[3]==('num','0'): return self.typ(e[2])
        if kind=='binary' and e[1] in ('===','!==','<','>','<=','>=','&&','||'): return 'boolean'
        if kind=='binary' and 'double' in (self.typ(e[2]),self.typ(e[3])):return 'double'
        if kind=='binary' and e[1]=='+' and 'String' in (self.typ(e[2]),self.typ(e[3])):return 'String'
        if kind=='unary' and e[1]=='!': return 'boolean'
        if kind=='call' and e[1][0]=='member' and e[1][2]=='slice': return self.typ(e[1][1])
        if kind=='call' and e[1][0]=='member' and e[1][2]=='split': return 'String[]' if e[2] and e[2][0]!=('str','') else 'char[]'
        if kind=='call' and e[1][0]=='member' and self.typ(e[1][1])=='PriorityQueue<int[]>':return 'int[]'
        if kind=='new' and e[1][0]=='call' and e[1][1]==('id','Heap'):return 'PriorityQueue<int[]>'
        if kind=='new' and e[1][0]=='call' and e[1][1]==('id','Map'):return 'Map<Object, Integer>'
        if kind=='new' or (kind=='call' and e[1][0]=='member' and e[1][2]=='fill'): return 'int[]'
        return 'int'
    def expr(self,e):
        k=e[0]; py=self.lang=='python'; ex=self.expr
        if k=='id':
            return ({'true':'True','false':'False','null':'None','Infinity':"float('inf')",'min':'minimum','max':'maximum'} if py else {'Infinity':'Integer.MAX_VALUE'}).get(e[1],e[1])
        if k=='num': return e[1]
        if k=='str': return repr(e[1]) if py else ("'"+e[1]+"'" if len(e[1])==1 else json.dumps(e[1]))
        if k=='array': return '['+', '.join(map(ex,e[1]))+']' if py else 'Arrays.asList('+', '.join(map(ex,e[1]))+')'
        if k=='tuple': return ', '.join(map(ex,e[1]))
        if k=='object': return '{'+', '.join(repr(key)+': '+ex(value) for key,value in e[1])+'}' if py else 'Map.of('+', '.join(json.dumps(key)+', '+ex(value) for key,value in e[1])+')'
        if k=='new':
            if e[1][0]=='call' and e[1][1]==('id','Heap'):return '[]' if py else 'new PriorityQueue<>((x, y) -> Arrays.compare(x, y))'
            if e[1][0]=='call' and e[1][1]==('id','Map'): return '{}' if py else 'new HashMap<>()'
            # The parser binds new Array(n).fill(0) as one constructor expression.
            v=e[1]
            if v[0]=='call' and v[1][0]=='member' and v[1][2]=='fill':
                constructor=v[1][1]
                if constructor[0]=='call' and constructor[1]==('id','Array'):
                    size=ex(constructor[2][0]); return '[0] * '+size if py else 'new int['+size+']'
            raise ValueError(e)
        if k=='index':
            a,i=ex(e[1]),ex(e[2]); return f'{a}[{i}]' if py or self.typ(e[1])!='String' else f'{a}.charAt({i})'
        if k=='member':
            a,member=ex(e[1]),e[2]
            if member=='length': return f'len({a})' if py else a+('.size()' if self.typ(e[1]).startswith('List') else '.length()' if self.typ(e[1])=='String' else '.length')
            if member=='size': return f'len({a})' if py else f'{a}.size()'
            return a+'.'+member
        if k=='unary':
            if e[1]=='...': return ex(e[2])
            return '('+({'!':'not '} if py else {}).get(e[1],e[1])+ex(e[2])+')'
        if k=='post': return ex(e[2])+(' += 1' if e[1]=='++' else ' -= 1') if py else ex(e[2])+e[1]
        if k=='ternary':
            cond=self.condition(e[1]); yes=ex(e[2]); no=ex(e[3]); return f'({yes} if {cond} else {no})' if py else f'({cond} ? {yes} : {no})'
        if k=='binary':
            op,a,b=e[1:]
            if op=='=>': return 'lambda '+ex(a)+': '+ex(b) if py else '('+ex(a)+') -> '+ex(b)
            if op=='**': return f'({ex(a)} ** {ex(b)})' if py else f'({ex(a)} * {ex(a)})'
            if op in ('===','!=='):
                if py: return f'({ex(a)} {"==" if op=="===" else "!="} {ex(b)})'
                lhs=json.dumps(a[1]) if a[0]=='str' and self.typ(b)=='String' else ex(a)
                rhs=json.dumps(b[1]) if b[0]=='str' and self.typ(a)=='String' else ex(b)
                return ('!' if op=='!==' else '')+f'Objects.equals({lhs}, {rhs})'
            if op=='||' and b==('num','0'): return ex(a) if not py else f'({ex(a)} or 0)'
            op=({'&&':'and','||':'or','/':'//','/=':'//='} if py else {}).get(op,op)
            wrap=op not in ('=','+=','-=','*=','/=','//=')
            return ('(' if wrap else '')+ex(a)+' '+op+' '+ex(b)+(')' if wrap else '')
        if k=='call':
            callee,args=e[1:]; arg=[ex(a) for a in args]
            if callee[0]=='id' and callee[1] in self.lesson.get('helperTypes',{}):return callee[1]+'('+', '.join(arg)+')'
            if callee==('id','next') and self.types.get('next')=='function': return 'next('+', '.join(arg)+')' if py else 'next.applyAsInt('+', '.join(arg)+')'
            if callee[0]=='member':
                obj,method=callee[1:]; name=ex(obj)
                if self.typ(obj)=='PriorityQueue<int[]>':
                    if method=='push':return f'heapq.heappush({name}, {arg[0]})' if py else name+'.add(new int[]{'+', '.join(ex(x) for x in args[0][1])+'})' if args[0][0]=='array' else name+'.add('+arg[0]+')'
                    if method=='pop':return f'heapq.heappop({name})' if py else name+'.poll()'
                    if method=='peek':return name+'[0]' if py else name+'.peek()'
                if name=='Math':
                    if method=='floor': return f'({arg[0]})' if py else f'((int) Math.floor({arg[0]}))'
                    fn={'min':'min','max':'max','abs':'abs'}[method]
                    if args and args[0][0]=='unary' and args[0][1]=='...': return f'max({arg[0]})' if py else f'Collections.max({arg[0]})'
                    return (fn if py else 'Math.'+fn)+'('+', '.join(arg)+')'
                if method=='split':
                    if args and args[0]!=('str',''):return f'{name}.split({arg[0]})' if py else f'{name}.split({json.dumps(args[0][1])}, -1)'
                    return f'list({name})' if py else f'{name}.toCharArray()'
                if method=='sort':
                    if self.typ(obj)=='int[][]':
                        column=args[0][3][2][2][1]
                        return f'{name}.sort(key=lambda item: item[{column}])' if py else f'Arrays.sort({name}, (x, y) -> Integer.compare(x[{column}], y[{column}]))'
                    return f'{name}.sort()' if py else f'Arrays.sort({name})'
                if method=='push': return f'{name}.append({arg[0]})' if py else f'{name}.add({arg[0]})'
                if method=='pop':return name+'.pop()' if py else name+'.remove('+name+'.size() - 1)'
                if method=='indexOf':return name+'.find('+arg[0]+')' if py else name+'.indexOf('+arg[0]+')'
                if method=='join':return f'{arg[0]}.join({name})' if py else f'{name}.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining({json.dumps(args[0][1])}))'
                if method=='get': return f'{name}.get({arg[0]}, 0)' if py else f'{name}.getOrDefault({arg[0]}, 0)'
                if method=='set': return f'{name}[{arg[0]}] = {arg[1]}' if py else f'{name}.put({arg[0]}, {arg[1]})'
                if method=='has': return f'({arg[0]} in {name})' if py else f'{name}.containsKey({arg[0]})'
                if method=='delete': return f'{name}.pop({arg[0]}, None)' if py else f'{name}.remove({arg[0]})'
                if method in ('clear','values'): return f'{name}.{method}()'
                if method=='slice':
                    if self.typ(obj).startswith('List'):
                        end=arg[1] if len(arg)>1 else (f'len({name})' if py else name+'.size()')
                        return f'{name}[{arg[0]}:{end}]' if py else f'new ArrayList<>({name}.subList({arg[0]}, {end}))'
                    end=arg[1] if len(arg)>1 else (f'len({name})' if py else name+'.length')
                    return f'{name}[{arg[0]}:{end}]' if py else f'{name}.substring({arg[0]}, {end})' if self.typ(obj)=='String' else f'Arrays.copyOfRange({name}, {arg[0]}, {end})'
            raise ValueError(('Unsupported call',e))
        raise ValueError(e)
    def condition(self,e):
        if e[0]=='unary' and e[1]=='!' and e[2][0]=='member' and e[2][2]=='length':
            return f'{self.expr(e[2])} == 0'
        if e[0]=='member' and e[2]=='length': return f'{self.expr(e)} > 0'
        return self.expr(e)
    def block(self,node):
        if node[0]=='block':
            for st in node[2]: self.statement(st)
        else: self.statement(node)
    def open(self,text,line):
        self.line(text+(':' if self.lang=='python' else ' {'),line); self.indent+=1
    def close(self,line):
        self.indent-=1
        if self.lang=='java': self.line('}',line)
    def simple(self,text,line): self.line(text+('' if self.lang=='python' else ';'),line)
    def statement(self,node):
        k,line,*v=node; py=self.lang=='python'; ex=self.expr
        if k=='block': self.block(node)
        elif k=='decl':
            for name,value in v[0]:
                # Representation scaffolding is explicit in both displayed solutions.
                if name=='next' and value[0]=='call' and value[1][0]=='member' and value[1][2]=='map':
                    tail='pos' if 'pos' in self.lesson['example'] else '-1'
                    self.types[name]='int[]'
                    self.simple(f'next = [i + 1 if i + 1 < len(a) else {tail} for i in range(len(a))]' if py else f'int[] next = new int[a.length]',line)
                    if not py: self.line(f'for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : {tail};',line)
                    continue
                if name=='out' and value[0]=='call' and value[1][0]=='member' and value[1][2]=='fill':
                    self.simple('out = [None] * len(a)' if py else 'int[] out = new int[a.length]',line);continue
                if name=='result' and value[0]=='array': self.types[name]='List<Object>'
                elif name=='a' and value[0]=='call': self.types[name]=self.typ(value)
                elif name=='product': self.types[name]='long'
                else: self.types[name]=self.typ(value)
                # Post-increment in a subscript must happen after saving the value.
                if value[0]=='index' and value[2][0]=='post':
                    idx=value[2][2]; value=('index',value[1],idx)
                    self.simple(('' if py else self.types[name]+' ')+name+' = '+ex(value),line)
                    self.simple(ex(('post','++',idx)),line);continue
                val=ex(value)
                if not py and value[0]=='array' and not value[1]: val='new ArrayList<>()'
                self.simple(('' if py else self.types[name]+' ')+ex(('id',name))+' = '+val,line)
        elif k=='expr':
            value=v[0]
            if value[0]=='binary' and value[1]=='=' and value[2][0]=='array':
                lhs,rhs=value[2][1],value[3][1]
                if py: self.simple(', '.join(map(ex,lhs))+' = '+', '.join(map(ex,rhs)),line)
                else:
                    self.line('{ int swap = '+ex(lhs[0])+'; '+ex(lhs[0])+' = '+ex(rhs[0])+'; '+ex(lhs[1])+' = swap; }',line)
            else: self.simple(ex(value),line)
        elif k=='if':
            self.open('if '+('' if py else '(')+self.condition(v[0])+('' if py else ')'),line);self.block(v[1]);self.close(line)
            if v[2]: self.open('else',line);self.block(v[2]);self.close(line)
        elif k=='while':
            self.open('while '+('' if py else '(')+self.condition(v[0])+('' if py else ')'),line)
            self.loops.append(None);self.block(v[1]);self.loops.pop();self.close(line)
        elif k=='for':
            init,cond,update,body=v
            if py:
                self.statement(init);self.open('while '+self.condition(cond),line);self.loops.append(update);self.block(body);self.loops.pop();self.simple(ex(update),line);self.close(line)
            else:
                name,value=init[2][0];self.types[name]='int'
                self.open(f'for (int {name} = {ex(value)}; {self.condition(cond)}; {ex(update)})',line);self.loops.append(None);self.block(body);self.loops.pop();self.close(line)
        elif k=='foreach':
            name,collection,body=v;self.types[name]='char' if self.typ(collection)=='String' else 'String'
            self.open(f'for {name} in {ex(collection)}' if py else f'for ({self.types[name]} {name} : {ex(collection)}'+('.toCharArray()' if self.typ(collection)=='String' else '')+')',line)
            self.loops.append(None);self.block(body);self.loops.pop();self.close(line)
        elif k=='do':
            if py:
                self.open('while True',line);self.loops.append(None);self.block(v[1]);self.loops.pop();self.open('if not ('+self.condition(v[0])+')',line);self.line('break',line);self.close(line);self.close(line)
            else:
                self.open('do',line);self.loops.append(None);self.block(v[1]);self.loops.pop();self.indent-=1;self.line('} while ('+self.condition(v[0])+');',line)
        elif k=='helper':
            name,args,body=v;self.types[name]='function'
            if name in self.lesson.get('helperTypes',{}):
                if not py:self.helpers.append(node);return
                self.types.update(self.lesson['helperTypes'][name]['args'])
                self.line('def '+name+'('+', '.join(args)+'):',line);self.indent+=1;self.block(body);self.indent-=1
                return
            if self.lesson['id']=='happy-number':
                self.line('def next(value):' if py else 'java.util.function.IntUnaryOperator next = value -> {',line);self.indent+=1
                self.simple("return sum(int(digit) ** 2 for digit in str(value))" if py else "return String.valueOf(value).chars().map(digit -> (digit - '0') * (digit - '0')).sum()",line)
                self.indent-=1
                if not py:self.line('};',line)
            else:
                self.line('def '+name+'('+', '.join(args)+'):' if py else 'java.util.function.IntUnaryOperator '+name+' = '+args[0]+' -> {',line);self.indent+=1;self.block(body);self.indent-=1
                if not py:self.line('};',line)
        elif k=='return': self.simple('return '+ex(v[0]),line)
        elif k=='continue':
            if py and self.loops and self.loops[-1]: self.simple(ex(self.loops[-1]),line)
            self.simple('continue',line)
        elif k=='break': self.simple('break',line)
        else: raise ValueError(node)
    def generate(self,tree):
        args=list(self.lesson['example']);py=self.lang=='python'
        if py:
            if 'new Heap' in self.lesson['code']:self.line('import heapq',1)
            self.line('def solve('+', '.join(args)+'):',1); self.indent=1
        else:
            self.line('import java.util.*;',1);self.open('class Solution',1)
            self.open('public static Object solve('+', '.join(self.types[k]+' '+k for k in args)+')',1)
        # Function parameter defaults used by shared lessons become explicit locals.
        if self.lesson['id']=='3sum': self.simple('target = 0' if py else 'int target = 0',1)
        if self.lesson['id']=='longest-k-unique-characters-substring0853':
            self.simple('a = list(s)' if py else 'char[] a = s.toCharArray()',1);self.types['a']='char[]'
        if self.lesson['id']=='fruit-into-baskets': self.simple('k = 2' if py else 'int k = 2',1)
        if self.lesson['id'] in ('reverse-linked-list','reverse-linked-list-ii','swap-nodes-in-pairs','reverse-nodes-in-k-group','reverse-nodes-in-even-length-groups'):
            for name,value in [('k','2'),('left','1'),('right','len(a)' if py else 'a.length')]:
                if name not in args:self.simple(('' if py else 'int ')+name+' = '+value,1)
        if self.lesson['id']=='remove-all-adjacent-duplicates-in-string':self.simple('k = 2' if py else 'int k = 2',1)
        self.block(tree)
        if not py:
            self.close(1)
            for node in self.helpers:
                _,line,name,args,body=node;meta=self.lesson['helperTypes'][name];self.types.update(meta['args'])
                self.open('static '+meta['return']+' '+name+'('+', '.join(meta['args'][arg]+' '+arg for arg in args)+')',line)
                self.block(body);self.close(line)
            self.close(1)
        return {'code':'\n'.join(self.lines),'map':self.map}

class PseudoEmitter(Emitter):
    def expr(self,e):
        k=e[0]; ex=self.expr
        if k=='id': return {'Infinity':'∞','null':'NONE','true':'TRUE','false':'FALSE'}.get(e[1],e[1])
        if k=='num':return e[1]
        if k=='str':return repr(e[1])
        if k=='array':return '['+', '.join(map(ex,e[1]))+']'
        if k=='tuple':return ', '.join(map(ex,e[1]))
        if k=='object':return '('+', '.join(key+': '+ex(value) for key,value in e[1])+')'
        if k=='new':
            v=e[1]
            if v[0]=='call' and v[1]==('id','Heap'):return 'empty min-priority heap (records compare left to right)'
            if v[0]=='call' and v[1][0]=='member' and v[1][2]=='fill':return 'array of '+ex(v[1][1][2][0])+' zeros'
            return 'empty frequency table'
        if k=='index':return ex(e[1])+'['+ex(e[2])+']'
        if k=='member':return ('length('+ex(e[1])+')' if e[2]=='length' else 'number of keys in '+ex(e[1]) if e[2]=='size' else ex(e[1])+'.'+e[2])
        if k=='unary':return ('' if e[1]=='...' else 'NOT ' if e[1]=='!' else e[1])+ex(e[2])
        if k=='post':return ('INCREASE ' if e[1]=='++' else 'DECREASE ')+ex(e[2])+' BY 1'
        if k=='ternary':return '('+ex(e[2])+' IF '+ex(e[1])+' ELSE '+ex(e[3])+')'
        if k=='binary':
            op,a,b=e[1:]
            if op in ('+=','-=','*=','/='):return ex(a)+' ← '+ex(a)+' '+op[0]+' '+ex(b)
            if op=='=':return ex(a)+' ← '+ex(b)
            if op=='||' and b==('num','0'):return ex(a)
            return '('+ex(a)+' '+{'===':'=','!==':'≠','&&':'AND','||':'OR','**':'^','=>':'→'}.get(op,op)+' '+ex(b)+')'
        if k=='call':
            callee,args=e[1:];values=list(map(ex,args))
            if callee[0]=='id' and callee[1] in self.lesson.get('helperTypes',{}):return callee[1]+'('+', '.join(values)+')'
            if callee==('id','next'):return 'next('+', '.join(values)+')'
            if callee[0]=='member':
                obj,method=callee[1:];name=ex(obj)
                if self.typ(obj)=='PriorityQueue<int[]>':
                    if method=='push':return 'INSERT '+values[0]+' INTO '+name
                    if method=='pop':return 'REMOVE MINIMUM FROM '+name
                    if method=='peek':return 'MINIMUM OF '+name
                if name=='Math':return method.upper()+'('+', '.join(values)+')'
                if method=='sort':return 'SORT '+name+(' by '+('start' if args[0][3][2][2][1]=='0' else 'end') if self.typ(obj)=='int[][]' else ' in ascending order')
                if method=='get':return 'count('+name+', '+values[0]+')'
                if method=='set':return name+'['+values[0]+'] ← '+values[1]
                if method=='has':return values[0]+' IS IN '+name
                if method=='delete':return 'REMOVE '+values[0]+' FROM '+name
                if method=='clear':return 'CLEAR '+name
                if method=='values':return 'counts('+name+')'
                if method=='split':return ('SPLIT '+name+' on '+values[0]) if args and args[0]!=('str','') else 'characters of '+name
                if method=='push':return 'APPEND '+values[0]+' TO '+name
                if method=='pop':return 'REMOVE LAST ITEM FROM '+name
                if method=='indexOf':return 'position of '+values[0]+' in '+name
                if method=='join':return 'JOIN '+name+' with separator '+values[0]
                if method=='slice':return 'slice of '+name+' from '+values[0]+' up to '+(values[1] if len(values)>1 else 'the end')+' (exclusive)'
        raise ValueError(e)

def pseudocode(lesson,tree):
    # Keep language-independent operations, with structural keywords and no implementation syntax.
    view=PseudoEmitter(lesson,'python').generate(tree)
    lines=[]
    for raw in view['code'].splitlines():
        indent=raw[:len(raw)-len(raw.lstrip())]; text=raw.strip()
        if text.startswith('def solve'): text='PROCEDURE '+lesson['id'].replace('-',' ').upper()
        elif text.startswith('def next'): text='DEFINE next(value): the next state in the sequence'
        else:
            if text.startswith('next = [i + 1'):text='CONNECT consecutive nodes; link the last node to '+('pos' if 'pos' in lesson['example'] else 'NONE')
            elif text.startswith('out = [None]'):text='out ← empty output array with length(a) slots'
            elif text.startswith('return sum(int(digit)'):text='RETURN sum of the squares of the digits of value'
            elif text=='a = list(s)':text='a ← characters of s'
            text=text.replace('True','TRUE').replace('False','FALSE').replace('None','NONE')
            # Only declaration assignments use =; comparisons already have parentheses.
            text=re.sub(r'^(\w+) = ',r'\1 ← ',text)
            for old,new in [('while ','WHILE '),('if ','IF '),('else:','ELSE'),('return ','RETURN '),('for ','FOR EACH '),('break','STOP LOOP'),('continue','CONTINUE LOOP')]:
                if text.startswith(old):text=new+text[len(old):];break
            text=text.rstrip(':')
        lines.append(indent+text)
    return {'code':'\n'.join(lines),'map':view['map']}

def main():
    lessons=json.loads((ROOT/'lesson-code.json').read_text(encoding='utf-8'))
    all_views={}
    for lesson in lessons:
        try:
            tree=Parser(lesson['code']).parse()
            all_views[lesson['id']]={lang:Emitter(lesson,lang).generate(tree) for lang in ('python','java')}
            all_views[lesson['id']]['pseudocode']=pseudocode(lesson,tree)
            if lesson['id']=='longest-substring-without-repeating-characters':
                code='''import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;
        Map<Character, Integer> lastIndex = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // if char already seen, move left pointer after its last occurrence
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            lastIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}'''
                mapping={}
                targets=[('const n =',5),('let left =',6),('const lastIndex =',7),('for (',9),('const c =',10),('if (lastIndex',13),('left = Math.max',14),('lastIndex.set',17),('maxLen = Math.max',18),('return maxLen',21)]
                for i,line in enumerate(lesson['code'].splitlines(),1):
                    mapping[str(i)]=[next((target for token,target in targets if token in line),4)]
                all_views[lesson['id']]['java']={'code':code,'map':mapping}
        except Exception as error:
            print('FAILED',lesson['id']);raise
    (ROOT/'code-modes.js').write_text('/* Generated by generate_code_modes.py; no network dependencies. */\nwindow.LabCodeModes = '+json.dumps(all_views,ensure_ascii=False,indent=2)+';\n',encoding='utf-8')
    (ROOT/'code-modes-data.json').write_text(json.dumps(all_views,ensure_ascii=False),encoding='utf-8')
    print('Generated pseudocode, Python and Java for',len(all_views),'lessons.')
if __name__=='__main__':main()
