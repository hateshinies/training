CREATE TABLE IF NOT EXISTS Patients
(
    patient_id   INT,
    patient_name VARCHAR(30),
    conditions   VARCHAR(100)
)
TRUNCATE TABLE Patients
INSERT INTO Patients (patient_id, patient_name, conditions)
VALUES ('1', 'Daniel', 'YFEV COUGH')
INSERT INTO Patients (patient_id, patient_name, conditions)
VALUES ('2', 'Alice', '')
INSERT INTO Patients (patient_id, patient_name, conditions)
VALUES ('3', 'Bob', 'DIAB100 MYOP')
INSERT INTO Patients (patient_id, patient_name, conditions)
VALUES ('4', 'George', 'ACNE DIAB100')
INSERT INTO Patients (patient_id, patient_name, conditions)
VALUES ('5', 'Alain', 'DIAB201')

/**
  Напишите решение для поиска идентификатора пациента,
  имени пациента и состояний пациентов с диабетом I типа.
  Диабет I типа всегда начинается с префикса DIAB1.
 */


SELECT *
  FROM Patients
 WHERE LEFT(conditions, 5) = 'DIAB1'
    OR conditions LIKE '% DIAB1%'