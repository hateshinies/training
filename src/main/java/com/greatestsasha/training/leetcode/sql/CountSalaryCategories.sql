CREATE TABLE IF NOT EXISTS Accounts
(
    account_id INT,
    income     INT
)
TRUNCATE TABLE Accounts
INSERT INTO Accounts (account_id, income)
VALUES ('3', '108939')
INSERT INTO Accounts (account_id, income)
VALUES ('2', '12747')
INSERT INTO Accounts (account_id, income)
VALUES ('8', '87709')
INSERT INTO Accounts (account_id, income)
VALUES ('6', '91796')

SELECT UNNEST(ARRAY ['Low Salary', 'Average Salary', 'High Salary'])                                                                                                                            AS category,
       UNNEST(ARRAY [ SUM(CASE WHEN income < 20000 THEN 1 ELSE 0 END),
           SUM(CASE WHEN income >= 20000 AND income <= 50000 THEN 1 ELSE 0 END),
           SUM(CASE WHEN income > 50000 THEN 1 ELSE 0 END) ]) AS accounts_count
  FROM accounts


SELECT 'Low Salary'                                     AS category,
       SUM(CASE WHEN income < 20000 THEN 1 ELSE 0 END)  AS accounts_count
  FROM Accounts

 UNION

SELECT 'Average Salary'                                                     AS category,
       SUM(CASE WHEN income >= 20000 AND income <= 50000 THEN 1 ELSE 0 END) AS accounts_count
  FROM Accounts

 UNION

SELECT 'High Salary'                                    AS category,
       SUM(CASE WHEN income > 50000 THEN 1 ELSE 0 END)  AS accounts_count
  FROM Accounts