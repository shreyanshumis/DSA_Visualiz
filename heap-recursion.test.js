const assert=require('node:assert/strict'),fs=require('node:fs');
const {lessons,makeTrace}=require('./algorithms');
let checks=0,seed=93821;const cases=[];
const random=n=>(seed=(Math.imul(seed,1664525)+1013904223)>>>0)%n;
function run(id,input){const lesson=lessons.find(l=>l.id===id),before=structuredClone(input),frames=makeTrace(lesson,input);assert.deepEqual(input,before);for(const frame of frames){assert.ok(frame.line>0&&frame.line<=lesson.code.split('\n').length);for(const key of ['heap','heap2'])if(frame[key])for(let i=1;i<frame[key].length;i++){const child=frame[key][i],parent=frame[key][Math.floor((i-1)/2)];assert.ok(!globalThis.Heap.prototype.less(child,parent),id+' broken heap');}}
 const answer=frames.at(-1).result;assert.deepEqual(new Function('return ('+lesson.code+')')()(structuredClone(input)),answer);checks++;cases.push({id,input:before,expected:answer});return answer;}
const check=(id,input,expected)=>assert.deepEqual(run(id,input),expected,id+' '+JSON.stringify(input));
const sorted=a=>a.slice().sort((a,b)=>a-b),median=a=>{const b=sorted(a),m=b.length>>1;return b.length%2?b[m]:(b[m-1]+b[m])/2;};
for(let trial=0;trial<100;trial++){
 const a=Array.from({length:1+random(9)},()=>random(15)-7),k=1+random(a.length),b=sorted(a);
 check('kth-smallest-element5635',{a,k},b[k-1]);check('kth-largest-element-in-an-array',{a,k},b.at(-k));
 check('find-median-from-data-stream',{a,k:1},a.map((_,i)=>median(a.slice(0,i+1))));
 check('sliding-window-median',{a,k},a.slice(k-1).map((_,i)=>median(a.slice(i,i+k))));
 const counts=new Map();a.forEach(x=>counts.set(x,(counts.get(x)||0)+1));const countK=1+random(counts.size);
 check('top-k-frequent-elements',{a,k:countK},[...counts].sort((x,y)=>y[1]-x[1]||x[0]-y[0]).slice(0,countK).map(x=>x[0]));
 const target=random(15)-7;
 check('find-k-closest-elements',{a:b,k,target},sorted(b.slice().sort((x,y)=>Math.abs(x-target)-Math.abs(y-target)||x-y).slice(0,k)));
 const stones=a.map(x=>Math.abs(x)+1),pile=stones.slice();while(pile.length>1){pile.sort((a,b)=>b-a);const x=pile.shift()-pile.shift();if(x)pile.push(x);}check('last-stone-weight',{a:stones},pile[0]||0);
 const s=Array.from({length:random(9)},()=>'ABC'[random(3)]).join(''),cooldown=random(4),freq=[...new Set(s)].map(c=>s.split(c).length-1),max=Math.max(0,...freq);
 check('task-scheduler',{s,k:cooldown},Math.max(s.length,(max-1)*(cooldown+1)+freq.filter(x=>x===max).length));
 const reorganized=run('reorganize-string',{s:s.toLowerCase()});if(max>Math.ceil(s.length/2))assert.equal(reorganized,'');else{assert.equal([...reorganized].sort().join(''),[...s.toLowerCase()].sort().join(''));assert.ok([...reorganized].every((x,i)=>!i||x!==reorganized[i-1]));}
 check('check-if-an-array-is-sorted0701',{a},a.every((x,i)=>!i||a[i-1]<=x));
 const text=s.toLowerCase();check('palindrome-string0817',{s:text},text===[...text].reverse().join(''));check('remove-character-recursive',{s:text,t:'a'},text.replaceAll('a',''));
 const n=random(100000);check('sum-of-digits1742',{n},[...String(n)].reduce((sum,x)=>sum+Number(x),0));
}
for(let trial=0;trial<60;trial++){
 const projects=Array.from({length:random(6)},()=>[random(7),random(6)]),capital=random(4),k=random(5);
 function invest(money,left,used){let best=money;if(!left)return best;for(let i=0;i<projects.length;i++)if(!(used&(1<<i))&&projects[i][0]<=money)best=Math.max(best,invest(money+projects[i][1],left-1,used|(1<<i)));return best;}
 check('ipo',{projects,capital,k},invest(capital,k,0));
 const courses=Array.from({length:random(7)},()=>[1+random(8),1+random(15)]);let best=0;
 for(let mask=0;mask<2**courses.length;mask++){let time=0;const chosen=courses.filter((_,i)=>mask&(1<<i)).sort((a,b)=>a[1]-b[1]);if(chosen.every(x=>(time+=x[0])<=x[1]))best=Math.max(best,chosen.length);}
 check('course-schedule-iii',{courses},best);
 const stations=Array.from({length:random(6)},(_,i)=>[2+i*3,1+random(10)]),fuel=random(8),target=5+random(25);let stops=Infinity;
 for(let mask=0;mask<2**stations.length;mask++){let reach=fuel,count=0,valid=true;for(let i=0;i<stations.length;i++)if(mask&(1<<i)){if(stations[i][0]>reach){valid=false;break;}reach+=stations[i][1];count++;}if(valid&&reach>=target)stops=Math.min(stops,count);}
 check('minimum-number-of-refueling-stops',{stations,fuel,target},stops===Infinity?-1:stops);
 const words=Array.from({length:1+random(10)},()=>['a','bb','cc'][random(3)]),counts=new Map();words.forEach(w=>counts.set(w,(counts.get(w)||0)+1));const wordK=1+random(counts.size);
 check('top-k-frequent-words',{words,k:wordK},[...counts].sort((a,b)=>b[1]-a[1]||a[0].localeCompare(b[0])).slice(0,wordK).map(x=>x[0]));
 const matrix=Array.from({length:1+random(4)},()=>sorted(Array.from({length:1+random(4)},()=>random(10)-5))),flat=sorted(matrix.flat()),rank=1+random(flat.length);
 check('merge-k-sorted-arrays',{matrix,k:flat.length},flat);check('kth-smallest-matrix-heap',{matrix,k:rank},flat[rank-1]);
}
for(let n=0,a=0,b=1;n<=10;n++){check('fibonacci-recursive',{n},a);[a,b]=[b,a+b];}
for(let n=0;n<=4;n++){const answers=run('generate-parentheses',{n}),expected=[];for(let mask=0;mask<2**(2*n);mask++){let s='',balance=0,ok=true;for(let i=0;i<2*n;i++){const open=!!(mask&(1<<i));s+=open?'(':')';balance+=open?1:-1;if(balance<0)ok=false;}if(ok&&balance===0)expected.push(s);}assert.deepEqual(answers.slice().sort(),expected.sort());}
for(const a of [[],[1],[1,2,3],[1,2,3,4]]){const answers=run('permutations',{a});let factorial=1;for(let i=1;i<=a.length;i++)factorial*=i;assert.equal(answers.length,factorial);assert.equal(new Set(answers.map(JSON.stringify)).size,factorial);answers.forEach(x=>assert.deepEqual(sorted(x),a));}
check('letter-combinations-of-a-phone-number',{s:''},[]);check('letter-combinations-of-a-phone-number',{s:'23'},['ad','ae','af','bd','be','bf','cd','ce','cf']);
check('combination-sum',{a:[2,3,6,7],target:7},[[2,2,3],[7]]);check('combination-sum',{a:[2],target:1},[]);
check('palindrome-partitioning',{s:'aab'},[['a','a','b'],['aa','b']]);check('palindrome-partitioning',{s:''},[[]]);
check('top-k-frequent-words',{words:['b','a','b','a','c'],k:2},['a','b']);
check('k-closest-points-to-origin',{points:[[1,3],[-2,2],[2,-2]],k:2},[[-2,2],[2,-2]]);
check('the-k-weakest-rows-in-a-matrix',{matrix:[[1,0],[0,0],[1,0]],k:3},[1,0,2]);
check('merge-k-sorted-arrays',{matrix:[[1,5],[2,3]],k:4},[1,2,3,5]);
check('kth-smallest-matrix-heap',{matrix:[[1,2],[2,4]],k:3},2);
check('minimum-number-of-refueling-stops',{stations:[],fuel:1,target:2},-1);
check('minimum-number-of-refueling-stops',{stations:[[10,60],[20,30],[30,30],[60,40]],fuel:10,target:100},2);
check('ipo',{projects:[[0,1],[1,2],[1,3]],k:2,capital:0},4);check('ipo',{projects:[],k:2,capital:5},5);
check('course-schedule-iii',{courses:[[100,200],[200,1300],[1000,1250],[2000,3200]]},3);check('course-schedule-iii',{courses:[]},0);
for(const [id,input] of [['fibonacci-recursive',{n:11}],['top-k-frequent-elements',{a:[1,1],k:2}],['letter-combinations-of-a-phone-number',{s:'10'}],['palindrome-partitioning',{s:'aaaaaaaa'}]])assert.throws(()=>makeTrace(lessons.find(l=>l.id===id),input));
if(process.env.CODE_MODE_CASES)fs.writeFileSync('language-cases.json',JSON.stringify(JSON.parse(fs.readFileSync('language-cases.json','utf8')).concat(cases)));
console.log(`PASS: ${checks} heap and recursion reference cases, heap invariants and validation.`);
