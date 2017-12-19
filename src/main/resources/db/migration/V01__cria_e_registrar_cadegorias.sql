CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL 
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
INSERT INTO categoria (nome) VALUES ('lazer');
INSERT INTO categoria (nome) VALUES ('Trabalho');
INSERT INTO categoria (nome) VALUES ('Outro');
INSERT INTO categoria (nome) VALUES ('Alimentaçao');