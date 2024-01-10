CREATE TABLE NOTIFICA.USUARIO (
    id_usuario NUMBER PRIMARY KEY NOT NULL,
    nome_usuario VARCHAR2(255) NOT NULL,
    numero_celular VARCHAR2(20) NOT NULL,
    senha_usuario VARCHAR2(255) NOT NULL,
    id_etnia NUMBER,
    data_nascimento DATE NOT NULL,
    id_classe_social NUMBER,
    id_genero NUMBER NOT NULL,
    id_tipo_usuario NUMBER NOT NULL,
    FOREIGN KEY (id_etnia) REFERENCES NOTIFICA.ETNIA(id_etnia),
    FOREIGN KEY (id_classe_social) REFERENCES NOTIFICA.CLASSE_SOCIAL(id_classe_social),
    FOREIGN KEY (id_genero) REFERENCES NOTIFICA.GENERO(id_genero),
    FOREIGN KEY (id_tipo_usuario) REFERENCES NOTIFICA.TIPO_USUARIO(id_tipo_usuario)
);

CREATE TABLE NOTIFICA.DENUNCIA (
    id_denuncia NUMBER PRIMARY KEY NOT NULL,
    descricao VARCHAR2(1000),
    latitude NUMBER,
    longitude NUMBER,
    id_usuario NUMBER,
    dataHora TIMESTAMP,
    id_situacao NUMBER,
    id_categoria NUMBER,
    curtidas NUMBER,
    validar_denuncia NUMBER,
    FOREIGN KEY (id_usuario) REFERENCES NOTIFICA.USUARIO(id_usuario),
    FOREIGN KEY (id_situacao ) REFERENCES NOTIFICA.SITUACAO(id_situacao),
    FOREIGN KEY (id_categoria) REFERENCES NOTIFICA.CATEGORIA(id_categoria)
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