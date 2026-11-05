const assert=require('node:assert/strict');
const fs=require('node:fs');
const {lessons,makeTrace}=require('./algorithms');
let seed=19328,checks=0;
const rnd=n=>{seed=(Math.imul(seed,1664525)+1013904223)>>>0;return seed%n;};
const cases=[];
function check(id,input,expected){
 const lesson=lessons.find(l=>l.id===id),frames=makeTrace(lesson,input);
 assert.deepEqual(frames.at(-1).result,expected,`${id}: ${JSON.stringify(input)}`);
 const lines=lesson.code.split('\n');
 for(const frame of frames)assert.ok(frame.line>=1&&frame.line<=lines.length);
 assert.deepEqual(new Function('return ('+lesson.code+')')()(structuredClone(input)),expected);
 cases.push({id,input,expected});checks++;
}
function union(items){
 const out=structuredClone(items);
 let changed=true;
 while(changed){changed=false;outer:for(let i=0;i<out.length;i++)for(let j=i+1;j<out.length;j++)if(Math.max(out[i][0],out[j][0])<=Math.min(out[i][1],out[j][1])){
  out[i]=[Math.min(out[i][0],out[j][0]),Math.max(out[i][1],out[j][1])];out.splice(j,1);changed=true;break outer;
 }}return out.sort((a,b)=>a[0]-b[0]);
}
for(let trial=0;trial<100;trial++){
 const a=Array.from({length:1+rnd(8)},()=>rnd(9)-4),k=rnd(12)-3;
 const subs=[];
 for(let i=0;i<a.length;i++)for(let j=i+1;j<=a.length;j++)subs.push(a.slice(i,j));
 const sum=a=>a.reduce((s,x)=>s+x,0),sums=subs.map(sum);
 check('maximum-subarray',{a},Math.max(...sums));
 check('smallest-sum-contiguous-subarray',{a},Math.min(...sums));
 check('maximum-product-subarray',{a},Math.max(...subs.map(a=>a.reduce((p,x)=>p*x,1))));
 let deleted=Math.max(...sums);
 for(const sub of subs)if(sub.length>1)for(const x of sub)deleted=Math.max(deleted,sum(sub)-x);
 check('maximum-subarray-sum-with-one-deletion',{a},deleted);
 check('maximum-absolute-sum-of-any-subarray',{a},Math.max(...sums.map(Math.abs),0));
 let circular=-Infinity;
 for(let i=0;i<a.length;i++)for(let len=1;len<=a.length;len++)circular=Math.max(circular,sum(Array.from({length:len},(_,j)=>a[(i+j)%a.length])));
 check('maximum-sum-circular-subarray',{a},circular);
 check('subarray-sum-equals-k',{a,k},sums.filter(x=>x===k).length);
 check('find-pivot-index',{a},a.findIndex((_,i)=>sum(a.slice(0,i))===sum(a.slice(i+1))));
 const divisor=1+rnd(6);
 check('subarray-sums-divisible-by-k',{a,k:divisor},sums.filter(x=>x%divisor===0).length);
 const binary=a.map(x=>Math.abs(x)%2);let balanced=0;
 for(let i=0;i<binary.length;i++)for(let j=i+1;j<=binary.length;j++)if(sum(binary.slice(i,j))*2===j-i)balanced=Math.max(balanced,j-i);
 check('contiguous-array',{a:binary},balanced);
 const lengths=subs.filter(s=>sum(s)>=k).map(s=>s.length);
 check('shortest-subarray-with-sum-at-least-k',{a,k},lengths.length?Math.min(...lengths):-1);
 const lower=rnd(9)-6,upper=lower+rnd(10);
 check('count-of-range-sum',{a,lower,upper},sums.filter(x=>x>=lower&&x<=upper).length);
 const intervals=Array.from({length:rnd(7)},()=>{let start=rnd(15)-5;return [start,start+1+rnd(5)];});
 const merged=union(intervals);
 check('merge-intervals',{intervals},merged);
 check('employee-free-time',{intervals},merged.slice(1).map((x,i)=>[merged[i][1],x[0]]));
 check('overlapping-intervals',{intervals},intervals.some((x,i)=>intervals.some((y,j)=>i<j&&Math.max(x[0],y[0])<=Math.min(x[1],y[1]))));
 const start=rnd(10)-3,newInterval=[start,start+rnd(5)];
 check('insert-interval',{intervals:merged,newInterval},union([...merged,newInterval]));
 const other=union(Array.from({length:rnd(6)},()=>{let start=rnd(15)-5;return[start,start+rnd(5)];}));
 const intersections=[];
 for(const x of merged)for(const y of other)if(Math.max(x[0],y[0])<=Math.min(x[1],y[1]))intersections.push([Math.max(x[0],y[0]),Math.min(x[1],y[1])]);
 check('interval-list-intersections',{intervals:merged,other},intersections);
 check('attend-all-meetings-ii',{intervals},Math.max(0,...intervals.map(([t])=>intervals.filter(x=>x[0]<=t&&t<x[1]).length)));
 const jobs=intervals.map(pair=>[...pair,rnd(8)]);
 check('maximum-cpu-load',{intervals:jobs},Math.max(0,...jobs.map(([t])=>jobs.filter(x=>x[0]<=t&&t<x[1]).reduce((s,x)=>s+x[2],0))));
}
for(const [id,input] of [
 ['maximum-subarray',{a:[]}],['maximum-product-subarray',{a:[10000,10000,10000]}],
 ['count-of-range-sum',{a:[1],lower:3,upper:2}],['subarray-sums-divisible-by-k',{a:[1],k:0}],
 ['insert-interval',{intervals:[[2,4],[1,3]],newInterval:[0,1]}],['merge-intervals',{intervals:[[3,1]]}],
 ['maximum-cpu-load',{intervals:[[0,1,-2]]}],['attend-all-meetings-ii',{intervals:[[1,1]]}]
])assert.throws(()=>makeTrace(lessons.find(l=>l.id===id),input));
if(process.env.CODE_MODE_CASES){const original=JSON.parse(fs.readFileSync('language-cases.json','utf8'));fs.writeFileSync('language-cases.json',JSON.stringify(original.concat(cases)));}
console.log(`PASS: ${checks} new-lesson oracle cases and 8 invalid inputs.`);
