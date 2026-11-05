const assert = require('node:assert/strict');
const {timing,changes,stateValues,variableHistory} = require('./playback');
for(const [speed,interval] of [[.1,18000],[.25,7200],[.5,3600],[1,1800],[2,900]]) {
  const t=timing(speed);
  assert.equal(t.interval,interval);
  assert.equal(t.preview+t.hold,interval);
  assert.ok(t.preview>=360,'Every speed gives a separate explanation phase.');
  assert.ok(t.motion<t.hold,'Movement settles before the next preview.');
}
assert.deepEqual(changes({p:{left:0,right:6}},{p:{left:1,right:6}}),['left: 0 → 1']);
assert.deepEqual(changes({p:{left:1,right:6}},{p:{left:0,right:5}}),['left: 1 → 0','right: 6 → 5']);
assert.deepEqual(changes({q:{write:4}},{q:{write:3}}),['write: 4 → 3']);
assert.deepEqual(changes({p:{fast:3}},{p:{fast:-1}}),['fast: 3 → -1']);
assert.deepEqual(changes({p:{}},{p:{left:0}}),[]);
assert.deepEqual(changes({p:{left:0}},{p:{left:0}}),[]);
console.log('PASS: playback timing, movement explanations, reverse steps, and output pointers.');
const history=variableHistory([
  {p:{left:0},vars:{sum:Infinity},freq:{a:1}},
  {p:{left:1},vars:{sum:3},freq:{a:2,b:1}},
  {p:{left:1}},
  {p:{left:1},vars:{sum:3},freq:{b:1}}
]);
assert.equal(history.filter(x=>x.name==='left').length,2);
assert.deepEqual(history.find(x=>x.name==='sum' && x.step===2),{step:2,name:'sum',before:Infinity,after:3});
assert.deepEqual(history.find(x=>x.name==='table[a]' && x.step===4),{step:4,name:'table[a]',before:2,after:undefined});
assert.equal(history.filter(x=>x.step===3).length,0,'Missing annotations must not create false changes.');
assert.equal(history.filter(x=>x.step<=1).length,3,'Rewinding reveals only changes already reached.');
assert.deepEqual(stateValues({q:{write:2},freq:{b:1}}),{write:2,'table[b]':1});
console.log('PASS: cumulative variable history, initialization, frequency changes, and rewind cutoff.');
