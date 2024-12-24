CREATE TABLE IF NOT EXISTS Delivery
(
    delivery_id                 INT,
    customer_id                 INT,
    order_date                  DATE,
    customer_pref_delivery_date DATE
)
TRUNCATE TABLE Delivery
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('1', '1', '2019-08-01', '2019-08-02')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('2', '2', '2019-08-02', '2019-08-02')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('3', '1', '2019-08-11', '2019-08-12')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('4', '3', '2019-08-24', '2019-08-24')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('5', '3', '2019-08-21', '2019-08-22')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('6', '2', '2019-08-11', '2019-08-13')
INSERT INTO Delivery (delivery_id, customer_id, order_date, customer_pref_delivery_date)
VALUES ('7', '4', '2019-08-09', '2019-08-09')

/**
  Если предпочтительная клиентом дата доставки совпадает с датой заказа, то заказ называется немедленным;
  Первый заказ клиента — это заказ с самой ранней датой заказа, который сделал клиент.
  Напишите решение для поиска процента немедленных заказов в первых заказах всех клиентов, округленных до 2 знаков после запятой.
  count(immediate) / count(all first)
 */

SELECT ROUND(COUNT(sub.customer_id) FILTER ( WHERE sub.isFirstImmediate IS TRUE ) * 100. / COUNT(sub.customer_id),
             2) AS immediate_percentage
  FROM (SELECT customer_id,
               MIN(order_date) =
               MIN(order_date) FILTER ( WHERE order_date = customer_pref_delivery_date ) AS isFirstImmediate
          FROM Delivery
         GROUP BY customer_id
         ORDER BY customer_id) sub