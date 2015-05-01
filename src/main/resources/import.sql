CREATE DATABASE expensemanager;
USE expensemanager;

CREATE TABLE users (
   ID               		 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   USER_NAME                 VARCHAR(255) NOT NULL,
   PASSWORD                  VARCHAR(255) NOT NULL,
   FIRST_NAME                VARCHAR(255) NOT NULL,
   LAST_NAME                 VARCHAR(255),
   EMAIL_ADDRESS             VARCHAR(255) NOT NULL,
   UNIQUE (USER_NAME),
   UNIQUE (EMAIL_ADDRESS)
);

CREATE TABLE expenses (
   ID               		 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   USER_ID                   INT NOT NULL,
   SHOP                  	 VARCHAR(255) NOT NULL,
   AMOUNT 					 DECIMAL(10,2) NOT NULL,
   SHOPDATE                  DATETIME NOT NULL,
   FOREIGN KEY (USER_ID) REFERENCES users(ID) ON DELETE CASCADE
);

INSERT INTO `expensemanager`.`users`
(`USER_NAME`,
`PASSWORD`,
`FIRST_NAME`,
`LAST_NAME`,
`EMAIL_ADDRESS`)
VALUES
("Guru", "", "Gururaj", "Kulkarni", "guru.nie@gmail.com");

INSERT INTO `expensemanager`.`users`
(`USER_NAME`,
`PASSWORD`,
`FIRST_NAME`,
`LAST_NAME`,
`EMAIL_ADDRESS`)
VALUES
("Pavan", "", "Pavan Kumar", "Chincholikar", "pavannie@gmail.com");

