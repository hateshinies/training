CREATE TABLE IF NOT EXISTS Products
(
    product_id  INT,
    new_price   INT,
    change_date DATE
)
TRUNCATE TABLE Products
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('1', '20', '2019-08-17')
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('2', '50', '2019-08-18')
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('1', '30', '2019-08-15')
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('1', '35', '2019-08-16')
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('2', '65', '2019-08-17')
INSERT INTO Products (product_id, new_price, change_date)
VALUES ('3', '20', '2019-08-18')

/**
  Напишите решение для поиска цен всех продуктов на 2019-08-16.
  Предположим, что цена всех продуктов до любого изменения составляет 10.
 */

  WITH sub AS (SELECT product_id,
                      change_date,
                      new_price,
                      RANK() OVER (PARTITION BY product_id ORDER BY change_date DESC) AS date_rank
                 FROM products
                WHERE change_date <= '2019-08-16'
                GROUP BY product_id, change_date, new_price)
SELECT sub.product_id, sub.new_price AS price
  FROM sub
 WHERE sub.date_rank = 1

 UNION ALL

SELECT DISTINCT product_id, '10'::NUMERIC AS price
  FROM products p
 WHERE NOT EXISTS(SELECT * FROM sub WHERE sub.product_id = p.product_id)
   AND p.change_date > '2019-08-16';


(SELECT DISTINCT ON (product_id) product_id, new_price AS price
   FROM Products
  WHERE change_date <= '2019-08-16'
  ORDER BY product_id, change_date DESC)

 UNION ALL

SELECT p.product_id, 10 AS price
  FROM Products p
 GROUP BY p.product_id
HAVING MIN(p.change_date) > '2019-08-16'