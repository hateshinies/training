Create table If Not Exists Transactions (id int, country varchar(4), state varchar, amount int, trans_date date)
Truncate table Transactions
insert into Transactions (id, country, state, amount, trans_date) values ('121', 'US', 'declined', '1000', '2018-12-18')
insert into Transactions (id, country, state, amount, trans_date) values ('122', 'US', 'declined', '2000', '2018-12-19')
insert into Transactions (id, country, state, amount, trans_date) values ('123', 'US', 'declined', '2000', '2019-01-01')
insert into Transactions (id, country, state, amount, trans_date) values ('124', 'DE', 'declined', '2000', '2019-01-07')

/**
  Напишите SQL-запрос, чтобы найти для каждого месяца и страны количество транзакций
  и их общую сумму, количество одобренных транзакций и их общую сумму.
 */

select to_char(trans_date, 'YYYY-MM') as month,
        country,
        count(amount)  trans_count,
        count(amount) FILTER ( WHERE state = 'approved' )  approved_count,
        sum(amount)  trans_total_amount,
        coalesce(sum(amount) FILTER ( WHERE state = 'approved' ),0) approved_total_amount
from Transactions
group by month, country

