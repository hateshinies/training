CREATE TABLE IF NOT EXISTS Seat
(
    id      INT,
    student VARCHAR(255)
)
TRUNCATE TABLE Seat
INSERT INTO Seat (id, student)
VALUES ('1', 'Abbot')
INSERT INTO Seat (id, student)
VALUES ('2', 'Doris')
INSERT INTO Seat (id, student)
VALUES ('3', 'Emerson')
INSERT INTO Seat (id, student)
VALUES ('4', 'Green')
INSERT INTO Seat (id, student)
VALUES ('5', 'Jeames')

SELECT CASE
           WHEN id % 2 = 1 AND id = (SELECT MAX(id) FROM Seat) THEN id
           WHEN id % 2 = 1 THEN id + 1
           WHEN id % 2 = 0 THEN id - 1 END AS id,
       student
  FROM Seat
 ORDER BY id;



