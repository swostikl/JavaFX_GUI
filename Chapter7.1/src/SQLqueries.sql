--1.A query that retrieves all the currencies from the database.
SELECT * FROM currencyDB.CURRENCY;

--2.A query that retrieves the currency with the abbreviation EUR (or other abbreviation, if you don't have EUR in your database).
SELECT * FROM CURRENCY where abbreviation = "EUR";

--3.A query that retrieves the number of currencies in the database.
SELECT COUNT(*) AS CURRENCY_count FROM CURRENCY;

--4.A query that retrieves the currency with the highest exchange rate.
SELECT * FROM CURRENCY ORDER BY rateToUSD DESC LIMIT 1;