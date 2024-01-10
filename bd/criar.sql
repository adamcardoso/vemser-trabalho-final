CREATE TABLE NOTIFICA.USUARIO (
    idUsuario NUMBER PRIMARY KEY NOT NULL,
    nomeUsuario VARCHAR2(255),
    numeroCelular VARCHAR2(20),
    senhaUsuario VARCHAR2(255),
    etniaUsuario VARCHAR2(50),
    dataNascimento DATE,
    classeSocial VARCHAR2(50),
    generoUsuario VARCHAR2(20),
    tipoUsuario VARCHAR2(50)
);

CREATE SEQUENCE NOTIFICA.SEQ_USUARIO
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE NOTIFICA.DENUNCIA (
    idDenuncia NUMBER PRIMARY KEY NOT NULL,
    descricao VARCHAR2(1000),
    latitude NUMBER,
    longitude NUMBER,
    usuarioId NUMBER,
    dataHora TIMESTAMP,
    statusDenuncia VARCHAR2(20),
    categoria VARCHAR2(20),
    curtidas NUMBER,
    validarDenuncia NUMBER,
    FOREIGN KEY (usuarioId) REFERENCES NOTIFICA.USUARIO(idUsuario)
);

CREATE SEQUENCE NOTIFICA.SEQ_DENUNCIA
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;