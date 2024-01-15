-- Inserir admins na tabela USUARIO
INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'assucena', '51981811001', 'senha123', TO_DATE('15/12/1990', 'DD/MM/YYYY'), '1', '3', '1', '2');

INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'renata', '51981811002', 'senha123', TO_DATE('15/12/1991', 'DD/MM/YYYY'), '2', '4', '1', '2');

INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'anderson', '51981811003', 'senha123', TO_DATE('15/12/1992', 'DD/MM/YYYY'), '3', '3', '0', '2');

INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'adam', '51981811004', 'senha123', TO_DATE('15/12/1993', 'DD/MM/YYYY'), '4', '4', '0', '2');

INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'andrekevin', '51981811005', 'senha123', TO_DATE('15/12/1994', 'DD/MM/YYYY'), '0', '3', '2', '2');

-- ADMIN
INSERT INTO VS_13_EQUIPE_7.USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia, classe_social, genero, tipo_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_USUARIO.NEXTVAL, 'admin', '51981811001', 'admin', TO_DATE('15/12/1990', 'DD/MM/YYYY'), '1', '1', '2', '1');


-- Usuário 1 Denúncia 1 e 2
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 1', 'Descrição da Denúncia 1', CURRENT_TIMESTAMP, '1', '1', '0', '0', '1', '1');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 1', '0', '1', '1');
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 2', 'Descrição da Denúncia 2', CURRENT_TIMESTAMP, '2', '1', '0', '0', '2', '1');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 2', '0', '2', '1');
INSERT INTO VS_13_EQUIPE_7.LOCALIZACAO (id_localização, latitude, longitude, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_LOCALIZACAO.NEXTVAL, '123.456', '789.012', 1, 1);


-- Usuário 2 Denúncia 3 e 4
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 3', 'Descrição da Denúncia 3', CURRENT_TIMESTAMP, '3', '4', '0', '0', '1', '2');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 3', '0', '3', '2');
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 4', 'Descrição da Denúncia 4', CURRENT_TIMESTAMP, '4', '4', '0', '0', '2', '2');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 4', '0', '4', '2');


-- Usuário 3 Denúncia 5 e 6
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 5', 'Descrição da Denúncia 5', CURRENT_TIMESTAMP, '1', '3', '0', '0', '1', '3');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 5', '0', '5', '3');
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 6', 'Descrição da Denúncia 6', CURRENT_TIMESTAMP, '2', '3', '0', '0', '2', '3');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 6', '0', '6', '3');


-- Usuário 4
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 7', 'Descrição da Denúncia 7', CURRENT_TIMESTAMP, '3', '2', '0', '0', '1', '4');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 7', '0', '7', '4');
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 8', 'Descrição da Denúncia 8', CURRENT_TIMESTAMP, '4', '2', '0', '0', '2', '4');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 8', '0', '8', '4');


-- Usuário 5
-- Denúncia 9
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 9', 'Descrição da Denúncia 9', CURRENT_TIMESTAMP, '1', '1', '0', '0', '1', '5');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 9', '0', '9', '5');
INSERT INTO VS_13_EQUIPE_7.DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_DENUNCIA.NEXTVAL, 'Título da Denúncia 10', 'Descrição da Denúncia 10', CURRENT_TIMESTAMP, '1', '1', '0', '0', '2', '5');
INSERT INTO VS_13_EQUIPE_7.COMENTARIO (id_comentario, comentario, curtida, id_denuncia, id_usuario)
VALUES (VS_13_EQUIPE_7.SEQ_COMENTARIO.NEXTVAL, 'Comentário sobre a denúncia 10', '0', '10', '5');


SELECT * FROM VS_13_EQUIPE_7.USUARIO;
SELECT * FROM VS_13_EQUIPE_7.DENUNCIA;


-- Desativa as restrições de chave estrangeira temporariamente
--ALTER TABLE VS_13_EQUIPE_7.COMENTARIO DISABLE CONSTRAINT fk_id_denuncia;
--ALTER TABLE VS_13_EQUIPE_7.LOCALIZACAO DISABLE CONSTRAINT fk_id_denuncia_localizacao;