const fs=require('node:fs');
const read=n=>fs.readFileSync(n,'utf8');
const escape=s=>s.replace(/<\/script/gi,'<\\/script');
const core=read('dbms-core.js');
const html=read('dbms.template.html').replace('/*STYLE*/',()=>read('dbms.css')+'\n'+read('navigation.css')).replace('/*ENGINE*/',()=>escape(read('sql-asm.js'))).replace('/*CORE*/',()=>escape(core+'\nwindow.dbmsCoreSource='+JSON.stringify(core)+';')).replace('/*APP*/',()=>escape(read('sql-guide.js')+'\n'+read('dbms-app.js')));
fs.writeFileSync('dbms.html',html);
console.log('Built offline dbms.html');

const pseudo=read('pseudocode.template.html').replace('/*CHEATSHEET*/',()=>read('bit-cheatsheet.html')).replace('/*STYLE*/',()=>read('dbms.css')+'\n'+read('navigation.css')).replace('/*CORE*/',()=>escape(read('pseudocode-core.js'))).replace('/*APP*/',()=>escape(read('pseudocode-app.js')));
fs.writeFileSync('pseudocode.html',pseudo);
console.log('Built offline pseudocode.html');
