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

-- Inserindo dados na tabela funcionario
INSERT INTO funcionario (nome, cpf, salario, tipo_funcionario, email, senha) VALUES
('João Silva', '12345678901', 2500.00, 'Vendedor', 'joao.silva@email.com', 'senha123'),
('Maria Oliveira', '10987654321', 3000.00, 'Gerente', 'maria.oliveira@email.com', 'senha456');

-- Inserindo dados na tabela produto
INSERT INTO produto (nome, descricao, categoria, validade, status, preco) VALUES
('Produto A', 'Descrição do Produto A', 'Categoria 1', '2025-12-31', 'Disponível', 19.99),
('Produto B', 'Descrição do Produto B', 'Categoria 2', '2025-11-30', 'Indisponível', 29.99);

