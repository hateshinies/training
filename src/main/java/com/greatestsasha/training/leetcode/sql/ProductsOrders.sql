CREATE TABLE IF NOT EXISTS Products
(
    product_id       INT,
    product_name     VARCHAR(40),
    product_category VARCHAR(40)
)
CREATE TABLE IF NOT EXISTS Orders
(
    product_id INT,
    order_date DATE,
    unit       INT
)
TRUNCATE TABLE Products
INSERT INTO Products (product_id, product_name, product_category)
VALUES ('1', 'Leetcode Solutions', 'Book')
INSERT INTO Products (product_id, product_name, product_category)
VALUES ('2', 'Jewels of Stringology', 'Book')
INSERT INTO Products (product_id, product_name, product_category)
VALUES ('3', 'HP', 'Laptop')
INSERT INTO Products (product_id, product_name, product_category)
VALUES ('4', 'Lenovo', 'Laptop')
INSERT INTO Products (product_id, product_name, product_category)
VALUES ('5', 'Leetcode Kit', 'T-shirt')
TRUNCATE TABLE Orders
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('1', '2020-02-05', '60')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('1', '2020-02-10', '70')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('2', '2020-01-18', '30')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('2', '2020-02-11', '80')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('3', '2020-02-17', '2')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('3', '2020-02-24', '3')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('4', '2020-03-01', '20')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('4', '2020-03-04', '30')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('4', '2020-03-04', '60')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('5', '2020-02-25', '50')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('5', '2020-02-27', '50')
INSERT INTO Orders (product_id, order_date, unit)
VALUES ('5', '2020-03-01', '50')

/**
  Напишите решение, чтобы получить названия товаров,
  заказанных в феврале 2020 года в количестве не менее 100 единиц, и их количество.
 */

SELECT product_name, SUM(unit) AS unit
  FROM products p
       INNER JOIN Orders o ON o.product_id = p.product_id AND
                              order_date BETWEEN DATE('2020-02-01') AND DATE('2020-03-01') - INTERVAL '1 DAY'
 GROUP BY product_name
HAVING SUM(unit) >= 100;