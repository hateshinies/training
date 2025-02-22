CREATE TABLE IF NOT EXISTS Teacher
(
    teacher_id INT,
    subject_id INT,
    dept_id    INT
)
TRUNCATE TABLE Teacher
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('1', '2', '3')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('1', '2', '4')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('1', '3', '3')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('2', '1', '1')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('2', '2', '1')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('2', '3', '1')
INSERT INTO Teacher (teacher_id, subject_id, dept_id)
VALUES ('2', '4', '1')

SELECT teacher_id, count(DISTINCT subject_id)
  FROM Teacher
  GROUP BY teacher_id
