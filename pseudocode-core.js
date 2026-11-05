// Original practice questions, not copied or represented as actual company test questions.
const bit = (op,a,b)=>[op,a,b];
const set=(name,value)=>['set',name,value];
const print=value=>['print',value];
const pseudoQuestions=[];
function add(title,topic,hint,program){pseudoQuestions.push({id:pseudoQuestions.length,title,topic,hint,program});}
for(let v=0;v<3;v++){
 const a=5+v*2,b=3+v;
 add('AND then XOR','Bitwise basics','AND keeps shared 1 bits. XOR keeps bits that differ.',[set('a',a),set('b',b),set('c',bit('&','a','b')),print(bit('^','c','a'))]);
 add('OR and a mask','Bitwise basics','OR sets a bit if either input has it. The final AND keeps only bits present in the mask.',[set('a',a),set('b',b),set('c',bit('|','a','b')),print(bit('&','c',6))]);
 add('Shift, then combine','Shifts','A left shift by 1 doubles these small positive integers. Apply the AND before the XOR shown outside it.',[set('a',a),set('b',bit('<<','a',1)),print(bit('^','b',bit('&','a',3)))]);
 add('Right shift loop','Loops','Each right shift by 1 discards the lowest bit of these non-negative integers.',[set('n',28+v*7),['for','i',1,3,[set('n',bit('>>','n',1))]],print('n')]);
 add('Branch on a bit','Conditions','n & 1 is 1 for odd numbers and 0 for even numbers.',[set('n',6+v),['if',bit('==',bit('&','n',1),0),[set('n',bit('^','n',3))],[set('n',bit('|','n',2))]],print('n')]);
 add('XOR swap','Assignments','Assignments execute in order. Each line uses the values produced by the previous line.',[set('a',a),set('b',b),set('a',bit('^','a','b')),set('b',bit('^','a','b')),set('a',bit('^','a','b')),print('a'),print('b')]);
 add('Toggle the same bit','Bit masks','XOR toggles a bit. Toggling the same bit twice restores it.',[set('n',a),set('mask',bit('<<',1,v+1)),set('n',bit('^','n','mask')),print('n'),set('n',bit('^','n','mask')),print('n')]);
 add('Set and clear bits','Bit masks','OR with a mask sets bits. AND with the complement of a mask clears those bits.',[set('n',a),set('n',bit('|','n',bit('<<',1,3))),set('n',bit('&','n',['~',bit('<<',1,v)])),print('n')]);
 add('Count set bits','While loops','n & (n - 1) removes the lowest set bit. Count how often you can do it before n becomes zero.',[set('n',11+v*4),set('count',0),['while',bit('>', 'n',0),[set('n',bit('&','n',bit('-','n',1))),set('count',bit('+','count',1))]],print('count')]);
 add('Running XOR','Loops','Track the accumulator after each iteration. Equal values cancel under XOR.',[set('total',0),['for','i',1,4+v,[set('total',bit('^','total','i'))]],print('total')]);
 add('Nested bit loops','Nested loops','For each outer iteration, the inner loop runs completely. Add each i & j value.',[set('sum',0),['for','i',1,2+v,[['for','j',1,3,[set('sum',bit('+','sum',bit('&','i','j')))]]]],print('sum')]);
 add('Complement is signed','Complement','Use signed 32-bit two’s complement: ~n = -n - 1. Then apply the 4-bit mask.',[set('n',a),set('x',['~','n']),print('x'),print(bit('&','x',15))]);
 add('Arithmetic versus XOR','Mixed operators','^ is XOR, not exponentiation. Parentheses specify exactly what to evaluate first.',[set('a',a),set('b',b),set('x',bit('^',bit('+','a',2),'b')),set('y',bit('+','a',bit('^',2,'b'))),print('x'),print('y')]);
 add('Branch inside a loop','Control flow','Test the current i each time. Odd and even iterations take different branches.',[set('x',a),['for','i',1,4,[['if',bit('==',bit('&','i',1),1),[set('x',bit('^','x','i'))],[set('x',bit('+','x','i'))]]]],print('x')]);
}
function expression(e){if(!Array.isArray(e))return String(e);return e[0]==='~'?`(~${expression(e[1])})`:`(${expression(e[1])} ${e[0]} ${expression(e[2])})`;}
function format(program,depth=0){const lines=[],pad='  '.repeat(depth);for(const s of program){if(s[0]==='set')lines.push(`${pad}${s[1]} ← ${expression(s[2])}`);if(s[0]==='print')lines.push(`${pad}PRINT ${expression(s[1])}`);if(s[0]==='for'){lines.push(`${pad}FOR ${s[1]} ← ${s[2]} TO ${s[3]} (inclusive)`);lines.push(...format(s[4],depth+1),pad+'END FOR');}if(s[0]==='while'){lines.push(pad+'WHILE '+expression(s[1]));lines.push(...format(s[2],depth+1),pad+'END WHILE');}if(s[0]==='if'){lines.push(pad+'IF '+expression(s[1]));lines.push(...format(s[2],depth+1),pad+'ELSE',...format(s[3],depth+1),pad+'END IF');}}return lines;}
function execute(program){const vars={},output=[],trace=[];let budget=1000;const binary=n=>(n>>>0).toString(2).padStart(8,'0');
 function value(e){if(typeof e==='number')return e;if(typeof e==='string'){if(!(e in vars))throw Error('Unknown variable '+e);return vars[e];}const a=value(e[1]),b=e.length>2?value(e[2]):0;switch(e[0]){case '+':return a+b;case '-':return a-b;case '&':return a&b;case '|':return a|b;case '^':return a^b;case '~':return ~a;case '<<':return a<<b;case '>>':return a>>b;case '==':return a===b;case '>':return a>b;default:throw Error('Unknown operator');}}
 function detail(e){if(!Array.isArray(e))return '';if(['&','|','^'].includes(e[0]))return `Bits: ${binary(value(e[1]))} ${e[0]} ${binary(value(e[2]))} = ${binary(value(e))}`;if(e[0]==='~')return `Signed complement: ~${value(e[1])} = -${value(e[1])} - 1 = ${value(e)}`;return '';}
 function run(p){for(const s of p){if(--budget<0)throw Error('Trace limit');switch(s[0]){case 'set':{const old=vars[s[1]],n=value(s[2]),why=detail(s[2]);vars[s[1]]=n;trace.push(`${s[1]}: ${old===undefined?'initial':old} → ${n} · ${expression(s[2])}${why?' · '+why:''}`);break;}case 'print':{const n=value(s[1]);output.push(n);trace.push('PRINT → '+n);break;}case 'if':{const yes=value(s[1]);trace.push(expression(s[1])+' is '+Boolean(yes)+' → '+(yes?'IF':'ELSE')+' branch');run(yes?s[2]:s[3]);break;}case 'for':{for(let i=s[2];i<=s[3];i++){vars[s[1]]=i;trace.push('Iteration: '+s[1]+' = '+i);run(s[4]);}trace.push('FOR complete');break;}case 'while':{while(value(s[1])){trace.push(expression(s[1])+' is true');run(s[2]);}trace.push(expression(s[1])+' is false → leave loop');break;}}}}
 run(program);return {output,trace};}
if(typeof module!=='undefined'&&module.exports)module.exports={pseudoQuestions,execute,format};
