CREATE TABLE IF NOT EXISTS Sales
(
    sale_id    INT,
    product_id INT,
    year       INT,
    quantity   INT,
    price      INT
)
CREATE TABLE IF NOT EXISTS Product
(
    product_id   INT,
    product_name VARCHAR(10)
)
TRUNCATE TABLE Sales
INSERT INTO Sales (sale_id, product_id, year, quantity, price)
VALUES ('1', '100', '2008', '10', '5000')
INSERT INTO Sales (sale_id, product_id, year, quantity, price)
VALUES ('2', '100', '2009', '12', '5000')
INSERT INTO Sales (sale_id, product_id, year, quantity, price)
VALUES ('7', '200', '2011', '15', '9000')
TRUNCATE TABLE Product
INSERT INTO Product (product_id, product_name)
VALUES ('100', 'Nokia')
INSERT INTO Product (product_id, product_name)
VALUES ('200', 'Apple')
INSERT INTO Product (product_id, product_name)
VALUES ('300', 'Samsung')

/**
  Напишите решение для выбора идентификатора продукта, года, количества и цены для первого года каждого проданного продукта.
 */
SELECT s.product_id, s.year AS first_year, s.quantity, s.price
  FROM sales s
 WHERE s.sale_id NOT IN (SELECT s1.sale_id
                           FROM sales ss
                                LEFT JOIN Sales s1 ON ss.product_id = s1.product_id AND ss.year < s1.year
                          WHERE s1.sale_id IS NOT NULL)
;
SELECT *
  FROM sales
;
(SELECT product_id
   FROM Sales
  GROUP BY product_id
 HAVING COUNT(product_id) > 1
  UNION
 SELECT product_id
   FROM Sales
  GROUP BY product_id
 HAVING COUNT(product_id) = 1)