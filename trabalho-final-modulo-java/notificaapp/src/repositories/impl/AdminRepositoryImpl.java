package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import helpers.ConversorDateHelper;
import models.Denuncia;
import models.Usuario;
import models.enums.*;
import repositories.interfaces.AdminRepository;
import repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminRepositoryImpl extends UsuarioRepositoryImpl implements AdminRepository {
    public Integer getProximoIdDaDenuncia(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_USUARIO.NEXTVAL mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }
    @Override
    public List<Usuario> listarTodosUsuarios(Usuario usuarioLogado) throws DataBaseException {
        if (Objects.nonNull(usuarioLogado) && usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
            List<Usuario> usuarios = new ArrayList<>();
            Connection con = null;
            Statement stmt = null;

            try {
                con = ConexaoBancoDeDados.getConnection();
                stmt = con.createStatement();

                String sql = "SELECT * FROM USUARIO";
                ResultSet res = stmt.executeQuery(sql);

                if (res.next()) {
                    do {
                        int isUsuario = res.getInt("id_usuario");
                        String nome = res.getString("nome_usuario");
                        String numeroCelular = res.getString("celular_usuario");
                        String senha = res.getString("senha_usuario");
                        Etnia etinia = Etnia.fromInt(res.getInt("etnia"));
                        LocalDate dataNascimento = res.getDate("data_nascimento") == null ? null : res.getDate("data_nascimento").toLocalDate();
                        ClasseSocial classeSocial = ClasseSocial.fromInt(res.getInt("classe_social"));
                        Genero genero = Genero.fromInt(res.getInt("genero"));
                        TipoUsuario tipo = TipoUsuario.fromInt(res.getInt("tipo_usuario"));

                        Usuario usuario = new Usuario(isUsuario, nome, numeroCelular, senha, etinia, dataNascimento, classeSocial, genero, tipo);

                        usuarios.add(usuario);
                    } while (res.next());
                } else {
                    System.out.println("A consulta SQL não retornou resultados.");
                }

                return usuarios;
            } catch (SQLException e) {
                throw new DataBaseException("Erro: " + e.getCause());
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new DataBaseException("Acesso negado. Perfil de administrador requerido.");
        }
    }

    public Usuario adicionarUsuario(Usuario u) throws DataBaseException {
        Connection connection = null;
        try {
            connection = ConexaoBancoDeDados.getConnection();

            String sql = """
                    INSERT INTO USUARIO u
                        (id_usuario, nome_usuario, celular_usuario, senha_usuario, data_nascimento, etnia,
                        classe_social, genero, tipo_usuario)
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)""";

            Integer proximoId = this.getProximoIdDaDenuncia(connection);
            u.setIdUsuario(proximoId);

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, u.getIdUsuario());
            stmt.setString(2, u.getNomeUsuario());
            stmt.setString(3, null);
            stmt.setString(4, u.getSenhaUsuario());
            stmt.setDate(5, null);
            stmt.setString(6, String.valueOf(u.getEtniaUsuario().getIdEtnia()));
            stmt.setString(7, String.valueOf(u.getClasseSocial().getIdClasseSocial()));
            stmt.setString(8, String.valueOf(u.getGeneroUsuario().getIdGenero()));
            stmt.setString(9, String.valueOf(u.getTipoUsuario().getIdTipoUsuario()));

            int res = stmt.executeUpdate();
            System.out.println("Usuários cadastrados = " + res);
            return u;
        } catch (SQLException e) {
            throw new DataBaseException("Erro: " + e.getCause());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Denuncia> listarTodasDenuncias(Usuario usuarioLogado) throws DataBaseException {
        if (Objects.nonNull(usuarioLogado) && usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
            List<Denuncia> denuncias = new ArrayList<>();
            Connection con = null;
            Statement stmt = null;

            try {
                con = ConexaoBancoDeDados.getConnection();
                stmt = con.createStatement();

                String sql = "SELECT D.id_denuncia, D.titulo, D.descricao, D.status_denuncia, D.categoria, " +
                        "U.id_usuario, U.nome_usuario " +
                        "FROM DENUNCIA D " +
                        "JOIN USUARIO U ON D.id_usuario = U.id_usuario";

                try (ResultSet res = stmt.executeQuery(sql)) {
                    while (res.next()) {
                        int idUsuario = res.getInt("id_usuario");

                        Denuncia denuncia = new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getString("titulo"),
                                res.getString("descricao"),
                                StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                                Categoria.fromInt(res.getInt("categoria"))
                        );

                        denuncia.setIdUsuario(idUsuario);

                        denuncias.add(denuncia);
                    }
                }

                return denuncias;
            } catch (SQLException e) {
                throw new DataBaseException("Erro: " + e.getCause());
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new DataBaseException("Acesso negado. Perfil de administrador requerido.");
        }
    }

    @Override
    public boolean excluirDenuncia(int idDenuncia) throws DataBaseException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "DELETE FROM DENUNCIA WHERE id_denuncia = ?";
            System.out.println("SQL Executado: " + sql);

            try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, idDenuncia);

                int res = stmt.executeUpdate();
                System.out.println("excluirDenuncia.res=" + res);
                return res > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir denúncia!");
            throw new DataBaseException("Erro: " + e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Denuncia obterDenunciaPorId(int idDenuncia) throws DataBaseException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT u.ID_USUARIO, " +
                    "u.NOME_USUARIO, " +
                    "d.ID_DENUNCIA, " +
                    "d.TITULO, " +
                    "d.DESCRICAO, " +
                    "d.STATUS_DENUNCIA, " +
                    "d.CATEGORIA " +
                    "FROM USUARIO u, DENUNCIA d " +
                    "WHERE d.ID_DENUNCIA = ?" +
                    "AND u.ID_USUARIO = d.ID_USUARIO ";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idDenuncia);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Usuario usuario = new Usuario(res.getInt("id_usuario"), res.getString("nome_usuario"));
                Denuncia denuncia = new Denuncia(
                        res.getInt("id_denuncia"),
                        res.getString("titulo"),
                        res.getString("descricao"),
                        StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                        Categoria.fromInt(res.getInt("categoria")),
                        usuario
                );

                return denuncia;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao obter denúncia por ID: " + e.getCause());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
