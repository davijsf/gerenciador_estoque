create database estoque;
use estoque;

CREATE TABLE funcionario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  cpf CHAR(11) NOT NULL UNIQUE,
  salario DECIMAL(10,2) NOT NULL,
  tipo_funcionario VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  senha VARCHAR(60) NOT NULL
);


CREATE TABLE produto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao TEXT,
  categoria VARCHAR(30),
  validade DATE,
  status VARCHAR(10),
  preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE estoque (
  id INT AUTO_INCREMENT PRIMARY KEY,
  produto_id INT NOT NULL,
  quantidade INT NOT NULL,
  tipo_estoque VARCHAR(50), -- 
  data_recebimento DATE,
  FOREIGN KEY (produto_id) REFERENCES produto(id)
);

