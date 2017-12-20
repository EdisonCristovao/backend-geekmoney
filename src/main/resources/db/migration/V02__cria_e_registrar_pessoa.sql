CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(20),
    cep VARCHAR(20),
    cidade VARCHAR(20),
    estado VARCHAR(20),
    ativo BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'Edison', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'mayk', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'Carinha', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'nen sei quem', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'Bety', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'Feios', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'o carinha ali', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'esse é o cara', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'outro cara', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'DW', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ( 'AAAa', 'Rua longe', '1222', 'casa', 'Serraria', '88888888', 'São jose', 'Sc', FALSE);