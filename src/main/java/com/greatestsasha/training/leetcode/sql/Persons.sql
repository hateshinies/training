Create table If Not Exists Person (Id int, Email varchar(255))
Truncate table Person
insert into Person (id, email) values ('1', 'john@example.com')
insert into Person (id, email) values ('2', 'bob@example.com')
insert into Person (id, email) values ('3', 'john@example.com')
insert into Person (id, email) values ('4', 'john@example.com')
insert into Person (id, email) values ('5', 'bob@example.com')
insert into Person (id, email) values ('6', 'babe@example.com')

/**
  Напишите решение для удаления всех дубликатов email, оставив только одно уникальное email с наименьшим идентификатором.
 */

-- O(n)
DELETE
  FROM Person
 WHERE id NOT IN (SELECT MIN(id) FROM Person GROUP BY email HAVING COUNT(email) >= 1);