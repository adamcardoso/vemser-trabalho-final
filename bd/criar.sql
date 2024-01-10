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