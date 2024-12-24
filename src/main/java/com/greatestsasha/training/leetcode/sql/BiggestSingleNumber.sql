CREATE TABLE IF NOT EXISTS MyNumbers
(
    num INT
)
TRUNCATE TABLE MyNumbers
INSERT INTO MyNumbers (num)
VALUES ('8')
INSERT INTO MyNumbers (num)
VALUES ('8')
INSERT INTO MyNumbers (num)
VALUES ('3')
INSERT INTO MyNumbers (num)
VALUES ('3')
INSERT INTO MyNumbers (num)
VALUES ('1')
INSERT INTO MyNumbers (num)
VALUES ('4')
INSERT INTO MyNumbers (num)
VALUES ('5')
INSERT INTO MyNumbers (num)
VALUES ('6')

SELECT (SELECT num
          FROM MyNumbers
         GROUP BY num
        HAVING COUNT(num) = 1
         ORDER BY num DESC NULLS LAST
         LIMIT 1) AS num;