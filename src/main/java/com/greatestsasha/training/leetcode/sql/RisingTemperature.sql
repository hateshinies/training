--/Write a solution to find all dates' id with higher temperatures compared to its previous dates (yesterday).
create table Weather
(
    id          int,
    recordDate date,
    temperature int
);
insert into Weather
values (4, now(), 30);
select *
from Weather;


SELECT id
FROM Weather curr
WHERE EXISTS
          (SELECT *
           FROM Weather prev
           WHERE curr.recordDate - INTERVAL '1 DAY' = prev.recordDate
             AND curr.temperature > prev.temperature);