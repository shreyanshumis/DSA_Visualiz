const assert=require('node:assert/strict'),fs=require('node:fs');
const {lessons,makeTrace}=require('./algorithms');
let seed=726231,checks=0;
const cases=[];
const rnd=n=>{seed=(Math.imul(seed,1664525)+1013904223)>>>0;return seed%n;};
const sorted=a=>a.slice().sort((a,b)=>a-b);
const makeArray=()=>Array.from({length:rnd(9)},()=>rnd(15)-6);
function check(id,input,expected,accept){
 const lesson=lessons.find(l=>l.id===id),original=structuredClone(input),frames=makeTrace(lesson,input),answer=frames.at(-1).result;
 assert.deepEqual(input,original);
 if(accept)assert.ok(accept(answer),id+' '+JSON.stringify(input));else assert.deepEqual(answer,expected,id+' '+JSON.stringify(input));
 assert.deepEqual(new Function('return ('+lesson.code+')')()(structuredClone(input)),answer);
 for(const frame of frames)assert.ok(frame.line>=1&&frame.line<=lesson.code.split('\n').length);
 if(lesson.answerSearch&&frames.at(-1).search){assert.equal(frames.at(-1).search.low,answer);assert.equal(frames.at(-1).search.high,answer);}
 cases.push({id,input,expected:answer});checks++;
}
function partition(a,k){
 if(k>a.length)return -1;
 let best=Infinity;
 for(let mask=0;mask<2**(a.length-1);mask++){
  let groups=1,sum=0,maximum=0;
  for(let i=0;i<a.length;i++){sum+=a[i];if(i===a.length-1||(mask&(1<<i))){maximum=Math.max(maximum,sum);sum=0;if(i<a.length-1)groups++;}}
  if(groups===k)best=Math.min(best,maximum);
 }return best;
}
function bouquetCount(a,day,k){let run=0,made=0;for(const x of a){run=x<=day?run+1:0;if(run===k){made++;run=0;}}return made;}
for(let trial=0;trial<100;trial++){
 const a=sorted(makeArray()),unique=[...new Set(a)],target=rnd(23)-8;
 check('binary-search',{a:unique,target},unique.indexOf(target));
 check('ceil-in-a-sorted-array',{a,target,bound:'ceiling'},a.findIndex(x=>x>=target));
 check('ceil-in-a-sorted-array',{a,target,bound:'upper'},a.findIndex(x=>x>target));
 check('find-first-and-last-position-of-element-in-sorted-array',{a,target},[a.indexOf(target),a.lastIndexOf(target)]);
 check('number-of-occurrence2259',{a,target},a.filter(x=>x===target).length);
 check('search-in-infinite-sorted-array',{a:unique,target},unique.indexOf(target));
 const ascent=1+rnd(5),descent=1+rnd(5),mountain=Array.from({length:ascent+1},(_,i)=>i*3).concat(Array.from({length:descent},(_,i)=>ascent*3-2*(i+1)));
 check('peak-index-in-a-mountain-array',{a:mountain},ascent);
 let peaks=makeArray();if(!peaks.length)peaks=[1];for(let i=1;i<peaks.length;i++)if(peaks[i]===peaks[i-1])peaks[i]++;
 check('find-peak-element',{a:peaks},null,i=>Number.isInteger(i)&&i>=0&&i<peaks.length&&(i===0||peaks[i]>peaks[i-1])&&(i===peaks.length-1||peaks[i]>peaks[i+1]));
 const base=unique.length?unique:[0],rotation=rnd(base.length),rotated=base.slice(rotation).concat(base.slice(0,rotation));
 check('find-minimum-in-rotated-sorted-array',{a:rotated},base[0]);
 check('rotation4723',{a:rotated},rotated.indexOf(base[0]));
 check('search-in-rotated-sorted-array',{a:rotated,target},rotated.indexOf(target));
 const piles=Array.from({length:1+rnd(7)},()=>1+rnd(15)),h=rnd(25)+1;
 let speed=-1;for(let s=1;s<=Math.max(...piles);s++)if(piles.reduce((n,x)=>n+Math.ceil(x/s),0)<=h){speed=s;break;}
 check('koko-eating-bananas',{a:piles,h},speed);
 const m=1+rnd(5),k=1+rnd(4);let day=-1;
 for(let d=1;d<=Math.max(...piles);d++)if(bouquetCount(piles,d,k)>=m){day=d;break;}
 check('minimum-number-of-days-to-make-m-bouquets',{a:piles,m,k},day);
 const stalls=[...new Set([0,20,...makeArray()])],cows=2+rnd(stalls.length-1);let gap=0;
 for(let mask=0;mask<2**stalls.length;mask++){
  const picked=sorted(stalls.filter((_,i)=>mask&(1<<i)));
  if(picked.length===cows)gap=Math.max(gap,Math.min(...picked.slice(1).map((x,i)=>x-picked[i])));
 }
 check('aggressive-cows',{a:stalls,k:cows},gap);
 const citations=sorted(piles);let hi=0;for(let h=0;h<=citations.length;h++)if(citations.filter(x=>x>=h).length>=h)hi=h;
 check('h-index-ii',{a:citations},hi);
 const children=1+rnd(35);let portion=0;
 for(let size=1;size<=Math.max(...piles);size++)if(piles.reduce((s,x)=>s+Math.floor(x/size),0)>=children)portion=size;
 check('maximum-candies-allocated-to-k-children',{a:piles,k:children},portion);
 const groups=1+rnd(piles.length+2);
 check('capacity-to-ship-packages-within-d-days',{a:piles,k:groups},partition(piles,Math.min(groups,piles.length)));
 check('allocate-minimum-number-of-pages0937',{a:piles,k:groups},partition(piles,groups));
 const nonnegative=piles.map(x=>x-1),split=1+rnd(nonnegative.length);
 check('split-array-largest-sum',{a:nonnegative,k:split},partition(nonnegative,split));
 const rows=1+rnd(4),columns=1+rnd(4),flat=Array.from({length:rows*columns},(_,i)=>i*2-10);
 const matrix=Array.from({length:rows},(_,r)=>flat.slice(r*columns,(r+1)*columns));
 check('search-a-2d-matrix',{matrix,target},flat.includes(target));
 const grid=[];for(let r=0;r<rows;r++){grid[r]=[];for(let c=0;c<columns;c++)grid[r][c]=Math.max(r?grid[r-1][c]:-5,c?grid[r][c-1]:-5)+rnd(3);}
 check('search-a-2d-matrix-ii',{matrix:grid,target},grid.flat().includes(target));
 const n=1+rnd(4),square=[];for(let r=0;r<n;r++){square[r]=[];for(let c=0;c<n;c++)square[r][c]=Math.max(r?square[r-1][c]:-9,c?square[r][c-1]:-9)+rnd(4);}
 const rank=1+rnd(n*n);
 check('kth-smallest-element-in-a-sorted-matrix',{matrix:square,k:rank},sorted(square.flat())[rank-1]);
 const tableM=1+rnd(8),tableN=1+rnd(8),tableK=1+rnd(tableM*tableN),table=Array.from({length:tableM},(_,r)=>Array.from({length:tableN},(_,c)=>(r+1)*(c+1)));
 check('kth-smallest-number-in-multiplication-table',{m:tableM,n:tableN,k:tableK},sorted(table.flat())[tableK-1]);
 let b=sorted(makeArray());if(!a.length&&!b.length)b=[0];const merged=sorted([...a,...b]),middle=Math.floor(merged.length/2);
 check('median-of-two-sorted-arrays',{a,b},merged.length%2?merged[middle]:(merged[middle-1]+merged[middle])/2);
}
for(const [id,input,expected] of [
 ['maximum-candies-allocated-to-k-children',{a:[0,0],k:4},0],['split-array-largest-sum',{a:[0,0,0],k:2},0],
 ['median-of-two-sorted-arrays',{a:[],b:[-3,-2]},-2.5],['median-of-two-sorted-arrays',{a:[1],b:[]},1],
 ['minimum-number-of-days-to-make-m-bouquets',{a:[1,2,3],m:1000000000,k:1000000000},-1]
])check(id,input,expected);
for(const [id,input] of [
 ['binary-search',{a:[1,1],target:1}],['ceil-in-a-sorted-array',{a:[1],target:1,bound:'wrong'}],
 ['peak-index-in-a-mountain-array',{a:[1,2,3]}],['find-peak-element',{a:[1,1]}],
 ['find-minimum-in-rotated-sorted-array',{a:[2,1,3]}],['koko-eating-bananas',{a:[1],h:0}],
 ['aggressive-cows',{a:[1,2],k:1}],['split-array-largest-sum',{a:[1,-1],k:1}],
 ['search-a-2d-matrix',{matrix:[[1,3],[2,4]],target:2}],['search-a-2d-matrix-ii',{matrix:[[1,2],[0,3]],target:2}],
 ['kth-smallest-element-in-a-sorted-matrix',{matrix:[[1,2]],k:1}],['kth-smallest-number-in-multiplication-table',{m:3,n:3,k:10}],
 ['median-of-two-sorted-arrays',{a:[],b:[]}],['median-of-two-sorted-arrays',{a:[1],b:[3,2]}]
])assert.throws(()=>makeTrace(lessons.find(l=>l.id===id),input),id);
if(process.env.CODE_MODE_CASES)fs.writeFileSync('language-cases.json',JSON.stringify(JSON.parse(fs.readFileSync('language-cases.json','utf8')).concat(cases)));
console.log(`PASS: ${checks} binary-search reference cases, converged answer ranges, and invalid inputs.`);
