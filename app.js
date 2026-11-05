(function () {
  'use strict';
  const {lessons,groups,makeTrace} = PointerLab;
  const $ = id => document.getElementById(id);
  const escape = value => String(value).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
  const format = value => value === Infinity ? '∞' : value === -Infinity ? '−∞' : value === undefined ? '—' : typeof value === 'string' ? value : JSON.stringify(value);
  let lesson, frames = [], position = 0, timer = null, preparing = false;
  let completionShown=false;
  const {timing,changes,movingPointers,stateValues,variableHistory} = LabPlayback;
  function tintExplanation(before,after) {
    const box=$('narration').closest('.narration');
    const moved=movingPointers(before,after);
    const palette=[['#1e3a35','#73dfbf'],['#3d3027','#ffc078'],['#302b44','#c5acff']];
    const colors=[...new Set(moved.map(pointer=>pointer.color))].map(index=>palette[index]);
    const backgrounds=colors.map(color=>color[0]);
    box.dataset.movingPointers=moved.map(pointer=>pointer.name).join(', ');
    box.style.setProperty('--motion-fill',backgrounds.length>1?`linear-gradient(120deg, ${backgrounds.join(', ')})`:backgrounds[0] || '#1c2c39');
    box.style.setProperty('--motion-border',colors[0]?.[1] || '#455570');
    box.style.setProperty('--motion-end',colors.at(-1)?.[1] || '#455570');
  }
  let historyEntries=[], historyPage=0, historyPosition=-1;
  function variableHue(name) {
    if(/^(left|slow|read|current|mid)$/.test(name))return 151;
    if(/^(right|fast|write|high|previous)$/.test(name))return 28;
    if(/^(anchor|low|start|i|j)$/.test(name))return 269;
    return [205,269,338,38,169][[...name].reduce((sum,c)=>sum+c.charCodeAt(0),0)%5];
  }
  function explanationText(note,frame) {
    const names=new Set(Object.keys(stateValues(frame)));
    return note.split(/(\b[A-Za-z_]\w*\b)/g).map(token=>names.has(token)?`<mark class="inline-variable" style="--variable-hue:${variableHue(token)}">${escape(token)}</mark>`:escape(token)).join('');
  }
  function renderHistory() {
    if(!lesson)return;
    const grouped=new Map();
    for(const entry of historyEntries){
      if(entry.step>position+1)continue;
      if(!grouped.has(entry.step))grouped.set(entry.step,[]);
      grouped.get(entry.step).push(entry);
    }
    const steps=[...grouped.entries()].reverse();
    const capacity=5;
    const pages=Math.max(1,Math.ceil(steps.length/capacity));
    historyPage=Math.min(historyPage,pages-1);
    const start=historyPage*capacity;
    $('variable-history').innerHTML=steps.slice(start,start+capacity).reverse().map(([step,entries])=>{
      const rows=entries.map(entry=>{
        const before=entry.before===undefined?'initial':format(entry.before),after=entry.after===undefined?'removed':format(entry.after);
        return `<div class="history-change" style="--variable-hue:${variableHue(entry.name)}"><span class="variable-badge" title="${escape(entry.name)}">${escape(entry.name)}</span><div class="history-values"><span title="${escape(before)}">${escape(before)}</span><b aria-label="changed to">↓</b><strong title="${escape(after)}">${escape(after)}</strong></div></div>`;
      }).join('');
      return `<section class="history-step ${step===position+1?'history-step-current':''}" aria-label="Changes at step ${step}"><h3>Step ${step}${step===position+1?'<span>Current</span>':''}</h3><div class="history-step-changes" style="grid-template-columns:repeat(${entries.length},minmax(0,1fr))">${rows}</div></section>`;
    }).join('') || '<p class="no-change">No variable changes yet.</p>';
    $('history-range').textContent=steps.length?`${start+1}–${Math.min(start+capacity,steps.length)} of ${steps.length} steps`:'No entries';
    $('history-newer').disabled=historyPage===0;
    $('history-older').disabled=historyPage>=pages-1;
  }
  const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)');
  let codeMode = 'pseudocode';
  function view() { return LabCodeModes[lesson.id][codeMode]; }
  function mappedLines(sourceLine) {
    const current=view(), lines=current.code.split('\n');
    let key=sourceLine;
    while(key>1 && !current.map[String(key)]) key--;
    const mapped=current.map[String(key)] || [1];
    const useful=mapped.filter(n=>!/^\s*(\}|else\b|ELSE\b|import\b|class\b)/.test(lines[n-1]));
    return useful.length?useful:[mapped[0]];
  }
  function renderCode() {
    const visualOnly=codeMode==='visualization';
    document.body.classList.toggle('visualization-only',visualOnly);
    $('code-panel').hidden=visualOnly;
    $('focus-toggle').hidden=visualOnly;
    if (visualOnly) {
      $('code').replaceChildren();
      return;
    }
    $('code').innerHTML = view().code.split('\n').map((line,i)=>`<span class="code-line" data-line="${i+1}"><span class="line-number">${i+1}</span><span class="code-text">${codeMode==='pseudocode'?escape(line):syntax(line)}</span></span>`).join('');
    $('code-heading').textContent=codeMode==='pseudocode'?'PSEUDOCODE':'SOLUTION CODE';
    $('code-caption').textContent=codeMode==='pseudocode'?'Language-independent steps · count(table, key) reads the stored value, or 0 when absent.':`${codeMode==='java'?'Java':'Python'} solution · highlighted statements match the dry run.${lesson.linked?' Lists use node IDs and next links.':''}`;
    $('copy').textContent='Copy';
    $('copy').setAttribute('aria-label','Copy '+codeMode);
    updateFocusLabel();
  }
  function updateFocusLabel() {
    $('focus-toggle').textContent=document.body.classList.contains('focus-mode')?(codeMode==='pseudocode'?'Show all steps':'Show full code'):'Focus on this step';
  }
  function renderLog() {
    $('log').innerHTML = frames.map((f,i)=>`<li><button data-step="${i}"><strong>${codeMode==='visualization'?'Step '+(i+1):'Line '+mappedLines(f.line)[0]}</strong> · ${escape(f.note)}</button></li>`).join('');
  }

  function catalog() {
    const query = $('search').value.toLowerCase().trim();
    $('catalog').innerHTML = groups.map((group,g) => {
      const items = lessons.filter(l=>l.group===g && (l.title+' '+l.description+' '+group).toLowerCase().includes(query));
      return items.length ? `<div class="nav-group"><h3>${escape(group)}<span>${items.length}</span></h3>${items.map(l=>`<button data-id="${l.id}" class="${lesson?.id===l.id?'selected':''}" ${lesson?.id===l.id?'aria-current="page"':''}><span class="num">${String(lessons.indexOf(l)+1).padStart(2,'0')}</span><span>${escape(l.title)}</span></button>`).join('')}</div>` : '';
    }).join('') || '<p style="font-size:12px">No matching lessons.</p>';
  }
  function stop() {
    clearTimeout(timer); timer = null;
    if (preparing) { preparing=false; explain(frames[position]); }
    $('play').innerHTML = '▶ <span>Play</span>';
    $('play').setAttribute('aria-label','Play dry run');
  }
  function showCompletion() {
    stop();
    completionShown=true;
    $('completion-lesson').textContent=lesson.title;
    $('completion-answer').textContent=JSON.stringify(frames.at(-1).result,null,2)??'No return value';
    const byStep=new Map();
    for(const entry of historyEntries){if(!byStep.has(entry.step))byStep.set(entry.step,[]);byStep.get(entry.step).push(entry);}
    $('completion-steps').innerHTML=frames.map((frame,index)=>{
      const updates=(byStep.get(index+1)||[]).map(e=>`${e.name}: ${e.before===undefined?'initial':format(e.before)} → ${e.after===undefined?'removed':format(e.after)}`);
      const checks=frame.decisions?.filter(d=>!index||JSON.stringify(d)!==JSON.stringify(frames[index-1].decisions?.find(old=>old.condition===d.condition)))||[];
      return `<li><button class="completion-step" data-review-step="${index}">Step ${index+1}${frame.done?' · Answer':''}</button><p>${escape(frame.note)}</p>${checks.length?`<ul>${checks.map(d=>`<li>${escape(d.condition)} — ${d.result===null?'Skipped':d.result?'True':'False'}: ${escape(d.detail)}</li>`).join('')}</ul>`:''}${updates.length?`<div class="completion-updates">${updates.map(t=>`<span>${escape(t)}</span>`).join('')}</div>`:''}</li>`;
    }).join('');
    if(!$('completion-dialog').open)$('completion-dialog').showModal();
    $('completion-dialog').querySelector('.completion-body').scrollTop=0;
  }
  function play() {
    if (timer) { stop(); return; }
    if (position === frames.length-1) { position=0; render(); }
    $('play').innerHTML = 'Ⅱ <span>Pause</span>';
    $('play').setAttribute('aria-label','Pause dry run');
    const prepare = () => {
      if (position >= frames.length-1) { stop(); return; }
      const upcoming = frames[position+1];
      preparing=true;
      $('step-label').textContent = 'UP NEXT · READ WHY, THEN WATCH';
      $('narration').innerHTML = explanationText(upcoming.note,upcoming);
      tintExplanation(frames[position],upcoming);
      $('movement').textContent = changes(frames[position],upcoming).join('   ·   ') || (codeMode==='visualization'?'Watch the highlighted values and the state below.':'Next: '+mappedLines(upcoming.line).map(n=>view().code.split('\n')[n-1].trim()).join(' '));
      $('phase').textContent = 'READ FIRST';
      highlight(upcoming.line,true);
      fitVisualization();
      timer = setTimeout(() => {
        preparing=false; position++; render(true);
        if (position >= frames.length-1) { stop(); return; }
        timer=setTimeout(prepare,timing($('speed').value).hold);
      },timing($('speed').value).preview);
    };
    timer = setTimeout(prepare,timing($('speed').value).hold);
  }
  function explain(frame,restoreHighlight = true) {
    if (!frame) return;
    $('narration').innerHTML = explanationText(frame.note,frame);
    const decisions=frame.decisions||[];
    $('decision-panel').hidden=!frames.some(f=>f.decisions?.length);
    $('decision-panel').innerHTML=decisions.length?`<h3>Decision conditions <small>Values when checked</small></h3><div class="decision-checks">${decisions.map(d=>`<div class="decision-check" data-result="${d.result===null?'skipped':d.result?'true':'false'}"><div><strong>${d.depth?'↳ '.repeat(d.depth):''}${escape(d.condition)}</strong><b>${d.result===null?'Skipped':d.result?'True':'False'}</b></div><p>${escape(d.detail)}</p></div>`).join('')}</div>`:'<h3>Decision conditions</h3><p class="decision-pending">Checks and their results will appear here as you step through the solution.</p>';
    tintExplanation(position?frames[position-1]:undefined,frame);
    $('step-label').textContent = frame.done?'THE RESULT':position===0?'START HERE':`STEP ${position+1} · WHY THIS HAPPENS`;
    $('movement').textContent = position ? changes(frames[position-1],frame).join('   ·   ') : 'Press Play to watch, or Next to learn one step at a time.';
    $('phase').textContent = frame.done?'COMPLETE':position===0?'READY':'STEP '+(position+1);
    if (restoreHighlight) highlight(frame.line,false);
    fitVisualization();
  }
  function syntax(text) {
    // Tokenize first so escaping cannot change the code being copied.
    return text.split(/('(?:[^'\\]|\\.)*'|"(?:[^"\\]|\\.)*"|\b(?:def|class|public|static|import|return|if|else|while|for|in|do|break|continue|new|int|long|boolean|char|String|Object|True|False|None|true|false|null|and|or|not)\b|\b\d+\b)/g).map(token=>{
      const safe = escape(token);
      if (/^['"]|^\d+$|^(True|False|None|true|false|null)$/.test(token)) return `<span class="literal">${safe}</span>`;
      if (/^(def|class|public|static|import|return|if|else|while|for|in|do|break|continue|new|int|long|boolean|char|String|Object|and|or|not)$/.test(token)) return `<span class="keyword">${safe}</span>`;
      return safe;
    }).join('');
  }
  function select(id) {
    stop();
    lesson = lessons.find(l=>l.id===id) || lessons[0];
    document.body.classList.toggle('extended-patterns',lesson.group>=6);
    $('group-label').textContent = groups[lesson.group];
    $('lesson-number').textContent = `PATTERN ${String(lesson.group+1).padStart(2,'0')} / LESSON ${String(lessons.indexOf(lesson)+1).padStart(2,'0')}`;
    $('title').textContent = lesson.title;
    $('description').textContent = lesson.description;
    $('time').textContent = `${lesson.complexity[0]} time`;
    $('space').textContent = `${lesson.complexity[1]} space`;
    $('source').href = lesson.source;
    $('source').title = 'Optional external reference; requires internet';
    $('idea-title').textContent = lesson.idea;
    $('idea').textContent = lesson.invariant;
    $('invariant').textContent = lesson.linked ? 'Node labels are stable IDs. The arrows and next labels show actual links; their screen positions do not change when links are rewired. −1 means null. Link storage and the teaching result are excluded from auxiliary-space labels.' : 'Indices in the visualization start at 0. Colored labels show pointer positions; an underline marks the active window. Playback storage is separate from algorithm space complexity.';
    $('constraints').textContent = lesson.matrixInput||lesson.multiplicationInput ? 'Nonempty matrices up to 8 × 8 · follow the sorting and rank constraints above' : lesson.intervalInput ? 'Up to 12 intervals per list · integer bounds within ±10000 · follow the endpoint rules above' : lesson.words ? 'Equal-length, nonempty words · ASCII text up to 80 characters' : lesson.strings || lesson.text ? 'Printable ASCII text · up to 80 characters' : lesson.id==='happy-number' ? 'Positive integer n · up to 1,000,000,000' : 'Array: up to 40 small integers · follow the problem constraints above';
    document.querySelector('.diagram-block .block-title').textContent=lesson.group===9?(lesson.answerSearch?'Test the candidate answer':lesson.matrixInput?'Follow the matrix search':'Narrow the search range'):lesson.intervalInput?'Follow the intervals':lesson.group===3?'Extend or restart':lesson.group===4?'Follow the prefix state':lesson.group===6?'Follow the links':lesson.group===7?'Follow the stack':lesson.group===8?'Follow the frequency table':'Follow the pointers';
    document.querySelector('.diagram-block .block-hint').textContent=lesson.group===9?(lesson.answerSearch?'The number line tracks candidate values, not array indices. Each test decides which half remains.':lesson.matrixInput?'Purple marks the current cell. Faded cells are excluded; green cells pass a value threshold.':lesson.medianInput?'Each cut separates the left partition (green) from the right partition.': 'Faded values are outside the candidate range. Compare mid before moving a boundary.'):lesson.intervalInput?'Bars share a time axis. Green marks the current interval; purple shows the candidate.':lesson.group===4?'Prefix boundaries, lookup entries, and working state update with each step.':lesson.group===6?'Boxes stay at fixed node IDs. Watch next links change and follow the current head.':lesson.group===7?'Stack grows left to right. Push and pop happen at the labeled top.':lesson.group===8?'Counts show available copies or occurrences. Watch the highlighted entry change.':'Numbers below the cells are indices. Colored labels show pointer positions.';
    renderCode();
    if(lesson.group===10){document.querySelector('.diagram-block .block-title').textContent='Follow the priority heaps';document.querySelector('.diagram-block .block-hint').textContent='The root has the smallest priority. Negative priorities select the largest value first.';}
    if(lesson.group===11){document.querySelector('.diagram-block .block-title').textContent='Follow the recursive choices';document.querySelector('.diagram-block .block-hint').textContent='Watch the current prefix, call depth, and saved answers as calls enter and return.';}
    $('input').value = JSON.stringify(lesson.example,null,2);
    location.hash = lesson.id;
    catalog(); run();
    document.body.classList.remove('lessons-open');
    $('lessons-toggle').setAttribute('aria-expanded','false');
  }
  function run() {
    stop();
    try {
      const input = JSON.parse($('input').value);
      const nextFrames = makeTrace(lesson,input);
      frames = nextFrames; position = 0;
      completionShown=false;
      if($('completion-dialog').open)$('completion-dialog').close();
      historyEntries=variableHistory(frames);historyPage=0;historyPosition=-1;
      $('error').textContent = '';
      $('input-summary').textContent = Object.entries(input).map(([k,v])=>`${k} = ${JSON.stringify(v)}`).join('   ·   ');
      $('timeline').max = frames.length-1;
      renderLog();
      render();
    } catch (error) { $('error').textContent = error instanceof SyntaxError ? 'Invalid JSON. Use quoted field names and commas between values.' : error.message; }
  }
  function row(values, pointers, title, previous, frame, linked = false, offset = 0) {
    if (!Array.isArray(values)) return '';
    const entries = Object.entries(pointers || {});
    const cut=frame.cuts?(title==='Input'?frame.cuts[0]:title==='Second sorted array'?frame.cuts[1]:null):null;
    const outside = entries.filter(([,p])=>p<0 || p>=values.length);
    return `<div class="array-row ${linked?'linked':''} ${entries.length&&entries.every(([name])=>name==='left'||name==='right')?'lr-pointers':''}" style="--pointer-lanes:${entries.length}"><div class="row-title">${escape(title)}${values.length?'':' · empty'}${cut!==null?' · cut after '+cut+' values':''}</div><div class="cells">${values.map((value,index)=>{
      const labels = entries.map(([name,p],color)=>({name,p,color:Math.min(color,2)})).filter(x=>x.p===index);
      const changed = previous && JSON.stringify(previous[index])!==JSON.stringify(value);
      const inWindow = title==='Input' && frame.window && index>=frame.window[0] && index<=frame.window[1];
      return `<div class="cell-wrap ${cut===index?'cut-before':''}"><div data-cell="${escape(title)}:${index}" class="cell ${labels.length?'pointer-'+labels[0].color:''} ${changed?'changed':''} ${inWindow?'in-window':''} ${lesson.group===9&&frame.window&&!inWindow?'search-discarded':''} ${cut!==null&&index<cut?'partition-left':''}">${escape(value === null?'·':Array.isArray(value)?value.join(','):value)}</div><div class="index">${index + offset}</div><div class="pointer-track">${labels.map(x=>`<div data-pointer="${escape(title)}:${escape(x.name)}" style="grid-row:${entries.findIndex(([name])=>name===x.name)+1}" class="pointer-label color-${x.color}" title="${escape(x.name)} pointer" aria-label="${escape(x.name)} pointer">↑ ${escape(x.name==='left'?'L':x.name==='right'?'R':x.name)}</div>`).join('')}</div>${linked?`<div class="edge-label">→ ${frame.next[index]===-1?'null':frame.next[index]}</div>`:''}</div>`;
    }).join('')}</div>${linked && values.length?links(frame.next):''}${outside.length?`<div class="connections">${outside.map(([name,p])=>`${escape(name)} → ${p===-1?'−1 (null / before start)':p+' (past end)'}`).join(' · ')}</div>`:''}</div>`;
  }
  function links(next) {
    const stride=codeMode==='visualization' && lesson.group<6?86:72, center=(stride-14)/2;
    const width=next.length*stride, height=lesson.group>=6?50:95;
    return `<svg class="link-diagram" style="--nodes:${next.length}" viewBox="0 0 ${width} ${height}" role="img" aria-label="Curved arrows show the next link from each node ID"><defs><marker id="arrowhead" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="5" markerHeight="5" orient="auto-start-reverse"><path d="M 0 0 L 10 5 L 0 10 z" fill="#91a78c"/></marker></defs>${next.map((to,from)=>{
      if(to<0)return '';
      const x1=from*stride+center,x2=to*stride+center,bend=from===to?40:Math.min(height-8,23+Math.abs(to-from)*10);
      const path=from===to?`M ${x1} 10 C ${x1+38} 45 ${x1-38} 45 ${x1} 10`:`M ${x1} 10 C ${x1} ${bend} ${x2} ${bend} ${x2} 10`;
      return `<path d="${path}" fill="none" stroke="${to<=from?'#b197bc':'#a5b79e'}" stroke-width="1.5" marker-end="url(#arrowhead)"/><text x="${x1}" y="8" font-size="8" fill="#7b8e80" text-anchor="middle">${from}</text>`;
    }).join('')}</svg>`;
  }
  function intervalScene(frame) {
    const sets=[['Intervals',frame.intervals],...(frame.other?[['Other list',frame.other]]:[]),...(frame.out?.length?[['Output',frame.out]]:[])];
    const all=sets.flatMap(([,items])=>items);
    if(Number.isFinite(frame.vars?.start)&&Number.isFinite(frame.vars?.end))all.push([frame.vars.start,frame.vars.end]);
    const minimum=Math.min(0,...all.map(x=>x[0])), maximum=Math.max(1,...all.map(x=>x[1]));
    const x=value=>100+(value-minimum)/(maximum-minimum)*450;
    let y=20;
    const shapes=[];
    for(const [title,items] of sets){
      shapes.push(`<text x="0" y="${y}" class="interval-heading">${escape(title)}</text>`);y+=12;
      items.forEach((pair,index)=>{
        const active=title==='Intervals'?Object.values(frame.p||{}).includes(index):title==='Other list'?frame.vars?.right===index:false;
        shapes.push(`<g class="interval-bar ${active?'interval-active':''}"><text x="0" y="${y+14}">${index}${pair.length===3?' · load '+pair[2]:''}</text><rect x="${x(pair[0])}" y="${y}" width="${Math.max(3,x(pair[1])-x(pair[0]))}" height="19" rx="4"/><text x="${x(pair[0])}" y="${y-2}">${pair[0]}</text><text x="${x(pair[1])+4}" y="${y+14}">${pair[1]}</text></g>`);y+=32;
      });
      if(!items.length){shapes.push(`<text x="100" y="${y+12}">No intervals</text>`);y+=24;}
      y+=10;
    }
    if(frame.vars && Number.isFinite(frame.vars.start)&&Number.isFinite(frame.vars.end)&&frame.vars.start<=frame.vars.end){
      shapes.push(`<text x="0" y="${y+13}">Candidate</text><rect class="interval-candidate" x="${x(frame.vars.start)}" y="${y}" width="${Math.max(3,x(frame.vars.end)-x(frame.vars.start))}" height="19" rx="4"/><text x="${x(frame.vars.start)}" y="${y-2}">${frame.vars.start}</text><text x="${x(frame.vars.end)+4}" y="${y+13}">${frame.vars.end}</text>`);y+=35;
    }
    return `<svg class="interval-scene" width="600" height="${y+8}" viewBox="0 0 600 ${y+8}" role="img" aria-label="Interval timeline: horizontal bars show starts, ends and overlaps">${shapes.join('')}</svg>`;
  }
  function matrixScene(frame) {
    const matrix=frame.matrix || Array.from({length:frame.multiplication.m},(_,r)=>Array.from({length:frame.multiplication.n},(_,c)=>(r+1)*(c+1)));
    const columns=Math.max(1,...matrix.map(row=>row.length));
    const tiles=matrix.flatMap((row,r)=>row.map((value,c)=>{
      const active=frame.cell?.[0]===r&&frame.cell?.[1]===c;
      const excluded=frame.flatRange?(r*columns+c<frame.flatRange[0]||r*columns+c>frame.flatRange[1]):frame.matrixRange?(r<frame.matrixRange[0]||c>frame.matrixRange[1]):false;
      const qualifies=frame.threshold!==undefined&&value<=frame.threshold;
      return `<div class="matrix-cell ${active?'matrix-current':''} ${excluded?'search-discarded':''} ${qualifies?'matrix-qualifies':''}" style="grid-row:${r+1};grid-column:${c+1}" aria-label="Row ${r}, column ${c}: ${value}${active?', current':''}${excluded?', excluded':''}"><b>${value}</b><small>${r},${c}</small></div>`;
    })).join('');
    return `<div class="matrix-visual"><div class="row-title">${frame.multiplication?'Multiplication table':'Matrix'} · labels are row, column${frame.threshold!==undefined?' · green ≤ '+frame.threshold:''}</div><div class="matrix-grid" style="grid-template-columns:repeat(${columns},48px)">${tiles}</div></div>`;
  }
  function heapScene(items,title,prior=[]) {
    const levels=Math.ceil(Math.log2(items.length+1)), width=Math.max(280,Math.pow(2,Math.max(0,levels-1))*86), height=Math.max(42,levels*52);
    const pos=i=>{const level=Math.floor(Math.log2(i+1)),first=2**level-1;return {x:(i-first+.5)*width/(2**level),y:22+level*52};};
    const edges=items.map((_,i)=>{if(!i)return '';const a=pos(Math.floor((i-1)/2)),b=pos(i);return `<path d="M${a.x} ${a.y+12} L${b.x} ${b.y-12}" stroke="#b1c4b7" stroke-width="2"/>`;}).join('');
    const nodes=items.map((entry,i)=>{const p=pos(i),changed=JSON.stringify(entry)!==JSON.stringify(prior[i]);return `<g><rect x="${p.x-39}" y="${p.y-14}" width="78" height="28" rx="7" fill="${changed?'#faeadc':i?'#f0f4ed':'#dfede5'}" stroke="${changed?'#cf8b51':i?'#c9d5c4':'#438567'}"/><text x="${p.x}" y="${p.y+4}" text-anchor="middle" fill="#284e46" font-size="12" font-family="monospace">${escape(entry.join(', '))}</text></g>`;}).join('');
    return `<div class="heap-visual"><div class="row-title">${escape(title)}</div>${items.length?`<svg width="${width}" height="${height}" viewBox="0 0 ${width} ${height}" role="img" aria-label="Heap with ${items.length} entries; root ${escape(items[0].join(', '))}">${edges}${nodes}</svg>`:'<p>Empty heap</p>'}</div>`;
  }
  function searchScene(frame) {
    const {low,high,mid,feasible,label}=frame.search;
    const initial=frames.find(f=>f.search)?.search || frame.search;
    const minimum=initial.low,maximum=initial.high,span=Math.max(1,maximum-minimum);
    const x=value=>12+(value-minimum)/span*456;
    return `<div class="answer-range"><div class="range-heading"><strong>${escape(label)}</strong><span class="${feasible===true?'test-pass':feasible===false?'test-fail':''}">${feasible===true?'✓ Feasible':feasible===false?'× Not feasible':low===high?'One candidate remains':'Candidate range'}</span></div><svg width="480" height="28" viewBox="0 0 480 28" role="img" aria-label="Candidate values from ${low} to ${high}${mid===undefined?'':', testing '+mid}"><path d="M12 14 H468" stroke="#dfe5dc" stroke-width="8" stroke-linecap="round"/><path d="M${x(low)} 14 H${x(high)}" stroke="#70a385" stroke-width="8" stroke-linecap="round"/>${mid===undefined?'':`<circle cx="${x(mid)}" cy="14" r="7" fill="#9c7ebe" stroke="#fff" stroke-width="2"/>`}</svg><div class="range-values"><span>low <b>${low}</b></span>${mid===undefined?'':`<span>mid <b>${mid}</b></span>`}<span>high <b>${high}</b></span></div></div>`;
  }
  function render(animate = false) {
    const frame = frames[position], previous = position>0 ? frames[position-1] : null;
    if (!frame) return;
    const oldPointers = new Map(), oldCells = new Map();
    if (animate && !reducedMotion.matches) {
      $('stage').querySelectorAll('[data-pointer]').forEach(el=>oldPointers.set(el.dataset.pointer,el.getBoundingClientRect()));
      $('stage').querySelectorAll('[data-cell]').forEach(el=>oldCells.set(el.dataset.cell,getComputedStyle(el).backgroundColor));
    }
    let html = frame.matrix||frame.multiplication?matrixScene(frame):frame.intervals ? intervalScene(frame) : row(frame.a || [],frame.p,lesson.id==='happy-number'?'Current numbers':'Input',previous?.a,frame,!!frame.next);
    if (frame.next) {
      const seen = new Set(), route = [];
      let n = frame.head ?? (frame.a.length ? 0 : -1);
      while(n!==-1 && !seen.has(n) && route.length<=frame.a.length) { seen.add(n); route.push(n); n=frame.next[n]; }
      html += `<div class="connections">From head (node IDs): ${route.join(' → ')}${route.length?' → ':''}${n===-1?'null':'↻ '+n} <br>Values on this route: ${route.map(id=>escape(frame.a[id])).join(' → ') || 'empty'}</div>`;
    }
    if (frame.out && !frame.intervals && !(frame.next && lesson.group>=6)) html += row(lesson.group>=10?frame.out.slice(-5):frame.out,frame.q,frame.outTitle || (lesson.group>=10&&frame.out.length>5?`Output · latest 5 of ${frame.out.length}`:lesson.id==='backspace-string-compare'?'String t':'Output so far'),lesson.group>=10?previous?.out?.slice(-5):previous?.out,frame,false,lesson.group>=10?Math.max(0,frame.out.length-5):0);
    if (frame.aux) html += row(frame.aux,{},frame.auxTitle || 'Working state',previous?.aux,frame);
    if(frame.stack){
      const stackValues=frame.stack.map((value,i)=>frame.stackKind==='indices'?`${value}: ${frame.a[value]}`:frame.stackCounts?`${value} ×${frame.stackCounts[i]}`:value);
      const oldStack=previous?.stack?.map((value,i)=>previous.stackKind==='indices'?`${value}: ${previous.a[value]}`:previous.stackCounts?`${value} ×${previous.stackCounts[i]}`:value);
      html+=`<div class="stack-block">${row(stackValues,stackValues.length?{top:stackValues.length-1}:{},frame.stackKind==='indices'?'Stack · node/index: value · bottom → top':'Stack · bottom → top',oldStack,frame)}</div>`;
    }
    const lookupFrame=frames.slice(0,position+1).reverse().find(f=>f.freq);
    const lookup=lookupFrame?.freq;
    $('lookup-panel').hidden=!frames.some(f=>f.freq);
    $('lookup-panel').innerHTML=lookup?`<h3>Lookup table</h3><p>${lesson.id==='longest-substring-without-repeating-characters'?'character → last index':lesson.id==='contiguous-array'?'balance → earliest index':'key → count'}</p><div class="lookup-entries">${Object.entries(lookup).map(([key,value])=>`<div class="lookup-entry ${frame.freq&&previous?.freq?.[key]!==value?'table-changed':''}"><b>${escape(key===' '?'space':key)}</b><span>→</span><strong>${escape(value)}</strong></div>`).join('')||'<span>Empty</span>'}</div>`:'<h3>Lookup table</h3><p>Empty · entries appear as the algorithm runs.</p>';
    if(frame.heap)html+=heapScene(frame.heap,frame.heapTitle||'Priority heap · smallest record at root',previous?.heap);
    if(frame.heap2)html+=heapScene(frame.heap2,'Second heap · smallest record at root',previous?.heap2);
    if(frame.search)html+=searchScene(frame);
    $('stage').innerHTML = `<div class="stage-scene ${lesson.group===4||lesson.group===8?'prefix-scene':''} ${lesson.group===7&&!frame.next?'stack-scene':''} ${lesson.group>=6&&frame.next?'reversal-scene':''} ${lesson.group===9?'search-scene':''} ${lesson.group>=10?'compact-scene':''}">${html}</div>`;
    fitVisualization();
    const duration=timing($('speed').value).motion;
    $('stage').querySelectorAll('[data-pointer]').forEach(el=>{
      const old=oldPointers.get(el.dataset.pointer);
      if (!old) return;
      const now=el.getBoundingClientRect();
      const scene=el.closest('.stage-scene');
      const scale=scene?scene.getBoundingClientRect().width/Math.max(scene.offsetWidth,1):1;
      if (old.left!==now.left || old.top!==now.top) el.animate([
        {transform:`translate(${(old.left-now.left)/scale}px, ${(old.top-now.top)/scale}px)`},
        {transform:'translate(0, 0)'}
      ],{duration,easing:'cubic-bezier(.22,1,.36,1)'});
    });
    $('stage').querySelectorAll('[data-cell]').forEach(el=>{
      const old=oldCells.get(el.dataset.cell);
      if(old)el.animate([{backgroundColor:old},{backgroundColor:getComputedStyle(el).backgroundColor}],{duration,easing:'ease-in-out'});
    });
    const vars=stateValues(frame), oldVars=stateValues(previous);
    if(frame.freq)for(const key of Object.keys(vars))if(key.startsWith('table['))delete vars[key];
    $('variables').innerHTML = Object.entries(vars).map(([name,value])=>{
      const changed=Object.hasOwn(oldVars,name) && format(oldVars[name])!==format(value);
      return `<div class="variable ${changed?'state-changed':''}" style="--variable-hue:${variableHue(name)}"><span class="variable-name variable-badge">${escape(name)}</span><strong>${escape(format(value))}</strong><small>${changed?'was '+escape(format(oldVars[name])):Object.hasOwn(oldVars,name)?'unchanged':'initial value'}</small></div>`;
    }).join('') || '<div class="variable">No variables yet</div>';
    const differences=[];
    if(previous){
      for(const [name,value] of Object.entries(vars)){
        if(Object.hasOwn(oldVars,name) && format(oldVars[name])!==format(value)) differences.push({name,from:format(oldVars[name]),to:format(value)});
      }
      for(const key of ['a','out','next']){
        (frame[key] || []).forEach((value,i)=>{
          if(previous[key] && i<previous[key].length && format(previous[key][i])!==format(value)) differences.push({name:`${key==='a'?'value':key==='out'?'output':'link'} at ${i}`,from:format(previous[key][i]),to:format(value)});
        });
      }
    }
    $('change-list').innerHTML=differences.length?differences.map(d=>`<div class="change-item"><span>${escape(d.name)}</span><div><del>${escape(d.from)}</del><b aria-label="changed to"> → </b><strong>${escape(d.to)}</strong></div></div>`).join(''):`<p class="no-change">${position===0?'Starting state. Press Next to follow the first decision.':'No values changed. This step checks a condition or explains the current state.'}</p>`;
    $('changes-title').textContent=position===0?'Starting point':`What changed · step ${position+1}`;
    explain(frame,false);
    $('result').classList.toggle('visible',!!frame.done);
    $('result').innerHTML = frame.done?`<span>${codeMode==='visualization'?'Result → ':'Return → '}${escape(JSON.stringify(frame.result))}</span><button id="review-answer">Review answer & steps</button>`:'';
    $('timeline').value = position;
    $('progress').textContent = `Step ${position+1} of ${frames.length}`;
    $('previous').disabled = position===0;
    $('next').disabled = position===frames.length-1;
    highlight(frame.line,animate);
    $('log').querySelector('.current')?.classList.remove('current');
    $('log').children[position]?.classList.add('current');
    if(historyPosition!==position){historyPage=0;historyPosition=position;}
    fitVisualization();
    if(animate&&!reducedMotion.matches){
      document.querySelectorAll('.variable.state-changed,.lookup-entry.table-changed').forEach(el=>el.animate([{opacity:.55,transform:'translateY(3px)'},{opacity:1,transform:'translateY(0)'}],{duration:320,easing:'cubic-bezier(.22,1,.36,1)'}));
    }
    if(!frame.done)completionShown=false;
    if(frame.done&&!completionShown)showCompletion();
  }
  function fitVisualization() {
    const laptop=window.innerWidth>=900;
    const scene=$('stage').querySelector('.stage-scene');
    if(scene){
      scene.style.transform='';
      if(laptop){
        const stage=$('stage');
        const scale=Math.min(1.15,(stage.clientWidth-16)/Math.max(scene.scrollWidth,1),(stage.clientHeight-16)/Math.max(scene.scrollHeight,1));
        scene.style.transform=`translate(-50%, -50%) scale(${Math.max(.05,scale)})`;
      }
    }
    for(const id of ['change-list','variables']){
      const content=$(id),parent=content.parentElement;
      content.style.transform='';
      content.style.gridTemplateColumns='';
      if(id==='variables'){content.style.height='';content.style.gridTemplateRows='';}
      if(laptop){
        const css=getComputedStyle(parent),title=parent.querySelector('h2');
        const available=parent.clientHeight-parseFloat(css.paddingTop)-parseFloat(css.paddingBottom)-title.offsetHeight-14;
        if(id==='variables'){
          const visualOnly=document.body.classList.contains('visualization-only');
          const rows=Math.max(1,Math.floor(available/76));
          const columns=Math.max(1,Math.ceil(content.children.length/rows));
          content.style.gridTemplateColumns=`repeat(${columns},minmax(0,1fr))`;
          if(visualOnly){
            content.style.height=`${Math.max(0,available)}px`;
            content.style.gridTemplateRows=`repeat(${Math.max(1,Math.ceil(content.children.length/columns))},minmax(0,1fr))`;
          }
          continue;
        }
        const scale=Math.min(1,Math.max(0,available)/Math.max(content.scrollHeight,1));
        content.style.transform=`scale(${Math.max(.05,scale)})`;
      }
    }
    const narration=$('narration').parentElement, panel=narration.parentElement;
    narration.style.transform='';
    renderHistory();
  }
  window.addEventListener('resize',fitVisualization);
  let historyHoverTimer, historyAnimation, historyClosing=false;
  const historyPanel=$('history-toggle').closest('.history-block');
  function setHistoryExpanded(expanded){
    clearTimeout(historyHoverTimer);
    const isOpen=document.body.classList.contains('history-expanded');
    if(expanded&&isOpen&&!historyClosing)return;
    if(!expanded&&!isOpen)return;
    const currentStyle=getComputedStyle(historyPanel);
    const startOpacity=currentStyle.opacity, startTransform=currentStyle.transform;
    if(historyAnimation){historyAnimation.cancel();historyAnimation=null;}
    historyClosing=!expanded;
    $('history-toggle').setAttribute('aria-expanded',String(expanded));
    $('history-toggle').textContent=expanded?'Hide recent changes ▴':'Show last 5 changes ▾';
    if(expanded){
      document.body.classList.add('history-expanded');
      fitVisualization();
      if(!reducedMotion.matches)historyAnimation=historyPanel.animate([{opacity:isOpen?startOpacity:0,transform:isOpen?startTransform:'translateY(14px)'},{opacity:1,transform:'translateY(0)'}],{duration:280,easing:'cubic-bezier(.22,1,.36,1)'});
    }else{
      const finish=()=>{document.body.classList.remove('history-expanded');historyClosing=false;historyAnimation=null;fitVisualization();};
      if(reducedMotion.matches){finish();return;}
      historyAnimation=historyPanel.animate([{opacity:startOpacity,transform:startTransform},{opacity:0,transform:'translateY(10px)'}],{duration:200,easing:'cubic-bezier(.4,0,1,1)'});
      historyAnimation.onfinish=finish;
    }
  }
  historyPanel.addEventListener('pointerenter',event=>{if(event.pointerType!=='touch')setHistoryExpanded(true);});
  historyPanel.addEventListener('pointerleave',event=>{if(event.pointerType!=='touch')historyHoverTimer=setTimeout(()=>setHistoryExpanded(false),220);});
  historyPanel.addEventListener('focusin',()=>setHistoryExpanded(true));
  historyPanel.addEventListener('focusout',event=>{if(!historyPanel.contains(event.relatedTarget)&&!historyPanel.matches(':hover'))setHistoryExpanded(false);});
  $('history-toggle').addEventListener('click',()=>setHistoryExpanded(!document.body.classList.contains('history-expanded')));
  $('history-newer').addEventListener('click',()=>{stop();historyPage=Math.max(0,historyPage-1);renderHistory();});
  $('history-older').addEventListener('click',()=>{stop();historyPage++;renderHistory();});
  function highlight(line,animate) {
    if (codeMode==='visualization') return;
    $('code').querySelectorAll('.code-line.active').forEach(el=>el.classList.remove('active'));
    const mapped=mappedLines(line);
    mapped.forEach(n=>$('code').querySelector(`[data-line="${n}"]`)?.classList.add('active'));
    const active = $('code').querySelector(`[data-line="${mapped[0]}"]`);
    if (active) {
      active.classList.add('active');
      const box = $('code-scroll'), ar = active.getBoundingClientRect(), br = box.getBoundingClientRect();
      if (ar.top<br.top+15 || ar.bottom>br.bottom-15) box.scrollTo({top:box.scrollTop+ar.top-br.top-box.clientHeight*.35,behavior:animate && !reducedMotion.matches?'smooth':'instant'});
    }
  }
  function step(delta) { stop(); position=Math.max(0,Math.min(frames.length-1,position+delta)); render(true); }
  $('lessons-toggle').addEventListener('click',()=>{
    const open=document.body.classList.toggle('lessons-open');
    $('lessons-toggle').setAttribute('aria-expanded',String(open));
    if(open){stop();($('lesson-browser').classList.contains('tools-open')?$('menu-tools'):$('search')).focus();}
  });
  $('lessons-close').addEventListener('click',()=>{document.body.classList.remove('lessons-open');$('lessons-toggle').setAttribute('aria-expanded','false');$('lessons-toggle').focus();});
  function menuSection(tools) {
    $('lesson-browser').classList.toggle('tools-open',tools);
    $('menu-tools-panel').hidden=!tools;
    $('menu-problems').setAttribute('aria-selected',String(!tools));
    $('menu-tools').setAttribute('aria-selected',String(tools));
  }
  $('menu-problems').addEventListener('click',()=>menuSection(false));
  $('menu-tools').addEventListener('click',()=>menuSection(true));
  $('focus-toggle').addEventListener('click',()=>{
    const focus=document.body.classList.toggle('focus-mode');
    $('focus-toggle').setAttribute('aria-pressed',String(focus));
    updateFocusLabel();
    fitVisualization();
  });
  $('code-mode').addEventListener('change',()=>{
    stop(); codeMode=$('code-mode').value;
    renderCode();renderLog();render();
  });
  $('search').addEventListener('input',catalog);
  $('catalog').addEventListener('click',event=>{ const button=event.target.closest('[data-id]'); if(button)select(button.dataset.id); });
  $('play').addEventListener('click',play);
  $('next').addEventListener('click',()=>step(1));
  $('previous').addEventListener('click',()=>step(-1));
  $('restart').addEventListener('click',()=>{stop();position=0;render();});
  $('timeline').addEventListener('input',()=>{stop();position=Number($('timeline').value);render();});
  $('speed').addEventListener('change',()=>{if(timer){stop();play();}});
  $('run').addEventListener('click',run);
  $('example').addEventListener('click',()=>{$('input').value=JSON.stringify(lesson.example,null,2);run();});
  $('log').addEventListener('click',event=>{const button=event.target.closest('[data-step]');if(button){stop();position=Number(button.dataset.step);render();}});
  $('copy').addEventListener('click',async()=>{
    if (codeMode==='visualization') return;
    try {
      if (navigator.clipboard && window.isSecureContext) await navigator.clipboard.writeText(view().code);
      else {
        const area=document.createElement('textarea');area.value=view().code;area.style.position='fixed';area.style.opacity='0';document.body.append(area);area.select();
        const copied=document.execCommand('copy');area.remove();if(!copied)throw Error('Copy failed');
      }
      $('copy').textContent='Copied';
    } catch { $('copy').textContent='Select code to copy'; }
    setTimeout(()=>$('copy').textContent='Copy',2000);
  });
  $('completion-close').addEventListener('click',()=>$('completion-dialog').close());
  $('completion-done').addEventListener('click',()=>$('completion-dialog').close());
  $('completion-replay').addEventListener('click',()=>{$('completion-dialog').close();stop();position=0;render();});
  $('completion-steps').addEventListener('click',event=>{const button=event.target.closest('[data-review-step]');if(button){$('completion-dialog').close();stop();position=Number(button.dataset.reviewStep);render();}});
  $('result').addEventListener('click',event=>{if(event.target.closest('#review-answer'))showCompletion();});
  document.addEventListener('keydown',event=>{
    if($('completion-dialog').open)return;
    if (/INPUT|TEXTAREA|SELECT|BUTTON/.test(event.target.tagName) || event.ctrlKey || event.metaKey || event.altKey) return;
    if (event.code==='Space') {event.preventDefault();play();}
    if (event.key==='ArrowRight') {event.preventDefault();step(1);}
    if (event.key==='ArrowLeft') {event.preventDefault();step(-1);}
    if (event.key.toLowerCase()==='r') {stop();position=0;render();}
    if (event.key==='/') {event.preventDefault();menuSection(false);document.body.classList.add('lessons-open');$('lessons-toggle').setAttribute('aria-expanded','true');stop();$('search').focus();}
  });
  document.addEventListener('keydown',event=>{if(event.key==='Escape' && document.body.classList.contains('lessons-open'))$('lessons-close').click();});
  window.addEventListener('hashchange',()=>{const id=location.hash.slice(1);if(id!==lesson.id)select(id);});
  document.addEventListener('visibilitychange',()=>{if(document.hidden)stop();});
  select(location.hash.slice(1));
})();
