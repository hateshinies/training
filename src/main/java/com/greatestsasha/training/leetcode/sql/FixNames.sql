CREATE TABLE IF NOT EXISTS Users
(
    user_id INT,
    name    VARCHAR(40)
)
TRUNCATE TABLE Users
INSERT INTO Users (user_id, name)
VALUES ('1', 'aLice')
INSERT INTO Users (user_id, name)
VALUES ('2', 'bOB')

SELECT user_id, UPPER(LEFT(name, 1)) || LOWER(RIGHT(name, - 1)) AS name
  FROM Users
 ORDER BY user_id