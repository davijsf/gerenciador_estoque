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



CREATE TABLE fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(20)
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

ALTER TABLE produto
ADD COLUMN fornecedor_id INT,
ADD CONSTRAINT fk_produto_fornecedor
FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id);


CREATE TABLE estoque (
 id INT AUTO_INCREMENT PRIMARY KEY,
 produto_id INT NOT NULL,
 quantidade INT NOT NULL,
 tipo_estoque VARCHAR(50), --
 data_recebimento DATE,
 FOREIGN KEY (produto_id) REFERENCES produto(id)
);

select * from produto;
select * from funcionario;
select * from estoque;
select * from fornecedor;

INSERT INTO fornecedor (nome, cnpj, telefone) VALUES
('Laticínios Bom Leite', '12.345.678/0001-90', '(11) 4002-8922'),
('Hortifruti Verde Vida', '23.456.789/0001-01', '(21) 98888-7777'),
('Distribuidora Central', '34.567.890/0001-12', '(31) 3333-2222'),
('Bebidas Geladas LTDA', '45.678.901/0001-23', '(41) 97777-6666'),
('Padaria Pão Quente', '56.789.012/0001-34', '(51) 3222-1100');


ALTER TABLE funcionario
MODIFY COLUMN tipo_funcionario VARCHAR(60);



