CREATE TABLE IF NOT EXISTS Queue
(
    person_id   INT,
    person_name VARCHAR(30),
    weight      INT,
    turn        INT
)
TRUNCATE TABLE Queue
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('5', 'Alice', '250', '1')
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('4', 'Bob', '175', '5')
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('3', 'Alex', '350', '2')
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('6', 'John Cena', '400', '3')
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('1', 'Winston', '500', '6')
INSERT INTO Queue (person_id, person_name, weight, turn)
VALUES ('2', 'Marie', '200', '4')


  WITH RECURSIVE rec AS (SELECT 'name'::VARCHAR AS name, 0 AS sum, 0 AS turns

                          UNION

                         SELECT person_name AS name, sum + weight AS sum, turns + 1 AS turns
                           FROM rec
                                INNER JOIN Queue q ON turn = turns + 1
                          WHERE sum + weight <= 1000)
SELECT name as person_name
  FROM rec
 ORDER BY turns DESC
 LIMIT 1;