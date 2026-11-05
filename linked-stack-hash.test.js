const assert=require('node:assert/strict'),fs=require('node:fs');
const {lessons,makeTrace}=require('./algorithms');
let seed=987134,checks=0;
const cases=[];
const rnd=n=>{seed=(Math.imul(seed,1664525)+1013904223)>>>0;return seed%n;};
const text=()=>Array.from({length:rnd(9)},()=> 'abc'[rnd(3)]).join('');
const freq=s=>[...s].reduce((m,c)=>(m[c]=(m[c]||0)+1,m),{});
function check(id,input,expected){
 const lesson=lessons.find(l=>l.id===id),original=structuredClone(input),frames=makeTrace(lesson,input);
 assert.deepEqual(input,original);
 assert.deepEqual(frames.at(-1).result,expected,id+' '+JSON.stringify(input));
 assert.deepEqual(new Function('return ('+lesson.code+')')()(structuredClone(input)),expected);
 if(lesson.linked){
  for(const f of frames)if(f.next){assert.deepEqual(f.a,input.a,'Node values must not change');assert.ok(f.next.every(n=>n===-1||n>=0&&n<input.a.length));}
  const final=frames.at(-1);
  if(final.next){let node=final.head??(input.a.length?0:-1);const seen=new Set(),route=[];
   while(node!==-1){assert.ok(!seen.has(node),'Final list cannot cycle');seen.add(node);route.push(input.a[node]);node=final.next[node];}
   assert.deepEqual(route,expected,'Final head and links must encode the returned list');
  }
 }
 cases.push({id,input,expected});checks++;
}
function reduceRuns(s,k){let prev;const re=new RegExp('(.)\\1{'+(k-1)+'}','g');do{prev=s;s=s.replace(re,'');}while(prev!==s);return s;}
function balanced(s){let old;do{old=s;s=s.replace(/\(\)|\[\]|\{\}/g,'');}while(old!==s);return s==='';}
for(let trial=0;trial<100;trial++){
 const a=Array.from({length:rnd(10)},()=>rnd(12)-4),s=text(),k=1+rnd(5);
 check('reverse-linked-list',{a},[...a].reverse());
 const pairs=[];for(let i=0;i<a.length;i+=2)pairs.push(...a.slice(i,i+2).reverse());
 check('swap-nodes-in-pairs',{a},pairs);
 if(a.length){
  const left=1+rnd(a.length),right=left+rnd(a.length-left+1),group=1+rnd(a.length);
  check('reverse-linked-list-ii',{a,left,right},[...a.slice(0,left-1),...a.slice(left-1,right).reverse(),...a.slice(right)]);
  const groups=[];for(let i=0;i<a.length;i+=group){const part=a.slice(i,i+group);groups.push(...(part.length===group?part.reverse():part));}
  check('reverse-nodes-in-k-group',{a,k:group},groups);
  const evens=[];for(let i=0,size=1;i<a.length;i+=size,size++){const part=a.slice(i,i+size);evens.push(...(part.length%2===0?part.reverse():part));}
  check('reverse-nodes-in-even-length-groups',{a},evens);
 }
 const rotate=rnd(100),shift=a.length?rotate%a.length:0;
 check('rotate-list',{a,k:rotate},shift?[...a.slice(-shift),...a.slice(0,-shift)]:a);
 check('remove-all-adjacent-duplicates-in-string',{s},reduceRuns(s,2));
 check('remove-all-adjacent-duplicates-in-string-ii',{s,k},reduceRuns(s,k));
 const brackets=Array.from({length:rnd(12)},()=> '()[]{}'[rnd(6)]).join('');
 check('valid-parentheses',{s:brackets},balanced(brackets));
 check('reverse-string-stack',{s},[...s].reverse().join(''));
 const greater=a.map((x,i)=>{for(let d=1;d<a.length;d++)if(a[(i+d)%a.length]>x)return a[(i+d)%a.length];return -1;});
 check('next-greater-element-ii',{a},greater);
 check('daily-temperatures',{a},a.map((x,i)=>{for(let j=i+1;j<a.length;j++)if(a[j]>x)return j-i;return 0;}));
 check('remove-nodes-from-linked-list',{a},a.filter((x,i)=>!a.slice(i+1).some(y=>y>x)));
 const parts=Array.from({length:rnd(9)},()=>['','a','b','.','..','...'][rnd(6)]),path='/'+parts.join('/');
 const dirs=[];for(const p of parts)if(p==='..')dirs.pop();else if(p!=='.'&&p!=='')dirs.push(p);
 check('simplify-path',{s:path},'/'+dirs.join('/'));
 const digits=Array.from({length:1+rnd(7)},()=>String(rnd(10))).join(''),remove=rnd(digits.length+1);
 let minimum=Infinity;
 for(let mask=0;mask<(1<<digits.length);mask++){
  const keep=[...digits].filter((_,i)=>mask&(1<<i)).join('');
  if(keep.length===digits.length-remove)minimum=Math.min(minimum,Number(keep));
 }
 check('remove-k-digits',{s:digits,k:remove},String(minimum));
 const f=freq(s);
 check('first-unique-character-in-a-string',{s},[...s].findIndex(c=>f[c]===1));
 const balloonText=Array.from({length:rnd(35)},()=> 'balloonxyz'[rnd(10)]).join(''),b=freq(balloonText);
 check('maximum-number-of-balloons',{s:balloonText},Math.min(b.b||0,b.a||0,Math.floor((b.l||0)/2),Math.floor((b.o||0)/2),b.n||0));
 check('longest-palindrome',{s},Object.values(f).reduce((n,x)=>n+x-x%2,0)+(Object.values(f).some(x=>x%2)?1:0));
 const t=text(),supply=freq(t);
 check('ransom-note',{s,t},Object.entries(f).every(([c,n])=>(supply[c]||0)>=n));
}
for(const [id,input,expected] of [
 ['reverse-nodes-in-even-length-groups',{a:[1,2,3,4,5]},[1,3,2,5,4]],
 ['valid-parentheses',{s:'{[()]}()'},true],['valid-parentheses',{s:'([)]'},false],
 ['simplify-path',{s:'/../...//a/../'},'/...'],['remove-k-digits',{s:'10200',k:1},'200'],
 ['remove-all-adjacent-duplicates-in-string-ii',{s:'deeedbbcccbdaa',k:3},'aa'],
 ['maximum-number-of-balloons',{s:'balloonballoon'},2],['longest-palindrome',{s:'Aa'},1]
])check(id,input,expected);
for(const [id,input] of [
 ['reverse-linked-list-ii',{a:[1,2],left:0,right:2}],['reverse-linked-list-ii',{a:[1],left:1,right:2}],
 ['reverse-nodes-in-k-group',{a:[1],k:2}],['rotate-list',{a:[1],k:-1}],
 ['valid-parentheses',{s:'a'}],['simplify-path',{s:'relative/path'}],
 ['remove-k-digits',{s:'12a',k:1}],['remove-k-digits',{s:'12',k:3}],
 ['remove-all-adjacent-duplicates-in-string-ii',{s:'abc',k:0}],['maximum-number-of-balloons',{s:'BALLOON'}]
])assert.throws(()=>makeTrace(lessons.find(l=>l.id===id),input));
if(process.env.CODE_MODE_CASES)fs.writeFileSync('language-cases.json',JSON.stringify(JSON.parse(fs.readFileSync('language-cases.json','utf8')).concat(cases)));
console.log(`PASS: ${checks} linked-list, stack and hash-map reference cases, plus invalid-input checks.`);
