Create table If Not Exists Employees(employee_id int, name varchar(20), reports_to int, age int)
Truncate table Employees
insert into Employees (employee_id, name, reports_to, age) values ('9', 'Hercy', NULL, '43')
insert into Employees (employee_id, name, reports_to, age) values ('6', 'Alice', '9', '41')
insert into Employees (employee_id, name, reports_to, age) values ('4', 'Bob', '9', '36')
insert into Employees (employee_id, name, reports_to, age) values ('2', 'Winston', NULL, '37')

SELECT m.employee_id, m.name, COUNT(e.employee_id) AS reports_count, ROUND(AVG(e.age)) AS average_age
  FROM Employees e
       INNER JOIN Employees m ON m.employee_id = e.reports_to
 GROUP BY m.employee_id, m.name
 ORDER BY m.employee_id;

/**
Напишите решение для вывода идентификаторов и имен всех менеджеров,
  количества сотрудников, которые подчиняются им напрямую,
  и среднего возраста подчиненных, округленного до ближайшего целого числа.
ORDER BY employee_id.
 */
SELECT employee_id, name, reports_count, average_age
  FROM Employees