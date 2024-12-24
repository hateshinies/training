CREATE TABLE IF NOT EXISTS Employee
(
    id           INT,
    name         VARCHAR(255),
    salary       INT,
    departmentId INT
)
CREATE TABLE IF NOT EXISTS Department
(
    id   INT,
    name VARCHAR(255)
)
TRUNCATE TABLE Employee
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('1', 'Joe', '85000', '1')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('2', 'Henry', '80000', '2')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('3', 'Sam', '60000', '2')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('4', 'Max', '90000', '1')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('5', 'Janet', '69000', '1')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('6', 'Randy', '85000', '1')
INSERT INTO Employee (id, name, salary, departmentId)
VALUES ('7', 'Will', '70000', '1')
TRUNCATE TABLE Department
INSERT INTO Department (id, name)
VALUES ('1', 'IT')
INSERT INTO Department (id, name)
VALUES ('2', 'Sales')

/**
  Руководители компании заинтересованы в том, чтобы узнать, кто зарабатывает больше всего денег
  в каждом из отделов компании. Высокооплачиваемый сотрудник в отделе — это сотрудник,
  чья зарплата входит в тройку лучших уникальных зарплат для этого отдела.

  Напишите решение, чтобы найти высокооплачиваемых сотрудников в каждом из отделов.
 */

  WITH sub AS (SELECT d.name                                                     AS Department,
                      e.name                                                     AS Employee,
                      e.salary                                                   AS Salary,
                      DENSE_RANK() OVER (PARTITION BY d.id ORDER BY salary DESC) AS rank
                 FROM Employee e
                      JOIN department d ON d.id = e.departmentId)
SELECT department as "Department", employee as "Employee", salary as "Salary"
  FROM sub
 WHERE rank < 4
