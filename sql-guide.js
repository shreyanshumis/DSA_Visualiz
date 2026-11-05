const sqlGuide = [
 ['CRUD: the four basic operations','Create a row with INSERT, read with SELECT, update with UPDATE, and delete with DELETE. CREATE TABLE defines a table; it is separate from inserting rows.',`CREATE TABLE notes (id INTEGER PRIMARY KEY, body TEXT);
INSERT INTO notes (id, body) VALUES (1, 'Learn SQL');
SELECT id, body FROM notes;
UPDATE notes SET body = 'Practice joins' WHERE id = 1;
DELETE FROM notes WHERE id = 1;`,'UPDATE or DELETE without WHERE affects every row. These examples are reference material; the question editor accepts read-only queries.'],
 ['SELECT and aliases','Choose the columns you want. AS gives a result column a readable label.',`SELECT name AS employee, salary FROM employees;`,'Use explicit columns instead of SELECT * when a question requests a particular output.'],
 ['WHERE, AND, OR, NOT','WHERE filters individual rows before grouping. Use parentheses when mixing AND and OR.',`SELECT name FROM employees
WHERE (city = 'Pune' OR city = 'Delhi') AND salary >= 75000;`,'AND requires both conditions; OR requires at least one.'],
 ['ORDER BY and LIMIT','ORDER BY sorts the result: ASC is ascending, DESC is descending. LIMIT keeps a chosen number of rows.',`SELECT name, salary FROM employees
ORDER BY salary DESC, name ASC LIMIT 3;`,'Without ORDER BY, row order is not guaranteed. Add a tie-breaker for a predictable top N.'],
 ['INNER JOIN','Return only matching rows. Aliases keep column names unambiguous.',`SELECT e.name, d.name FROM employees e
JOIN departments d ON e.department_id = d.id;`,'ON explains how rows match; WHERE filters the joined result.'],
 ['LEFT, RIGHT and FULL JOIN','LEFT preserves every row from the left table; RIGHT preserves the right; FULL preserves both. Missing matches produce NULL.',`SELECT d.name, e.name FROM departments d
LEFT JOIN employees e ON e.department_id = d.id;`,'RIGHT JOIN can be written by swapping tables in a LEFT JOIN. The bundled SQLite supports FULL OUTER JOIN.'],
 ['ON versus WHERE','For a LEFT JOIN, filters in ON restrict matches while preserving left rows. A WHERE filter on the right table may discard unmatched rows.',`SELECT d.name, COUNT(e.id) FROM departments d
LEFT JOIN employees e
  ON e.department_id = d.id AND e.salary >= 80000
GROUP BY d.id, d.name;`,'COUNT(e.id) gives zero for an empty department. COUNT(*) would count its preserved row.'],
 ['Self join and CROSS JOIN','A self join uses two aliases for the same table. CROSS JOIN returns every possible combination.',`SELECT a.name, b.name FROM employees a
JOIN employees b ON a.department_id = b.department_id
  AND a.id < b.id;
-- All possible employee/project pairs:
SELECT e.name, p.name FROM employees e CROSS JOIN projects p;`,'a.id < b.id avoids matching an employee to themselves and avoids mirrored pairs.'],
 ['GROUP BY and aggregates','Group rows into summaries. COUNT, SUM, AVG, MIN and MAX calculate values for each group.',`SELECT department_id, COUNT(*) AS people, AVG(salary) AS average
FROM employees WHERE department_id IS NOT NULL
GROUP BY department_id;`,'COUNT(*) counts rows; COUNT(column) excludes NULL. Select grouped columns or aggregates for portable SQL.'],
 ['HAVING','HAVING filters completed groups. WHERE filters the input rows.',`SELECT department_id, COUNT(*) FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id HAVING COUNT(*) >= 2;`,'A useful logical order: FROM/JOIN → WHERE → GROUP BY → HAVING → SELECT → DISTINCT → ORDER BY → LIMIT.'],
 ['NULL and COALESCE','NULL means missing or unknown. Test with IS NULL or IS NOT NULL. COALESCE picks the first non-null value.',`SELECT name, COALESCE(department_id, -1) FROM employees
WHERE department_id IS NULL;`,'= NULL is not a true comparison. Aggregates such as SUM ignore NULL values.'],
 ['DISTINCT, IN, BETWEEN, LIKE','DISTINCT removes duplicate result rows. IN checks membership. BETWEEN includes both boundaries. LIKE matches a text pattern.',`SELECT DISTINCT city FROM employees;
SELECT name FROM employees WHERE city IN ('Pune', 'Delhi');
SELECT name FROM employees WHERE salary BETWEEN 60000 AND 80000;
SELECT name FROM employees WHERE name LIKE 'A%';`,'% matches any number of characters; _ matches one character. Text case behaviour depends on database settings.'],
 ['Subqueries and EXISTS','A subquery computes a value or row set. EXISTS checks whether at least one matching row exists.',`SELECT name FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
SELECT e.name FROM employees e
WHERE NOT EXISTS (SELECT 1 FROM assignments a WHERE a.employee_id=e.id);`,'NOT EXISTS is useful for anti joins. NOT IN can behave unexpectedly when its subquery returns NULL.'],
 ['CASE expressions','Choose a value using conditions.',`SELECT name, CASE WHEN salary >= 80000 THEN 'High'
WHEN salary >= 60000 THEN 'Medium' ELSE 'Lower' END AS band
FROM employees;`,'The first true WHEN wins. CASE is an expression, so it can also be used inside aggregates.'],
 ['UNION and UNION ALL','Combine compatible result sets vertically. UNION removes duplicate rows; UNION ALL keeps them.',`SELECT name FROM employees
UNION ALL
SELECT name FROM departments;`,'Both queries must return the same number of columns in corresponding positions. Joins combine columns; UNION combines rows.'],
 ['WITH: common table expressions','Name an intermediate query to make a larger query easier to read.',`WITH averages AS (
 SELECT department_id, AVG(salary) AS average FROM employees GROUP BY department_id
)
SELECT * FROM averages WHERE average > 70000;`,'A CTE exists only within the statement. It is not a permanent table.'],
 ['Window functions','Calculate across related rows without collapsing them into a single grouped row.',`SELECT name, department_id,
 DENSE_RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rank
FROM employees WHERE department_id IS NOT NULL;`,'ROW_NUMBER gives unique positions; RANK leaves gaps after ties; DENSE_RANK does not.'],
 ['Transactions and constraints','A transaction groups changes. COMMIT saves them; ROLLBACK cancels them. PRIMARY KEY, UNIQUE, NOT NULL, CHECK and FOREIGN KEY constrain data.',`BEGIN;
UPDATE employees SET salary = salary + 1000 WHERE id = 1;
ROLLBACK;`,'These write examples are for a disposable database, outside the read-only question editor.']
];
