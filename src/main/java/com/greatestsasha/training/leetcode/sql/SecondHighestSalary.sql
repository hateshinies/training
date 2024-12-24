CREATE TABLE IF NOT EXISTS Employee
(
    id     INT,
    salary INT
)
TRUNCATE TABLE Employee
INSERT INTO Employee (id, salary)
VALUES ('1', '100')
INSERT INTO Employee (id, salary)
VALUES ('2', '200')
INSERT INTO Employee (id, salary)
VALUES ('3', '600')
INSERT INTO Employee (id, salary)
VALUES ('4', '400')
INSERT INTO Employee (id, salary)
VALUES ('5', '100')

/**
  Напишите решение для поиска второй по величине отдельной
  зарплаты из таблицы Employee. Если второй по величине зарплаты нет, верните null
 */

  WITH highest_salary AS (SELECT MAX(salary) AS salary FROM employee)

SELECT MAX(salary) AS "SecondHighestSalary"
  FROM Employee
 WHERE salary < (SELECT salary FROM highest_salary)