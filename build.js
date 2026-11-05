// Optional developer command: node build.js. The generated HTML needs no runtime.
const fs = require('node:fs');
const path = require('node:path');
const assert = require('node:assert/strict');
const {createHash} = require('node:crypto');
const read = name => fs.readFileSync(path.join(__dirname,name),'utf8');
// Version local assets so refreshing the server never mixes old helpers with new UI.
const {lessons,groups}=require('./algorithms');
const index=read('index.html').replace(/\d+ lessons · \d+ patterns/,`${lessons.length} lessons · ${groups.length} patterns`).replace(/(src|href)="([^"?]+\.(?:js|css))(?:\?[^\"]*)?"/g,(_,attr,file)=>`${attr}="${file}?v=${createHash('sha256').update(read(file)).digest('hex').slice(0,10)}"`);
fs.writeFileSync(path.join(__dirname,'index.html'),index);
const html=index
  .replace(/<link rel="stylesheet" href="([^"?]+)\?[^\"]*">/g,(_,file)=>'<style>'+read(file)+'</style>')
  .replace(/<script src="([^"?]+)\?[^\"]*"><\/script>/g,(_,file)=>'<script>'+read(file)+'</script>');
const scripts = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)];
assert.equal(scripts.length,4);
scripts.forEach(match=>new Function(match[1]));
assert.ok(!/<script[^>]+src=|<link[^>]+href=|@import|fetch\(/.test(html));
for (const match of read('app.js').matchAll(/\$\('([^']+)'\)/g)) {
  assert.ok(html.includes('id="'+match[1]+'"'), 'Missing DOM target: '+match[1]);
}
fs.writeFileSync(path.join(__dirname,'DSAVisualiz.html'),html);
console.log('Built DSAVisualiz.html — one portable offline file.');

require('./build-dbms.js');
