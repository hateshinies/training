Create table If Not Exists Queries (query_name varchar(30), result varchar(50), position int, rating int)
Truncate table Queries
insert into Queries (query_name, result, position, rating) values (null, 'Golden Retriever', '1', '5')
insert into Queries (query_name, result, position, rating) values (null, 'German Shepherd', '2', '5')
insert into Queries (query_name, result, position, rating) values (null, 'Mule', '200', '1')
insert into Queries (query_name, result, position, rating) values ('Cat', 'Shirazi', '5', '2')
insert into Queries (query_name, result, position, rating) values ('Cat', 'Siamese', '3', '3')
insert into Queries (query_name, result, position, rating) values ('Cat', 'Sphynx', '7', '4')

/**
  Мы определяем качество запроса как:
Среднее значение отношения между рейтингом запроса и его позицией.

Мы также определяем процент плохих запросов как:
Процент всех запросов с рейтингом менее 3.

Напишите решение для нахождения каждого query_name, quality и poor_query_percentage.
Оба значения следует округлить до 2 десятичных знаков.
 */

select query_name,
       round(sum(rating::numeric / position) / count(position), 2) as quality,
       round(count(*) filter ( where rating < 3 ) / count(rating)::numeric * 100.00, 2) as poor_query_percentage
from Queries
where query_name is not null
group by query_name