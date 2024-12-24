CREATE TABLE IF NOT EXISTS Activity
(
    player_id    INT,
    device_id    INT,
    event_date   DATE,
    games_played INT
)
TRUNCATE TABLE Activity
INSERT INTO Activity (player_id, device_id, event_date, games_played)
VALUES ('1', '2', '2016-03-01', '5')
INSERT INTO Activity (player_id, device_id, event_date, games_played)
VALUES ('1', '2', '2016-03-02', '6')
INSERT INTO Activity (player_id, device_id, event_date, games_played)
VALUES ('2', '3', '2017-06-25', '1')
INSERT INTO Activity (player_id, device_id, event_date, games_played)
VALUES ('3', '1', '2016-03-02', '0')
INSERT INTO Activity (player_id, device_id, event_date, games_played)
VALUES ('3', '4', '2018-07-03', '5')

/**
  Напишите решение, чтобы сообщить долю игроков, которые вошли в систему снова на следующий день после дня,
  когда они вошли в систему впервые, округлив ее до 2 знаков после запятой.
  Другими словами, нужно подсчитать количество игроков, которые вошли в систему в течение как минимум двух последовательных дней,
  начиная с даты их первого входа, а затем разделить это число на общее количество игроков. AS fraction
 */

SELECT ROUND(COUNT(a.player_id)::NUMERIC / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
  FROM Activity a
       INNER JOIN (SELECT player_id, MIN(event_date) AS first_date FROM Activity GROUP BY player_id) sub
                  ON sub.first_date = a.event_date - INTERVAL '1 DAY' AND sub.player_id = a.player_id
