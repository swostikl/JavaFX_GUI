-- 1. A statement for dropping the previous version of the database, if it exists.
DROP DATABASE IF EXISTS currencyDB;

-- 2. creating the database
CREATE DATABASE currencyDB;

-- USE NEW DATABASE
USE currencyDB;

-- Dropping  CURRENCY table if exist
DROP TABLE IF EXISTS CURRENCY;

-- 3. create the currency table
CREATE TABLE CURRENCY (
    id INT NOT NULL AUTO_INCREMENT,
    abbreviation CHAR (3),
    name VARCHAR (50) NOT NULL,
    rateToUSD DECIMAL(15,3) NOT NULL,
    PRIMARY KEY (id)
);

-- 4. populating the table with data (eight currencies with up-to-date exchange rates in the table.)

INSERT INTO CURRENCY(abbreviation, name, rateToUSD) VALUES
("USD", "United States Dollar", 1.000),
("NPR", "Nepalin Rupia", 141.78),
("INR", "Indian Rupees", 88.64),
("JPY", "Japanese Yen", 149.51),
("CNY", "Chinese Yen", 7.13),
("EUR", "Euro", 0.85),
("CAD", "Canadian Dollar", 1.39),
("GBP", "British Pound", 0.75);

--5. dropping the user account appuser, if it exists.
DROP USER IF EXISTS 'appuser'@'localhost';

--6.creating the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password';

--7.granting the privileges to the user account appuser
GRANT SELECT, INSERT, UPDATE, DELETE ON currencyDB.* TO 'appuser'@'localhost';





