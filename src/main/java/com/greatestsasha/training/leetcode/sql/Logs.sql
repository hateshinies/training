CREATE TABLE IF NOT EXISTS Logs
(
    id  INT,
    num INT
)
TRUNCATE TABLE Logs
INSERT INTO Logs (id, num)
VALUES ('1', '1')
INSERT INTO Logs (id, num)
VALUES ('2', '1')
INSERT INTO Logs (id, num)
VALUES ('3', '1')
INSERT INTO Logs (id, num)
VALUES ('4', '2')
INSERT INTO Logs (id, num)
VALUES ('5', '1')
INSERT INTO Logs (id, num)
VALUES ('6', '2')
INSERT INTO Logs (id, num)
VALUES ('7', '2')

SELECT DISTINCT l3.num AS "ConsecutiveNums"
  FROM logs l1
       JOIN logs l2 ON l1.id = l2.id + 1
       JOIN logs l3 ON l1.id = l3.id + 2
 WHERE l1.num = l2.num
   AND l2.num = l3.num

explain ANALYZE select id from Logs where id = 5