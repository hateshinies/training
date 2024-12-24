CREATE TABLE IF NOT EXISTS Employees
(
    employee_id INT,
    name        VARCHAR(20),
    manager_id  INT,
    salary      INT
)
TRUNCATE TABLE Employees
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('3', 'Mila', '9', '60301')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('12', 'Antonella', NULL, '31000')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('13', 'Emery', NULL, '67084')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('1', 'Kalel', '11', '21241')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('9', 'Mikaela', NULL, '5937')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('11', 'Joziah', '6', '28485')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('13', 'Louie', 16, '6801')
INSERT INTO Employees (employee_id, name, manager_id, salary)
VALUES ('17', 'Mylah', '20', '26540')

SELECT e.employee_id
  FROM Employees e
       LEFT JOIN Employees m ON e.manager_id = m.employee_id
 WHERE e.salary < 30000
   AND e.manager_id IS NOT NULL
   AND m.employee_id IS NULL
 ORDER BY e.employee_id