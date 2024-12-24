CREATE TABLE IF NOT EXISTS Movies
(
    movie_id INT,
    title    VARCHAR(30)
)
CREATE TABLE IF NOT EXISTS Users
(
    user_id INT,
    name    VARCHAR(30)
)
CREATE TABLE IF NOT EXISTS MovieRating
(
    movie_id   INT,
    user_id    INT,
    rating     INT,
    created_at DATE
)
TRUNCATE TABLE Movies
INSERT INTO Movies (movie_id, title)
VALUES ('1', 'Avengers')
INSERT INTO Movies (movie_id, title)
VALUES ('2', 'Frozen 2')
INSERT INTO Movies (movie_id, title)
VALUES ('3', 'Joker')
INSERT INTO Movies (movie_id, title)
VALUES ('4', 'Her')
INSERT INTO Movies (movie_id, title)
VALUES ('5', 'Batman')
INSERT INTO Movies (movie_id, title)
VALUES ('6', 'Interstellar')
TRUNCATE TABLE Users
INSERT INTO Users (user_id, name)
VALUES ('1', 'Daniel')
INSERT INTO Users (user_id, name)
VALUES ('2', 'Monica')
INSERT INTO Users (user_id, name)
VALUES ('3', 'Maria')
INSERT INTO Users (user_id, name)
VALUES ('4', 'James')
INSERT INTO Users (user_id, name)
VALUES ('5', 'Anna')
TRUNCATE TABLE MovieRating
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('1', '1', '3', '2020-01-12')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('1', '2', '4', '2020-02-11')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('1', '3', '2', '2020-02-12')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('1', '4', '1', '2020-01-01')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('2', '1', '5', '2020-02-17')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('2', '2', '2', '2020-02-01')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('2', '3', '2', '2020-03-01')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('3', '1', '3', '2020-02-22')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('3', '2', '4', '2020-02-25')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('2', '5', '5', '2020-02-25')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('1', '5', '6', '2020-02-25')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('3', '5', '6', '2020-02-25')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('4', '5', '6', '2020-02-25')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('5', '5', '6', '2020-02-21')
INSERT INTO MovieRating (movie_id, user_id, rating, created_at)
VALUES ('6', '5', '6', '2020-02-21')


/**
  Найдите имя пользователя, который оценил наибольшее количество фильмов.
  В случае ничьей верните лексикографически меньшее имя пользователя.

  Найдите название фильма с самым высоким средним рейтингом в феврале 2020 года.
  В случае ничьей верните лексикографически меньшее название фильма.
 */

(WITH maxx AS (SELECT COUNT(movie_id) FROM MovieRating GROUP BY user_id ORDER BY count DESC LIMIT 1)
 --
SELECT u.name AS results
 FROM (SELECT user_id AS id
         FROM movierating mr
        GROUP BY user_id
       HAVING COUNT(mr.user_id) = (SELECT count FROM maxx)) sub
 left JOIN users u on u.user_id = sub.id
   )

 UNION ALL

(WITH avg_rat AS (SELECT AVG(rating) AS avg
                    FROM MovieRating
                   WHERE created_at BETWEEN DATE('2020-02-01') AND DATE('2020-02-29')
                   GROUP BY movie_id
                   ORDER BY avg DESC
                   LIMIT 1)

SELECT m.title
 FROM MovieRating mr
      JOIN Movies m ON m.movie_id = mr.movie_id
WHERE mr.created_at BETWEEN DATE('2020-02-01') AND DATE('2020-02-29')
GROUP BY m.title
HAVING AVG(mr.rating) = (SELECT avg FROM avg_rat)
ORDER BY title
LIMIT 1)


begin;
SELECT user_id, COUNT(user_id) AS count
  FROM MovieRating
 GROUP BY user_id
 ORDER BY user_id
end;