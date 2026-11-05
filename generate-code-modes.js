// Developer-only: regenerate the bundled teaching views after algorithm edits.
const fs = require('node:fs');
const path = require('node:path');
const {execFileSync} = require('node:child_process');
const {lessons} = require('./algorithms');
fs.writeFileSync(path.join(__dirname,'lesson-code.json'),JSON.stringify(lessons.map(({id,code,example,helperTypes})=>({id,code,example,helperTypes}))));
execFileSync('python',['generate_code_modes.py'],{cwd:__dirname,stdio:'inherit'});
