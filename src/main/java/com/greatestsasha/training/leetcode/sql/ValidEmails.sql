CREATE TABLE IF NOT EXISTS Users
(
    user_id INT,
    name    VARCHAR(30),
    mail    VARCHAR(50)
)
TRUNCATE TABLE Users
INSERT INTO Users (user_id, name, mail)
VALUES ('1', 'Winston', 'winston@leetcode.com')
INSERT INTO Users (user_id, name, mail)
VALUES ('2', 'Jonathan', 'jonathanisgreat')
INSERT INTO Users (user_id, name, mail)
VALUES ('3', 'Annabelle', 'bella-@leetcode.com')
INSERT INTO Users (user_id, name, mail)
VALUES ('4', 'Sally', 'sally.come@leetcode.com')
INSERT INTO Users (user_id, name, mail)
VALUES ('5', 'Marwan', 'quarz#2020@leetcode.com')
INSERT INTO Users (user_id, name, mail)
VALUES ('6', 'David', 'david69@gmail.com')
INSERT INTO Users (user_id, name, mail)
VALUES ('7', 'Shapiro', '.shapo@leetcode.com')

/**
  Найти пользователей с действительными адресами электронной почты.
  Действительный адрес электронной почты имеет префиксное имя и домен, где:
  Префиксное имя — это строка, которая может содержать буквы (в верхнем или нижнем регистре),
  цифры, подчеркивание «_», точку «.» и/или тире «-». Префиксное имя должно начинаться с буквы.
Домен — «@leetcode.com».
 */

SELECT *
  FROM Users
 WHERE mail ~* '^[a-z][\w\d_.-]*@leetcode\.com$'
