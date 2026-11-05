# DSAVisualiz

An offline DSA playground covering **121 lessons across 12 patterns**: two pointers, fast/slow pointers, sliding window, Kadane, prefix sum, merge intervals, in-place linked-list reversal, stacks, hash maps, binary search, heaps, and recursion/backtracking.

The latest additions include 23 binary-search lessons, all 17 requested heap lessons, and all 10 requested recursion/backtracking lessons. Heap diagrams show parent/child relationships and highlight changed positions in orange. Numeric records compare left to right; negative priorities implement max heaps. Recursive lessons show entering a call, returning to its caller, and undoing choices. Larger generated outputs show the latest five entries in the diagram; the final result retains every answer.

The sliding-window median lesson deliberately uses a straightforward two-heap rebuild per window, with its O(nk log k) complexity disclosed. Infinite-array search simulates a finite accessible prefix with infinity beyond the supplied values. The ceiling lesson offers both ceiling (≥ target) and strict upper-bound (> target) modes. Input bounds are deliberately smaller for branching recursion so a dry run remains manageable.

Current-state cards retain readable 18px values rather than shrinking the whole panel. Heap and recursive diagrams use compact blocks to fit a laptop viewport.

The separately requested tree, graph, and dynamic-programming lesson groups are still pending; they are not included in the 121 implemented lessons.

The linked-list lessons change next links while keeping node values fixed, show the current head, and return the values reached from that head. As with the existing linked-list lessons, Python and Java use node IDs and next-link arrays as an explicit teaching representation. Stack lessons show bottom-to-top entries and a labeled top; hash-map lessons highlight changing counts. The next-greater lesson is circular, following the supplied problem link.

Run `node linked-stack-hash.test.js` for the linked-list, stack, and hash-map reference cases. Include it after the other two test scripts with `CODE_MODE_CASES=1` to add its cases to the displayed Python/Java verification.

The 19 newer lessons include monotonic-deque and merge-counting prefix algorithms, interval timelines, and matching pseudocode/Python/Java views. Employee free time accepts all employees’ busy intervals flattened into one `intervals` list. Closed interval lessons merge touching endpoints; meetings and CPU jobs use end-exclusive timing so resources can be reused at the same time.

Run `node new-lessons.test.js` for randomized reference checks of the new lessons. Set `CODE_MODE_CASES=1` when running `node test.js` followed by `node new-lessons.test.js`, then run `python code-modes.test.py` to check both displayed solution languages. Rebuild the portable offline file with `node build.js` after changes.

## Open it

Double-click **DSAVisualiz.html**. This single file contains the whole app and can be copied to any folder or another computer. Use a current Chrome, Edge, Firefox, or Safari browser. No installation, server, account, CDN, external fonts, or internet connection is required. The optional original-problem links require internet.

Alternatively, open **index.html**, keeping it alongside `styles.css`, `focus.css`, `dashboard.css`, `algorithms.js`, `playback.js`, `code-modes.js`, and `app.js`. After editing those source files, `node build.js` regenerates the portable file.

## Learn with it

- Click **☰ Menu → Problems** to browse lessons or search by name or pattern. The menu closes once you choose a lesson. **Tools & details** contains custom input, approach notes, complexity, reference links, keyboard help, and the complete execution log. Close the menu with its close button or Escape.
- **Pseudocode** is the default. The **Learning view** menu above the problem offers **Visualization only**, **Pseudocode**, **Python**, and **Java**. Changing the view pauses playback and preserves your input, speed, and current step.
- **Visualization only** expands the diagram to the full width and hides the entire code/pseudocode panel and its controls. Pointer movement, explanations, variables, results, and playback remain visible. The execution log uses step numbers, and upcoming-step hints do not reveal code. Use the same menu to switch back at any time.
- On laptop screens (900 CSS pixels wide or more), **all four modes fill the viewport**. The explanation, fitted diagram, separately highlighted variables, five-column change log, and bottom playback controls stay visible without page scrolling. Pseudocode/Python/Java add a dedicated code panel. The default focus view follows the active statements automatically; optional full code scrolls inside its panel. The history shows the last five steps with changes, left to right, with old values above new values; use Newer/Older to inspect earlier entries without scrolling. Rewinding hides future changes, and new inputs start a fresh log. Very large examples scale down to fit the available diagram area.
- Pointer badges have separate rows below indices. The **What changed** block shows before/after values; updated state cards also show their previous value, so changes are not indicated by color alone.
- The default focus view shows the current operation beside the visualization. **Show all steps** reveals the complete pseudocode; **Show full code** reveals the complete selected Python or Java solution.
- Extra explanations and custom inputs are under **☰ Menu → Tools & details → Understand the approach & try your own input**.
- Automatic playback explains the upcoming operation before animating it, then holds the resulting state for inspection. Pointer labels glide between positions and show before/after indices. Reduced-motion preferences disable motion.
- Playback defaults to **0.5×** (3.6 seconds per step). Choose **0.1×** (18 seconds), **0.25×** (7.2 seconds), **1×** (1.8 seconds), or **2×** (0.9 seconds). Slow playback leaves time to read and inspect each result; manual Next/Back remains immediate.
- Play the dry run, change speed, or use previous/next to inspect each operation.
- Drag the timeline to rewind to any saved state.
- Watch the highlighted pseudocode, Python, or Java statements, labeled pointers, active window, live variables, and explanation.
- Edit the example as JSON and click **Run dry run**. Invalid inputs show an explanation without replacing the existing run.
- Copy the selected pseudocode or executable Python/Java solution, or expand the execution log to jump to a step. Python exposes `solve(...)`; Java exposes `Solution.solve(...)` with typed input parameters and an `Object` result to support the app’s varied teaching results.

