-- Apagar o banco de dados se existir
DROP DATABASE IF EXISTS jdbc_fx;

-- Criar o banco de dados
CREATE DATABASE jdbc_fx;

-- Selecionar o banco de dados criado
USE jdbc_fx;

-- Criar a tabela department
CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  Balance double DEFAULT 0,
  PRIMARY KEY (Id)
);

-- Criar a tabela funcionarios
CREATE TABLE funcionarios (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (Id)
);

-- Inserir dados na tabela department
INSERT INTO department (Name) VALUES 
  ('Marketing'),
  ('Vendas'),
  ('Recursos Humanos');

-- Inserir dados na tabela funcionarios
INSERT INTO funcionarios (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.co
