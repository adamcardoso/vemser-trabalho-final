CREATE TABLE NOTIFICA.USUARIO (
    id_usuario NUMBER PRIMARY KEY NOT NULL,
    nome_usuario VARCHAR2(255) NOT NULL,
    numero_celular VARCHAR2(20) NOT NULL,
    senha_usuario VARCHAR2(255) NOT NULL,
    etnia_usuario VARCHAR2(50),
    data_nascimento DATE NOT NULL,
    classe_social VARCHAR2(50),
    genero_usuario VARCHAR2(20),
    tipo_usuario VARCHAR2(50) NOT NULL
);

CREATE TABLE NOTIFICA.DENUNCIA (
    id_denuncia NUMBER PRIMARY KEY NOT NULL,
    descricao VARCHAR2(1000) NOT NULL,
    latitude NUMBER NOT NULL,
    longitude NUMBER NOT NULL,
    usuario_id NUMBER NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    status_denuncia VARCHAR2(20) NOT NULL,
    categoria VARCHAR2(20) NOT NULL,
    curtidas NUMBER,
    validar_denuncia NUMBER,
    FOREIGN KEY (usuario_id) REFERENCES NOTIFICA.USUARIO(id_usuario)
);

CREATE SEQUENCE NOTIFICA.SEQ_USUARIO
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE SEQUENCE NOTIFICA.SEQ_DENUNCIA
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;