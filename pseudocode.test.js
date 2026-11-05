const assert=require('node:assert/strict');
const {pseudoQuestions,execute,format}=require('./pseudocode-core');
const expected=[
 [4],[6],[11],[3],[5],[3,5],[7,5],[12],[3],[4],[6],[-6,10],[4,6],[9],
 [3],[6],[13],[4],[7],[4,7],[3,7],[13],[4],[1],[12],[-8,8],[13,13],[15],
 [8],[4],[19],[5],[11],[5,9],[1,9],[9],[3],[7],[12],[-10,6],[14,16],[13]
];
assert.equal(pseudoQuestions.length,42);
for(const q of pseudoQuestions){const result=execute(q.program);assert.deepEqual(result.output,expected[q.id],q.id+': '+q.title);assert.ok(result.trace.length>0);assert.ok(format(q.program).join('\n').includes('PRINT'));}
assert.deepEqual(execute([['set','n',-8],['print',['>>','n',1]]]).output,[-4]);
const fs=require('node:fs');for(const name of ['dbms.html','pseudocode.html']){const html=fs.readFileSync(name,'utf8');for(const match of html.matchAll(/<script>([\s\S]*?)<\/script>/g))new Function(match[1]);assert.ok(!/<script[^>]+src=|<link[^>]+href=/.test(html));}
console.log('PASS: 42 independent expected outputs, signed shifts, dry runs and offline script syntax.');
