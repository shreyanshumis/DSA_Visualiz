/* Pure playback helpers shared by the offline UI and its tests. */
(function(root) {
  function timing(speed) {
    const interval = 1800 / Number(speed);
    return {interval, preview:interval * .4, hold:interval * .6, motion:Math.min(1800,interval * .45)};
  }
  function changes(before, after) {
    const changes = [];
    for (const key of ['p','q']) {
      for (const [name,to] of Object.entries(after[key] || {})) {
        const from = before?.[key]?.[name];
        if (from !== undefined && from !== to) changes.push(`${name}: ${from} → ${to}`);
      }
    }
    return changes;
  }
  function movingPointers(before, after) {
    const moved=[];
    for(const row of ['p','q'])Object.entries(after?.[row] || {}).forEach(([name,to],index)=>{
      const from=before?.[row]?.[name];
      if(from!==undefined && from!==to)moved.push({name,color:Math.min(index,2)});
    });
    return moved;
  }
  function stateValues(frame) {
    if(!frame)return {};
    const values={...(frame.vars || {})};
    for(const name of ['low','high','mid','feasible'])if(frame.search?.[name]!==undefined && !(name in values))values[name]=frame.search[name];
    for(const key of ['p','q'])for(const [name,value] of Object.entries(frame[key] || {}))if(!(name in values))values[name]=value;
    if(frame.freq)for(const [key,value] of Object.entries(frame.freq))values[`table[${key}]`]=value;
    return values;
  }
  function variableHistory(frames) {
    const known=new Map(), entries=[];
    frames.forEach((frame,index)=>{
      const values=stateValues(frame);
      if(frame.freq){
        for(const [name,before] of known)if(name.startsWith('table[') && !Object.hasOwn(values,name)){
          entries.push({step:index+1,name,before,after:undefined});known.delete(name);
        }
      }
      for(const [name,after] of Object.entries(values)){
        const exists=known.has(name),before=known.get(name);
        if(!exists || !Object.is(before,after))entries.push({step:index+1,name,before,after});
        known.set(name,after);
      }
    });
    return entries;
  }
  const api = {timing,changes,movingPointers,stateValues,variableHistory};
  root.LabPlayback = api;
  if (typeof module !== 'undefined') module.exports = api;
})(typeof window !== 'undefined' ? window : globalThis);
