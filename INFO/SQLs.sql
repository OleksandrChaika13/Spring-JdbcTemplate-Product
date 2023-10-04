--Створення БД
CREATE DATABASE demo_db;

--Створення таблиці
CREATE TABLE IF NOT EXISTS products
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  quota INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id)
);