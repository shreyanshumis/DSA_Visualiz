'use strict';
const assert = require('node:assert/strict');
const {lessons,makeTrace} = require('./algorithms');
let checks=0, seed=41729;
const languageCases = [];
function random(n) { seed=(Math.imul(seed,1664525)+1013904223)>>>0; return seed%n; }
const array=(n=1+random(8),lo=-4,range=9)=>Array.from({length:n},()=>lo+random(range));
const text=(n=random(9))=>array(n,0,3).map(x=>'abc'[x]).join('');
const sorted=a=>[...a].sort((a,b)=>a-b);
const canon=a=>a.map(x=>JSON.stringify(x)).sort();
function run(id,input) {
  const l=lessons.find(l=>l.id===id);
  assert.ok(l,id);
  const before=structuredClone(input), frames=makeTrace(l,input);
  assert.deepEqual(input,before,'trace must not mutate the editor input');
  assert.ok(frames.at(-1).done);
  const code=l.code.split('\n');
  for(const f of frames) assert.ok(f.line>=1 && f.line<=code.length,`${id}: bad line ${f.line}`);
  assert.match(code[frames.at(-1).line-1],/return/,'completion highlights the executed return');
  const solve=new Function('return ('+l.code+')')();
  assert.deepEqual(solve(structuredClone(input)),frames.at(-1).result,`${id}: displayed solution differs from trace`);
  checks++;
  if (process.env.CODE_MODE_CASES) languageCases.push({id,input:before,expected:frames.at(-1).result});
  return frames.at(-1).result;
}
function equal(id,input,expected) {assert.deepEqual(run(id,input),expected,id+' '+JSON.stringify(input));}
function windows(a) {const result=[];for(let i=0;i<a.length;i++)for(let j=i+1;j<=a.length;j++)result.push({i,j,values:a.slice(i,j)});return result;}
function combinations(a,k) {const out=[];function visit(start,picked){if(picked.length===k){out.push(picked);return;}for(let i=start;i<a.length;i++)visit(i+1,[...picked,a[i]]);}visit(0,[]);return out;}
const sum=a=>a.reduce((s,x)=>s+x,0);
function bruteCircle(a) {
  for(let start=0;start<a.length;start++){
    let i=start;const seen=new Set();
    while((a[i]>0)===(a[start]>0)){
      if(seen.has(i))return true;
      seen.add(i);const j=((i+a[i])%a.length+a.length)%a.length;
      if(j===i)break;i=j;
    }
  }return false;
}
assert.equal(lessons.length,121);
for(const l of lessons)run(l.id,l.example);
for(let round=0;round<70;round++) {
  const a=array(), order=sorted(a), target=random(17)-8;
  const pair=run('two-sum-ii-input-array-is-sorted',{a:order.length<2?[0,1]:order,target});
  const pairInput=order.length<2?[0,1]:order;
  if(pair.length)assert.equal(pairInput[pair[0]-1]+pairInput[pair[1]-1],target);
  else assert.equal(combinations(pairInput,2).some(x=>sum(x)===target),false);
  const binary=array(random(10),0,2);
  equal('segregate-0s-and-1s5106',{a:binary},sorted(binary));
  const unique=[...new Set(order)];
  equal('remove-duplicates-from-sorted-array',{a:order},{length:unique.length,values:unique});
  const twice=order.filter((x,i)=>i<2 || x!==order[i-2]);
  equal('remove-duplicates-from-sorted-array-ii',{a:order},{length:twice.length,values:twice});
  equal('remove-duplicates-from-sorted-list',{a:order},unique);
  equal('squares-of-a-sorted-array',{a:order},sorted(a.map(x=>x*x)));
  const tripleInput=a.length<3?[...a,0,1,2]:a, triples=combinations(tripleInput,3);
  assert.deepEqual(canon(run('3sum',{a:tripleInput})),[...new Set(triples.filter(x=>sum(x)===0).map(x=>JSON.stringify(sorted(x))))].sort());
  const close=run('3sum-closest',{a:tripleInput,target});
  assert.equal(Math.abs(close-target),Math.min(...triples.map(x=>Math.abs(sum(x)-target))));
  equal('count-triplets-with-sum-smaller-than-x5549',{a:tripleInput,target},triples.filter(x=>sum(x)<target).length);
  const positive=array(random(8),1,7), k=1+random(40);
  equal('subarray-product-less-than-k',{a:positive,k},windows(positive).filter(x=>x.values.reduce((p,n)=>p*n,1)<k).length);
  const colors=array(random(10),0,3);
  equal('sort-colors',{a:colors},sorted(colors));
  assert.deepEqual(canon(run('4sum',{a,target})),[...new Set(combinations(a,4).filter(x=>sum(x)===target).map(x=>JSON.stringify(sorted(x))))].sort());
  const erased=s=>{const stack=[];for(const c of s){if(c==='#')stack.pop();else stack.push(c);}return stack.join('');};
  const back=()=>array(random(10),0,4).map(x=>'ab##'[x]).join('');
  const s=back(),t=back();equal('backspace-string-compare',{s,t},erased(s)===erased(t));
  const first=a.findIndex((x,i)=>x!==order[i]);
  const last=a.findLastIndex((x,i)=>x!==order[i]);
  equal('shortest-unsorted-continuous-subarray',{a},first===-1?0:last-first+1);
  const pos=random(a.length+1)-1;
  equal('linked-list-cycle',{a,pos},pos>=0);
  equal('linked-list-cycle-ii',{a,pos},pos<0?null:{index:pos,value:a[pos]});
  const n=1+random(2000), seen=new Set();let v=n;
  while(v!==1 && !seen.has(v)){seen.add(v);v=sum(String(v).split('').map(x=>Number(x)**2));}
  equal('happy-number',{n},v===1);
  const size=1+random(8), duplicate=1+random(size), nums=Array.from({length:size},(_,i)=>i+1).concat(duplicate);
  for(let i=nums.length-1;i>0;i--){const j=random(i+1);[nums[i],nums[j]]=[nums[j],nums[i]];}
  equal('find-the-duplicate-number',{a:nums},duplicate);
  equal('middle-of-the-linked-list',{a},{index:Math.floor(a.length/2),value:a[Math.floor(a.length/2)]});
  equal('palindrome-linked-list',{a},a.join(',')===[...a].reverse().join(','));
  const reordered=[];for(let i=0,j=a.length-1;i<=j;i++,j--){reordered.push(a[i]);if(i!==j)reordered.push(a[j]);}
  equal('reorder-list',{a},reordered);
  const jumps=a.map(x=>x||1);equal('circular-array-loop',{a:jumps},bruteCircle(jumps));
  const width=1+random(a.length);
  equal('max-sum-subarray-of-size-k5313',{a,k:width},Math.max(...windows(a).filter(x=>x.values.length===width).map(x=>sum(x.values))));
  const goal=1+random(20), lengths=windows(positive).filter(x=>sum(x.values)>=goal).map(x=>x.values.length);
  equal('minimum-size-subarray-sum',{a:positive,target:goal},lengths.length?Math.min(...lengths):0);
  const str=text(), distinct=1+random(4), substrings=windows(str.split(''));
  const exact=substrings.filter(x=>new Set(x.values).size===distinct).map(x=>x.values.length);
  equal('longest-k-unique-characters-substring0853',{s:str,k:distinct},exact.length?Math.max(...exact):-1);
  equal('fruit-into-baskets',{a},Math.max(0,...windows(a).filter(x=>new Set(x.values).size<=2).map(x=>x.values.length)));
  equal('longest-substring-without-repeating-characters',{s:str},Math.max(0,...substrings.filter(x=>new Set(x.values).size===x.values.length).map(x=>x.values.length)));
  const budget=random(4);
  equal('longest-repeating-character-replacement',{s:str.toUpperCase(),k:budget},Math.max(0,...substrings.filter(x=>x.values.length-Math.max(...[...new Set(x.values)].map(c=>x.values.filter(v=>v===c).length))<=budget).map(x=>x.values.length)));
  equal('max-consecutive-ones-iii',{a:binary,k:budget},Math.max(0,...windows(binary).filter(x=>x.values.filter(v=>v===0).length<=budget).map(x=>x.values.length)));
  const pattern=text(1+random(3));
  const covers=(values,pattern)=>[...new Set(pattern)].every(c=>values.filter(x=>x===c).length>=pattern.split('').filter(x=>x===c).length);
  const valid=substrings.filter(x=>covers(x.values,pattern)).sort((a,b)=>a.values.length-b.values.length || a.i-b.i);
  equal('minimum-window-substring',{s:str,t:pattern},valid[0]?.values.join('')||'');
  const anagrams=substrings.filter(x=>x.values.length===pattern.length && covers(x.values,pattern)).map(x=>x.i);
  equal('permutation-in-string',{s:str,t:pattern},anagrams.length>0);
  equal('find-all-anagrams-in-a-string',{s:str,t:pattern},anagrams);
  const wordSize=1+random(2), words=Array.from({length:1+random(3)},()=>text(wordSize)), longText=text(14), wordResults=[];
  for(let i=0;i+wordSize*words.length<=longText.length;i++){
    const found=[];for(let j=0;j<words.length;j++)found.push(longText.slice(i+j*wordSize,i+(j+1)*wordSize));
    if(canon(found).join('|')===canon(words).join('|'))wordResults.push(i);
  }
  assert.deepEqual(sorted(run('substring-with-concatenation-of-all-words',{s:longText,words})),wordResults);
}
// Empty, repeated-value, and known tricky boundary cases.
equal('linked-list-cycle',{a:[],pos:-1},false);
equal('linked-list-cycle-ii',{a:[1],pos:0},{index:0,value:1});
equal('middle-of-the-linked-list',{a:[]},null);
equal('palindrome-linked-list',{a:[]},true);
equal('palindrome-linked-list',{a:[1,2,2,1]},true);
equal('reorder-list',{a:[]},[]);
equal('subarray-product-less-than-k',{a:[1,1,1],k:1},0);
equal('minimum-window-substring',{s:'a',t:'aa'},'');
equal('minimum-window-substring',{s:'',t:''},'');
equal('permutation-in-string',{s:'',t:''},true);
equal('find-all-anagrams-in-a-string',{s:'aaaa',t:'aa'},[0,1,2]);
equal('substring-with-concatenation-of-all-words',{s:'wordgoodgoodgoodbestword',words:['word','good','best','good']},[8]);
equal('circular-array-loop',{a:[-1,-2,-3,-4,-5,6]},false);
equal('circular-array-loop',{a:[1,-1,5,1,4]},true);
equal('shortest-unsorted-continuous-subarray',{a:[]},0);
const invalid=[
  ['sort-colors',{a:[3]}],['two-sum-ii-input-array-is-sorted',{a:[2,1],target:3}],
  ['max-sum-subarray-of-size-k5313',{a:[1],k:0}],['linked-list-cycle',{a:[1],pos:2}],
  ['find-the-duplicate-number',{a:[0,1]}],['minimum-size-subarray-sum',{a:[1,-1],target:1}],
  ['substring-with-concatenation-of-all-words',{s:'abc',words:['a','bc']}],
  ['circular-array-loop',{a:[0]}],['longest-repeating-character-replacement',{s:'abc',k:1}],
  ['fruit-into-baskets',{a:[1,2,3],k:3}],['happy-number',{n:0}]
];
for(const [id,input] of invalid){assert.throws(()=>makeTrace(lessons.find(l=>l.id===id),input));checks++;}
// Trace snapshots preserve earlier arrays even after later swaps.
const flags=lessons.find(l=>l.id==='sort-colors');
const trace=makeTrace(flags,{a:[2,0,1]});
assert.deepEqual(trace[0].a,[2,0,1]);assert.deepEqual(trace.at(-1).a,[0,1,2]);
console.log(`PASS: ${checks} example, randomized-oracle, edge-case, and validation checks across all ${lessons.length} lessons.`);
if (process.env.CODE_MODE_CASES) require('node:fs').writeFileSync('language-cases.json',JSON.stringify(languageCases));
