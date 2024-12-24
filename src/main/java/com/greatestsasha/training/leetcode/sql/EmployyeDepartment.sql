CREATE TABLE IF NOT EXISTS Employee
(
    employee_id   INT,
    department_id INT,
    primary_flag  VARCHAR
)
TRUNCATE TABLE Employee
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('1', '1', 'N')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('2', '1', 'Y')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('2', '2', 'N')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('3', '3', 'N')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('4', '2', 'N')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('4', '3', 'Y')
INSERT INTO Employee (employee_id, department_id, primary_flag)
VALUES ('4', '4', 'N')


SELECT sub.employee_id, e.department_id
  FROM Employee e
       INNER JOIN (SELECT employee_id FROM Employee GROUP BY employee_id HAVING COUNT(primary_flag) = 1) sub
                  ON e.employee_id = sub.employee_id
 UNION
SELECT employee_id, department_id
  FROM Employee
 WHERE primary_flag = 'Y'

