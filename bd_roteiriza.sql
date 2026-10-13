USE master
GO
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'bd_roteiriza')
    DROP DATABASE bd_roteiriza
GO
CREATE DATABASE bd_roteiriza
GO
USE bd_roteiriza
GO
CREATE TABLE Usuario (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(254) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NULL,
    nivelAcesso VARCHAR(10) NULL,
    dataCadastro SMALLDATETIME NOT NULL DEFAULT GETDATE(),
    statusUsuario VARCHAR(20) NOT NULL DEFAULT 'ATIVO'
);
GO
CREATE TABLE Destino (
    id INT IDENTITY(1,1) PRIMARY KEY,
    estado VARCHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    descricao VARCHAR(1000) NULL,
    melhor_epoca VARCHAR(100) NULL
);
GO
CREATE TABLE Atracao (
    id INT IDENTITY(1,1) PRIMARY KEY,
    destino_id INT NOT NULL REFERENCES Destino(id),
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(1000) NULL,
    categoria VARCHAR(50) NULL,
    preco DECIMAL(10,2) NOT NULL DEFAULT 0,
    faixa_orcamento VARCHAR(20) NOT NULL DEFAULT 'ECONOMICO',
    CHECK (faixa_orcamento IN ('ECONOMICO','MEDIO','LUXO')),
    CHECK (preco >= 0)
);
GO
CREATE TABLE Guia (
    id INT IDENTITY(1,1) PRIMARY KEY,
    destino_id INT NOT NULL REFERENCES Destino(id),
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(1000) NULL,
    telefone VARCHAR(20) NULL,
    email VARCHAR(254) NULL,
    idiomas VARCHAR(150) NULL,
    preco_medio DECIMAL(10,2) NOT NULL DEFAULT 0,
    certificado BIT NOT NULL DEFAULT 0,
    CHECK (preco_medio >= 0)
);
GO
CREATE TABLE Roteiro (
    id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES Usuario(id),
    destino_id INT NOT NULL REFERENCES Destino(id),
    nome VARCHAR(150) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    orcamento_estimado DECIMAL(10,2) NOT NULL DEFAULT 0,
    CHECK (data_fim >= data_inicio)
);
GO
CREATE TABLE RoteiroAtividade (
    id INT IDENTITY(1,1) PRIMARY KEY,
    roteiro_id INT NOT NULL REFERENCES Roteiro(id) ON DELETE CASCADE,
    atracao_id INT NULL REFERENCES Atracao(id),
    guia_id INT NULL REFERENCES Guia(id),
    data DATE NOT NULL,
    horario TIME NULL,
    valor DECIMAL(10,2) NOT NULL DEFAULT 0,
    CHECK (valor >= 0),
    CHECK (atracao_id IS NOT NULL OR guia_id IS NOT NULL)
);
GO
CREATE TABLE AvaliacaoGuia (
    id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES Usuario(id),
    guia_id INT NOT NULL REFERENCES Guia(id) ON DELETE CASCADE,
    nota INT NOT NULL CHECK (nota >= 1 AND nota <= 5),
    comentario VARCHAR(500) NULL,
    data_avaliacao SMALLDATETIME NOT NULL DEFAULT GETDATE(),
    UNIQUE (usuario_id, guia_id)
);
GO
CREATE TABLE AvaliacaoAtracao (
    id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES Usuario(id),
    atracao_id INT NOT NULL REFERENCES Atracao(id) ON DELETE CASCADE,
    nota INT NOT NULL CHECK (nota >= 1 AND nota <= 5),
    comentario VARCHAR(500) NULL,
    data_avaliacao SMALLDATETIME NOT NULL DEFAULT GETDATE(),
    UNIQUE (usuario_id, atracao_id)
);
GO
CREATE TABLE RecuperarSenha (
    id INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(254) NOT NULL,
    codigo CHAR(6) NOT NULL,
    geradoEm SMALLDATETIME NOT NULL DEFAULT GETDATE(),
    expiraEm SMALLDATETIME NOT NULL,
    statusCodigo BIT NOT NULL DEFAULT 1
);
GO
INSERT INTO Usuario (nome, email, senha, telefone, nivelAcesso)
VALUES ('Admin Roteiriza', 'admin@roteiriza.com.br', '$2a$10$Su2UIixHK/L2kAMDQAB1AusVsF6eEzwVoyU/hUBnQKtc78yGO0nzi', '(11) 90000-0000', 'ADMIN');
INSERT INTO Usuario (nome, email, senha, telefone, nivelAcesso)
VALUES ('Maria Silva', 'maria@email.com', '$2a$10$Su2UIixHK/L2kAMDQAB1AusVsF6eEzwVoyU/hUBnQKtc78yGO0nzi', '(11) 98888-1111', 'USER');
GO
INSERT INTO Destino (estado, cidade, descricao, melhor_epoca)
VALUES ('RJ', 'Rio de Janeiro', 'Praias, Cristo e Pao de Acucar.', 'Maio a Setembro');
INSERT INTO Destino (estado, cidade, descricao, melhor_epoca)
VALUES ('BA', 'Porto Seguro', 'Praias e centro historico.', 'Junho a Agosto');
INSERT INTO Destino (estado, cidade, descricao, melhor_epoca)
VALUES ('SP', 'Campos do Jordao', 'Montanha e inverno.', 'Junho a Agosto');
GO
INSERT INTO Atracao (destino_id, nome, descricao, categoria, preco, faixa_orcamento)
VALUES (1, 'Cristo Redentor', 'Corcovado.', 'Mirante', 80, 'MEDIO');
INSERT INTO Atracao (destino_id, nome, descricao, categoria, preco, faixa_orcamento)
VALUES (1, 'Copacabana', 'Praia famosa.', 'Praia', 0, 'ECONOMICO');
INSERT INTO Atracao (destino_id, nome, descricao, categoria, preco, faixa_orcamento)
VALUES (2, 'Taperapua', 'Barracas e barco.', 'Praia', 50, 'ECONOMICO');
INSERT INTO Atracao (destino_id, nome, descricao, categoria, preco, faixa_orcamento)
VALUES (3, 'Teleferico', 'Vista panoramica.', 'Passeio', 120, 'LUXO');
GO
INSERT INTO Guia (destino_id, nome, descricao, telefone, email, idiomas, preco_medio, certificado)
VALUES (1, 'Carlos Souza', 'Guia no Rio ha 10 anos.', '(21) 97777-2222', 'carlos.guia@email.com', 'Portugues, Ingles', 150, 1);
INSERT INTO Guia (destino_id, nome, descricao, telefone, email, idiomas, preco_medio, certificado)
VALUES (2, 'Ana Beatriz', 'Turismo historico.', '(73) 96666-3333', 'ana.guia@email.com', 'Portugues, Espanhol', 120, 0);
GO
INSERT INTO Roteiro (usuario_id, destino_id, nome, data_inicio, data_fim, orcamento_estimado)
VALUES (2, 1, 'Fim de semana no Rio', '2026-11-01', '2026-11-03', 280);
GO
INSERT INTO RoteiroAtividade (roteiro_id, atracao_id, guia_id, data, horario, valor)
VALUES (1, 1, 1, '2026-11-01', '09:00', 230);
INSERT INTO RoteiroAtividade (roteiro_id, atracao_id, guia_id, data, horario, valor)
VALUES (1, 2, NULL, '2026-11-02', '10:00', 50);
GO
INSERT INTO AvaliacaoGuia (usuario_id, guia_id, nota, comentario)
VALUES (2, 1, 5, 'Otimo guia!');
INSERT INTO AvaliacaoAtracao (usuario_id, atracao_id, nota, comentario)
VALUES (2, 1, 5, 'Vista incrivel!');
GO
