"""Execute displayed Python and compile/run displayed Java against shared test cases."""
import json, pathlib, subprocess, copy
ROOT=pathlib.Path(__file__).parent
views=json.loads((ROOT/'code-modes-data.json').read_text(encoding='utf-8'))
lessons=json.loads((ROOT/'lesson-code.json').read_text(encoding='utf-8'))
cases=json.loads((ROOT/'language-cases.json').read_text(encoding='utf-8'))
functions={}
for lesson in lessons:
    ns={}
    try:exec(views[lesson['id']]['python']['code'],ns)
    except Exception:
        print(lesson['id'],views[lesson['id']]['python']['code']);raise
    functions[lesson['id']]=ns['solve']
for case in cases:
    answer=functions[case['id']](**copy.deepcopy(case['input']))
    assert answer==case['expected'],(case,answer)
print('PASS:',len(cases),'Python solution cases')

def literal(value, matrix=False):
    if isinstance(value,list):return 'new '+('int[]' if matrix or (value and isinstance(value[0],list)) else 'String' if value and isinstance(value[0],str) else 'int')+'[]{'+', '.join(literal(v) for v in value)+'}'
    if isinstance(value,str):return json.dumps(value)
    return str(value)
classes=[]
for i,lesson in enumerate(lessons):
    code=views[lesson['id']]['java']['code'].replace('import java.util.*;','').replace('class Solution {',f'class Solution{i} {{')
    classes.append(code)
names={l['id']:(i,list(l['example'])) for i,l in enumerate(lessons)}
methods=[]
for batch in range(0,len(cases),100):
    lines=[f'static void batch{batch}() {{']
    for case in cases[batch:batch+100]:
        i,keys=names[case['id']]
        args=', '.join(literal(case['input'][key],key in ('intervals','other','matrix','stations','projects','courses','points')) for key in keys)
        call=f'new Solution{i}().lengthOfLongestSubstring({args})' if case['id']=='longest-substring-without-repeating-characters' else f'Solution{i}.solve({args})'
        lines.append(f'System.out.println(json({call}));')
    methods.append('\n'.join(lines)+'\n}')
harness=r'''
class LanguageCheck {
static String json(Object value) {
 if (value == null) return "null";
 if (value instanceof String || value instanceof Character) return "\""+value.toString().replace("\\","\\\\").replace("\"","\\\"")+"\"";
 if (value instanceof Map<?,?> map) { List<String> parts=new ArrayList<>(); for(var e:map.entrySet()) parts.add(json(e.getKey())+":"+json(e.getValue())); return "{"+String.join(",",parts)+"}"; }
 if (value instanceof Iterable<?> list) { List<String> parts=new ArrayList<>(); for(Object e:list)parts.add(json(e));return "["+String.join(",",parts)+"]"; }
 if (value.getClass().isArray()) { List<String> parts=new ArrayList<>(); for(int i=0;i<java.lang.reflect.Array.getLength(value);i++)parts.add(json(java.lang.reflect.Array.get(value,i)));return "["+String.join(",",parts)+"]"; }
 return value.toString();
}
'''
harness+='\n'.join(methods)+'\npublic static void main(String[] args) {'+''.join(f'batch{b}();' for b in range(0,len(cases),100))+'}\n}'
output=ROOT/'language-check';output.mkdir(exist_ok=True)
file=output/'LanguageCheck.java';file.write_text('import java.util.*;\n'+'\n'.join(classes)+harness,encoding='utf-8')
subprocess.run(['javac','-encoding','UTF-8',str(file)],check=True)
result=subprocess.run(['java','-cp',str(output),'LanguageCheck'],check=True,text=True,capture_output=True)
answers=result.stdout.splitlines()
assert len(answers)==len(cases),(len(answers),len(cases))
for case,answer in zip(cases,answers):assert json.loads(answer)==case['expected'],(case,answer)
print('PASS:',len(cases),'compiled Java solution cases')