Keyboard: **Space** plays/pauses, **← / →** steps, **R** restarts, **/** focuses search. Shortcuts do not intercept input editing.

## Teaching conventions

- Visualization indices start at zero; Two Sum II returns the required one-based indices.
- Linked lists use stable node IDs and a `next` array so rewiring is visible. `−1` represents null. The spatial order of the nodes remains fixed; the links determine traversal order. List-model storage and returned teaching summaries are excluded from auxiliary-space labels.
- Remove Duplicates includes sorted array, sorted array II, and sorted linked list variants. The duplicated minimum-size-subarray-sum entry shares one lesson.
- GFG's K-distinct lesson uses **exactly K** distinct characters; Fruits into Baskets allows **at most two** types.
- Permutation and anagram examples use `s` for the text and `t` for the pattern.
- The palindrome-list solution demonstrates reversal without restoring the list. Circular-array-loop uses a simple O(n²)-time, O(1)-space Floyd search over starting points; its complexity is explicitly labeled.
- Playback stops at annotated algorithm operations, with closely related statements sometimes grouped into a step. It is a replayable algorithm trace, not a JavaScript debugger or an exported video.
- Examples are intentionally small: arrays up to 40 integers and printable ASCII strings up to 80 characters. This keeps diagrams and dry runs readable. Empty patterns return true for permutation search and no indices for the anagram listing.
- Python and Java solutions and language-independent pseudocode are generated from the same algorithm definitions as the trace, with source-line mappings. The internal browser engine uses JavaScript, which is not offered as solution code. The app animates the equivalent algorithm locally; it does not require or launch Python or Java. Custom JSON is data, never evaluated as code.

## Validate the algorithms

Node.js is only needed for development tests, not for using the app:

```sh
node test.js
node playback.test.js
node new-lessons.test.js
node linked-stack-hash.test.js
node binary-search.test.js
node heap-recursion.test.js
```

The tests compare algorithms to independent brute-force oracles, cover empty and invalid inputs, and verify that the copied code matches the trace result and that older snapshots survive later mutations.

To regenerate language views after algorithm changes, run `node generate-code-modes.js`, then `node build.js`. This developer step needs Python. To validate the displayed solutions, set `CODE_MODE_CASES=1` and run the five algorithm test files in the order shown above (playback is independent), then run `python code-modes.test.py` (requires Python and JDK 17+). The latest full run executes 10,329 cases in Python and compiles and executes the same cases in Java.

## DBMS practice
Open `dbms.html` directly, or choose **DBMS practice** in the DSA header. Keep `dbms.html` next to `DSAVisualiz.html` for navigation between sections. Both pages are self-contained and need no internet or server.

28 SQLite query exercises cover SELECT, filtering, sorting, DISTINCT, NULL, grouping/HAVING, joins, subqueries and DENSE_RANK. Tables and expected results are available beside the editor. Check query (Ctrl/Cmd+Enter) evaluates output on the sample and an additional dataset; incorrect answers reveal progressive exercise hints. These are curated hints, not an online AI service. Drafts and solved progress are saved in browser storage when available.

Queries run in a disposable worker with an 8-second timeout. Each attempt receives a fresh dataset; the practice section accepts one read-only query. Column aliases are ignored, column order and duplicate rows are checked; row order is checked when required by the question. Passing finite datasets is practice feedback, not proof for every possible input.

Developer: `node build.js` builds both pages; `node dbms.test.js` checks grading. SQLite is bundled using sql.js 1.13.0 (`sql-asm.js`), with its license in `SQLJS-LICENSE.txt`. Source: https://github.com/sql-js/sql.js.


### SQL intro and expanded joins
The SQL quick intro button opens an 18-topic guide covering CRUD, SELECT, WHERE, joins, ORDER BY, GROUP BY, HAVING, NULL, CASE, UNION, CTEs, windows and transactions. CRUD examples are reference examples; the question editor remains read-only. The 15 added join exercises use projects and assignments alongside employees and departments. Existing question IDs and drafts are preserved.

### Pseudocode practice
Open `pseudocode.html`, or use the Pseudocode practice link in the DSA/DBMS header. Contains 42 original Accenture-style output questions (14 patterns with three variants each), not official company questions. Topics include AND/OR/XOR, shifts, complement, masks, XOR swaps, nested loops, bit counts and branching. Answers use decimal integers; bitwise operations use signed 32-bit rules and explicit parentheses. Correct answers unlock a dry run, and an explicit reveal button lets learners review without earning solved credit. Drafts and progress are stored locally. Run `node pseudocode.test.js` for the independent expected-output checks. Keep all three generated HTML files together for offline navigation.
