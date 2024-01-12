CREATE TABLE USUARIO (
	id_usuario NUMBER PRIMARY KEY,
	nome_usuario VARCHAR2(50) NOT NULL,
	celular_usuario VARCHAR2(11),
	senha_usuario VARCHAR2(18) NOT NULL,
	data_nascimento DATE,
	etnia CHAR(1), 
	classe_social CHAR(1),
	genero CHAR(1),
	tipo_usuario CHAR(1)
);


CREATE TABLE DENUNCIA(
	id_denuncia NUMBER PRIMARY KEY,
	titulo VARCHAR2(50) NOT NULL,
	descricao VARCHAR2(255) NOT NULL, 
	data_hora TIMESTAMP NOT NULL,
	status_denuncia CHAR(1), 
	categoria CHAR(1),
	curtida NUMBER,
	validar_denuncia NUMBER,
	tipo_denuncia NUMBER,
	id_usuario NUMBER,
	CONSTRAINT fk_id_usuario FOREIGN KEY(id_usuario) REFERENCES USUARIO (id_usuario)
);

CREATE TABLE COMENTARIO (
	id_comentario NUMBER (30) PRIMARY KEY NOT NULL,
	comentario VARCHAR(255) NOT NULL,
	curtida  NUMBER,
	id_denuncia NUMBER,
	id_usuario NUMBER,
	CONSTRAINT fk_id_denuncia FOREIGN KEY(id_denuncia) REFERENCES DENUNCIA (id_denuncia),
	CONSTRAINT fk_id_usuario_comentario FOREIGN KEY(id_usuario) REFERENCES USUARIO (id_usuario)
);


CREATE TABLE LOCALIZACAO (
	id_localização NUMBER PRIMARY KEY NOT NULL,
	latitude VARCHAR2(9),
	longitude VARCHAR2(9),
	id_denuncia NUMBER,
	CONSTRAINT fk_id_denuncia_localizacao FOREIGN KEY(id_denuncia) REFERENCES DENUNCIA (id_denuncia)
);