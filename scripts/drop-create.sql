CREATE DATABASE DB_CALL_CENTER;

USE DB_CALL_CENTER;

CREATE TABLE TB_FORNECEDOR (
	CD_FORNECEDOR INT NOT NULL AUTO_INCREMENT,
    NM_FORNECEDOR VARCHAR(255) NOT NULL,
    DS_EMAIL VARCHAR(255) NULL,
    DS_RAZAO_SOCIAL VARCHAR(255) NOT NULL,
    DS_CNPJ VARCHAR(255) NOT NULL,
    PRIMARY KEY(CD_FORNECEDOR)
);