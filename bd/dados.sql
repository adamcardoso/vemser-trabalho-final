INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'ABERTO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'EM_ANALISE');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'EM_ANDAMENTO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'RESOLVIDO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'FECHADO');

INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'AGUA_POTAVEL');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'SANEAMENTO_BASICO');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'GESTAO_RESIDUOS');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'EDUCACAO_HIGIENE');

INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'João Silva', '11987654321', 'Senha123', 'INDIGENA', TO_DATE('01-01-1990', 'DD-MM-YYYY'), 'C', 'MASCULINO', 'ADMIN');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Maria Santos', '11912345678', 'Senha456', 'PARDO', TO_DATE('02-02-1992', 'DD-MM-YYYY'), 'C', 'FEMININO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Paulo Costa', '11911223344', 'Senha789', 'PRETO', TO_DATE('03-03-1988', 'DD-MM-YYYY'), 'D', 'MASCULINO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Ana Pereira', '11999887766', 'Senha012', 'PRETO', TO_DATE('04-04-1994', 'DD-MM-YYYY'), 'E', 'OUTRO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario5', '11911111111', 'Senha111', 'AMARELO', TO_DATE('05-05-1995', 'DD-MM-YYYY'), 'A', 'MASCULINO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario6', '11922222222', 'Senha222', 'BRANCO', TO_DATE('06-06-1996', 'DD-MM-YYYY'), 'B', 'FEMININO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario7', '11933333333', 'Senha333', 'PARDO', TO_DATE('07-07-1997', 'DD-MM-YYYY'), 'C', 'MASCULINO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario8', '11944444444', 'Senha444', 'PRETO', TO_DATE('08-08-1998', 'DD-MM-YYYY'), 'D', 'FEMININO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario9', '11955555555', 'Senha555', 'INDIGENA', TO_DATE('09-09-1999', 'DD-MM-YYYY'), 'E', 'OUTRO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.USUARIO (idUsuario, nomeUsuario, numeroCelular, senhaUsuario, etniaUsuario, dataNascimento, classeSocial, generoUsuario, tipoUsuario)
VALUES (NOTIFICA.SEQ_USUARIO.NEXTVAL, 'Usuario10', '11966666666', 'Senha666', 'BRANCO', TO_DATE('10-10-2000', 'DD-MM-YYYY'), 'A', 'MASCULINO', 'INDIVIDUAL');
INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Falta de água, há mais de 5 dias!', 24.5558, 48.6396, 1, CURRENT_TIMESTAMP, 1, 1, 9, 2
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Desperdício de água pelo meu vizinho o dia todo', -20.5558, 44.6396, 2, CURRENT_TIMESTAMP, 2, 4, 12, 4
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Lixo sendo jogado no Rio Tijuca', 21.5558, -45.6396, 3, CURRENT_TIMESTAMP, 3, 3, 3, 12
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Empresa só faz o paliativo, esgoto precário e continua vazamento de esgoto', -22.5558, -46.6396, 4, CURRENT_TIMESTAMP, 4, 2, 15, 14
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Depois que fizeram obras na região não consigo mais receber a água', 30.5558, 50.6396, 5, CURRENT_TIMESTAMP, 5, 1, 30, 8
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Cisterna vazia há 2 meses. Solicitei caminhão pipa e nada até o momento', -33.5558, 55.6396, 6, CURRENT_TIMESTAMP, 1, 1, 6, 16
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Água de esgoto, sendo despejada no rio, que absurdo!', 36.5558, -56.6396, 7, CURRENT_TIMESTAMP, 2, 2, 24, 22
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Mau cheiro de esgoto na rua toda depois das obras realizadas no bairro', -34.5558, -54.6396, 8, CURRENT_TIMESTAMP, 3, 2, 21, 20
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Hoje faz 9 dias que estou sem água devido um vazamento na rua', 16.5558, 18.6396, 9, CURRENT_TIMESTAMP, 4, 1, 27, 24
);

INSERT INTO NOTIFICA.DENUNCIA ( 
 	    id_denuncia, descricao, latitude, longitude, id_usuario, dataHora, id_situacao, id_categoria, curtidas, validar_denuncia
) VALUES (
	NOTIFICA.SEQ_DENUNCIA.NEXTVAL, 'Corte do fornecimento de água há 3 dias, sem previsão para voltar', -12.5558, 14.6396, 10, CURRENT_TIMESTAMP, 5, 1, 18, 6
);