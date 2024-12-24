CREATE TABLE IF NOT EXISTS Triangle
(
    x INT,
    y INT,
    z INT
)
TRUNCATE TABLE Triangle
INSERT INTO Triangle (x, y, z)
VALUES ('13', '15', '30')
INSERT INTO Triangle (x, y, z)
VALUES ('10', '20', '15')

SELECT t.*, CASE WHEN x + y > z AND x + z > y AND y + z > x THEN 'YES' ELSE 'NO' END AS triangle
  FROM Triangle t