CREATE DATABASE cajero;

CREATE TABLE cliente
(
  usuario VARCHAR(20) PRIMARY KEY,
  password VARCHAR(15),
  cuenta INTEGER(10),
  nombre VARCHAR(40)
);

CREATE TABLE cuenta
(
  idcuenta INTEGER(10) PRIMARY KEY,
  saldo FLOAT(15, 2)
);

INSERT INTO cliente (usuario, password, cuenta, nombre)
VALUES ('Usuario 1', 'contrasenia', 1, 'John Doe');

INSERT INTO cuenta (idcuenta, saldo)
VALUES (1, 123456.00);

INSERT INTO cliente (usuario, password, cuenta, nombre)
VALUES ('Usuario 2', 'contrasenia', 2, 'Johna Doe');

INSERT INTO cuenta (idcuenta, saldo)
VALUES (2, 0.00);
