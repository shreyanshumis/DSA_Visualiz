/* All lesson code and examples live here. No packages or network requests. */
(function (root) {
  'use strict';
  const lessons = [];
  const groups = ['Two pointers', 'Fast & slow pointers', 'Sliding window', 'Kadane pattern', 'Prefix sum', 'Merge intervals', 'Linked list reversal', 'Stack', 'Hash maps', 'Binary search', 'Heap'];
  // Numeric records compare lexicographically; negate a priority for a max heap.
  class Heap {
    constructor(){this.items=[];}
    get size(){return this.items.length;}
    less(a,b){for(let i=0;i<a.length;i++){if(a[i]!==b[i])return a[i]<b[i];}return false;}
    push(value){const a=this.items;a.push(value);let i=a.length-1;while(i>0){const p=(i-1)>>1;if(!this.less(a[i],a[p]))break;[a[i],a[p]]=[a[p],a[i]];i=p;}}
    peek(){return this.items[0];}
    pop(){const a=this.items,first=a[0],last=a.pop();if(a.length){a[0]=last;let i=0;while(true){let child=2*i+1;if(child>=a.length)break;if(child+1<a.length&&this.less(a[child+1],a[child]))child++;if(!this.less(a[child],a[i]))break;[a[i],a[child]]=[a[child],a[i]];i=child;}}return first;}
  }
  root.Heap=Heap;
  function add(id, title, group, description, idea, invariant, complexity, example, fn, options = {}) {
    let raw = fn.toString();
    if (options.mode) raw = raw.replaceAll('"MODE"', JSON.stringify(options.mode));
    const clean = [], instrumented = [];
    let helperIndent = null;
    for (const line of raw.split(/\r?\n/)) {
      if (line.trim().startsWith('yield {')) {
        instrumented.push(line.replace('yield {', `yield {line: ${clean.length},`));
      } else {
        clean.push(line.replace('function*', 'function').replaceAll('yield* ', ''));
        const indent = line.match(/^ */)[0].length;
        if (/^\s+function\*? /.test(line) && !line.includes('}')) helperIndent = indent;
        const ret = helperIndent === null && line.match(/^(\s*)(if \(.+\) )?return (.*);$/);
        if (ret) {
          instrumented.push(`${ret[1]}${ret[2] || ''}{ yield {returnLine:${clean.length}}; return ${ret[3]}; }`);
        } else instrumented.push(line);
        if (helperIndent !== null && indent === helperIndent && line.trim() === '}') helperIndent = null;
      }
    }
    const indent = Math.min(...clean.slice(1, -1).filter(x => x.trim()).map(x => x.match(/^ */)[0].length));
    const code = clean.map((x, i) => i === 0 ? x : x.slice(Math.min(indent, x.match(/^ */)[0].length))).join('\n');
    const run = new Function('return (' + instrumented.join('\n') + ')')();
    lessons.push({id, title, group, description, idea, invariant, complexity, example, code, run,
      source: options.source || 'https://leetcode.com/problems/' + id + '/', ...options});
  }

  add('two-sum-ii-input-array-is-sorted', 'Pair with target sum', 0,
    'Find two numbers in a sorted array that add up to the target. Return their 1-based indices.',
    'Start far apart. Move closer with purpose.',
    'In a sorted array, moving left forward raises the sum; moving right backward lowers it. Each move rules out a number that cannot form the answer.',
    ['O(n)', 'O(1)'], {a:[1,2,4,6,8,9], target:12}, function* pairSum({a, target}) {
      let left = 0, right = a.length - 1;
      yield {a, p:{left,right}, vars:{target}, note:'Place left at the smallest value and right at the largest.'};
      while (left < right) {
        const sum = a[left] + a[right];
        yield {a, p:{left,right}, vars:{sum,target}, note:`${a[left]} + ${a[right]} = ${sum}. Compare this sum with ${target}.`};
        if (sum === target) {
          yield {a, p:{left,right}, vars:{sum,target}, note:'The sum equals the target. Convert the zero-based positions to 1-based indices.'};
          return [left + 1, right + 1];
        }
        if (sum < target) {
          left++;
          yield {a, p:{left,right}, vars:{sum,target}, note:'The sum was too small. Move left one place right to try a larger value.'};
        } else {
          right--;
          yield {a, p:{left,right}, vars:{sum,target}, note:'The sum was too large. Move right one place left to try a smaller value.'};
        }
      }
      return [];
    }, {sorted:true, min:2});

  add('segregate-0s-and-1s5106', 'Rearrange 0 and 1', 0,
    'Move all zeros before all ones, in place.', 'Build a zero region and a one region.',
    'Everything before left is 0; everything after right is 1. Swap only when both ends are on the wrong side.',
    ['O(n)', 'O(1)'], {a:[1,0,1,0,0,1,0]}, function* segregate({a}) {
      let left = 0, right = a.length - 1;
      yield {a,p:{left,right},note:'The region between the pointers has not been classified yet.'};
      while (left < right) {
        if (a[left] === 0) {
          left++;
          yield {a,p:{left,right},note:'This zero is already on the correct side. Advance left.'};
        } else if (a[right] === 1) {
          right--;
          yield {a,p:{left,right},note:'This one is already on the correct side. Retreat right.'};
        } else {
          [a[left], a[right]] = [a[right], a[left]];
          yield {a,p:{left,right},note:'Swap the misplaced one and zero.'};
          left++; right--;
          yield {a,p:{left,right},note:'Both ends are now correct. Narrow the unclassified region.'};
        }
      }
      return a;
    }, {binary:true,source:'https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1'});

  function* deduplicate({a}) {
    const limit = "MODE" === 'twice' ? 2 : 1;
    let write = 0;
    yield {a,p:{write},vars:{limit},note:'write marks the next position in the compacted prefix.'};
    for (let read = 0; read < a.length; read++) {
      const keep = write < limit || a[read] !== a[write - limit];
      yield {a,p:{read,write},vars:{limit,keep},note:keep?'Keep this value: it does not exceed the allowed number of copies.':'Skip this value: the prefix already contains the allowed copies.'};
      if (keep) {
        a[write] = a[read];
        yield {a,p:{read,write},vars:{limit},note:'Copy the current value into the next position of the valid prefix.'};
        write++;
        yield {a,p:{read,write},vars:{length:write},note:'Advance write. Values after this prefix are no longer part of the answer.'};
      }
    }
    return {length:write, values:a.slice(0,write)};
  }
  for (const twice of [false,true]) add('remove-duplicates-from-sorted-array' + (twice ? '-ii':''),
    'Remove duplicates · array' + (twice ? ' II':''),0,
    `Compact a sorted array in place, keeping ${twice?'at most two copies':'one copy'} of each value. The returned prefix is the answer.`,
    'Read everything. Write only what belongs.', 'The prefix before write is always valid. Compare against the value one allowed group back, not against overwritten input.',
    ['O(n)', 'O(1) auxiliary'],{a:[0,0,0,1,1,2,2,3]},deduplicate,{mode:twice?'twice':'once',sorted:true});

  add('remove-duplicates-from-sorted-list','Remove duplicates · linked list',0,
    'Remove adjacent duplicate nodes from a sorted linked list. Node IDs are array indices; next stores the links.',
    'Bypass a duplicate, then check again.', 'Keep current in place after deleting a duplicate: its new neighbor may also be a duplicate. Removed nodes stay visible so you can see which links changed.',
    ['O(n)','O(1) auxiliary'],{a:[1,1,2,3,3,3,4]},function* deleteDuplicates({a}) {
      const next = a.map((_,i) => i + 1 < a.length ? i + 1 : -1);
      let current = a.length ? 0 : -1;
      yield {a,next,p:{current},note:'Each next entry points to a node ID; −1 means null.'};
      while (current !== -1 && next[current] !== -1) {
        const neighbor = next[current];
        yield {a,next,p:{current,neighbor},note:`Compare node ${current} with its next node ${neighbor}.`};
        if (a[current] === a[neighbor]) {
          next[current] = next[neighbor];
          yield {a,next,p:{current,neighbor},note:'Duplicate found. Redirect current.next past the duplicate node.'};
        } else {
          current = neighbor;
          yield {a,next,p:{current},note:'The values differ. Move current to the next retained node.'};
        }
      }
      const result = [];
      for (let node = a.length ? 0 : -1; node !== -1; node = next[node]) result.push(a[node]);
      return result;
    },{sorted:true,linked:true});

  add('squares-of-a-sorted-array','Squaring a sorted array',0,
    'Square every value and return the squares in sorted order.', 'The biggest square is at one of the ends.',
    'Negative numbers can have large squares. Compare both ends and fill the result backward, placing the largest remaining square first.',
    ['O(n)','O(n) output'],{a:[-7,-3,-1,4,8]},function* sortedSquares({a}) {
      let left = 0, right = a.length - 1;
      const out = Array(a.length).fill(null);
      yield {a,out,p:{left,right},note:'Prepare the output array. The next largest square goes in its rightmost free slot.'};
      for (let write = a.length - 1; write >= 0; write--) {
        const leftSquare = a[left] ** 2, rightSquare = a[right] ** 2;
        yield {a,out,p:{left,right},q:{write},vars:{leftSquare,rightSquare},note:'Compare the squares at both ends.'};
        if (leftSquare > rightSquare) {
          out[write] = leftSquare; left++;
          yield {a,out,p:{left,right},q:{write},note:'The left square is larger. Place it in output and advance left.'};
        } else {
          out[write] = rightSquare; right--;
          yield {a,out,p:{left,right},q:{write},note:'The right square is at least as large. Place it in output and retreat right.'};
        }
      }
      return out;
    },{sorted:true});

  function* triplets({a,target = 0}) {
    const mode = "MODE";
    a.sort((x,y) => x-y);
    yield {a,vars:{target},note:'Sort first so pointer movement has a predictable effect on the sum.'};
    let closest = a[0] + a[1] + a[2], count = 0;
    const result = [];
    for (let anchor = 0; anchor < a.length - 2; anchor++) {
      if (mode === 'zero' && anchor > 0 && a[anchor] === a[anchor-1]) continue;
      let left = anchor + 1, right = a.length - 1;
      yield {a,p:{left,right,anchor},vars:{target,count,closest},note:'Fix one value, then use two pointers on the suffix.'};
      while (left < right) {
        const sum = a[anchor] + a[left] + a[right];
        yield {a,p:{left,right,anchor},vars:{sum,target,count,closest},out:result,note:`Current triplet: ${a[anchor]} + ${a[left]} + ${a[right]} = ${sum}.`};
        if (mode === 'closest' && Math.abs(sum-target) < Math.abs(closest-target)) {
          closest = sum;
          yield {a,p:{left,right,anchor},vars:{sum,target,closest},note:'This sum is closer to the target. Save it as the best so far.'};
        }
        if (mode === 'smaller' && sum < target) {
          count += right - left;
          yield {a,p:{left,right,anchor},vars:{count,added:right-left},note:`All ${right-left} choices from left+1 through right work with this anchor and left. Count them at once.`};
          left++;
          yield {a,p:{left,right,anchor},vars:{count},note:'Try the next left value.'};
        } else if (mode === 'zero' && sum === 0) {
          result.push([a[anchor],a[left],a[right]]);
          yield {a,p:{left,right,anchor},out:result,note:'Save this unique zero-sum triplet.'};
          left++; right--;
          yield {a,p:{left,right,anchor},out:result,note:'Move both pointers inward after finding a match.'};
          while (left < right && a[left] === a[left-1]) {
            left++;
            yield {a,p:{left,right,anchor},out:result,note:'Skip a repeated left value to avoid duplicate triplets.'};
          }
          while (left < right && a[right] === a[right+1]) {
            right--;
            yield {a,p:{left,right,anchor},out:result,note:'Skip a repeated right value.'};
          }
        } else if (sum < target) {
          left++;
          yield {a,p:{left,right,anchor},vars:{closest,count},note:'The sum is too small. Advance left.'};
        } else {
          right--;
          yield {a,p:{left,right,anchor},vars:{closest,count},note:'Move right leftward to reduce the sum.'};
        }
      }
    }
    return mode === 'zero' ? result : mode === 'closest' ? closest : count;
  }
  add('3sum','Triplet sum to zero',0,'Find every unique triplet with sum zero. Input is sorted as part of the solution.',
    'One anchor turns three choices into two.', 'Skip repeated anchors and matched endpoint values to avoid duplicate value triplets.',
    ['O(n²)','O(n) sort + output'],{a:[-1,0,1,2,-1,-4]},triplets,{mode:'zero',min:3});
  add('3sum-closest','Triplet sum closest to target',0,'Return the sum of a triplet closest to the target. Ties keep the first sum found.',
    'Remember the best sum while narrowing the search.', 'A sorted suffix lets you improve a small sum by advancing left and reduce a large sum by retreating right.',
    ['O(n²)','O(n) sort'],{a:[-1,2,1,-4],target:1},triplets,{mode:'closest',min:3});
  add('count-triplets-with-sum-smaller-than-x5549','Triplets with smaller sum',0,'Count index triplets whose sum is strictly less than the target.',
    'A successful right endpoint proves a whole range.', 'When the sum is small enough, every third index between left+1 and right also works. Add right−left; do not enumerate them.',
    ['O(n²)','O(n) sort'],{a:[-1,0,2,3],target:3},triplets,{mode:'smaller',min:3,source:'https://www.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1'});

  add('subarray-product-less-than-k','Subarrays with product below target',0,'Count contiguous subarrays with product strictly less than k. Values must be positive integers.',
    'Grow right. Shrink left until the product fits.', 'After shrinking, every suffix ending at right and beginning inside the window is valid. Add the window length.',
    ['O(n)','O(1)'],{a:[10,5,2,6],k:100},function* productWindow({a,k}) {
      if (k <= 1) return 0;
      let left = 0, product = 1, count = 0;
      yield {a,p:{left},vars:{product,count,k},note:'Start with an empty window and a product of 1.'};
      for (let right = 0; right < a.length; right++) {
        product *= a[right];
        yield {a,p:{left,right},window:[left,right],vars:{product,count,k},note:'Include the new rightmost value in the product.'};
        while (product >= k && left <= right) {
          product /= a[left]; left++;
          yield {a,p:{left,right},window:[left,right],vars:{product,count,k},note:'The product is too large. Divide out the left value and shrink.'};
        }
        count += right - left + 1;
        yield {a,p:{left,right},window:[left,right],vars:{product,count,added:right-left+1},note:'Count all valid suffixes ending at right.'};
      }
      return count;
    },{positive:true});

  add('sort-colors','Dutch national flag',0,'Sort 0s, 1s, and 2s in place with three pointers.', 'Three colors. One unclassified region.',
    'Before low is 0, from low to mid−1 is 1, and after high is 2. After swapping with high, inspect mid again because its new value is unknown.',
    ['O(n)','O(1)'],{a:[2,0,2,1,1,0]},function* sortColors({a}) {
      let low = 0, mid = 0, high = a.length - 1;
      yield {a,p:{mid,high,low},note:'mid scans the unclassified region from low through high.'};
      while (mid <= high) {
        const color = a[mid];
        yield {a,p:{mid,high,low},vars:{color},note:`Inspect ${color} at mid.`};
        if (color === 0) {
          [a[low],a[mid]] = [a[mid],a[low]];
          yield {a,p:{mid,high,low},note:'Move this zero into the zero region.'};
          low++; mid++;
          yield {a,p:{mid,high,low},note:'Both the zero region and the scanned region grow.'};
        } else if (color === 1) {
          mid++;
          yield {a,p:{mid,high,low},note:'A one is already in the middle region. Advance mid.'};
        } else {
          [a[mid],a[high]] = [a[high],a[mid]];
          yield {a,p:{mid,high,low},note:'Swap the two to the high end.'};
          high--;
          yield {a,p:{mid,high,low},note:'Shrink high. Keep mid here to inspect the value that just arrived.'};
        }
      }
      return a;
    },{colors:true});

  add('4sum','Quadruple sum to target',0,'Find every unique group of four values that adds up to the target.', 'Fix two anchors, then solve a pair sum.',
    'Skip equal anchors and equal matched endpoints. Sorted order makes the two moving pointers sufficient for each anchor pair.',
    ['O(n³)','O(n) sort + output'],{a:[1,0,-1,0,-2,2],target:0},function* fourSum({a,target}) {
      a.sort((x,y) => x-y);
      const result = [];
      yield {a,out:result,vars:{target},note:'Sort the input to make equal values adjacent.'};
      for (let i = 0; i < a.length - 3; i++) {
        if (i > 0 && a[i] === a[i-1]) continue;
        for (let j = i + 1; j < a.length - 2; j++) {
          if (j > i+1 && a[j] === a[j-1]) continue;
          let left = j + 1, right = a.length - 1;
          yield {a,p:{left,right,i,j},out:result,vars:{target},note:'Fix i and j. Search the remaining suffix with left and right.'};
          while (left < right) {
            const sum = a[i]+a[j]+a[left]+a[right];
            yield {a,p:{left,right,i,j},out:result,vars:{sum,target},note:'Compare the four-value sum to the target.'};
            if (sum === target) {
              result.push([a[i],a[j],a[left],a[right]]);
              yield {a,p:{left,right,i,j},out:result,vars:{sum},note:'Save this matching quadruplet.'};
              left++; right--;
              yield {a,p:{left,right,i,j},out:result,note:'Move both endpoints inward.'};
              while (left < right && a[left] === a[left-1]) {
                left++;
                yield {a,p:{left,right,i,j},out:result,note:'Skip a repeated left endpoint.'};
              }
              while (left < right && a[right] === a[right+1]) {
                right--;
                yield {a,p:{left,right,i,j},out:result,note:'Skip a repeated right endpoint.'};
              }
            } else if (sum < target) {
              left++;
              yield {a,p:{left,right,i,j},out:result,vars:{sum},note:'Sum too small: move left forward.'};
            } else {
              right--;
              yield {a,p:{left,right,i,j},out:result,vars:{sum},note:'Sum too large: move right backward.'};
            }
          }
        }
      }
      return result;
    });

  add('backspace-string-compare','Comparing strings with backspaces',0,'Compare two strings after applying # as a backspace. Extra backspaces on empty text do nothing.',
    'Read backward to discover what survives.', 'A backspace only affects characters to its left. A skip counter lets you ignore deleted characters without building a new string.',
    ['O(n + m)','O(1)'],{s:'ab##c',t:'ad#c'},function* backspaceCompare({s,t}) {
      let left = s.length - 1, right = t.length - 1;
      yield {a:s.split(''),out:t.split(''),p:{left},q:{right},note:'Start at the end of both strings.'};
      while (left >= 0 || right >= 0) {
        let skip = 0;
        while (left >= 0) {
          if (s[left] === '#') skip++;
          else if (skip > 0) skip--;
          else break;
          left--;
          yield {a:s.split(''),out:t.split(''),p:{left},q:{right},vars:{skip},note:'In s, consume a backspace or a character erased by it.'};
        }
        skip = 0;
        while (right >= 0) {
          if (t[right] === '#') skip++;
          else if (skip > 0) skip--;
          else break;
          right--;
          yield {a:s.split(''),out:t.split(''),p:{left},q:{right},vars:{skip},note:'In t, consume a backspace or a character erased by it.'};
        }
        const x = left >= 0 ? s[left] : null, y = right >= 0 ? t[right] : null;
        yield {a:s.split(''),out:t.split(''),p:{left},q:{right},vars:{x,y},note:'Compare the surviving characters. null means there are no characters left.'};
        if (x !== y) return false;
        left--; right--;
        yield {a:s.split(''),out:t.split(''),p:{left},q:{right},note:'The surviving characters match. Continue backward.'};
      }
      return true;
    },{strings:true});

  add('shortest-unsorted-continuous-subarray','Minimum window sort',0,'Find the length of the shortest window that must be sorted to sort the whole array.',
    'Find disorder, then include everything it disturbs.', 'The minimum inside the disordered window may belong farther left; its maximum may belong farther right. Extend both boundaries before returning the length.',
    ['O(n)','O(1)'],{a:[1,3,2,0,-1,7,10]},function* minimumWindowSort({a}) {
      let left = 0, right = a.length - 1;
      yield {a,p:{left,right},note:'Find the first inversion from each end.'};
      while (left < a.length - 1 && a[left] <= a[left+1]) {
        left++;
        yield {a,p:{left,right},note:'This adjacent pair is ordered. Continue from the left.'};
      }
      if (left >= a.length - 1) return 0;
      while (right > 0 && a[right] >= a[right-1]) {
        right--;
        yield {a,p:{left,right},note:'This adjacent pair is ordered. Continue from the right.'};
      }
      let min = Infinity, max = -Infinity;
      for (let scan = left; scan <= right; scan++) {
        min = Math.min(min,a[scan]); max = Math.max(max,a[scan]);
        yield {a,p:{left,right,scan},window:[left,right],vars:{min,max},note:'Measure the smallest and largest values in the disordered window.'};
      }
      while (left > 0 && a[left-1] > min) {
        left--;
        yield {a,p:{left,right},window:[left,right],vars:{min,max},note:'The window minimum must go before this value. Extend left.'};
      }
      while (right < a.length - 1 && a[right+1] < max) {
        right++;
        yield {a,p:{left,right},window:[left,right],vars:{min,max},note:'The window maximum must go after this value. Extend right.'};
      }
      return right-left+1;
    });

  function* cycleList({a,pos}) {
    const findStart = "MODE" === 'start';
    const next = a.map((_,i) => i+1 < a.length ? i+1 : pos);
    let slow = a.length ? 0 : -1, fast = slow;
    yield {a,next,p:{slow,fast},note:'Start both pointers at the head. next links may loop back to an earlier node.'};
    while (fast !== -1 && next[fast] !== -1) {
      slow = next[slow];
      yield {a,next,p:{slow,fast},note:'Move slow forward by one link.'};
      fast = next[fast];
      yield {a,next,p:{slow,fast},note:'Move fast forward by its first link.'};
      fast = next[fast];
      yield {a,next,p:{slow,fast},note:'Move fast forward by its second link.'};
      if (slow === fast) {
        yield {a,next,p:{slow,fast},note:'The pointers meet: a cycle exists.'};
        if (!findStart) return true;
        slow = 0;
        yield {a,next,p:{slow,fast},note:'Reset slow to the head. Both pointers now move one link per round.'};
        while (slow !== fast) {
          slow = next[slow]; fast = next[fast];
          yield {a,next,p:{slow,fast},note:'Advance both one link. Their next meeting is the cycle entry.'};
        }
        return {index:slow,value:a[slow]};
      }
    }
    return findStart ? null : false;
  }
  for (const start of [false,true]) add('linked-list-cycle'+(start?'-ii':''),start?'Start of linked list cycle':'Linked list cycle',1,
    start?'Return the node where a cycle begins, or null. pos is the last node’s next index (−1 for no cycle).':'Detect whether the list has a cycle. pos is the index linked from the last node (−1 for no cycle).',
    start?'Meet once. Reset. Meet at the entrance.':'Different speeds reveal a loop.',
    start?'After a collision, the distance from head to entry matches the remaining distance from the meeting point to entry modulo the cycle length.':'In a cycle, fast gains one node on slow each round and must eventually catch it. Without a cycle, fast reaches null.',
    ['O(n)','O(1) auxiliary'],{a:[3,2,0,-4,7],pos:1},cycleList,{mode:start?'start':'detect',linked:true,cycle:true});

  add('happy-number','Happy number',1,'Repeatedly replace a positive integer by the sum of the squares of its digits. Does the sequence reach 1?',
    'A number sequence can contain a cycle too.', 'Each number has exactly one successor. Floyd’s two speeds detect a repeated cycle; a collision at 1 means happy.',
    ['O(log n + cycle length)','O(log n) digit string'],{n:19},function* happyNumber({n}) {
      function next(value) { return String(value).split('').reduce((sum,digit) => sum+Number(digit)**2,0); }
      let slow = n, fast = n;
      yield {a:[slow,fast],p:{slow:0,fast:1},vars:{slow,fast},note:'The two boxes show the current slow and fast numbers, not list positions.'};
      do {
        slow = next(slow);
        yield {a:[slow,fast],p:{slow:0,fast:1},vars:{slow,fast},note:'Slow: replace the number by the sum of its squared digits.'};
        fast = next(fast);
        yield {a:[slow,fast],p:{slow:0,fast:1},vars:{slow,fast},note:'Fast takes its first sum-of-squares step.'};
        fast = next(fast);
        yield {a:[slow,fast],p:{slow:0,fast:1},vars:{slow,fast},note:'Fast takes its second step. Check whether the two numbers meet.'};
      } while (slow !== fast);
      return slow === 1;
    });

  add('find-the-duplicate-number','Find the duplicate number',1,'An array of n+1 integers uses values 1…n with one distinct repeated value. Find that value without modifying the array.',
    'Treat each value as a next-node address.', 'The repeated value is the entrance of a cycle in the index → value graph. Detect a meeting, reset to index 0, and move both one step.',
    ['O(n)','O(1)'],{a:[1,3,4,2,2]},function* findDuplicate({a}) {
      let slow = 0, fast = 0;
      yield {a,next:a,p:{slow,fast},note:'Each value is the index of the next node. Start at index 0.'};
      do {
        slow = a[slow];
        yield {a,next:a,p:{slow,fast},note:'Follow one index → value link with slow.'};
        fast = a[fast];
        yield {a,next:a,p:{slow,fast},note:'Follow fast’s first link.'};
        fast = a[fast];
        yield {a,next:a,p:{slow,fast},note:'Follow fast’s second link.'};
      } while (slow !== fast);
      slow = 0;
      yield {a,next:a,p:{slow,fast},note:'Collision found. Reset slow to index 0.'};
      while (slow !== fast) {
        slow = a[slow]; fast = a[fast];
        yield {a,next:a,p:{slow,fast},note:'Follow one link each until they meet at the duplicate value.'};
      }
      return slow;
    },{duplicate:true});

  add('middle-of-the-linked-list','Middle of the linked list',1,'Return the middle node. For even length, choose the second middle node.',
    'Half the speed means halfway through.', 'When fast reaches the end, slow has covered half as many links. The loop condition naturally selects the second middle.',
    ['O(n)','O(1) auxiliary'],{a:[1,2,3,4,5,6]},function* middleNode({a}) {
      const next = a.map((_,i) => i+1 < a.length ? i+1 : -1);
      let slow = a.length ? 0 : -1, fast = slow;
      yield {a,next,p:{slow,fast},note:'Both pointers begin at the head.'};
      while (fast !== -1 && next[fast] !== -1) {
        slow = next[slow];
        yield {a,next,p:{slow,fast},note:'Move slow forward one link.'};
        fast = next[fast];
        yield {a,next,p:{slow,fast},note:'Fast takes the first of its two steps.'};
        fast = next[fast];
        yield {a,next,p:{slow,fast},note:'Fast takes its second step. When it cannot continue, slow is at the middle.'};
      }
      return slow === -1 ? null : {index:slow,value:a[slow]};
    },{linked:true});

  add('palindrome-linked-list','Palindrome linked list',1,'Check whether a linked list reads the same forward and backward. This teaching solution reverses the second half.',
    'Find the middle. Reverse half. Compare.', 'Reverse the second half by rewiring one link at a time. Comparing from head and reversed tail checks mirrored values. This version does not restore the links.',
    ['O(n)','O(1) auxiliary'],{a:[1,2,3,2,1]},function* palindromeList({a}) {
      const next = a.map((_,i) => i+1 < a.length ? i+1 : -1);
      let slow = a.length ? 0 : -1, fast = slow;
      yield {a,next,p:{slow,fast},note:'First find the middle with fast and slow pointers.'};
      while (fast !== -1 && next[fast] !== -1) {
        slow = next[slow]; fast = next[next[fast]];
        yield {a,next,p:{slow,fast},note:'Slow advances one link and fast advances two.'};
      }
      let previous = -1, current = slow;
      yield {a,next,p:{current,previous},note:'Reverse the chain starting at the middle. previous begins at null.'};
      while (current !== -1) {
        const following = next[current];
        yield {a,next,p:{current,previous,following},note:'Save the old next node before changing the link.'};
        next[current] = previous;
        yield {a,next,p:{current,previous,following},note:'Reverse current.next so it points to previous.'};
        previous = current; current = following;
        yield {a,next,p:{current,previous},note:'Advance along the original chain using the saved link.'};
      }
      let left = a.length ? 0 : -1, right = previous;
      yield {a,next,p:{left,right},note:'Compare from the original head and the reversed half’s head.'};
      while (right !== -1) {
        const equal = a[left] === a[right];
        yield {a,next,p:{left,right},vars:{equal},note:'These two nodes are mirrored positions in the original list.'};
        if (!equal) return false;
        left = next[left]; right = next[right];
        yield {a,next,p:{left,right},note:'Both values match. Move inward along both chains.'};
      }
      return true;
    },{linked:true});

  add('reorder-list','Rearrange a linked list',1,'Reorder L0 → L1 → … → Ln into L0 → Ln → L1 → Ln−1 → … by changing links.',
    'Split, reverse, then weave the two halves.', 'Detach the halves before reversing. When merging, save both next links before overwriting either one.',
    ['O(n)','O(1) auxiliary'],{a:[1,2,3,4,5]},function* reorderList({a}) {
      const next = a.map((_,i) => i+1 < a.length ? i+1 : -1);
      if (a.length < 2) return a;
      let slow = 0, fast = 0;
      yield {a,next,p:{slow,fast},note:'Find the last node of the first half.'};
      while (next[fast] !== -1 && next[next[fast]] !== -1) {
        slow = next[slow]; fast = next[next[fast]];
        yield {a,next,p:{slow,fast},note:'Slow advances once; fast advances twice.'};
      }
      let current = next[slow], previous = -1;
      next[slow] = -1;
      yield {a,next,p:{current,previous,slow},note:'Detach the second half so the two lists end independently.'};
      while (current !== -1) {
        const following = next[current];
        next[current] = previous;
        yield {a,next,p:{current,previous,following},note:'Reverse this link in the second half.'};
        previous = current; current = following;
        yield {a,next,p:{current,previous},note:'Advance to the next node in the original second half.'};
      }
      let left = 0, right = previous;
      yield {a,next,p:{left,right},note:'Weave the first half with the reversed second half.'};
      while (right !== -1) {
        const nextLeft = next[left], nextRight = next[right];
        yield {a,next,p:{left,right},vars:{nextLeft,nextRight},note:'Save both next links before weaving.'};
        next[left] = right; next[right] = nextLeft;
        yield {a,next,p:{left,right},note:'Insert the right node immediately after the left node.'};
        left = nextLeft; right = nextRight;
        yield {a,next,p:{left,right},note:'Advance to the next pair of nodes.'};
      }
      const result = [];
      for (let node = 0; node !== -1; node = next[node]) result.push(a[node]);
      return result;
    },{linked:true});

  add('circular-array-loop','Cycle in a circular array',1,'Find a circular jump cycle longer than one node, with every jump in the same direction.',
    'Wrap indices, but keep one direction.', 'Reject direction changes and single-node loops. This straightforward Floyd solution tries each start independently, using O(n²) worst-case time.',
    ['O(n²)','O(1)'],{a:[2,-1,1,2,2]},function* circularArrayLoop({a}) {
      const n = a.length;
      for (let start = 0; start < n; start++) {
        const positive = a[start] > 0;
        function next(i) {
          if (i === -1 || (a[i] > 0) !== positive) return -1;
          const j = ((i + a[i]) % n + n) % n;
          return j === i ? -1 : j;
        }
        let slow = start, fast = start;
        yield {a,p:{slow,fast,start},vars:{direction:positive?'forward':'backward'},note:'Choose a start and fix the required jump direction.'};
        do {
          slow = next(slow);
          yield {a,p:{slow,fast,start},note:'Slow takes one wrapped jump; −1 means an invalid move.'};
          fast = next(fast);
          yield {a,p:{slow,fast,start},note:'Fast takes its first jump.'};
          fast = next(fast);
          yield {a,p:{slow,fast,start},note:'Fast takes its second jump. A self-loop or direction change stops this search.'};
          if (slow === -1 || fast === -1) break;
          if (slow === fast) return true;
        } while (slow !== fast);
      }
      return false;
    },{nonzero:true});

  add('max-sum-subarray-of-size-k5313','Maximum sum subarray of size K',2,'Find the maximum sum of exactly k consecutive values.', 'Reuse the previous window’s work.',
    'Add the incoming value and subtract the outgoing value. The window always has exactly k elements when its sum is compared to best.',
    ['O(n)','O(1)'],{a:[2,1,5,1,3,2],k:3},function* maxWindowSum({a,k}) {
      let left = 0, sum = 0, best = -Infinity;
      yield {a,p:{left},vars:{sum,best,k},note:'Build the first window before comparing sums.'};
      for (let right = 0; right < a.length; right++) {
        sum += a[right];
        yield {a,p:{left,right},window:[left,right],vars:{sum,best,k},note:'Add the new rightmost value.'};
        if (right-left+1 === k) {
          best = Math.max(best,sum);
          yield {a,p:{left,right},window:[left,right],vars:{sum,best,k},note:'This window has exactly k values. Update the best sum.'};
          sum -= a[left]; left++;
          yield {a,p:{left,right},window:[left,right],vars:{sum,best,k},note:'Remove the outgoing left value so the next iteration can slide right.'};
        }
      }
      return best;
    },{fixedK:true,source:'https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1'});

  add('minimum-size-subarray-sum','Smallest subarray with a given sum',2,'Return the minimum length of a contiguous subarray with sum at least target, or 0 if none exists.',
    'Expand until valid. Shrink while still valid.', 'Positive values make shrinking reduce the sum. Each time the sum reaches target, save the length and try removing the leftmost value.',
    ['O(n)','O(1)'],{a:[2,3,1,2,4,3],target:7},function* minSubarrayLength({a,target}) {
      let left = 0, sum = 0, best = Infinity;
      yield {a,p:{left},vars:{sum,best,target},note:'Start with an empty window.'};
      for (let right = 0; right < a.length; right++) {
        sum += a[right];
        yield {a,p:{left,right},window:[left,right],vars:{sum,best,target},note:'Grow the window by adding the right value.'};
        while (sum >= target) {
          best = Math.min(best,right-left+1);
          yield {a,p:{left,right},window:[left,right],vars:{sum,best,target},note:'The sum reaches target. Save this length before trying a shorter window.'};
          sum -= a[left]; left++;
          yield {a,p:{left,right},window:[left,right],vars:{sum,best,target},note:'Remove the leftmost value. Keep shrinking if the sum is still large enough.'};
        }
      }
      return best === Infinity ? 0 : best;
    },{positive:true,positiveTarget:true});

  function* distinctWindow({s,a = s.split(''),k = 2}) {
    const exact = "MODE" === 'exact';
    let left = 0, best = exact ? -1 : 0;
    const freq = new Map();
    yield {a,p:{left},vars:{k,best},note:'Track how many different values are inside the current window.'};
    for (let right = 0; right < a.length; right++) {
      freq.set(a[right],(freq.get(a[right]) || 0)+1);
      yield {a,p:{left,right},window:[left,right],vars:{distinct:freq.size,best,k},freq:Object.fromEntries(freq),note:'Add the incoming value to the frequency table.'};
      while (freq.size > k) {
        freq.set(a[left],freq.get(a[left])-1);
        if (freq.get(a[left]) === 0) freq.delete(a[left]);
        left++;
        yield {a,p:{left,right},window:[left,right],vars:{distinct:freq.size,best,k},freq:Object.fromEntries(freq),note:'Too many distinct values. Remove the left value, deleting its entry if its count reaches zero.'};
      }
      if (!exact || freq.size === k) {
        best = Math.max(best,right-left+1);
        yield {a,p:{left,right},window:[left,right],vars:{distinct:freq.size,best,k},freq:Object.fromEntries(freq),note:exact?'Exactly k distinct characters: update the longest length.':'At most two fruit types: update the longest valid window.'};
      }
    }
    return best;
  }
  add('longest-k-unique-characters-substring0853','Longest substring with K distinct',2,'Find the longest substring with exactly k distinct characters, or −1 if there is none.',
    'Track character counts, not just membership.', 'Only delete a character from the map when its count reaches zero. The linked GFG variant requires exactly k distinct characters.',
    ['O(n)','O(k)'],{s:'aabacbebebe',k:3},distinctWindow,{mode:'exact',text:true,positiveK:true,source:'https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1'});
  add('fruit-into-baskets','Fruits into baskets',2,'Find the longest contiguous run containing at most two fruit types. Each array value is a fruit type.',
    'Two baskets means two distinct values.', 'A third type makes the window invalid. Shrink until a type disappears completely, then measure the window.',
    ['O(n)','O(1)'],{a:[1,2,1,2,3,2,2]},distinctWindow,{mode:'fruit'});

  add('longest-substring-without-repeating-characters','No-repeat substring',2,'Find the length of the longest substring with no repeated characters.', 'Jump past the last occurrence.',
    'left never moves backward. A repeated character only matters when its previous occurrence lies inside the current window.',
    ['O(n)','O(alphabet)'],{s:'abcabcbb'},function* noRepeat({s}) {
      const n = s.length;
      let left = 0, maxLen = 0;
      const lastIndex = new Map();
      yield {a:s.split(''),p:{left},vars:{n,maxLen},note:'Remember each character’s last index. Start with an empty window.'};
      for (let right = 0; right < n; right++) {
        const c = s[right];
        yield {a:s.split(''),p:{left,right},window:[left,right],vars:{n,c,maxLen},decisions:[{condition:'right < n',result:right<n,detail:`${right} < ${n}: process this character`},{condition:'Character seen before?',result:lastIndex.has(c),depth:1,detail:`${JSON.stringify(c)} ${lastIndex.has(c)?'has last index '+lastIndex.get(c):'is not in lastIndex'}`},{condition:'Previous index + 1 > left?',result:lastIndex.has(c)?lastIndex.get(c)+1>left:null,depth:2,detail:lastIndex.has(c)?`${lastIndex.get(c)+1} > ${left}: ${lastIndex.get(c)+1>left?'advance left':'keep left; never move backward'}`:'Skipped: character has not been seen'}],note:'Check the grouped conditions to decide whether left needs to move.'};
        if (lastIndex.has(c)) {
          left = Math.max(left, lastIndex.get(c) + 1);
          yield {a:s.split(''),p:{left,right},window:[left,right],vars:{n,c,maxLen,previous:lastIndex.get(c)},note:'Move left after the previous occurrence. Math.max prevents left from moving backward.'};
        }
        lastIndex.set(c, right);
        yield {a:s.split(''),p:{left,right},window:[left,right],vars:{n,c,maxLen},freq:Object.fromEntries(lastIndex),note:'Record this character’s latest index.'};
        yield {a:s.split(''),p:{left,right},window:[left,right],vars:{n,c,maxLen},freq:Object.fromEntries(lastIndex),decisions:[{condition:'Window length > maxLen?',result:right-left+1>maxLen,detail:`${right-left+1} > ${maxLen}: ${right-left+1>maxLen?'update maxLen':'keep maxLen'}`}],note:'Math.max compares the window length with the best length before choosing which value to keep.'};
        maxLen = Math.max(maxLen, right - left + 1);
        yield {a:s.split(''),p:{left,right},window:[left,right],vars:{n,c,maxLen},freq:Object.fromEntries(lastIndex),note:'The window now has no repeats. Update the longest length seen so far.'};
      }
      yield {a:s.split(''),p:{left},vars:{n,maxLen},decisions:[{condition:'right < n',result:false,detail:`${n} < ${n} is false: exit the loop and return ${maxLen}`}],note:'Every character has been processed. The loop condition is now false.'};
      return maxLen;
    },{text:true});

  add('longest-repeating-character-replacement','Same letters after replacement',2,'Find the longest substring that can be made of one repeated uppercase letter using at most k replacements.',
    'Keep the most common letter. Replace the rest.', 'window length − maximum character frequency is the replacement cost. This version recomputes the maximum over the fixed 26-letter alphabet so the displayed window is always valid after shrinking.',
    ['O(26n) = O(n)','O(26)'],{s:'AABABBA',k:1},function* characterReplacement({s,k}) {
      const a = s.split(''), freq = new Map();
      let left = 0, best = 0;
      yield {a,p:{left},vars:{best,k},note:'The frequency table tells us which letter is cheapest to keep.'};
      for (let right = 0; right < a.length; right++) {
        freq.set(a[right],(freq.get(a[right]) || 0)+1);
        let maxFreq = Math.max(...freq.values());
        yield {a,p:{left,right},window:[left,right],vars:{best,k,maxFreq,cost:right-left+1-maxFreq},freq:Object.fromEntries(freq),note:'Add a letter. Compute how many other letters must be replaced.'};
        while (right-left+1-maxFreq > k) {
          freq.set(a[left],freq.get(a[left])-1); left++;
          maxFreq = Math.max(...freq.values());
          yield {a,p:{left,right},window:[left,right],vars:{best,k,maxFreq,cost:right-left+1-maxFreq},freq:Object.fromEntries(freq),note:'Too many replacements are required. Shrink left and recalculate the maximum frequency.'};
        }
        best = Math.max(best,right-left+1);
        yield {a,p:{left,right},window:[left,right],vars:{best,k,maxFreq},note:'This window can be made uniform within the budget. Update best.'};
      }
      return best;
    },{text:true,uppercase:true,nonnegativeK:true});

  add('max-consecutive-ones-iii','Ones after replacement',2,'Find the longest contiguous run of ones after flipping at most k zeros.', 'Spend one replacement per zero.',
    'A window is valid exactly when its zero count is at most k. Remove values from the left until the replacement budget fits.',
    ['O(n)','O(1)'],{a:[1,1,1,0,0,0,1,1,1,1,0],k:2},function* longestOnes({a,k}) {
      let left = 0, zeros = 0, best = 0;
      yield {a,p:{left},vars:{zeros,best,k},note:'Count only the zeros inside the current window.'};
      for (let right = 0; right < a.length; right++) {
        if (a[right] === 0) zeros++;
        yield {a,p:{left,right},window:[left,right],vars:{zeros,best,k},note:'Include the right value, spending one flip if it is zero.'};
        while (zeros > k) {
          if (a[left] === 0) zeros--;
          left++;
          yield {a,p:{left,right},window:[left,right],vars:{zeros,best,k},note:'The flip budget is exceeded. Shrink from the left, refunding a flip for each removed zero.'};
        }
        best = Math.max(best,right-left+1);
        yield {a,p:{left,right},window:[left,right],vars:{zeros,best,k},note:'Save the longest window within the flip budget.'};
      }
      return best;
    },{binary:true,nonnegativeK:true});

  add('minimum-window-substring','Minimum window substring',2,'Find the shortest substring of s containing every character in t, including repeated copies.',
    'Satisfy every need, then squeeze the window.', 'The need table stores remaining requirements; negative counts mean surplus. missing reaches zero only when every required copy is present.',
    ['O(n + m)','O(alphabet)'],{s:'ADOBECODEBANC',t:'ABC'},function* minimumWindow({s,t}) {
      if (!t.length) return '';
      const a = s.split(''), need = new Map();
      for (const c of t) need.set(c,(need.get(c) || 0)+1);
      let left = 0, missing = t.length, bestStart = 0, bestLength = Infinity;
      yield {a,p:{left},vars:{missing,bestLength},freq:Object.fromEntries(need),note:'Load the required character counts. The table tracks remaining need.'};
      for (let right = 0; right < a.length; right++) {
        const c = a[right];
        if (need.has(c)) {
          if (need.get(c) > 0) missing--;
          need.set(c,need.get(c)-1);
        }
        yield {a,p:{left,right},window:[left,right],vars:{missing,bestLength},freq:Object.fromEntries(need),note:'Include the right character. Reduce missing only if this copy was needed.'};
        while (missing === 0) {
          if (right-left+1 < bestLength) {
            bestStart = left; bestLength = right-left+1;
            yield {a,p:{left,right},window:[left,right],vars:{missing,bestLength,best:s.slice(bestStart,bestStart+bestLength)},note:'All required copies are present. Save this smaller window.'};
          }
          const outgoing = a[left++];
          if (need.has(outgoing)) {
            need.set(outgoing,need.get(outgoing)+1);
            if (need.get(outgoing) > 0) missing++;
          }
          yield {a,p:{left,right},window:[left,right],vars:{missing,bestLength},freq:Object.fromEntries(need),note:'Remove the left character. Stop shrinking if a required copy is now missing.'};
        }
      }
      return bestLength === Infinity ? '' : s.slice(bestStart,bestStart+bestLength);
    },{strings:true});

  function* anagramWindow({s,t}) {
    const mode = "MODE";
    if (!t.length) return mode === 'permutation' ? true : [];
    const a = s.split(''), need = new Map(), result = [];
    for (const c of t) need.set(c,(need.get(c) || 0)+1);
    let left = 0, missing = t.length;
    yield {a,p:{left},vars:{missing,size:t.length},freq:Object.fromEntries(need),note:'A permutation must have exactly the same length and character counts as t.'};
    for (let right = 0; right < a.length; right++) {
      const c = a[right];
      if (need.has(c)) {
        if (need.get(c) > 0) missing--;
        need.set(c,need.get(c)-1);
      }
      yield {a,p:{left,right},window:[left,right],vars:{missing},freq:Object.fromEntries(need),out:result,note:'Include the new character and update the remaining requirements.'};
      if (right-left+1 > t.length) {
        const outgoing = a[left++];
        if (need.has(outgoing)) {
          need.set(outgoing,need.get(outgoing)+1);
          if (need.get(outgoing) > 0) missing++;
        }
        yield {a,p:{left,right},window:[left,right],vars:{missing},freq:Object.fromEntries(need),out:result,note:'Keep a fixed-length window by removing the outgoing character.'};
      }
      if (right-left+1 === t.length && missing === 0) {
        result.push(left);
        yield {a,p:{left,right},window:[left,right],vars:{missing},out:result,note:'Exact length and no missing characters: this window is an anagram.'};
        if (mode === 'permutation') return true;
      }
    }
    return mode === 'permutation' ? false : result;
  }
  add('permutation-in-string','Permutation in a string',2,'Does s contain a contiguous permutation of t? Here s is the text and t is the pattern.',
    'Order may change. Counts must match.', 'Slide a window the size of the pattern. Zero missing copies in a full window proves a permutation.',
    ['O(n + m)','O(alphabet)'],{s:'eidbaooo',t:'ab'},anagramWindow,{mode:'permutation',strings:true});
  add('find-all-anagrams-in-a-string','String anagrams',2,'Return every zero-based starting index of an anagram of t inside s.',
    'Use the same window; collect every match.', 'A full-length window with every required copy has exactly the pattern’s multiset. Overlapping anagrams are counted.',
    ['O(n + m)','O(alphabet) + output'],{s:'cbaebabacd',t:'abc'},anagramWindow,{mode:'anagrams',strings:true});

  add('substring-with-concatenation-of-all-words','Words concatenation',2,'Find starting indices of substrings made of all words exactly once each, in any order. All words have equal nonzero length.',
    'Slide by a whole word, for each possible alignment.', 'Scan each offset modulo word length. Unknown words reset the window; too many copies of a known word shrink it. Repeated words require repeated copies.',
    ['O(n × word length)','O(words) + output'],{s:'barfoofoobarthefoobarman',words:['bar','foo','the']},function* wordConcatenation({s,words}) {
      const width = words[0].length, need = new Map(), result = [], a = s.split('');
      for (const word of words) need.set(word,(need.get(word) || 0)+1);
      yield {a,vars:{width},freq:Object.fromEntries(need),out:result,note:'Count required copies of each word.'};
      for (let offset = 0; offset < width; offset++) {
        let left = offset, count = 0;
        const seen = new Map();
        yield {a,p:{left},vars:{offset,count},out:result,note:'Start a word-aligned scan at this offset.'};
        for (let right = offset; right+width <= s.length; right += width) {
          const word = s.slice(right,right+width);
          yield {a,p:{left,right},window:[left,right+width-1],vars:{word,count,offset},freq:Object.fromEntries(seen),out:result,note:'Read one whole word starting at right.'};
          if (!need.has(word)) {
            seen.clear(); count = 0; left = right+width;
            yield {a,p:{left,right},vars:{word,count,offset},freq:{},out:result,note:'Unknown word: reset the window after it.'};
            continue;
          }
          seen.set(word,(seen.get(word) || 0)+1); count++;
          yield {a,p:{left,right},window:[left,right+width-1],vars:{word,count},freq:Object.fromEntries(seen),out:result,note:'Add this word to the window counts.'};
          while (seen.get(word) > need.get(word)) {
            const outgoing = s.slice(left,left+width);
            seen.set(outgoing,seen.get(outgoing)-1); left += width; count--;
            yield {a,p:{left,right},window:[left,right+width-1],vars:{word,count},freq:Object.fromEntries(seen),out:result,note:'Too many copies of the incoming word. Remove words from the left until counts fit.'};
          }
          if (count === words.length) {
            result.push(left);
            yield {a,p:{left,right},window:[left,right+width-1],vars:{count},out:result,note:'All words occur exactly as required. Record this starting index.'};
          }
        }
      }
      return result;
    },{words:true});

  // Kadane: keep the best subarray ending at each position.
  function* kadane({a}) {
    const mode = "MODE";
    let ending = a[0], best = a[0], start = 0;
    yield {a,p:{read:0},window:[0,0],vars:{ending,best,start},note:'Start with the first value: the chosen subarray must be nonempty.'};
    for (let read = 1; read < a.length; read++) {
      const extended = ending + a[read];
      yield {a,p:{read},window:[start,read],vars:{ending,extended,best},note:'Compare extending the previous subarray with starting fresh at this value.'};
      if ((mode === 'max' && a[read] > extended) || (mode === 'min' && a[read] < extended)) {
        ending = a[read]; start = read;
        yield {a,p:{read},window:[start,read],vars:{ending,best,start},note:'Starting here is better. Discard the previous prefix.'};
      } else {
        ending = extended;
        yield {a,p:{read},window:[start,read],vars:{ending,best,start},note:'Extend the previous subarray: its contribution improves this candidate.'};
      }
      best = mode === 'max' ? Math.max(best,ending) : Math.min(best,ending);
      yield {a,p:{read},window:[start,read],vars:{ending,best},note:'Update the best answer across all ending positions seen so far.'};
    }
    return best;
  }
  add('maximum-subarray','Maximum subarray sum',3,'Find the largest sum of a nonempty contiguous subarray.','Extend or restart.','ending is the largest sum ending here; best is the largest across every processed position.',['O(n)','O(1)'],{a:[-2,1,-3,4,-1,2,1,-5,4]},kadane,{mode:'max',min:1});
  add('smallest-sum-contiguous-subarray','Minimum subarray sum',3,'Find the smallest sum of a nonempty contiguous subarray.','Keep a helpful negative prefix.','The minimum ending here either starts here or extends the previous minimum.',['O(n)','O(1)'],{a:[3,-4,2,-3,-1,7,-5]},kadane,{mode:'min',min:1,source:'https://www.geeksforgeeks.org/problems/smallest-sum-contiguous-subarray/1'});
  add('maximum-product-subarray','Maximum product subarray',3,'Find the largest product of a nonempty contiguous subarray.','Keep both extremes: a negative can turn the minimum into the maximum.','Both updates use the previous maximum and minimum, before either is overwritten.',['O(n)','O(1)'],{a:[2,3,-2,4,-2]},function* productSubarray({a}) {
    let largest = a[0], smallest = a[0], best = a[0];
    yield {a,p:{read:0},vars:{largest,smallest,best},note:'A one-element subarray is both the largest and smallest ending product.'};
    for (let read = 1; read < a.length; read++) {
      const x = a[read], fromLargest = largest*x, fromSmallest = smallest*x;
      yield {a,p:{read},vars:{x,fromLargest,fromSmallest,best},note:'Consider starting at x, extending the largest, or extending the smallest. A negative reverses their roles.'};
      largest = Math.max(x,Math.max(fromLargest,fromSmallest));
      smallest = Math.min(x,Math.min(fromLargest,fromSmallest));
      best = Math.max(best,largest);
      yield {a,p:{read},vars:{largest,smallest,best},note:'Save both ending products and update best. Zero naturally resets both products.'};
    }
    return best;
  },{min:1,exactProducts:true});
  add('maximum-subarray-sum-with-one-deletion','Maximum subarray sum with one deletion',3,'Choose a nonempty subarray and optionally delete at most one element; at least one value must remain.','Track a state with no deletion and a state with a deletion.','drop either deletes this value from keep, or extends an already-deleted subarray. Updates use the previous keep.',['O(n)','O(1)'],{a:[1,-2,0,3]},function* oneDeletion({a}) {
    let keep = a[0], drop = -1000000000, best = a[0];
    yield {a,p:{read:0},vars:{keep,best},note:'keep starts with one value. The deletion state is unavailable until there is another value; its code sentinel is below every valid sum.'};
    for (let read = 1; read < a.length; read++) {
      drop = Math.max(keep,drop+a[read]);
      yield {a,p:{read},vars:{keep,drop,best},note:'Either delete the current value (previous keep) or retain it after a previous deletion.'};
      keep = Math.max(a[read],keep+a[read]);
      best = Math.max(best,Math.max(keep,drop));
      yield {a,p:{read},vars:{keep,drop,best},note:'Update the no-deletion state by extending or restarting, then compare both states with best.'};
    }
    return best;
  },{min:1});
  add('maximum-absolute-sum-of-any-subarray','Maximum absolute sum of any subarray',3,'Return the largest absolute subarray sum; an empty subarray may contribute zero.','Track the strongest positive and negative sums.','The greatest magnitude is either the largest positive ending sum or the negation of the smallest.',['O(n)','O(1)'],{a:[1,-3,2,3,-4]},function* absoluteSum({a}) {
    let high = 0, low = 0, best = 0;
    yield {a,vars:{high,low,best},note:'Start with the empty subarray, whose sum is zero.'};
    for (let read = 0; read < a.length; read++) {
      high = Math.max(0,high+a[read]);
      low = Math.min(0,low+a[read]);
      best = Math.max(best,Math.max(high,-low));
      yield {a,p:{read},vars:{high,low,best},note:'Extend both extremes, reset an unhelpful sign to zero, and compare their magnitudes.'};
    }
    return best;
  },{min:1});
  add('maximum-sum-circular-subarray','Maximum sum in a circular array',3,'Find the maximum nonempty circular subarray sum, using each element at most once.','A wrapping answer excludes a minimum-sum middle segment.','Compare normal maximum with total minus minimum. If all values are negative, keep the normal maximum to avoid an empty answer.',['O(n)','O(1)'],{a:[5,-3,5]},function* circularSum({a}) {
    let high = a[0], low = a[0], maximum = a[0], minimum = a[0], total = a[0];
    yield {a,p:{read:0},vars:{high,low,maximum,minimum,total},note:'Start maximum and minimum Kadane scans together, while tracking the total.'};
    for (let read = 1; read < a.length; read++) {
      high = Math.max(a[read],high+a[read]);
      low = Math.min(a[read],low+a[read]);
      maximum = Math.max(maximum,high); minimum = Math.min(minimum,low); total += a[read];
      yield {a,p:{read},vars:{high,low,maximum,minimum,total},note:'Update both ending sums, both global extremes, and the total.'};
    }
    yield {a,vars:{maximum,minimum,total,wrapping:total-minimum},note:'A wrapping segment keeps the two ends and removes the minimum middle. For an all-negative array, return the normal maximum instead.'};
    return maximum < 0 ? maximum : Math.max(maximum,total-minimum);
  },{min:1});

  add('subarray-sum-equals-k','Subarray sum equals K',4,'Count nonempty contiguous subarrays with sum k, including arrays with negatives.','Look up a previous prefix equal to prefix minus k.','Count matches before recording the current prefix, so empty subarrays are never counted.',['O(n) expected','O(n)'],{a:[1,2,1,-1,2],k:3},function* sumEquals({a,k}) {
    let prefix = 0, count = 0;
    const freq = new Map(); freq.set(0,1);
    yield {a,vars:{prefix,count,k},freq:Object.fromEntries(freq),note:'Record the empty prefix once, so subarrays beginning at index zero can match.'};
    for (let read = 0; read < a.length; read++) {
      prefix += a[read];
      const needed = prefix-k, matches = freq.get(needed) || 0;
      count += matches;
      yield {a,p:{read},vars:{prefix,needed,matches,count},freq:Object.fromEntries(freq),note:'Each earlier prefix equal to needed creates one subarray ending here with sum k.'};
      freq.set(prefix,(freq.get(prefix) || 0)+1);
      yield {a,p:{read},vars:{prefix,count},freq:Object.fromEntries(freq),note:'Record this prefix only after counting earlier matches.'};
    }
    return count;
  });
  add('find-pivot-index','Find pivot index',4,'Return the leftmost index whose left and right sums are equal, or -1.','Subtract left and current from total to get right.','The pivot value belongs to neither side. Check equality before adding it to left.',['O(n)','O(1)'],{a:[1,7,3,6,5,6]},function* pivotIndex({a}) {
    let total = 0, left = 0;
    for (let read = 0; read < a.length; read++) {
      total += a[read];
      yield {a,p:{read},vars:{total},note:'First accumulate the total sum.'};
    }
    for (let read = 0; read < a.length; read++) {
      const right = total-left-a[read];
      yield {a,p:{read},vars:{total,left,right},note:'Exclude the current value. Compare the sum strictly to its left with the sum strictly to its right.'};
      if (left === right) return read;
      left += a[read];
      yield {a,p:{read},vars:{left,right},note:'No balance here. Include this value in the left sum for the next candidate.'};
    }
    return -1;
  });
  add('subarray-sums-divisible-by-k','Subarray sums divisible by K',4,'Count nonempty subarrays whose sums are divisible by positive k.','Equal prefix remainders cancel.','Normalize remainders into 0 through k-1, including for negative sums.',['O(n) expected','O(min(n,k))'],{a:[4,5,0,-2,-3,1],k:5},function* divisible({a,k}) {
    let remainder = 0, count = 0;
    const freq = new Map(); freq.set(0,1);
    yield {a,vars:{remainder,count,k},freq:Object.fromEntries(freq),note:'The empty prefix has remainder zero.'};
    for (let read = 0; read < a.length; read++) {
      remainder = ((remainder+a[read])%k+k)%k;
      const matches = freq.get(remainder) || 0;
      count += matches;
      yield {a,p:{read},vars:{remainder,matches,count},freq:Object.fromEntries(freq),note:'Every previous prefix with this same remainder leaves a subarray sum divisible by k.'};
      freq.set(remainder,matches+1);
      yield {a,p:{read},vars:{remainder,count},freq:Object.fromEntries(freq),note:'Add this remainder to the frequency table for future endpoints.'};
    }
    return count;
  },{positiveK:true});
  add('contiguous-array','Contiguous array',4,'Find the longest contiguous segment containing equally many zeros and ones.','Treat zero as -1 and one as +1.','A repeated balance means the intervening segment has equal counts. Keep the earliest index for the longest length.',['O(n) expected','O(n)'],{a:[0,1,0,0,1,1,0]},function* balancedBinary({a}) {
    let balance = 0, best = 0;
    const last = new Map(); last.set(0,-1);
    yield {a,vars:{balance,best},freq:Object.fromEntries(last),note:'Balance zero first occurs before the array, at index -1. The table stores earliest indices, not counts.'};
    for (let read = 0; read < a.length; read++) {
      balance += a[read] === 0 ? -1 : 1;
      if (last.has(balance)) {
        const start = last.get(balance)+1;
        best = Math.max(best,read-start+1);
        yield {a,p:{read},window:[start,read],vars:{balance,best,start},freq:Object.fromEntries(last),note:'This balance occurred earlier. The highlighted segment has equal zero and one counts.'};
      } else {
        last.set(balance,read);
        yield {a,p:{read},vars:{balance,best},freq:Object.fromEntries(last),note:'Save the first occurrence of this balance. Never overwrite an earlier index.'};
      }
    }
    return best;
  },{binary:true});
  add('shortest-subarray-with-sum-at-least-k','Shortest subarray with sum at least K',4,'Return the shortest nonempty subarray length with sum at least k, allowing negatives; return -1 if absent.','Maintain an increasing deque of prefix sums.','Remove valid starts from the front; remove dominated prefixes from the back because a later, smaller prefix is always better.',['O(n)','O(n)'],{a:[2,-1,2,1],k:3},function* shortestPrefix({a,k}) {
    const prefix = new Array(a.length+1).fill(0), deque = new Array(a.length+1).fill(0);
    for (let read = 0; read < a.length; read++) {
      prefix[read+1] = prefix[read]+a[read];
      yield {a,p:{read},out:prefix,outTitle:'Prefix sums',q:{write:read+1},note:'prefix[i] is the sum of the first i values; prefix[0] is zero.'};
    }
    let head = 0, tail = 0, best = a.length+1;
    for (let end = 0; end < prefix.length; end++) {
      yield {a,p:{read:end-1},out:prefix,outTitle:'Prefix sums',q:{end},vars:{head,tail,best},aux:deque.slice(head,tail),auxTitle:'Deque: prefix indices',note:'Consider this prefix as the right boundary. The deque holds candidate starting boundaries.'};
      while (head < tail && prefix[end]-prefix[deque[head]] >= k) {
        best = Math.min(best,end-deque[head]); head++;
        yield {a,out:prefix,outTitle:'Prefix sums',q:{end},vars:{head,tail,best},aux:deque.slice(head,tail),auxTitle:'Deque: prefix indices',note:'The front forms a valid subarray. Record its length and remove it: future endpoints only make it longer.'};
      }
      while (head < tail && prefix[end] <= prefix[deque[tail-1]]) {
        tail--;
        yield {a,out:prefix,outTitle:'Prefix sums',q:{end},vars:{head,tail,best},aux:deque.slice(head,tail),auxTitle:'Deque: prefix indices',note:'Remove the back: this later prefix is no larger, so it gives at least as much sum in less length.'};
      }
      deque[tail] = end; tail++;
      yield {a,out:prefix,outTitle:'Prefix sums',q:{end},vars:{head,tail,best},aux:deque.slice(head,tail),auxTitle:'Deque: prefix indices',note:'Append this prefix index. Candidate prefix sums are increasing again.'};
    }
    return best <= a.length ? best : -1;
  });
  add('count-of-range-sum','Count range sum',4,'Count subarray sums in the inclusive interval [lower, upper].','Count cross-half prefix differences while merging sorted runs.','Within each merge, earlier prefix indices occupy the left run. Two moving bounds count right prefixes whose differences lie in range.',['O(n log n)','O(n)'],{a:[-2,5,-1],lower:-2,upper:2},function* rangeSum({a,lower,upper}) {
    let prefix = new Array(a.length+1).fill(0);
    const buffer = new Array(a.length+1).fill(0);
    for (let read = 0; read < a.length; read++) {
      prefix[read+1] = prefix[read]+a[read];
      yield {a,p:{read},out:prefix,outTitle:'Prefix sums',q:{write:read+1},note:'Build prefix sums. A subarray sum is a later prefix minus an earlier prefix.'};
    }
    let count = 0;
    for (let width = 1; width < prefix.length; width *= 2) {
      for (let start = 0; start < prefix.length; start += 2*width) {
        const mid = Math.min(start+width,prefix.length), end = Math.min(start+2*width,prefix.length);
        let low = mid, high = mid;
        for (let left = start; left < mid; left++) {
          while (low < end && prefix[low]-prefix[left] < lower) {
            low++;
            yield {a,out:prefix,outTitle:'Sorted prefix runs',q:{left,low,high},vars:{count,width},note:'Move low past differences below the inclusive lower bound.'};
          }
          while (high < end && prefix[high]-prefix[left] <= upper) {
            high++;
            yield {a,out:prefix,outTitle:'Sorted prefix runs',q:{left,low,high},vars:{count,width},note:'Move high to the first difference above the inclusive upper bound.'};
          }
          count += high-low;
          yield {a,out:prefix,outTitle:'Sorted prefix runs',q:{left,low,high},vars:{count,matches:high-low,width},note:'Every right prefix in [low, high) creates a valid subarray. Add high minus low.'};
        }
        let left = start, right = mid;
        for (let write = start; write < end; write++) {
          if (left < mid && (right >= end || prefix[left] <= prefix[right])) {
            buffer[write] = prefix[left]; left++;
          } else {
            buffer[write] = prefix[right]; right++;
          }
        }
        for (let write = start; write < end; write++) prefix[write] = buffer[write];
        yield {a,out:prefix,outTitle:'Sorted prefix runs',vars:{count,width,start,mid,end},note:'Merge these two sorted runs. The next pass doubles the run width.'};
      }
    }
    return count;
  },{rangeBounds:true});

  function* mergeOrGaps({intervals}) {
    const mode = "MODE";
    intervals.sort((x,y)=>x[0]-y[0]);
    const result = [];
    yield {a:intervals,intervals,vars:{},out:result,note:'Sort all busy intervals by start time. Touching intervals are connected; only positive-length gaps count as free time.'};
    if (intervals.length === 0) return result;
    let start = intervals[0][0], end = intervals[0][1];
    yield {a:intervals,intervals,p:{read:0},vars:{start,end},out:result,note:'Begin the first connected interval.'};
    for (let read = 1; read < intervals.length; read++) {
      yield {a:intervals,intervals,p:{read},vars:{start,end},out:result,note:'Compare the next start with the end of the connected interval.'};
      if (intervals[read][0] <= end) {
        end = Math.max(end,intervals[read][1]);
        yield {a:intervals,intervals,p:{read},vars:{start,end},out:result,note:'These intervals overlap or touch. Extend the end; never shorten it for a nested interval.'};
      } else {
        if (mode === 'merge') result.push([start,end]);
        else result.push([end,intervals[read][0]]);
        start = intervals[read][0]; end = intervals[read][1];
        yield {a:intervals,intervals,p:{read},vars:{start,end},out:result,note:'A gap separates the intervals. Record the completed interval or the finite free gap, then begin a new interval.'};
      }
    }
    if (mode === 'merge') result.push([start,end]);
    yield {a:intervals,intervals,vars:{start,end},out:result,note:'The scan is complete. Free time excludes the unbounded time before and after every schedule.'};
    return result;
  }
  add('merge-intervals','Merge intervals',5,'Merge all overlapping closed intervals. Touching endpoints also merge.','Sort starts, then grow one connected interval.','A gap finalizes the current merged interval; later starts cannot reach back across it.',['O(n log n)','O(n) output'],{intervals:[[1,3],[2,6],[8,10],[15,18]]},mergeOrGaps,{mode:'merge',intervalInput:true});
  add('employee-free-time','Employee free time',5,'Find finite, positive-length gaps when every employee is free. Enter all employees’ busy intervals together in intervals.','Merge everyone’s busy time; the gaps are shared free time.','The union of all employee schedules is exactly the time when at least one employee is busy.',['O(n log n)','O(n) output'],{intervals:[[1,2],[5,6],[1,3],[4,10]]},mergeOrGaps,{mode:'gaps',intervalInput:true,source:'https://www.codertrain.co/employee-free-time'});
  add('insert-interval','Insert interval',5,'Insert newInterval into sorted, disjoint closed intervals, merging all overlaps.','Copy before, merge overlaps, copy after.','Once an interval starts after the merged end, every remaining interval is also after it.',['O(n)','O(n) output'],{intervals:[[1,3],[6,9]],newInterval:[2,5]},function* insertInterval({intervals,newInterval}) {
    const result = [];
    let read = 0, start = newInterval[0], end = newInterval[1];
    yield {a:intervals,intervals,vars:{start,end},out:result,note:'The highlighted start and end describe the interval being inserted.'};
    while (read < intervals.length && intervals[read][1] < start) {
      result.push(intervals[read]); read++;
      yield {a:intervals,intervals,p:{read},vars:{start,end},out:result,note:'This interval ends before the new start. Copy it unchanged.'};
    }
    while (read < intervals.length && intervals[read][0] <= end) {
      start = Math.min(start,intervals[read][0]); end = Math.max(end,intervals[read][1]); read++;
      yield {a:intervals,intervals,p:{read},vars:{start,end},out:result,note:'Absorb the overlap by expanding both boundaries.'};
    }
    result.push([start,end]);
    yield {a:intervals,intervals,vars:{start,end},out:result,note:'Append the single merged interval.'};
    while (read < intervals.length) {
      result.push(intervals[read]); read++;
      yield {a:intervals,intervals,p:{read},out:result,note:'The remaining intervals are strictly after the inserted interval. Copy them unchanged.'};
    }
    return result;
  },{intervalInput:true,disjoint:true});
  add('interval-list-intersections','Intervals intersection',5,'Intersect two sorted lists of disjoint closed intervals. A shared endpoint counts.','Overlap starts at the later start and ends at the earlier end.','Advance the interval that ends first: it cannot intersect anything beyond the other interval’s end.',['O(n + m)','O(n + m) output'],{intervals:[[0,2],[5,10],[13,23],[24,25]],other:[[1,5],[8,12],[15,24],[25,26]]},function* intersections({intervals,other}) {
    let left = 0, right = 0;
    const result = [];
    while (left < intervals.length && right < other.length) {
      const start = Math.max(intervals[left][0],other[right][0]), end = Math.min(intervals[left][1],other[right][1]);
      yield {a:intervals,intervals,other,p:{left},vars:{right,start,end},out:result,note:'Compare the selected interval from each list. An overlap exists when start is no greater than end.'};
      if (start <= end) result.push([start,end]);
      if (intervals[left][1] < other[right][1]) left++;
      else right++;
      yield {a:intervals,intervals,other,p:{left},vars:{right,start,end},out:result,note:'Record any intersection and advance the list whose interval ends first.'};
    }
    return result;
  },{intervalInput:true,disjoint:true});
  add('overlapping-intervals','Overlapping intervals',5,'Determine whether any two closed intervals overlap, including a shared endpoint.','Sort intervals and compare the next start with the furthest end.','If a start is at most the previous furthest end, there is an overlap.',['O(n log n)','O(log n) sort stack'],{intervals:[[1,3],[7,9],[2,4]]},function* anyOverlap({intervals}) {
    intervals.sort((x,y)=>x[0]-y[0]);
    if (intervals.length < 2) return false;
    let end = intervals[0][1];
    yield {a:intervals,intervals,p:{read:0},vars:{end},note:'Sort by start and remember the first end.'};
    for (let read = 1; read < intervals.length; read++) {
      yield {a:intervals,intervals,p:{read},vars:{end,start:intervals[read][0]},note:'If this start is at or before end, two intervals overlap.'};
      if (intervals[read][0] <= end) return true;
      end = Math.max(end,intervals[read][1]);
    }
    return false;
  },{intervalInput:true,source:'https://www.geeksforgeeks.org/check-if-any-two-intervals-overlap-among-a-given-set-of-intervals/'});
  function* resourceSweep({intervals}) {
    const mode = "MODE";
    intervals.sort((x,y)=>x[0]-y[0]);
    const endings = intervals.slice(0);
    endings.sort((x,y)=>x[1]-y[1]);
    let right = 0, active = 0, best = 0;
    yield {a:intervals,intervals,vars:{active,best},note:'Sort starts and ends separately. An end at the same time as a start releases its resource first.'};
    for (let left = 0; left < intervals.length; left++) {
      while (right < endings.length && endings[right][1] <= intervals[left][0]) {
        active -= mode === 'rooms' ? 1 : endings[right][2]; right++;
        yield {a:intervals,intervals,p:{left},vars:{right,active,best,time:intervals[left][0]},note:'Release a finished meeting or job before accepting the next start.'};
      }
      active += mode === 'rooms' ? 1 : intervals[left][2];
      best = Math.max(best,active);
      yield {a:intervals,intervals,p:{left},vars:{right,active,best,time:intervals[left][0]},note:'Add the new meeting or job. The highest simultaneous resource usage is the answer.'};
    }
    return best;
  }
  add('attend-all-meetings-ii','Minimum meeting rooms',5,'Find the minimum rooms for positive-duration meetings. A room can be reused when one meeting ends as another starts.','Track simultaneous meetings with a sweep.','Process every finished meeting before adding a new one; peak active count is the required room count.',['O(n log n)','O(n)'],{intervals:[[0,30],[5,10],[15,20]]},resourceSweep,{mode:'rooms',intervalInput:true,positiveDuration:true,source:'https://www.geeksforgeeks.org/problems/attend-all-meetings-ii/1'});
  add('maximum-cpu-load','Maximum CPU load',5,'Find peak simultaneous CPU load. Each interval is [start, end, load]; jobs release their load at end.','Sweep job starts and release completed jobs.','active is the sum of loads of jobs still running. Intervals use [start,end) timing.',['O(n log n)','O(n)'],{intervals:[[1,4,3],[2,5,4],[7,9,6]]},resourceSweep,{mode:'load',intervalInput:true,positiveDuration:true,loads:true,source:'https://www.geeksforgeeks.org/maximum-cpu-load-from-the-given-list-of-jobs/'});

  function* reverseGroups({a,k=2,left=1,right=a.length}) {
    const mode = "MODE";
    const next = a.map((_,i) => i+1 < a.length ? i+1 : -1);
    let head = a.length ? 0 : -1, before = -1, current = head;
    let planned = mode === 'full' ? a.length : mode === 'sub' ? right-left+1 : mode === 'even' ? 1 : k;
    yield {a,next,head,p:{current,before},vars:{head,planned},note:'Node values stay fixed. We reverse next links and reconnect each group; -1 means null.'};
    if (mode === 'sub') {
      for (let position = 1; position < left; position++) {
        before = current; current = next[current];
        yield {a,next,head,p:{current,before},vars:{head,position},note:'Walk to the first node of the requested 1-based sub-list. Remember the node before it.'};
      }
    }
    while (current !== -1) {
      let after = current, count = 0, last = current;
      while (after !== -1 && count < planned) {
        last = after; after = next[after]; count++;
        yield {a,next,head,p:{current,after},vars:{head,count,planned},note:'Count the actual nodes in this group and save the first node after it before changing links.'};
      }
      const reverse = mode === 'even' ? count%2 === 0 : count === planned;
      yield {a,next,head,p:{current,after},vars:{head,count,planned,reverse},note:mode==='even'?'Reverse only if the actual group length is even, including a short final group.':'Reverse a complete group. A short final K-group remains unchanged.'};
      if (reverse) {
        const tail = current;
        let previous = after;
        while (current !== after) {
          const following = next[current];
          yield {a,next,head,p:{current,previous,following},vars:{head},note:'Save following before overwriting the current next link.'};
          next[current] = previous;
          yield {a,next,head,p:{current,previous,following},vars:{head,changedNode:current,oldNext:following,newNext:previous},note:'Redirect current.next to previous. The saved following pointer keeps the unprocessed nodes reachable.'};
          previous = current; current = following;
          yield {a,next,head,p:{current,previous},vars:{head},note:'Advance previous to the reversed node and current to the saved following node.'};
        }
        if (before === -1) head = previous;
        else next[before] = previous;
        before = tail;
        yield {a,next,head,p:{current,before},vars:{head},note:'Reconnect the preceding node (or head) to the reversed group. Its old first node is now its tail.'};
      } else {
        before = last; current = after;
        yield {a,next,head,p:{current,before},vars:{head},note:'Keep this group in its original order and advance beyond it.'};
      }
      if (mode === 'sub' || mode === 'full') break;
      if (mode === 'even') planned++;
    }
    const result = [];
    for (let node = head; node !== -1; node = next[node]) result.push(a[node]);
    yield {a,next,head,vars:{head},out:result,note:'Follow the updated head and next links to read the final list. Values were never swapped.'};
    return result;
  }
  add('reverse-linked-list','Reverse a linked list',6,'Reverse all links of a singly linked list.','Save next, reverse the link, advance.','previous is the head of the reversed part; current starts the unprocessed part. Node IDs stay fixed while links change.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[1,2,3,4,5]},reverseGroups,{mode:'full',linked:true});
  add('reverse-linked-list-ii','Reverse a sub-list',6,'Reverse the nodes from 1-based position left to right, inclusive.','Remember both boundaries and reconnect after reversing.','The old first node becomes the sub-list tail. The preceding node points to the new first node.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[1,2,3,4,5],left:2,right:4},reverseGroups,{mode:'sub',linked:true,subList:true,min:1});
  add('swap-nodes-in-pairs','Reverse list in pairs',6,'Swap each pair of adjacent nodes by changing links. Leave a final unpaired node alone.','Reverse complete groups of two.','The tail of each processed pair reconnects to the next pair; node values remain unchanged.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[1,2,3,4,5]},reverseGroups,{mode:'pairs',linked:true});
  add('reverse-nodes-in-k-group','Reverse every K-element sub-list',6,'Reverse each complete group of k nodes. A shorter final group stays in order.','Count before reversing.','Look ahead to verify a complete group before touching any links.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[1,2,3,4,5],k:3},reverseGroups,{mode:'k',linked:true,fixedK:true,min:1});
  add('reverse-nodes-in-even-length-groups','Reverse nodes in even-length groups',6,'Partition the list into groups of intended lengths 1, 2, 3, …; reverse groups whose actual length is even.','Actual group length decides, not the intended size.','The final group may be shorter. Count it first, then apply the even-length rule.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[5,2,6,3,9,1,7,3,8,4]},reverseGroups,{mode:'even',linked:true,min:1});
  add('rotate-list','Rotate a linked list',6,'Rotate the list to the right by k places.','Connect tail to head, then cut at the new tail.','Only k modulo length matters. The new tail is length minus shift minus one positions after the original head.',['O(n)','O(1) auxiliary; O(n) teaching representation'],{a:[1,2,3,4,5],k:2},function* rotateList({a,k}) {
    const next = a.map((_,i) => i+1 < a.length ? i+1 : -1);
    let head = a.length ? 0 : -1;
    yield {a,next,head,vars:{head,k},note:'Keep node values fixed; rotation changes only the head and two links.'};
    if (a.length < 2) return a;
    let tail = head, length = 1;
    while (next[tail] !== -1) {
      tail = next[tail]; length++;
      yield {a,next,head,p:{tail},vars:{length,k},note:'Find the current tail and count nodes.'};
    }
    const shift = k%length;
    yield {a,next,head,p:{tail},vars:{length,shift},note:'Full rotations change nothing. Reduce k modulo the list length.'};
    if (shift === 0) return a;
    next[tail] = head;
    yield {a,next,head,p:{tail},vars:{shift},note:'Temporarily connect tail to head, forming a ring.'};
    let newTail = head;
    for (let step = 0; step < length-shift-1; step++) {
      newTail = next[newTail];
      yield {a,next,head,p:{newTail},vars:{shift,step},note:'Move to the node immediately before the new head.'};
    }
    head = next[newTail]; next[newTail] = -1;
    yield {a,next,head,p:{newTail},vars:{head,shift},note:'Save the new head, then break the ring at newTail. The list now ends at null.'};
    const result = [];
    for (let node = head; node !== -1; node = next[node]) result.push(a[node]);
    return result;
  },{linked:true,nonnegativeK:true});

  function* duplicatesStack({s,k=2}) {
    const a = s.split(''), stack = a.slice(0), counts = new Array(s.length).fill(0);
    let size = 0;
    yield {a,stack:[],vars:{size,k},note:'The stack stores surviving characters and the length of the run ending at each position.'};
    for (let read = 0; read < a.length; read++) {
      const count = size > 0 && stack[size-1] === a[read] ? counts[size-1]+1 : 1;
      stack[size] = a[read]; counts[size] = count; size++;
      yield {a,p:{read},stack:stack.slice(0,size),stackCounts:counts.slice(0,size),vars:{size,count,k},note:'Push the character. If it matches the previous top, extend that adjacent run.'};
      if (count === k) {
        size -= k;
        yield {a,p:{read},stack:stack.slice(0,size),stackCounts:counts.slice(0,size),vars:{size,k},note:'The top run reached k copies. Pop those copies; earlier characters can now become adjacent.'};
      }
    }
    const result = [];
    for (let i = 0; i < size; i++) result.push(stack[i]);
    return result.join('');
  }
  add('remove-all-adjacent-duplicates-in-string','Remove adjacent duplicates',7,'Repeatedly remove adjacent pairs of equal characters until no pair remains.','A stack exposes newly adjacent pairs.','The stack is the fully reduced form of the prefix processed so far.',['O(n)','O(n)'],{s:'abbaca'},duplicatesStack,{text:true});
  add('remove-all-adjacent-duplicates-in-string-ii','Remove adjacent duplicates II',7,'Repeatedly remove runs of k adjacent equal characters.','Keep the run count beside each stack entry.','Removing a run exposes the previous run count, allowing cascading deletions.',['O(n)','O(n)'],{s:'deeedbbcccbdaa',k:3},duplicatesStack,{text:true,positiveK:true});
  add('valid-parentheses','Balanced parentheses',7,'Check that every opening bracket has the correct closing bracket, in nesting order. Input uses only ()[]{}.', 'The most recent opening bracket must close first.','Store expected closing brackets. Every closing bracket must match the stack top, and the stack must be empty at the end.',['O(n)','O(n)'],{s:'{[()]}'},function* validParentheses({s}) {
    const a = s.split(''), stack = a.slice(0);
    let size = 0;
    yield {a,stack:[],vars:{size},note:'An empty stack means no bracket is waiting to close.'};
    for (let read = 0; read < a.length; read++) {
      const ch = a[read];
      if (ch === '(' || ch === '[' || ch === '{') {
        stack[size] = ch === '(' ? ')' : ch === '[' ? ']' : '}'; size++;
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'Push the closing bracket expected for this opening bracket.'};
      } else {
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'A closing bracket must equal the expected bracket at the top. An empty stack or different bracket is invalid.'};
        if (size === 0 || stack[size-1] !== ch) return false;
        size--;
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'The pair matches. Pop the expected closing bracket.'};
      }
    }
    return size === 0;
  },{text:true,brackets:true});
  add('reverse-string-stack','Reverse a string',7,'Reverse a string using a stack.','Last in, first out reverses order.','Push every character, then repeatedly pop the most recently pushed character.',['O(n)','O(n)'],{s:'hello'},function* reverseString({s}) {
    const a = s.split(''), stack = a.slice(0);
    let size = 0;
    const result = [];
    for (let read = 0; read < a.length; read++) {
      stack[size] = a[read]; size++;
      yield {a,p:{read},stack:stack.slice(0,size),vars:{size,result},note:'Push the next character onto the stack.'};
    }
    while (size > 0) {
      size--; result.push(stack[size]);
      yield {a,stack:stack.slice(0,size),vars:{size,result},note:'Pop the top character and append it to the result. Last in becomes first out.'};
    }
    return result.join('');
  },{text:true,source:'https://leetcode.com/problems/reverse-string/'});
  function* monotonicNext({a}) {
    const mode = "MODE";
    const stack = new Array(a.length).fill(0), answer = new Array(a.length).fill(0);
    let size = 0;
    for (let i = 0; i < a.length; i++) answer[i] = mode === 'greater' ? -1 : 0;
    yield {a,stack:[],stackKind:'indices',out:answer,vars:{size},note:'The stack holds indices still waiting for a strictly greater value. Unresolved answers use -1 for next greater, or zero days for temperatures.'};
    const limit = mode === 'greater' ? 2*a.length : a.length;
    for (let step = 0; step < limit; step++) {
      const read = step%a.length;
      yield {a,p:{read},stack:stack.slice(0,size),stackKind:'indices',out:answer,vars:{size,step},note:step>=a.length?'Wrap around for a second pass to resolve circular next-greater queries. Do not push indices again.':'Compare this value with unresolved indices at the top of the decreasing stack.'};
      while (size > 0 && a[stack[size-1]] < a[read]) {
        size--; const waiting = stack[size];
        answer[waiting] = mode === 'greater' ? a[read] : read-waiting;
        yield {a,p:{read},stack:stack.slice(0,size),stackKind:'indices',out:answer,q:{write:waiting},vars:{size,waiting},note:'This is the first strictly greater value for the popped index. Record its value, or the number of days waited.'};
      }
      if (step < a.length) {
        stack[size] = read; size++;
        yield {a,p:{read},stack:stack.slice(0,size),stackKind:'indices',out:answer,vars:{size},note:'Push this index. Equal values stay unresolved because the answer must be strictly greater.'};
      }
    }
    return answer;
  }
  add('next-greater-element-ii','Next greater element (circular)',7,'For each value, return the next strictly greater value when scanning circularly, or -1.','Scan twice with a decreasing stack of unresolved indices.','Each index enters and leaves the stack at most once. The second pass resolves wraparound answers.',['O(n)','O(n)'],{a:[1,2,1]},monotonicNext,{mode:'greater'});
  add('daily-temperatures','Daily temperatures',7,'For each day, return how many days until a strictly warmer temperature, or zero.','Resolve cooler days when a warmer day arrives.','Indices remaining in the stack have not yet seen a warmer day.',['O(n)','O(n)'],{a:[73,74,75,71,69,72,76,73]},monotonicNext,{mode:'days'});
  add('remove-nodes-from-linked-list','Remove nodes from linked list',7,'Remove every node that has a strictly greater value somewhere to its right.','A larger incoming node removes smaller stack tops.','The stack holds surviving nodes in nonincreasing value order. Reconnect their next links after the scan.',['O(n)','O(n)'],{a:[5,2,13,3,8]},function* removeListNodes({a}) {
    const next = a.map((_,i) => i+1 < a.length ? i+1 : -1), stack = new Array(a.length).fill(0);
    let size = 0, head = a.length ? 0 : -1;
    for (let current = head; current !== -1; current = next[current]) {
      while (size > 0 && a[stack[size-1]] < a[current]) {
        size--;
        yield {a,next,head,p:{current},stack:stack.slice(0,size),stackKind:'indices',vars:{size,removed:stack[size]},note:'The incoming node is greater than this stack top. Remove that earlier node from the survivor stack.'};
      }
      stack[size] = current; size++;
      yield {a,next,head,p:{current},stack:stack.slice(0,size),stackKind:'indices',vars:{size},note:'Keep the current node as a survivor candidate. Equal values do not remove each other.'};
    }
    for (let i = 0; i < size; i++) {
      next[stack[i]] = i+1 < size ? stack[i+1] : -1;
      yield {a,next,head,stack:stack.slice(0,size),stackKind:'indices',p:{current:stack[i]},vars:{size},note:'Reconnect this survivor to the next survivor, or null for the last one.'};
    }
    head = size > 0 ? stack[0] : -1;
    const result = [];
    for (let node = head; node !== -1; node = next[node]) result.push(a[node]);
    yield {a,next,head,stack:stack.slice(0,size),stackKind:'indices',vars:{head,size},note:'Update head to the first survivor. Detached nodes no longer belong to the list.'};
    return result;
  },{linked:true});
  add('simplify-path','Simplify path',7,'Simplify an absolute Unix path. Ignore empty components and dot; dot-dot goes up one directory without going above root.','Keep directory names on a stack.','Names other than exactly dot or dot-dot are ordinary names, including three dots.',['O(n)','O(n)'],{s:'/a/./b/../../c/'},function* simplifyPath({s}) {
    const a = s.split('/'), stack = a.slice(0);
    let size = 0;
    yield {a,stack:[],vars:{size},note:'Split on slashes. Each stack entry is a directory name, not a character.'};
    for (let read = 0; read < a.length; read++) {
      const part = a[read];
      if (part === '' || part === '.') {
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'Ignore an empty component or a single dot: neither changes the directory.'};
        continue;
      }
      if (part === '..') {
        if (size > 0) size--;
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'Dot-dot pops one directory if possible. At root, stay at root.'};
      } else {
        stack[size] = part; size++;
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size},note:'Push this ordinary directory name.'};
      }
    }
    const result = [];
    for (let i = 0; i < size; i++) result.push(stack[i]);
    return '/'+result.join('/');
  },{text:true,absolutePath:true});
  add('remove-k-digits','Remove K digits',7,'Remove exactly k digits to form the smallest possible nonnegative number. Strip leading zeros; return "0" if empty.','Remove larger preceding digits before a smaller digit.','Earlier digits matter more. Keep a nondecreasing stack while deletions remain; remove trailing digits if necessary.',['O(n)','O(n)'],{s:'1432219',k:3},function* removeDigits({s,k}) {
    const a = s.split(''), stack = a.slice(0);
    let size = 0, remaining = k;
    yield {a,stack:[],vars:{size,remaining},note:'remaining counts how many digits still must be deleted.'};
    for (let read = 0; read < a.length; read++) {
      while (remaining > 0 && size > 0 && stack[size-1] > a[read]) {
        size--; remaining--;
        yield {a,p:{read},stack:stack.slice(0,size),vars:{size,remaining},note:'Pop a larger earlier digit. Replacing it with this smaller digit improves the earliest possible position.'};
      }
      stack[size] = a[read]; size++;
      yield {a,p:{read},stack:stack.slice(0,size),vars:{size,remaining},note:'Push this digit after removing larger predecessors while the deletion budget allows.'};
    }
    while (remaining > 0) {
      size--; remaining--;
      yield {a,stack:stack.slice(0,size),vars:{size,remaining},note:'Digits left in the stack are already ordered. Delete from the end to spend the remaining budget.'};
    }
    let start = 0;
    const result = [];
    while (start < size && stack[start] === '0') start++;
    for (let i = start; i < size; i++) result.push(stack[i]);
    yield {a,stack:stack.slice(0,size),vars:{start,result},note:'Strip leading zeros from the surviving digits. An empty result is represented as zero.'};
    return result.length === 0 ? '0' : result.join('');
  },{text:true,digits:true,nonnegativeK:true});

  add('first-unique-character-in-a-string','First non-repeating character',8,'Return the index of the first character that occurs once, or -1.','Count first, then scan in original order.','The frequency table gives global counts. The second scan finds the first count of one.',['O(n)','O(alphabet)'],{s:'loveleetcode'},function* firstUnique({s}) {
    const a = s.split(''), freq = new Map();
    for (let read = 0; read < a.length; read++) {
      freq.set(a[read],(freq.get(a[read]) || 0)+1);
      yield {a,p:{read},freq:Object.fromEntries(freq),note:'Count this character. The table tracks occurrences across the whole string.'};
    }
    for (let read = 0; read < a.length; read++) {
      yield {a,p:{read},freq:Object.fromEntries(freq),vars:{count:freq.get(a[read])},note:'Scan in input order. The first character with count one is the answer.'};
      if (freq.get(a[read]) === 1) return read;
    }
    return -1;
  },{text:true});
  add('maximum-number-of-balloons','Maximum number of balloons',8,'Count how many copies of "balloon" can be built using each lowercase character at most once.','The scarcest required character limits the answer.','balloon requires b:1, a:1, l:2, o:2, n:1. Divide each available count by its required count, round down, and take the minimum.',['O(n)','O(1) alphabet'],{s:'loonbalxballpoon'},function* balloons({s}) {
    const a = s.split(''), freq = new Map();
    for (let read = 0; read < a.length; read++) {
      freq.set(a[read],(freq.get(a[read]) || 0)+1);
      yield {a,p:{read},freq:Object.fromEntries(freq),note:'Count available letters.'};
    }
    const letters = 'balon';
    let best = s.length;
    for (let read = 0; read < letters.length; read++) {
      const letter = letters[read], required = letter === 'l' || letter === 'o' ? 2 : 1;
      const available = freq.get(letter) || 0, copies = Math.floor(available/required);
      best = Math.min(best,copies);
      yield {a,freq:Object.fromEntries(freq),vars:{letter,required,available,copies,best},note:'Divide this letter count by its required copies. The smallest capacity across required letters limits complete balloons.'};
    }
    return best;
  },{text:true,lowercase:true});
  add('longest-palindrome','Longest palindrome',8,'Return the longest palindrome length constructible from these characters. Character case matters; reordering is allowed.','Use all pairs, plus at most one unpaired center.','Every second occurrence completes a pair. If any character remains unpaired, exactly one can be used as the center.',['O(n)','O(alphabet)'],{s:'abccccdd'},function* palindromeLength({s}) {
    const a = s.split(''), freq = new Map();
    let paired = 0;
    for (let read = 0; read < a.length; read++) {
      const count = (freq.get(a[read]) || 0)+1;
      freq.set(a[read],count);
      if (count%2 === 0) paired += 2;
      yield {a,p:{read},freq:Object.fromEntries(freq),vars:{count,paired},note:count%2===0?'This occurrence completes another pair. Put one copy on each side of the palindrome.':'This character currently has an unpaired copy. Only one unpaired character can occupy the center.'};
    }
    const center = paired < s.length ? 1 : 0;
    yield {a,freq:Object.fromEntries(freq),vars:{paired,center},note:'Use one center if any unpaired character remains; all complete pairs can be used.'};
    return paired+center;
  },{text:true});
  add('ransom-note','Ransom note',8,'Can note s be built from magazine t? Each magazine character may be used once.','Count the supply, then consume one copy per needed character.','A missing character immediately proves failure; never reuse an already consumed copy.',['O(n + m)','O(alphabet)'],{s:'aa',t:'aab'},function* ransomNote({s,t}) {
    const a = s.split(''), magazine = t.split(''), freq = new Map();
    for (let read = 0; read < magazine.length; read++) {
      freq.set(magazine[read],(freq.get(magazine[read]) || 0)+1);
      yield {a,out:magazine,outTitle:'Magazine',q:{read},freq:Object.fromEntries(freq),note:'Count the available supply from the magazine.'};
    }
    for (let read = 0; read < a.length; read++) {
      const available = freq.get(a[read]) || 0;
      yield {a,p:{read},out:magazine,outTitle:'Magazine',freq:Object.fromEntries(freq),vars:{available},note:'Check whether a copy of the next note character remains.'};
      if (available === 0) return false;
      freq.set(a[read],available-1);
      yield {a,p:{read},out:magazine,outTitle:'Magazine',freq:Object.fromEntries(freq),note:'Consume exactly one copy so it cannot be reused.'};
    }
    return true;
  },{strings:true});

  add('binary-search','Binary search basic',9,'Return the index of target in a strictly increasing array, or -1.','Compare the middle and discard the impossible half.','If target exists, it stays inside the inclusive interval [left, right].',['O(log n)','O(1)'],{a:[-1,0,3,5,9,12],target:9},function* basicBinary({a,target}) {
    let left = 0, right = a.length-1;
    yield {a,p:{left,right},window:[left,right],vars:{target},note:'Start with the whole array as the candidate range.'};
    while (left <= right) {
      const mid = left+Math.floor((right-left)/2);
      yield {a,p:{left,right,mid},window:[left,right],vars:{target,value:a[mid]},note:'Compare the middle value with target. Sorted order lets us discard one whole half.'};
      if (a[mid] === target) return mid;
      if (a[mid] < target) left = mid+1;
      else right = mid-1;
      yield {a,p:{left,right,mid},window:[left,right],vars:{target},note:'Exclude mid and the impossible half. The remaining range is strictly smaller.'};
    }
    return -1;
  },{sorted:true,unique:true});
  add('ceil-in-a-sorted-array','Upper bound / ceiling',9,'Return the first qualifying index, or -1. bound="ceiling" means value ≥ target; bound="upper" means value > target.','Keep a qualifying middle, then search earlier.','The half-open range [left, right) converges to the first qualifying position. Equal values qualify only for ceiling.',['O(log n)','O(1)'],{a:[1,2,2,4,6,8],target:2,bound:'ceiling'},function* ceiling({a,target,bound}) {
    let left = 0, right = a.length;
    yield {a,p:{left,right},window:[left,right-1],vars:{target,bound},note:'right is exclusive and may be one position past the array. Ceiling includes equality; strict upper bound skips equals.'};
    while (left < right) {
      const mid = left+Math.floor((right-left)/2), qualifies = bound === 'upper' ? a[mid] > target : a[mid] >= target;
      yield {a,p:{left,right,mid},window:[left,right-1],vars:{target,qualifies},note:'Test whether the middle value qualifies under the selected boundary rule.'};
      if (qualifies) right = mid;
      else left = mid+1;
      yield {a,p:{left,right,mid},window:[left,right-1],vars:{target,qualifies},note:qualifies?'Keep mid as a possible answer and search to its left.':'This value and everything before it fail. Move left beyond mid.'};
    }
    return left < a.length ? left : -1;
  },{sorted:true,boundChoice:true,source:'https://www.geeksforgeeks.org/problems/ceil-in-a-sorted-array/1'});
  function* equalRange({a,target}) {
    const mode = "MODE";
    let first = 0, past = 0;
    for (let phase = 0; phase < 2; phase++) {
      let left = 0, right = a.length;
      yield {a,p:{left,right},window:[left,right-1],vars:{phase,target},note:phase===0?'First find the first value greater than or equal to target.':'Now find the first value strictly greater than target.'};
      while (left < right) {
        const mid = left+Math.floor((right-left)/2);
        yield {a,p:{left,right,mid},window:[left,right-1],vars:{phase,target},note:'Compare mid using the boundary rule for this pass.'};
        if (a[mid] < target || (phase === 1 && a[mid] === target)) left = mid+1;
        else right = mid;
        yield {a,p:{left,right,mid},window:[left,right-1],vars:{phase,target},note:'Shrink the half-open interval. The second pass moves past every equal value.'};
      }
      if (phase === 0) first = left;
      else past = left;
      yield {a,vars:{first,past,phase},note:'Save this boundary. Occurrences occupy [first, past), so the count is past minus first.'};
    }
    if (mode === 'count') return past-first;
    return first === past ? [-1,-1] : [first,past-1];
  }
  add('find-first-and-last-position-of-element-in-sorted-array','First and last position',9,'Return the first and last target indices, or [-1,-1].','Find both edges of the equal-value block.','The first upper boundary minus one is the last occurrence; matching boundaries mean the target is absent.',['O(log n)','O(1)'],{a:[5,7,7,8,8,10],target:8},equalRange,{mode:'positions',sorted:true});
  add('number-of-occurrence2259','Count number of occurrences',9,'Count target occurrences in a sorted array without a linear scan.','Subtract lower bound from upper bound.','All target values occupy one contiguous index block. Its length is upper minus lower.',['O(log n)','O(1)'],{a:[1,2,2,2,3,4],target:2},equalRange,{mode:'count',sorted:true,source:'https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1'});
  add('search-in-infinite-sorted-array','Search in an infinite sorted array',9,'Simulate unknown-length sorted data with a finite example. Reads past the supplied data return infinity.','Double the bound until it brackets target, then binary-search.','Exponential expansion discovers a range without using the length as its initial bound. Out-of-range infinity safely handles absent targets.',['O(log p), where p is the discovered bound','O(1)'],{a:[3,5,7,9,10,90,100,130,140,160],target:100},function* infiniteSearch({a,target}) {
    let left = 0, right = 1;
    yield {a,p:{left,right},window:[left,right],vars:{target},note:'Start with a small range. The example simulates an unknown-length reader: a read beyond the data is infinity.'};
    while (right < a.length && a[right] < target) {
      left = right+1; right *= 2;
      yield {a,p:{left,right},window:[left,right],vars:{target},note:'The value at the bound is too small. Double the bound and discard the already-checked prefix.'};
    }
    while (left <= right) {
      const mid = left+Math.floor((right-left)/2), value = mid < a.length ? a[mid] : Infinity;
      yield {a,p:{left,right,mid},window:[left,right],vars:{target,value},note:'Binary-search the discovered range. Infinity behaves like a value larger than the target.'};
      if (value === target) return mid;
      if (value < target) left = mid+1;
      else right = mid-1;
      yield {a,p:{left,right,mid},window:[left,right],vars:{target},note:'Keep only the half that can contain target.'};
    }
    return -1;
  },{sorted:true,unique:true,source:'https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/'});
  function* findPeak({a}) {
    let left = 0, right = a.length-1;
    yield {a,p:{left,right},window:[left,right],note:'Keep a range guaranteed to contain a peak. Missing neighbors outside the array act like negative infinity.'};
    while (left < right) {
      const mid = left+Math.floor((right-left)/2), rising = a[mid] < a[mid+1];
      yield {a,p:{left,right,mid},window:[left,right],vars:{rising,nextValue:a[mid+1]},note:'Compare mid with its next neighbor to learn whether this slope rises or falls.'};
      if (rising) left = mid+1;
      else right = mid;
      yield {a,p:{left,right,mid},window:[left,right],vars:{rising},note:rising?'An uphill step guarantees a peak to the right; mid is not a peak.':'A falling step keeps mid as a possible peak and rules out the right side.'};
    }
    return left;
  }
  add('peak-index-in-a-mountain-array','Peak index in a mountain',9,'Find the peak index in a strict mountain: a nonempty rising part followed by a nonempty falling part.','Follow the slope toward the summit.','The unique mountain peak remains between left and right.',['O(log n)','O(1)'],{a:[0,2,5,8,6,3,1]},findPeak,{mountain:true,min:3});
  add('find-peak-element','Find a peak element',9,'Return any strict local peak index. Adjacent values must differ; the array need not be a single mountain.','An uphill direction leads to some peak.','Even with multiple peaks, the retained side contains at least one.',['O(log n)','O(1)'],{a:[1,2,1,3,5,6,4]},findPeak,{unequalNeighbors:true,min:1});
  function* rotatedMinimum({a}) {
    const mode = "MODE";
    let left = 0, right = a.length-1;
    yield {a,p:{left,right},window:[left,right],note:'The input is a rotation of a strictly increasing array. Find the smallest value’s index.'};
    while (left < right) {
      const mid = left+Math.floor((right-left)/2);
      yield {a,p:{left,right,mid},window:[left,right],note:'Compare mid with the right endpoint to locate the rotation break.'};
      if (a[mid] > a[right]) left = mid+1;
      else right = mid;
      yield {a,p:{left,right,mid},window:[left,right],note:'If mid exceeds the right endpoint, the minimum is after mid. Otherwise retain mid and the left side.'};
    }
    return mode === 'value' ? a[left] : left;
  }
  add('find-minimum-in-rotated-sorted-array','Minimum in rotated sorted array',9,'Return the minimum value in a rotated, strictly increasing array.','Locate the rotation break.','The minimum stays inside the inclusive search interval. Distinct values make the comparison decisive.',['O(log n)','O(1)'],{a:[4,5,6,7,0,1,2]},rotatedMinimum,{mode:'value',rotated:true,min:1});
  add('rotation4723','Number of rotations',9,'Return the minimum element’s index: the number of right rotations modulo n from the increasing order.','The minimum marks the rotation count.','An unrotated array has its minimum at index zero. Values must be distinct.',['O(log n)','O(1)'],{a:[15,18,2,3,6,12]},rotatedMinimum,{mode:'index',rotated:true,min:1,source:'https://www.geeksforgeeks.org/problems/rotation4723/1'});
  add('search-in-rotated-sorted-array','Search in rotated sorted array',9,'Return the target index in a rotated strictly increasing array, or -1.','Identify the sorted half, then test whether target lies in it.','At least one side of mid is sorted. Use that side’s bounds to discard the impossible half.',['O(log n)','O(1)'],{a:[4,5,6,7,0,1,2],target:0},function* rotatedSearch({a,target}) {
    let left = 0, right = a.length-1;
    yield {a,p:{left,right},window:[left,right],vars:{target},note:'Begin with the entire rotated array.'};
    while (left <= right) {
      const mid = left+Math.floor((right-left)/2);
      yield {a,p:{left,right,mid},window:[left,right],vars:{target,leftSorted:a[left]<=a[mid]},note:'Check mid, then identify which half is sorted.'};
      if (a[mid] === target) return mid;
      if (a[left] <= a[mid]) {
        if (a[left] <= target && target < a[mid]) right = mid-1;
        else left = mid+1;
        yield {a,p:{left,right,mid},window:[left,right],vars:{target},note:'The left half was sorted. Keep it only if its value bounds include target; otherwise keep the right half.'};
      } else {
        if (a[mid] < target && target <= a[right]) left = mid+1;
        else right = mid-1;
        yield {a,p:{left,right,mid},window:[left,right],vars:{target},note:'The right half was sorted. Keep it only if its value bounds include target; otherwise keep the left half.'};
      }
    }
    return -1;
  },{rotated:true,min:1});

  add('koko-eating-bananas','Koko eating bananas',9,'Find the smallest integer eating speed that finishes piles a within h hours. One pile is handled per hour.','Binary-search speed; count the hours it requires.','Faster speeds never take more hours, so feasible speeds form a suffix of the answer range.',['O(n log max(a))','O(1)'],{a:[3,6,7,11],h:8},function* koko({a,h}) {
    if (h < a.length) return -1;
    let low = 1, high = 1;
    for (let i = 0; i < a.length; i++) high = Math.max(high,a[i]);
    yield {a,search:{low,high,label:'Eating speed'},vars:{low,high,h},note:'Search speeds from one up to the largest pile. If h is smaller than the number of piles, completion is impossible.'};
    while (low < high) {
      const mid = low+Math.floor((high-low)/2);
      let hours = 0;
      for (let read = 0; read < a.length; read++) {
        hours += Math.floor((a[read]+mid-1)/mid);
        yield {a,p:{read},search:{low,high,mid,label:'Eating speed'},vars:{low,high,mid,hours,h},note:'At this speed, round each pile’s hours upward. Add them to the running total.'};
      }
      const feasible = hours <= h;
      yield {a,search:{low,high,mid,feasible,label:'Eating speed'},vars:{hours,h,feasible},note:feasible?'This speed finishes in time. Try a smaller speed, retaining mid.':'This speed is too slow. Every smaller speed also fails.'};
      if (feasible) high = mid;
      else low = mid+1;
      yield {a,search:{low,high,label:'Eating speed'},vars:{low,high},note:'The first feasible speed remains inside the smaller range.'};
    }
    return low;
  },{positive:true,min:1,positiveParams:['h'],answerSearch:true});
  add('minimum-number-of-days-to-make-m-bouquets','Minimum days to make m bouquets',9,'a contains bloom days. Make m disjoint bouquets, each using k adjacent bloomed flowers.','Search the day; count adjacent runs available by then.','Waiting longer cannot remove a feasible bouquet arrangement. Unbloomed flowers break runs.',['O(n log max(a))','O(1)'],{a:[1,10,3,10,2],m:3,k:1},function* bouquets({a,m,k}) {
    if (m > Math.floor(a.length/k)) return -1;
    let low = 1, high = 1;
    for (let i = 0; i < a.length; i++) high = Math.max(high,a[i]);
    yield {a,search:{low,high,label:'Day'},vars:{low,high,m,k},note:'Search the earliest feasible day. Each bouquet needs k adjacent flowers and flowers cannot be reused.'};
    while (low < high) {
      const mid = low+Math.floor((high-low)/2);
      let run = 0, made = 0;
      for (let read = 0; read < a.length; read++) {
        if (a[read] <= mid) run++;
        else run = 0;
        if (run === k) { made++; run = 0; }
        yield {a,p:{read},search:{low,high,mid,label:'Day'},vars:{run,made,m,k},note:'Count bloomed adjacent flowers. Make a bouquet at k, reset to avoid reuse, and break the run at an unbloomed flower.'};
      }
      const feasible = made >= m;
      yield {a,search:{low,high,mid,feasible,label:'Day'},vars:{made,m,feasible},note:feasible?'Enough bouquets: retain this day and test earlier days.':'Too few bouquets: all earlier days also fail.'};
      if (feasible) high = mid;
      else low = mid+1;
      yield {a,search:{low,high,label:'Day'},vars:{low,high},note:'Update the candidate-day interval. The earliest feasible day remains inside it.'};
    }
    return low;
  },{positive:true,min:1,positiveParams:['m','k'],answerSearch:true});
  add('aggressive-cows','Aggressive cows',9,'Place k cows in distinct stalls a, maximizing the minimum distance between any two cows.','Search the gap; greedily place each cow as early as possible.','A feasible gap implies every smaller gap is feasible. Early placement leaves the most room for later cows.',['O(n log n + n log range)','O(log n) sort stack'],{a:[1,2,4,8,9],k:3},function* cows({a,k}) {
    a.sort((x,y)=>x-y);
    let low = 0, high = a[a.length-1]-a[0];
    yield {a,search:{low,high,label:'Minimum gap'},vars:{low,high,k},note:'Sort stalls. We seek the largest gap that still lets all cows fit.'};
    while (low < high) {
      const mid = low+Math.floor((high-low+1)/2);
      let placed = 1, previous = 0;
      for (let read = 1; read < a.length; read++) {
        if (a[read]-a[previous] >= mid) { placed++; previous = read; }
        yield {a,p:{read,previous},search:{low,high,mid,label:'Minimum gap'},vars:{placed,k},note:'Place a cow in the earliest stall at least mid away from the last placed cow.'};
      }
      const feasible = placed >= k;
      yield {a,search:{low,high,mid,feasible,label:'Minimum gap'},vars:{placed,k,feasible},note:feasible?'All cows fit. Keep this gap and try a larger one.':'Not enough cows fit. Discard this gap and every larger gap.'};
      if (feasible) low = mid;
      else high = mid-1;
      yield {a,search:{low,high,label:'Minimum gap'},vars:{low,high},note:'Update the gap interval. Upper-mid rounding ensures progress even with two remaining candidates.'};
    }
    return low;
  },{min:2,unique:true,cows:true,answerSearch:true,source:'https://www.geeksforgeeks.org/problems/aggressive-cows/1'});
  add('h-index-ii','H-index II',9,'Given sorted nonnegative citation counts, find the largest h with at least h papers cited at least h times.','Find the first index where citations meet the remaining paper count.','At index mid there are n-mid papers with at least a[mid] citations. The qualifying condition changes from false to true.',['O(log n)','O(1)'],{a:[0,1,3,5,6]},function* hIndex({a}) {
    let left = 0, right = a.length;
    yield {a,p:{left,right},window:[left,right-1],note:'Use a half-open search range. A boundary at n means h is zero.'};
    while (left < right) {
      const mid = left+Math.floor((right-left)/2), papers = a.length-mid;
      yield {a,p:{left,right,mid},window:[left,right-1],vars:{papers,citations:a[mid]},note:'Can each of the papers from mid onward support an h-index of papers?' }; 
      if (a[mid] >= papers) right = mid;
      else left = mid+1;
      yield {a,p:{left,right,mid},window:[left,right-1],vars:{papers},note:'Search for the earliest qualifying index: it gives the largest number of qualifying papers.'};
    }
    return a.length-left;
  },{sorted:true,nonnegative:true});
  add('maximum-candies-allocated-to-k-children','Maximum candies to k children',9,'Split piles a into equal-size portions for k children. Portions cannot combine candies from different piles.','Search the portion size; sum floor(pile / size).','If a size is feasible, every smaller positive size is feasible. Zero is returned when even size one cannot serve everyone.',['O(n log max(a))','O(1)'],{a:[5,8,6],k:3},function* candies({a,k}) {
    let low = 0, high = 0;
    for (let i = 0; i < a.length; i++) high = Math.max(high,a[i]);
    yield {a,search:{low,high,label:'Candies per child'},vars:{low,high,k},note:'Search for the largest feasible portion size. Zero is the fallback, never a divisor.'};
    while (low < high) {
      const mid = low+Math.floor((high-low+1)/2);
      let portions = 0;
      for (let read = 0; read < a.length; read++) {
        portions += Math.floor(a[read]/mid);
        yield {a,p:{read},search:{low,high,mid,label:'Candies per child'},vars:{portions,k},note:'Each pile contributes only whole portions. Leftovers cannot be combined across piles.'};
      }
      const feasible = portions >= k;
      yield {a,search:{low,high,mid,feasible,label:'Candies per child'},vars:{portions,k,feasible},note:feasible?'Enough portions: retain mid and try larger portions.':'Too few portions: reduce the upper bound below mid.'};
      if (feasible) low = mid;
      else high = mid-1;
      yield {a,search:{low,high,label:'Candies per child'},vars:{low,high},note:'Update the remaining portion-size interval.'};
    }
    return low;
  },{min:1,nonnegative:true,positiveParams:['k'],answerSearch:true});
  function* partitionCapacity({a,k}) {
    const mode = "MODE";
    if (mode !== 'ship' && k > a.length) return -1;
    let low = 0, high = 0;
    for (let i = 0; i < a.length; i++) { low = Math.max(low,a[i]); high += a[i]; }
    yield {a,search:{low,high,label:'Maximum group sum'},vars:{low,high,k},note:'Keep item order. Capacity must fit the largest item; the sum of all items is always enough for one group.'};
    while (low < high) {
      const mid = low+Math.floor((high-low)/2);
      let groupsUsed = 1, load = 0;
      for (let read = 0; read < a.length; read++) {
        if (load+a[read] > mid) { groupsUsed++; load = 0; }
        load += a[read];
        yield {a,p:{read},search:{low,high,mid,label:'Maximum group sum'},vars:{load,groupsUsed,k},note:'Greedily extend the current contiguous group. Start a new group only when this item would exceed capacity.'};
      }
      const feasible = groupsUsed <= k;
      yield {a,search:{low,high,mid,feasible,label:'Maximum group sum'},vars:{groupsUsed,k,feasible},note:feasible?'At most k groups suffice. For exact-k partitioning, split groups further without raising their maximum sum. Try a smaller capacity.':'Too many groups are required. Every smaller capacity also fails.'};
      if (feasible) high = mid;
      else low = mid+1;
      yield {a,search:{low,high,label:'Maximum group sum'},vars:{low,high},note:'Update the capacity interval, retaining the smallest feasible answer.'};
    }
    return low;
  }
  add('capacity-to-ship-packages-within-d-days','Capacity to ship packages in D days',9,'Find the minimum ship capacity to send weights a in order within k days. Here k is D.','Test a capacity by greedily filling each day.','A larger capacity never increases the number of required days.',['O(n log sum(a))','O(1)'],{a:[1,2,3,4,5,6,7,8,9,10],k:5},partitionCapacity,{mode:'ship',positive:true,min:1,positiveParams:['k'],answerSearch:true});
  add('allocate-minimum-number-of-pages0937','Book allocation',9,'Allocate ordered books a to exactly k students in nonempty contiguous groups. Minimize the largest page total; return -1 if k exceeds the number of books.','Binary-search the largest allowed student load.','Greedy packing uses the fewest students for a candidate limit. With k ≤ n, a solution using fewer students can be split further.',['O(n log sum(a))','O(1)'],{a:[12,34,67,90],k:2},partitionCapacity,{mode:'books',positive:true,min:1,positiveParams:['k'],answerSearch:true,source:'https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1'});
  add('split-array-largest-sum','Split array largest sum',9,'Split a nonnegative array into exactly k nonempty contiguous groups, minimizing the largest group sum.','Binary-search the answer, not an array index.','Nonnegative values make the greedy group-count test monotone in capacity.',['O(n log sum(a))','O(1)'],{a:[7,2,5,10,8],k:2},partitionCapacity,{mode:'split',nonnegative:true,min:1,fixedK:true,answerSearch:true});

  add('search-a-2d-matrix','Search a 2D matrix',9,'Rows are sorted and each row starts after the previous row ends. Return whether target exists.','Binary-search flattened indices without copying the matrix.','Convert index mid to row floor(mid / columns) and column mid modulo columns.',['O(log(rows × columns))','O(1)'],{matrix:[[1,3,5,7],[10,11,16,20],[23,30,34,60]],target:3},function* matrixBinary({matrix,target}) {
    const rows = matrix.length, columns = matrix[0].length;
    let left = 0, right = rows*columns-1;
    yield {matrix,flatRange:[left,right],vars:{left,right,target},note:'Treat all rows as one globally sorted sequence. The matrix stays two-dimensional.'};
    while (left <= right) {
      const mid = left+Math.floor((right-left)/2), row = Math.floor(mid/columns), column = mid%columns;
      const value = matrix[row][column];
      yield {matrix,cell:[row,column],flatRange:[left,right],vars:{left,right,mid,row,column,value},note:'Map mid to its matrix cell, then compare that value with target.'};
      if (value === target) return true;
      if (value < target) left = mid+1;
      else right = mid-1;
      yield {matrix,cell:[row,column],flatRange:[left,right],vars:{left,right,target},note:'Discard the half of flattened indices that cannot contain target.'};
    }
    return false;
  },{matrixInput:'flat'});
  add('search-a-2d-matrix-ii','Search a 2D matrix II',9,'Every row and every column is sorted. Return whether target exists; rows need not form one flattened sorted sequence.','Start at the top-right corner and eliminate a row or column.','A value too large rules out its entire column below; a value too small rules out its row to the left. This is staircase search.',['O(rows + columns)','O(1)'],{matrix:[[1,4,7,11],[2,5,8,12],[3,6,9,16]],target:6},function* matrixStaircase({matrix,target}) {
    let row = 0, column = matrix[0].length-1;
    yield {matrix,cell:[row,column],matrixRange:[row,column],vars:{row,column,target},note:'Start at top right. Remaining candidates lie below and to the left.'};
    while (row < matrix.length && column >= 0) {
      const value = matrix[row][column];
      yield {matrix,cell:[row,column],matrixRange:[row,column],vars:{row,column,value,target},note:'Compare the current corner with target.'};
      if (value === target) return true;
      if (value > target) column--;
      else row++;
      yield {matrix,cell:[row,column],matrixRange:[row,column],vars:{row,column,target},note:'Too large: discard this column and move left. Too small: discard this row and move down.'};
    }
    return false;
  },{matrixInput:'sorted'});
  add('kth-smallest-element-in-a-sorted-matrix','Kth smallest in a sorted matrix',9,'Return the kth smallest value, counting duplicates, in a square matrix sorted by rows and columns.','Binary-search a value and count entries at most that value.','The first value with at least k entries no larger than it is the answer. A bottom-left staircase counts in linear time per probe.',['O(n log value range)','O(1)'],{matrix:[[1,5,9],[10,11,13],[12,13,15]],k:8},function* matrixKth({matrix,k}) {
    const rows = matrix.length, columns = matrix[0].length;
    let low = matrix[0][0], high = matrix[rows-1][columns-1];
    yield {matrix,search:{low,high,label:'Candidate value'},vars:{low,high,k},note:'Search the value range, not cell indices. Duplicate entries each count toward rank.'};
    while (low < high) {
      const mid = low+Math.floor((high-low)/2);
      let row = rows-1, column = 0, count = 0;
      while (row >= 0 && column < columns) {
        if (matrix[row][column] <= mid) { count += row+1; column++; }
        else row--;
        yield {matrix,cell:[row,column],threshold:mid,search:{low,high,mid,label:'Candidate value'},vars:{row,column,count,k},note:'At bottom left, a qualifying cell means every cell above it in this column also qualifies. Count that column and move right; otherwise move up.'};
      }
      const feasible = count >= k;
      yield {matrix,threshold:mid,search:{low,high,mid,feasible,label:'Candidate value'},vars:{count,k,feasible},note:feasible?'At least k entries are no larger. Keep mid and search smaller values.':'Fewer than k entries qualify. The kth value must be larger.'};
      if (feasible) high = mid;
      else low = mid+1;
      yield {matrix,search:{low,high,label:'Candidate value'},vars:{low,high,k},note:'Narrow the value range toward the first value that reaches rank k.'};
    }
    return low;
  },{matrixInput:'square',answerSearch:true});
  add('kth-smallest-number-in-multiplication-table','Kth smallest in a multiplication table',9,'Return the kth smallest entry, counting duplicates, in the m by n table whose cell (r,c) is (r+1) × (c+1).','Count products at most mid without generating or sorting the table.','Row r contributes min(n, floor(mid / r)) entries. These counts increase monotonically with mid.',['O(m log(mn))','O(1)'],{m:3,n:3,k:5},function* multiplicationKth({m,n,k}) {
    let low = 1, high = m*n;
    yield {multiplication:{m,n},search:{low,high,label:'Product value'},vars:{low,high,k},note:'The displayed table is illustrative; the solution only counts products mathematically.'};
    while (low < high) {
      const mid = low+Math.floor((high-low)/2);
      let count = 0;
      for (let row = 1; row <= m; row++) {
        const contributed = Math.min(n,Math.floor(mid/row)); count += contributed;
        yield {multiplication:{m,n},cell:[row-1,contributed-1],threshold:mid,search:{low,high,mid,label:'Product value'},vars:{row,contributed,count,k},note:'In this row, products are multiples of row. Count up to floor(mid / row), capped at the number of columns.'};
      }
      const feasible = count >= k;
      yield {multiplication:{m,n},threshold:mid,search:{low,high,mid,feasible,label:'Product value'},vars:{count,k,feasible},note:feasible?'At least k products qualify. Try a smaller candidate value.':'Too few products qualify. Increase the candidate value.'};
      if (feasible) high = mid;
      else low = mid+1;
      yield {multiplication:{m,n},search:{low,high,label:'Product value'},vars:{low,high,k},note:'Narrow the candidate-product interval until only the kth value remains.'};
    }
    return low;
  },{multiplicationInput:true,answerSearch:true});
  add('median-of-two-sorted-arrays','Median of two sorted arrays',9,'Find the median of two sorted arrays without merging them. At least one must be nonempty.','Binary-search the partition of the smaller array.','Keep the left partition one element larger when the total is odd. A valid cut has both left-side maxima no greater than the opposite right-side minima.',['O(log min(n,m))','O(1)'],{a:[1,3],b:[2,4]},function* medianTwo({a,b}) {
    if (a.length > b.length) { const swap = a; a = b; b = swap; }
    const half = Math.floor((a.length+b.length+1)/2);
    let left = 0, right = a.length;
    yield {a,out:b,outTitle:'Second sorted array',vars:{left,right,half},note:'Search cuts in the shorter array; swap array roles if necessary. A cut is a boundary between elements.'};
    while (left <= right) {
      const cutA = left+Math.floor((right-left)/2), cutB = half-cutA;
      const aLeft = cutA === 0 ? -Infinity : a[cutA-1], aRight = cutA === a.length ? Infinity : a[cutA];
      const bLeft = cutB === 0 ? -Infinity : b[cutB-1], bRight = cutB === b.length ? Infinity : b[cutB];
      yield {a,out:b,outTitle:'Second sorted array',p:{cutA},q:{cutB},cuts:[cutA,cutB],vars:{aLeft,aRight,bLeft,bRight},note:'Check values on both sides of each cut. Missing values at the outside boundaries act as infinities.'};
      if (aLeft <= bRight && bLeft <= aRight) {
        yield {a,out:b,outTitle:'Second sorted array',cuts:[cutA,cutB],vars:{aLeft,aRight,bLeft,bRight},note:'Both cross-boundary comparisons hold. For an odd total take the largest left value; for an even total average the two middle values.'};
        if ((a.length+b.length)%2 === 1) return Math.max(aLeft,bLeft);
        return (Math.max(aLeft,bLeft)+Math.min(aRight,bRight))*0.5;
      }
      if (aLeft > bRight) right = cutA-1;
      else left = cutA+1;
      yield {a,out:b,outTitle:'Second sorted array',vars:{left,right,half},note:'If aLeft is too large, move the cut left. Otherwise move it right to include more of the shorter array.'};
    }
    return 0;
  },{sorted:true,medianInput:true});

  function* kthHeap({a,k}) {
    const heap = new Heap();
    const sign = "MODE" === 'smallest' ? -1 : 1;
    yield {a,heap:heap.items,vars:{k},note:'Keep only k candidates. The root is the least useful retained candidate.'};
    for (let i = 0; i < a.length; i++) {
      heap.push([sign * a[i]]);
      yield {a,p:{i},heap:heap.items,vars:{k},note:'Insert this value into the candidate heap.'};
      if (heap.size > k) {
        heap.pop();
        yield {a,p:{i},heap:heap.items,vars:{k},note:'There are too many candidates. Remove the least useful one at the root.'};
      }
    }
    return sign * heap.peek()[0];
  }
  add('kth-smallest-element5635','Kth smallest · heap',10,'Return the kth smallest value, counting duplicates.','Retain k small values in a max heap.','Negated priorities put the largest retained value at the root.',['O(n log k)','O(k)'],{a:[7,10,4,3,20,15],k:3},kthHeap,{mode:'smallest',fixedK:true,min:1,source:'https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1'});
  add('kth-largest-element-in-an-array','Kth largest · heap',10,'Return the kth largest value, counting duplicates.','Retain k large values in a min heap.','The smallest retained value is the current kth largest.',['O(n log k)','O(k)'],{a:[3,2,1,5,6,4],k:2},kthHeap,{mode:'largest',fixedK:true,min:1});

  add('top-k-frequent-elements','Top K frequent elements',10,'Return the k most frequent distinct values. Ties use smaller values first.','Count first, then extract maximum frequencies.','Each distinct value enters the heap once with negative frequency.',['O(n + u log u)','O(u)'],{a:[1,1,1,2,2,3],k:2},function* topFrequency({a,k}) {
    const freq = new Map(), heap = new Heap(), result = [];
    for (let i = 0; i < a.length; i++) {
      freq.set(a[i], (freq.get(a[i]) || 0) + 1);
      yield {a,p:{i},freq:Object.fromEntries(freq),vars:{k},note:'Count this value before ranking the distinct values.'};
    }
    const seen = new Map();
    for (let i = 0; i < a.length; i++) {
      if (!seen.has(a[i])) {
        heap.push([-freq.get(a[i]), a[i]]);
        seen.set(a[i], 1);
        yield {a,p:{i},heap:heap.items,heapTitle:'Heap · [−frequency, value]',out:result,vars:{k},note:'Insert each distinct value once. More frequent values have smaller negative priorities.'};
      }
    }
    while (result.length < k) {
      const entry = heap.pop();
      result.push(entry[1]);
      yield {a,heap:heap.items,out:result,vars:{k},note:'Extract the most frequent remaining value.'};
    }
    return result;
  },{heapDistinctK:true,min:1,positiveK:true});

  add('top-k-frequent-words','Top K frequent words',10,'Return words by descending frequency, breaking ties alphabetically.','Use alphabetical rank as the second heap key.','A negative frequency is primary; sorted word position breaks ties.',['O(n log n)','O(n)'],{words:['i','love','leetcode','i','love','coding'],k:2},function* topWords({words,k}) {
    words.sort();
    const freq = new Map(), heap = new Heap(), result = [];
    for (let i = 0; i < words.length; i++) {
      freq.set(words[i], (freq.get(words[i]) || 0) + 1);
      yield {a:words,p:{i},freq:Object.fromEntries(freq),vars:{k},note:'Sort words alphabetically and count occurrences.'};
    }
    for (let i = 0; i < words.length; i++) {
      if (i === 0 || words[i] !== words[i - 1]) {
        heap.push([-freq.get(words[i]), i]);
        yield {a:words,p:{i},heap:heap.items,heapTitle:'Heap · [−frequency, alphabetical position]',out:result,vars:{k},note:'Insert a distinct word. Its sorted position resolves equal-frequency ties.'};
      }
    }
    while (result.length < k) {
      const entry = heap.pop();
      result.push(words[entry[1]]);
      yield {a:words,heap:heap.items,out:result,vars:{k},note:'The root gives the next word in the required order.'};
    }
    return result;
  },{heapWords:true,positiveK:true});

  add('k-closest-points-to-origin','K closest points to origin',10,'Return k points with the smallest squared distance from (0,0).','Rank by x² + y²; no square root is needed.','The root is the closest unselected point.',['O(n log n)','O(n)'],{points:[[1,3],[-2,2],[2,-2]],k:2},function* closestPoints({points,k}) {
    const heap = new Heap(), result = [];
    for (let i = 0; i < points.length; i++) {
      const distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
      heap.push([distance, i]);
      yield {a:points,heap:heap.items,heapTitle:'Heap · [squared distance, point index]',p:{i},vars:{distance,k},note:'Insert the point using squared distance as its priority.'};
    }
    while (result.length < k) {
      const entry = heap.pop();
      result.push(points[entry[1]]);
      yield {a:points,heap:heap.items,out:result,vars:{k},note:'Extract the closest remaining point.'};
    }
    return result;
  },{heapRows:'points',positiveK:true});

  add('find-k-closest-elements','Find K closest elements · heap',10,'Return k values closest to target, sorted ascending; prefer smaller values on ties.','Distance first, value second.','Only selected values are copied into the final sorted answer.',['O(n log n)','O(n)'],{a:[1,2,3,4,5],k:4,target:3},function* closestValues({a,k,target}) {
    const heap = new Heap(), selected = new Heap(), result = [];
    for (let i = 0; i < a.length; i++) {
      heap.push([Math.abs(a[i] - target), a[i]]);
      yield {a,p:{i},heap:heap.items,heapTitle:'Heap · [distance, value]',vars:{k,target},note:'Rank by distance. A smaller value wins an equal-distance tie.'};
    }
    for (let i = 0; i < k; i++) {
      const entry = heap.pop();
      selected.push([entry[1]]);
      yield {a,heap:heap.items,heap2:selected.items,vars:{k,target},note:'Move the next closest value into the answer-order heap.'};
    }
    while (selected.size > 0) {
      result.push(selected.pop()[0]);
      yield {a,heap:selected.items,out:result,vars:{k,target},note:'Read the chosen values in ascending order.'};
    }
    return result;
  },{sorted:true,fixedK:true,min:1});

  add('the-k-weakest-rows-in-a-matrix','K weakest rows in a matrix',10,'Rank rows by soldier count, then row index. Each row has ones before zeros.','The weakest row has the fewest ones.','The heap orders [soldiers, row index].',['O(rows × columns + rows log rows)','O(rows)'],{matrix:[[1,1,0,0],[1,1,1,1],[1,0,0,0],[1,1,0,0]],k:2},function* weakestRows({matrix,k}) {
    const heap = new Heap(), result = [];
    for (let r = 0; r < matrix.length; r++) {
      let soldiers = 0;
      for (let c = 0; c < matrix[r].length; c++) {
        soldiers += matrix[r][c];
        yield {matrix,cell:[r,c],heap:heap.items,vars:{r,soldiers,k},note:'Count soldiers in this row.'};
      }
      heap.push([soldiers, r]);
      yield {matrix,heap:heap.items,vars:{r,soldiers,k},note:'Insert the row with its soldier count and row-index tie breaker.'};
    }
    while (result.length < k) {
      result.push(heap.pop()[1]);
      yield {matrix,heap:heap.items,out:result,vars:{k},note:'Extract the weakest unselected row.'};
    }
    return result;
  },{heapRows:'matrix',weakRows:true,positiveK:true});

  function* mergeHeapRows({matrix,k}) {
    const heap = new Heap(), result = [];
    for (let r = 0; r < matrix.length; r++) {
      if (matrix[r].length > 0) {
        heap.push([matrix[r][0], r, 0]);
        yield {matrix,heap:heap.items,heapTitle:'Heap · [value, row, column]',vars:{r},note:'Seed the heap with the first unread item of each sorted row.'};
      }
    }
    while (heap.size > 0) {
      const entry = heap.pop(), value = entry[0], r = entry[1], c = entry[2];
      result.push(value);
      yield {matrix,cell:[r,c],heap:heap.items,out:result,vars:{value,r,c},note:'The smallest frontier value is the next item in the merged order.'};
      if ("MODE" === 'kth' && result.length === k) return value;
      if (c + 1 < matrix[r].length) {
        heap.push([matrix[r][c + 1], r, c + 1]);
        yield {matrix,cell:[r,c+1],heap:heap.items,out:result,vars:{r,c},note:'Advance only the row just removed; its next value joins the frontier.'};
      }
    }
    return result;
  }
  add('merge-k-sorted-arrays','Merge K sorted arrays',10,'Merge sorted rows into one sorted sequence.','Keep one frontier entry per row.','All earlier items of each row have already been emitted.',['O(N log rows)','O(rows), excluding output'],{matrix:[[1,4,7],[2,5,8],[3,6,9]],k:9},mergeHeapRows,{mode:'merge',heapRows:'matrix',sortedRows:true,source:'https://www.geeksforgeeks.org/problems/merge-k-sorted-arrays/1'});
  add('kth-smallest-matrix-heap','Kth smallest in sorted matrix · heap',10,'Extract only the first k values of the merged row streams.','Use a heap as a set of row pointers.','The kth extracted value is the kth smallest, including duplicates.',['O(rows + k log rows)','O(rows)'],{matrix:[[1,5,9],[10,11,13],[12,13,15]],k:8},mergeHeapRows,{mode:'kth',heapRows:'matrix',sortedRows:true,matrixK:true,source:'https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/'});

  add('last-stone-weight','Last stone weight',10,'Repeatedly smash the two heaviest stones. Return the final weight or zero.','Always extract the two largest weights.','Negative weights turn the min heap into a max heap.',['O(n log n)','O(n)'],{a:[2,7,4,1,8,1]},function* stones({a}) {
    const heap = new Heap();
    for (let i = 0; i < a.length; i++) {
      heap.push([-a[i]]);
      yield {a,p:{i},heap:heap.items,heapTitle:'Max heap · negative weights',note:'Insert this stone; the heaviest stone rises to the root.'};
    }
    while (heap.size > 1) {
      const first = -heap.pop()[0], second = -heap.pop()[0], remaining = first - second;
      yield {a,heap:heap.items,vars:{first,second,remaining},note:'Smash the two heaviest stones. Their difference is the remaining weight.'};
      if (remaining > 0) {
        heap.push([-remaining]);
        yield {a,heap:heap.items,vars:{remaining},note:'Put the surviving stone back into the heap.'};
      }
    }
    return heap.size === 0 ? 0 : -heap.peek()[0];
  },{positive:true});

  add('task-scheduler','CPU task scheduler',10,'Schedule uppercase tasks with at least k intervening slots between equal tasks. Return elapsed slots.','Run the most frequent available task; wait for cooling tasks.','The ready heap ranks remaining counts. The cooling heap ranks release times.',['O(T log u)','O(u)'],{s:'AAABBB',k:2},function* scheduleTasks({s,k}) {
    const freq = new Map(), seen = new Map(), heap = new Heap(), cooling = new Heap(), result = [];
    for (let i = 0; i < s.length; i++) freq.set(s[i], (freq.get(s[i]) || 0) + 1);
    for (let i = 0; i < s.length; i++) {
      if (!seen.has(s[i])) {
        heap.push([-freq.get(s[i]), i]);
        seen.set(s[i], 1);
      }
    }
    let time = 0;
    yield {a:s.split(''),heap:heap.items,heap2:cooling.items,vars:{time,k},note:'Ready records are [−remaining count, task index]. Cooling records begin with their release time.'};
    while (heap.size > 0 || cooling.size > 0) {
      while (cooling.size > 0 && cooling.peek()[0] <= time) {
        const ready = cooling.pop();
        heap.push([ready[1], ready[2]]);
        yield {a:s.split(''),heap:heap.items,heap2:cooling.items,out:result,vars:{time,k},note:'This task has completed its cooldown and may run again.'};
      }
      if (heap.size > 0) {
        const entry = heap.pop(), remaining = -entry[0] - 1;
        result.push(s[entry[1]]);
        if (remaining > 0) cooling.push([time + k + 1, -remaining, entry[1]]);
        yield {a:s.split(''),heap:heap.items,heap2:cooling.items,out:result,vars:{time,k,remaining},note:'Run the most frequent available task. If unfinished, release it after k intervening slots.'};
      } else {
        result.push('idle');
        yield {a:s.split(''),heap:heap.items,heap2:cooling.items,out:result,vars:{time,k},note:'Every unfinished task is cooling down, so this slot must be idle.'};
      }
      time++;
    }
    return time;
  },{uppercase:true,nonnegativeK:true,cooldown:true});

  add('reorganize-string','Reorganize string',10,'Rearrange letters so neighboring letters differ; return an empty string if impossible.','Hold the last chosen letter outside the heap.','The previous letter cannot be selected on the immediately following turn.',['O(n log u)','O(u)'],{s:'aaabbc'},function* reorganize({s}) {
    const freq = new Map(), seen = new Map(), heap = new Heap();
    let result = '', heldCount = 0, heldIndex = -1;
    for (let i = 0; i < s.length; i++) freq.set(s[i], (freq.get(s[i]) || 0) + 1);
    for (let i = 0; i < s.length; i++) {
      if (!seen.has(s[i])) {
        heap.push([-freq.get(s[i]), i]);
        seen.set(s[i], 1);
      }
    }
    yield {a:s.split(''),heap:heap.items,vars:{heldCount,heldIndex},note:'Rank letters by remaining count. The previous letter will stay outside the heap for one turn.'};
    while (heap.size > 0) {
      const entry = heap.pop();
      result += s[entry[1]];
      yield {a:s.split(''),heap:heap.items,out:result.split(''),vars:{heldCount,heldIndex},note:'Choose the most frequent eligible letter; the held letter is not eligible.'};
      if (heldCount > 0) heap.push([-heldCount, heldIndex]);
      heldCount = -entry[0] - 1;
      heldIndex = entry[1];
      yield {a:s.split(''),heap:heap.items,out:result.split(''),vars:{heldCount,heldIndex},note:'Release the previous letter, then hold the letter just used until the next turn.'};
    }
    return heldCount === 0 ? result : '';
  },{lowercase:true});

  add('minimum-number-of-refueling-stops','Minimum refueling stops',10,'Stations are [position, fuel]. Reach target with the fewest stops or return −1.','When fuel runs out, choose the largest previously passed supply.','The heap contains reachable stations whose fuel has not yet been used.',['O(n log n)','O(n)'],{stations:[[10,60],[20,30],[30,30],[60,40]],target:100,fuel:10},function* refuel({stations,target,fuel}) {
    const heap = new Heap();
    let reach = fuel, stops = 0, i = 0;
    yield {a:stations,heap:heap.items,vars:{reach,stops,target},note:'reach is the farthest position attainable with fuel selected so far.'};
    while (reach < target) {
      while (i < stations.length && stations[i][0] <= reach) {
        heap.push([-stations[i][1], i]);
        i++;
        yield {a:stations,heap:heap.items,vars:{reach,stops,i,target},note:'This station is reachable. Save its fuel as an option; no stop is committed yet.'};
      }
      if (heap.size === 0) return -1;
      reach -= heap.pop()[0];
      stops++;
      yield {a:stations,heap:heap.items,vars:{reach,stops,i,target},note:'Commit one stop at the largest available fuel supply. It extends reach as far as possible.'};
    }
    return stops;
  },{fuelInput:true});

  add('ipo','IPO · maximize capital',10,'Projects are [required capital, profit]. Choose at most k projects.','Choose the most profitable affordable project.','The heap contains affordable projects that have not been selected.',['O(n log n + k log n)','O(n)'],{projects:[[0,1],[1,2],[1,3]],k:2,capital:0},function* ipo({projects,k,capital}) {
    projects.sort((x,y) => x[0] - y[0]);
    const heap = new Heap();
    let i = 0;
    for (let chosen = 0; chosen < k; chosen++) {
      while (i < projects.length && projects[i][0] <= capital) {
        heap.push([-projects[i][1], i]);
        i++;
        yield {a:projects,heap:heap.items,vars:{capital,chosen,k,i},note:'This project is affordable. Insert its profit into the max heap.'};
      }
      if (heap.size === 0) return capital;
      capital -= heap.pop()[0];
      yield {a:projects,heap:heap.items,vars:{capital,chosen,k,i},note:'Complete the most profitable available project. Its profit may unlock more projects.'};
    }
    return capital;
  },{projectsInput:true,nonnegativeK:true});

  add('course-schedule-iii','Course schedule III',10,'Courses are [duration, deadline]. Find the maximum number that can finish on time.','Process deadlines in order; discard the longest course if late.','The retained courses fit all processed deadlines with the smallest possible total time for that count.',['O(n log n)','O(n)'],{courses:[[100,200],[200,1300],[1000,1250],[2000,3200]]},function* courses({courses}) {
    courses.sort((x,y) => x[1] - y[1]);
    const heap = new Heap();
    let time = 0;
    for (let i = 0; i < courses.length; i++) {
      time += courses[i][0];
      heap.push([-courses[i][0], i]);
      yield {a:courses,p:{i},heap:heap.items,vars:{time,deadline:courses[i][1]},note:'Tentatively include this course in deadline order.'};
      if (time > courses[i][1]) {
        time += heap.pop()[0];
        yield {a:courses,p:{i},heap:heap.items,vars:{time,deadline:courses[i][1]},note:'The schedule is late. Removing the longest retained course frees the most time.'};
      }
    }
    return heap.size;
  },{coursesInput:true});

  function* heapMedians({a,k}) {
    const result = [];
    const lower = new Heap(), upper = new Heap();
    for (let right = 0; right < a.length; right++) {
      const left = "MODE" === 'window' ? Math.max(0, right - k + 1) : 0;
      if ("MODE" === 'window') {
        while (lower.size > 0) lower.pop();
        while (upper.size > 0) upper.pop();
      }
      const start = "MODE" === 'window' ? left : right;
      for (let i = start; i <= right; i++) {
        if (lower.size === 0 || a[i] <= -lower.peek()[0]) lower.push([-a[i]]);
        else upper.push([a[i]]);
        yield {a,p:{left,right,i},window:[left,right],heap:lower.items,heap2:upper.items,heapTitle:'Lower half · negative priorities',vars:{left,right},out:result,note:'Insert into the lower max heap or the upper min heap according to the current dividing value.'};
        if (lower.size > upper.size + 1) upper.push([-lower.pop()[0]]);
        if (upper.size > lower.size) lower.push([-upper.pop()[0]]);
        yield {a,p:{left,right,i},window:[left,right],heap:lower.items,heap2:upper.items,heapTitle:'Lower half · negative priorities',vars:{left,right},out:result,note:'Balance the heaps: the lower half has either the same count or one extra value.'};
      }
      if ("MODE" === 'stream' || right - left + 1 === k) {
        const median = lower.size === upper.size ? (-lower.peek()[0] + upper.peek()[0]) * 0.5 : -lower.peek()[0];
        result.push(median);
        yield {a,p:{left,right},window:[left,right],heap:lower.items,heap2:upper.items,out:result,vars:{median,left,right},note:'For odd counts read the lower root; for even counts average the two roots.'};
      }
    }
    return result;
  }
  add('find-median-from-data-stream','Find median in a data stream',10,'Return the median after each incoming value.','Two balanced heaps meet at the median.','All lower-half values are at most all upper-half values.',['O(n log n)','O(n)'],{a:[5,2,8,1,3],k:1},heapMedians,{mode:'stream',min:1});
  add('sliding-window-median','Sliding window median · two heaps',10,'Return each size-k window median. This teaching variant rebuilds two heaps per window.','Partition each window into balanced lower and upper halves.','Rebuilding drops expired values explicitly; this is O(nk log k), not the optimized lazy-deletion variant.',['O(nk log k)','O(k), excluding output'],{a:[1,3,-1,-3,5,3,6,7],k:3},heapMedians,{mode:'window',fixedK:true,min:1});

  groups.push('Recursion & backtracking');
  add('fibonacci-recursive','Fibonacci · recursion',11,'Return F(n), where F(0)=0 and F(1)=1.','A call waits for its two smaller calls.','Each return combines completed child calls. Repeated subproblems make this approach exponential.',['O(2ⁿ)','O(n) call stack'],{n:5},function* fibonacci({n}) {
    function* visit(value, depth) {
      yield {a:[value],vars:{value,depth},note:`Enter fib(${value}) at recursion depth ${depth}.`};
      if (value <= 1) {
        yield {a:[value],vars:{value,depth},note:'Base case: return this value without making more calls.'};
        return value;
      }
      const left = yield* visit(value - 1, depth + 1);
      yield {a:[value],vars:{value,depth,left},note:'The first child returned. Save its result while evaluating the second child.'};
      const right = yield* visit(value - 2, depth + 1);
      const total = left + right;
      yield {a:[value],vars:{value,depth,left,right,total},note:'Both children returned. Add their results and return to the waiting parent.'};
      return total;
    }
    const answer = yield* visit(n, 0);
    return answer;
  },{source:'https://leetcode.com/problems/fibonacci-number/',recursiveN:10,helperTypes:{visit:{return:'int',args:{value:'int',depth:'int'}}}});

  add('palindrome-string0817','Check palindrome · recursion',11,'Check whether a string reads the same in both directions.','Compare the outer pair, then recurse inward.','All characters outside the two pointers already match.',['O(n)','O(n) call stack'],{s:'racecar'},function* recursivePalindrome({s}) {
    function* visit(s, left, right) {
      yield {a:s.split(''),p:{left,right},vars:{left,right},note:'Compare this outer pair. A mismatch ends the recursion immediately.'};
      if (left >= right) return true;
      if (s[left] !== s[right]) return false;
      const matches = yield* visit(s, left + 1, right - 1);
      yield {a:s.split(''),p:{left,right},vars:{matches},note:'The inner substring returned. Pass its answer back to the caller.'};
      return matches;
    }
    const answer = yield* visit(s, 0, s.length - 1);
    return answer;
  },{source:'https://www.geeksforgeeks.org/problems/palindrome-string0817/1',helperTypes:{visit:{return:'boolean',args:{s:'String',left:'int',right:'int'}}}});

  add('check-if-an-array-is-sorted0701','Check sorted array · recursion',11,'Check whether values are in nondecreasing order.','Verify one neighboring pair per call.','Every earlier pair is already known to be ordered.',['O(n)','O(n) call stack'],{a:[1,2,2,5,8]},function* recursiveSorted({a}) {
    function* visit(a, i) {
      yield {a,p:{i},vars:{i},note:'This call checks the next adjacent pair, then delegates the remaining suffix.'};
      if (i + 1 >= a.length) return true;
      if (a[i] > a[i + 1]) return false;
      const ordered = yield* visit(a, i + 1);
      yield {a,p:{i},vars:{ordered},note:'Return the suffix result to the previous call.'};
      return ordered;
    }
    const answer = yield* visit(a, 0);
    return answer;
  },{source:'https://www.geeksforgeeks.org/problems/check-if-an-array-is-sorted0701/1',helperTypes:{visit:{return:'boolean',args:{a:'int[]',i:'int'}}}});

  add('sum-of-digits1742','Sum of digits · recursion',11,'Return the sum of the digits of a nonnegative integer.','Separate the last digit and recurse on the prefix.','Each call contributes exactly one decimal digit.',['O(log n)','O(log n) call stack'],{n:472},function* digitSum({n}) {
    function* visit(value, depth) {
      yield {a:String(value).split(''),vars:{value,depth},note:'This call owns the last digit and delegates the remaining prefix.'};
      if (value < 10) return value;
      const digit = value % 10;
      const rest = yield* visit(Math.floor(value / 10), depth + 1);
      const total = digit + rest;
      yield {a:String(value).split(''),vars:{value,depth,digit,rest,total},note:'The prefix sum returned. Add the last digit and return upward.'};
      return total;
    }
    const answer = yield* visit(n, 0);
    return answer;
  },{recursiveN:1000000000,source:'https://www.geeksforgeeks.org/problems/sum-of-digits1742/1',helperTypes:{visit:{return:'int',args:{value:'int',depth:'int'}}}});

  add('remove-character-recursive','Remove character · recursion',11,'Remove every occurrence of the single character t from s.','Build the answer while recursive calls return.','The returned suffix contains none of the unwanted character.',['O(n²) with immutable strings','O(n²) with copied strings'],{s:'banana',t:'a'},function* removeCharacter({s,t}) {
    function* visit(s, t, i) {
      yield {a:s.split(''),p:{i},vars:{i},note:'Visit this character, then recursively clean the suffix.'};
      if (i === s.length) return '';
      const suffix = yield* visit(s, t, i + 1);
      const cleaned = s[i] === t[0] ? suffix : s[i] + suffix;
      yield {a:s.split(''),p:{i},out:cleaned.split(''),vars:{i},note:s[i]===t[0]?'Drop this matching character and return the cleaned suffix.':'Keep this character before the cleaned suffix.'};
      return cleaned;
    }
    const answer = yield* visit(s, t, 0);
    return answer;
  },{singleCharacter:true,source:'https://www.geeksforgeeks.org/problems/remove-all-occurrences-of-a-character-in-a-string/1',helperTypes:{visit:{return:'String',args:{s:'String',t:'String',i:'int'}}}});

  add('generate-parentheses','Generate parentheses',11,'Generate all balanced strings of n pairs of parentheses.','Choose an opening or a legal closing parenthesis.','Never close more pairs than have been opened; never open more than n.',['O(n × Catalan(n))','O(n) recursion, excluding output'],{n:3},function* parentheses({n}) {
    const result = [];
    function* visit(n, path, opened, closed, result) {
      yield {a:path.split(''),out:result,vars:{opened,closed,n},note:'Enter this prefix. Only choices that preserve balance are explored.'};
      if (closed === n) {
        result.push(path);
        yield {a:path.split(''),out:result,vars:{opened,closed,n},note:'All pairs are closed. Save this complete answer and return.'};
        return 0;
      }
      if (opened < n) {
        yield* visit(n, path + '(', opened + 1, closed, result);
        yield {a:path.split(''),out:result,vars:{opened,closed,n},note:'Return to this prefix after exploring the opening-parenthesis branch.'};
      }
      if (closed < opened) {
        yield* visit(n, path + ')', opened, closed + 1, result);
        yield {a:path.split(''),out:result,vars:{opened,closed,n},note:'Return to this prefix after exploring the closing-parenthesis branch.'};
      }
      return 0;
    }
    yield* visit(n, '', 0, 0, result);
    return result;
  },{recursiveN:4,helperTypes:{visit:{return:'int',args:{n:'int',path:'String',opened:'int',closed:'int',result:'List<Object>'}}}});

  add('letter-combinations-of-a-phone-number','Phone-number letter combinations',11,'Choose one mapped letter for each digit 2–9.','Each recursion level chooses for one digit.','The prefix contains exactly one choice for each earlier digit.',['O(d × 4ᵈ)','O(d), excluding output'],{s:'23'},function* phone({s}) {
    const result = [];
    const letters = 'abc def ghi jkl mno pqrs tuv wxyz'.split(' ');
    function* visit(s, letters, depth, path, result) {
      yield {a:s.split(''),p:{depth},aux:path.split(''),auxTitle:'Chosen prefix',out:result,vars:{depth},note:'Choose a letter for the digit at this recursion depth.'};
      if (depth === s.length) {
        result.push(path);
        yield {a:s.split(''),aux:path.split(''),out:result,vars:{depth},note:'Every digit has a letter. Save this complete combination.'};
        return 0;
      }
      const choices = letters['23456789'.indexOf(s[depth])];
      for (let i = 0; i < choices.length; i++) {
        yield* visit(s, letters, depth + 1, path + choices[i], result);
        yield {a:s.split(''),p:{depth},aux:path.split(''),out:result,vars:{depth,i},note:'Backtrack to this prefix and try the next letter for the same digit.'};
      }
      return 0;
    }
    if (s.length > 0) yield* visit(s, letters, 0, '', result);
    return result;
  },{phoneInput:true,helperTypes:{visit:{return:'int',args:{s:'String',letters:'String[]',depth:'int',path:'String',result:'List<Object>'}}}});

  add('permutations','Permutations',11,'Generate every ordering of distinct input values.','Choose an unused value, recurse, then undo the choice.','used marks exactly the values in the current prefix.',['O(n × n!)','O(n), excluding output'],{a:[1,2,3]},function* permutations({a}) {
    const result = [], used = new Array(a.length).fill(0), path = new Array(a.length).fill(0);
    function* visit(a, used, path, depth, result) {
      yield {a,aux:path.slice(0,depth),auxTitle:'Current permutation prefix',out:result,vars:{depth},note:'This recursion level fills the next position with an unused value.'};
      if (depth === a.length) {
        result.push(path.slice(0,depth));
        yield {a,aux:path.slice(0,depth),out:result,vars:{depth},note:'All positions are filled. Save a copy of the permutation.'};
        return 0;
      }
      for (let i = 0; i < a.length; i++) {
        if (used[i] === 0) {
          used[i] = 1;
          path[depth] = a[i];
          yield {a,p:{i},aux:path.slice(0,depth+1),out:result,vars:{depth,i},note:'Choose this unused value and mark it as used before going deeper.'};
          yield* visit(a, used, path, depth + 1, result);
          used[i] = 0;
          yield {a,p:{i},aux:path.slice(0,depth),out:result,vars:{depth,i},note:'Undo the used mark. This value is available for another branch.'};
        }
      }
      return 0;
    }
    yield* visit(a, used, path, 0, result);
    return result;
  },{unique:true,backtrackArray:5,helperTypes:{visit:{return:'int',args:{a:'int[]',used:'int[]',path:'int[]',depth:'int',result:'List<Object>'}}}});

  add('combination-sum','Combination sum',11,'Find combinations of distinct positive candidates summing to target. Values may be reused.','Recurse from the current candidate to permit reuse.','Nondecreasing candidate positions avoid duplicate orderings.',['Exponential in target / minimum value','O(target / minimum value), excluding output'],{a:[2,3,6,7],target:7},function* combinations({a,target}) {
    a.sort((x,y) => x - y);
    const result = [], path = [];
    function* visit(a, start, remaining, path, result) {
      yield {a,p:{start},aux:path.slice(0),out:result,vars:{start,remaining},note:'Only choose candidates at or after start, so ordering does not create duplicate combinations.'};
      if (remaining === 0) {
        result.push(path.slice(0));
        yield {a,aux:path.slice(0),out:result,vars:{remaining},note:'The remaining sum is zero. Save a copy of this combination.'};
        return 0;
      }
      for (let i = start; i < a.length; i++) {
        if (a[i] > remaining) break;
        path.push(a[i]);
        yield {a,p:{i},aux:path.slice(0),out:result,vars:{remaining,i},note:'Choose this candidate. Recurse from the same index to allow using it again.'};
        yield* visit(a, i, remaining - a[i], path, result);
        path.pop();
        yield {a,p:{i},aux:path.slice(0),out:result,vars:{remaining,i},note:'Undo the last choice before trying the next larger candidate.'};
      }
      return 0;
    }
    yield* visit(a, 0, target, path, result);
    return result;
  },{positive:true,unique:true,positiveTarget:true,combinationInput:true,helperTypes:{visit:{return:'int',args:{a:'int[]',start:'int',remaining:'int',path:'List<Object>',result:'List<Object>'}}}});

  add('palindrome-partitioning','Palindrome partitioning',11,'Split a string into all possible lists of palindromic substrings.','Try a palindromic prefix, recurse on the suffix, then undo.','Every chosen piece is a palindrome and the pieces cover exactly the processed prefix.',['O(n² × 2ⁿ)','O(n), excluding output'],{s:'aab'},function* partition({s}) {
    const result = [], path = [];
    function* visit(s, start, path, result) {
      yield {a:s.split(''),p:{start},aux:path.slice(0),out:result,vars:{start},note:'Try each possible next piece beginning at start.'};
      if (start === s.length) {
        result.push(path.slice(0));
        yield {a:s.split(''),aux:path.slice(0),out:result,vars:{start},note:'All characters are covered by palindromes. Save this partition.'};
        return 0;
      }
      for (let end = start; end < s.length; end++) {
        let left = start, right = end;
        while (left < right && s[left] === s[right]) {
          left++;
          right--;
        }
        yield {a:s.split(''),p:{left,right},window:[start,end],aux:path.slice(0),out:result,vars:{start,end},note:left>=right?'The candidate piece is a palindrome. It may be chosen.':'The candidate has a mismatched pair. Skip it.'};
        if (left >= right) {
          path.push(s.slice(start, end + 1));
          yield* visit(s, end + 1, path, result);
          path.pop();
          yield {a:s.split(''),p:{start},aux:path.slice(0),out:result,vars:{start,end},note:'Remove the last piece and try a different end position.'};
        }
      }
      return 0;
    }
    yield* visit(s, 0, path, result);
    return result;
  },{partitionInput:true,helperTypes:{visit:{return:'int',args:{s:'String',start:'int',path:'List<Object>',result:'List<Object>'}}}});

  function validate(lesson, input) {
    if(lesson.phoneInput&&(typeof input.s!=='string'||!/^[2-9]{0,4}$/.test(input.s)))throw Error('Use up to four digits from 2 to 9.');
    if(lesson.backtrackArray&&input.a?.length>lesson.backtrackArray)throw Error(`Use at most ${lesson.backtrackArray} values for a readable recursion tree.`);
    if(lesson.combinationInput&&(input.target>20||input.a?.length>8))throw Error('Use target at most 20 and at most 8 candidates.');
    if(lesson.partitionInput&&input.s?.length>7)throw Error('Use at most 7 characters for the partition dry run.');
    if(lesson.recursiveN!==undefined&&(!Number.isInteger(input.n)||input.n<0||input.n>lesson.recursiveN))throw Error(`Use n from 0 to ${lesson.recursiveN}.`);
    if(lesson.singleCharacter&&input.t?.length!==1)throw Error('t must be exactly one character.');
    if(lesson.group===10){
      if(lesson.cooldown&&input.k>20)throw Error('Use a cooldown from 0 to 20 for a readable schedule.');
      for(const key of ['stations','projects','courses'])if(key in lesson.example){const rows=input[key];if(!Array.isArray(rows)||rows.length>20||rows.some(row=>!Array.isArray(row)||row.length!==2||row.some(x=>!Number.isInteger(x)||x<0||x>10000)))throw Error('Use at most 20 pairs of nonnegative integers up to 10000.');}
      if(lesson.fuelInput&&(input.fuel<0||input.target<0||input.stations.some((row,i)=>i&&row[0]<input.stations[i-1][0])))throw Error('Use nonnegative fuel/target and stations in position order.');
      if(lesson.projectsInput&&input.capital<0)throw Error('Starting capital must be nonnegative.');
      if(lesson.coursesInput&&input.courses.some(row=>row[0]<1||row[1]<1))throw Error('Course durations and deadlines must be positive.');
      if(lesson.heapDistinctK && input.k>new Set(input.a).size)throw Error('k cannot exceed the number of distinct values.');
      if(lesson.heapWords && (!Array.isArray(input.words)||!input.words.length||input.words.length>30||input.words.some(w=>typeof w!=='string'||!/^[a-z]{1,20}$/.test(w))||input.k>new Set(input.words).size))throw Error('Use 1–30 lowercase words and k no larger than the number of distinct words.');
      if(lesson.heapRows){const rows=input[lesson.heapRows];if(!Array.isArray(rows)||!rows.length||rows.length>8||rows.some(row=>!Array.isArray(row)||!row.length||row.length>8||row.some(x=>!Number.isInteger(x)||Math.abs(x)>10000)))throw Error('Use 1–8 nonempty rows of at most 8 integers within ±10000.');
        if(lesson.heapRows==='points'&&rows.some(row=>row.length!==2))throw Error('Each point needs exactly two coordinates.');
        if(lesson.sortedRows&&rows.some(row=>row.some((x,i)=>i&&x<row[i-1])))throw Error('Each row must be sorted ascending.');
        if(lesson.weakRows&&rows.some(row=>row.some((x,i)=>(x!==0&&x!==1)||(i&&x>row[i-1]))))throw Error('Rows must have ones followed by zeros.');
        const limit=lesson.matrixK?rows.reduce((n,row)=>n+row.length,0):rows.length;
        if(lesson.mode!=='merge'&&(!Number.isInteger(input.k)||input.k<1||input.k>limit))throw Error('k is outside the available range.');
      }
    }
    if (!input || typeof input !== 'object' || Array.isArray(input)) throw Error('Use a JSON object with the fields shown in the example.');
    if(lesson.group===9){
      for(const [key,value] of Object.entries(input))if(typeof value==='number'&&(!Number.isInteger(value)||Math.abs(value)>1000000000))throw Error(`${key} must be an integer within ±1,000,000,000.`);
      for(const key of lesson.positiveParams||[])if(!Number.isInteger(input[key])||input[key]<1)throw Error(`${key} must be a positive integer.`);
      if(lesson.boundChoice&&!['ceiling','upper'].includes(input.bound))throw Error('bound must be "ceiling" (≥ target) or "upper" (> target).');
      if(lesson.matrixInput){
        const matrix=input.matrix;
        if(!Array.isArray(matrix)||!matrix.length||matrix.length>8||!Array.isArray(matrix[0])||!matrix[0].length||matrix[0].length>8||matrix.some(row=>!Array.isArray(row)||row.length!==matrix[0].length||row.some(x=>!Number.isInteger(x)||Math.abs(x)>10000)))throw Error('Use a nonempty rectangular matrix, at most 8 × 8, with integers within ±10000.');
        if(matrix.some(row=>row.some((x,c)=>c&&x<row[c-1])))throw Error('Each matrix row must be sorted.');
        if(lesson.matrixInput==='flat'){
          if(matrix.some((row,r)=>r&&row[0]<=matrix[r-1].at(-1)))throw Error('Each row must start strictly after the previous row ends.');
        }else if(matrix.some((row,r)=>r&&row.some((x,c)=>x<matrix[r-1][c])))throw Error('Each matrix column must also be sorted.');
        if(lesson.matrixInput==='square'&&(matrix.length!==matrix[0].length||!Number.isInteger(input.k)||input.k<1||input.k>matrix.length*matrix[0].length))throw Error('Use a square matrix with k between 1 and its number of cells.');
      }
      if(lesson.multiplicationInput&&(!Number.isInteger(input.m)||!Number.isInteger(input.n)||input.m<1||input.n<1||input.m>8||input.n>8||!Number.isInteger(input.k)||input.k<1||input.k>input.m*input.n))throw Error('For a readable table, use m and n from 1 to 8, and k from 1 to m × n.');
      if(lesson.medianInput&&(!Array.isArray(input.b)||input.b.length>40||input.b.some((x,i)=>!Number.isInteger(x)||Math.abs(x)>10000||(i&&x<input.b[i-1]))||!((input.a?.length||0)+input.b.length)))throw Error('b must be sorted, contain at most 40 integers within ±10000, and at least one array must be nonempty.');
    }
    if(lesson.group>=3)for(const key of ['k','lower','upper'])if(key in input && (!Number.isInteger(input[key]) || Math.abs(input[key])>1000000000))throw Error(`${key} must be an integer between -1,000,000,000 and 1,000,000,000.`);
    if(lesson.rangeBounds && input.lower>input.upper)throw Error('lower must not exceed upper.');
    if(lesson.subList && (!Number.isInteger(input.left)||!Number.isInteger(input.right)||input.left<1||input.right<input.left||input.right>input.a?.length))throw Error('Use 1-based positions with 1 ≤ left ≤ right ≤ list length.');
    if(lesson.brackets && (typeof input.s!=='string'||!/^[()[\]{}]*$/.test(input.s)))throw Error('Use only parentheses and brackets: ()[]{}.');
    if(lesson.lowercase && (typeof input.s!=='string'||!/^[a-z]*$/.test(input.s)))throw Error('Use lowercase letters a–z.');
    if(lesson.digits && (typeof input.s!=='string'||!/^\d+$/.test(input.s)||input.k>input.s.length))throw Error('Use a nonempty digit string and k between zero and its length.');
    if(lesson.absolutePath && (typeof input.s!=='string'||!input.s.startsWith('/')))throw Error('Use an absolute Unix path beginning with /.');
    if(lesson.intervalInput){
      const check=(items,name,disjoint=false)=>{
        if(!Array.isArray(items)||items.length>12)throw Error(`${name} must contain at most 12 intervals.`);
        items.forEach((pair,i)=>{
          if(!Array.isArray(pair)||pair.length!==(lesson.loads?3:2)||pair.some(x=>!Number.isInteger(x)||Math.abs(x)>10000)||pair[0]>pair[1]||(lesson.positiveDuration && pair[0]===pair[1])||(lesson.loads && pair[2]<0))throw Error('Use valid [start, end] intervals (or [start, end, nonnegative load] for CPU); bounds must be integers within -10000 to 10000. Meetings and jobs need positive duration.');
          if(disjoint && i && items[i-1][1]>=pair[0])throw Error(`${name} must be sorted and disjoint, without shared endpoints.`);
        });
      };
      check(input.intervals,'intervals',lesson.disjoint);
      if('other' in lesson.example)check(input.other,'other',true);
      if('newInterval' in lesson.example)check([input.newInterval],'newInterval');
    }
    for (const key of Object.keys(lesson.example)) {
      if (!(key in input)) throw Error(`Missing field: ${key}.`);
      if (typeof lesson.example[key] === 'number' && !Number.isSafeInteger(input[key])) throw Error(`${key} must be a safe integer.`);
    }
    if ('a' in lesson.example) {
      if (!Array.isArray(input.a) || input.a.length > 40 || input.a.some(x=>!Number.isSafeInteger(x) || Math.abs(x)>10000)) throw Error('a must contain at most 40 integers between −10000 and 10000.');
      if (lesson.min && input.a.length < lesson.min) throw Error(`Use at least ${lesson.min} values.`);
      if(lesson.nonnegative && input.a.some(x=>x<0))throw Error('Use only nonnegative array values.');
      if(lesson.unique && new Set(input.a).size!==input.a.length)throw Error('This lesson requires distinct array values.');
      if(lesson.unequalNeighbors && input.a.some((x,i)=>i&&x===input.a[i-1]))throw Error('Adjacent values must differ for strict peak search.');
      if(lesson.rotated && (new Set(input.a).size!==input.a.length||input.a.filter((x,i)=>x>input.a[(i+1)%input.a.length]).length>1))throw Error('Use a rotation of a strictly increasing array with distinct values.');
      if(lesson.mountain){
        const peak=input.a.indexOf(Math.max(...input.a));
        if(peak===0||peak===input.a.length-1||input.a.some((x,i)=>i&&(i<=peak?x<=input.a[i-1]:x>=input.a[i-1])))throw Error('Use a strict mountain with an interior peak and no flat or extra turning points.');
      }
      if(lesson.cows&&(!Number.isInteger(input.k)||input.k<2||input.k>input.a.length))throw Error('Use between 2 and n cows in distinct stalls.');
      if(lesson.exactProducts)for(let left=0;left<input.a.length;left++){
        let product=1;
        for(let right=left;right<input.a.length;right++){
          product*=input.a[right];
          if(!Number.isSafeInteger(product)||product < -2147483648 || product > 2147483647)throw Error('Every subarray product must fit in a signed 32-bit integer, matching the problem constraints.');
        }
      }
      if (lesson.sorted && input.a.some((x,i)=>i && x<input.a[i-1])) throw Error('This lesson requires a sorted array (smallest to largest).');
      if (lesson.binary && input.a.some(x=>x!==0 && x!==1)) throw Error('Use only 0 and 1.');
      if (lesson.colors && input.a.some(x=>x<0 || x>2)) throw Error('Use only 0, 1, and 2.');
      if (lesson.positive && input.a.some(x=>x<=0)) throw Error('Every array value must be a positive integer.');
      if (lesson.nonzero && input.a.some(x=>x===0)) throw Error('Circular jumps must be nonzero.');
      if (lesson.fixedK && (input.k < 1 || input.k > input.a.length)) throw Error('k must be between 1 and the array length.');
      if (lesson.cycle && (input.pos < -1 || input.pos >= input.a.length || (!input.a.length && input.pos !== -1))) throw Error('pos must be −1 (no cycle) or a valid node index.');
      if (lesson.duplicate) {
        const counts = new Map(); input.a.forEach(x=>counts.set(x,(counts.get(x)||0)+1));
        if (input.a.length < 2 || input.a.some(x=>x<1 || x>=input.a.length) || [...counts.values()].filter(x=>x>1).length!==1) throw Error('Use n+1 values in 1…n with exactly one distinct repeated value.');
      }
    }
    for (const key of ['s','t']) if (key in lesson.example) {
      if (typeof input[key] !== 'string' || input[key].length>80 || !/^[\x20-\x7e]*$/.test(input[key])) throw Error(`${key} must be text of at most 80 printable ASCII characters.`);
    }
    if (lesson.uppercase && !/^[A-Z]*$/.test(input.s)) throw Error('Use uppercase letters A–Z for s.');
    if (lesson.nonnegativeK && input.k<0) throw Error('k must be zero or positive.');
    if (lesson.positiveK && input.k<1) throw Error('k must be positive.');
    if (lesson.positiveTarget && input.target<=0) throw Error('target must be positive.');
    if (lesson.id==='subarray-product-less-than-k' && input.k>1000000000) throw Error('For exact numeric dry runs, use k at most 1,000,000,000.');
    if ('n' in lesson.example && (input.n<(lesson.recursiveN!==undefined?0:1) || input.n>1000000000)) throw Error('n is outside the supported range.');
    if (lesson.words && (!Array.isArray(input.words) || !input.words.length || input.words.length>12 || input.words.some(w=>typeof w!=='string' || !w.length || w.length!==input.words[0].length || w.length>20 || !/^[\x20-\x7e]+$/.test(w)))) throw Error('Use 1–12 nonempty words of equal length, up to 20 ASCII characters each.');
    const allowed = Object.keys(lesson.example);
    for (const key of Object.keys(input)) if (!allowed.includes(key)) throw Error(`Unknown field: ${key}. Use only ${allowed.join(', ')}.`);
  }
  function makeTrace(lesson, input) {
    validate(lesson,input);
    const clone = value => structuredClone(value);
    const iterator = lesson.run(clone(input)), frames = [];
    let last = {a:input.a || input.intervals || (input.s || '').split(''),...(lesson.intervalInput?{intervals:input.intervals}:{}),p:{},vars:{},line:1}, returnLine = 1;
    for (let i=0;i<12000;i++) {
      const step = iterator.next();
      if (step.done) {
        frames.push({...last,line:returnLine,result:clone(step.value),done:true,note:'Finished. The result below is the value returned by the solution.'});
        return frames;
      }
      if (step.value.returnLine) { returnLine = step.value.returnLine; continue; }
      const decisions=step.value.decisions===undefined?last.decisions:step.value.decisions;
      last = clone({...step.value,...(decisions?{decisions}:{})});
      frames.push(last);
    }
    throw Error('This input produces too many steps. Try a smaller example.');
  }
  root.PointerLab = {lessons,groups,validate,makeTrace};
  if (typeof module !== 'undefined') module.exports = root.PointerLab;
})(typeof window !== 'undefined' ? window : globalThis);
