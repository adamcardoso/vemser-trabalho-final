INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'ABERTO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'EM_ANALISE');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'EM_ANDAMENTO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'RESOLVIDO');
INSERT INTO NOTIFICA.SITUACAO (id_situacao, nome) VALUES (NOTIFICA.SEQ_SITUACAO.NEXTVAL, 'FECHADO');

INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'AGUA_POTAVEL');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'SANEAMENTO_BASICO');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'GESTAO_RESIDUOS');
INSERT INTO NOTIFICA.CATEGORIA (id_categoria, nome) VALUES (NOTIFICA.SEQ_CATEGORIA.NEXTVAL, 'EDUCACAO_HIGIENE');

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