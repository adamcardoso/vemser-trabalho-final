package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;
import repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.time.LocalDate;

public class UsuarioRepositoryImpl implements UsuarioRepository<Integer, Usuario> {
    @Override
    public Integer getProximoIdDoUsuario(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Usuario adicionarUsuario(Usuario usuario) throws DataBaseException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "INSERT INTO USUARIO (id_usuario, nome_usuario, celular_usuario, senha_usuario, etnia, data_nascimento, classe_social, genero, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getNumeroCelular());
            stmt.setString(4, usuario.getSenhaUsuario());
            stmt.setInt(5, usuario.getEtniaUsuario().getIdEtnia());
            stmt.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            stmt.setInt(7, usuario.getClasseSocial().getIdClasseSocial());
            stmt.setInt(8, usuario.getGeneroUsuario().getIdGenero());
            stmt.setInt(9, usuario.getTipoUsuario().getIdTipoUsuario());

            int res = stmt.executeUpdate();

            if (res > 0) {
                return usuario;
            } else {
                throw new DataBaseException("Falha ao adicionar usuário.");
            }

        } catch (SQLException e) {
            throw new DataBaseException(e.getCause());
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

    @Override
    public boolean editarUsuario(Integer id, Usuario usuario) throws DataBaseException {
        return false;
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws DataBaseException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" nome_usuario = ?,");
            sql.append(" celular_usuario = ?,");
            sql.append(" senha_usuario = ?,");
            sql.append(" data_nascimento = ?,");
            sql.append(" etnia = ?,");
            sql.append(" classe_social = ?,");
            sql.append(" genero = ?,");
            sql.append(" tipo_usuario = ?");
            sql.append(" WHERE id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setLong(2, Long.parseLong(usuario.getNumeroCelular()));
            stmt.setString(3, usuario.getSenhaUsuario());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setInt(5, usuario.getEtniaUsuario().getIdEtnia());
            stmt.setInt(6, usuario.getClasseSocial().getIdClasseSocial());
            stmt.setInt(7, usuario.getGeneroUsuario().getIdGenero());
            stmt.setInt(8, usuario.getTipoUsuario().getIdTipoUsuario());
            stmt.setInt(9, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new DataBaseException(e.getCause());
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
    public Usuario listarUsuario(int idUsuario) throws DataBaseException  {
        Usuario usuario = new Usuario();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = String.format("SELECT * FROM USUARIO WHERE id_usuario = '%d'", idUsuario);
            ResultSet res = stmt.executeQuery(sql);

            try {
                while (res.next()) {
                    int isUsuario = res.getInt("id_usuario");
                    String nome = res.getString("nome_usuario");
                    String numeroCelular = res.getString("celular_usuario");
                    String senha = res.getString("senha_usuario");
                    Etnia etinia = Etnia.fromInt(res.getInt("etnia"));
                    LocalDate dataNascimento = res.getDate("data_nascimento").toLocalDate();
                    ClasseSocial classeSocial = ClasseSocial.fromInt(res.getInt("classe_social"));
                    Genero genero = Genero.fromInt(res.getInt("genero"));
                    TipoUsuario tipo = TipoUsuario.fromInt(res.getInt("tipo_usuario"));

                    usuario = new Usuario(isUsuario, nome, numeroCelular, senha, etinia, dataNascimento, classeSocial, genero, tipo);
                }
            } catch (SQLException e) {
                System.out.println("entro try");
                throw new DataBaseException ("Erro: "+ e.getCause());
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario fazerLogin(String nomeUsuario, String senha) throws DataBaseException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM USUARIO WHERE UPPER(TRIM(nome_usuario)) = UPPER(?) AND senha_usuario = ?";
            System.out.println("Consulta SQL executada!");

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, nomeUsuario);
                stmt.setString(2, senha);

                // Utilize execute() em vez de executeQuery()
                boolean resultSet = stmt.execute();

                if (resultSet) {
                    ResultSet res = stmt.getResultSet();

                    try {
                        if (res.next()) {
                            int isUsuario = res.getInt("id_usuario");
                            String nome = res.getString("nome_usuario");
                            String numeroCelular = res.getString("celular_usuario");
                            Etnia etinia = Etnia.fromInt(res.getInt("etnia"));
                            LocalDate dataNascimento = res.getDate("data_nascimento").toLocalDate();
                            ClasseSocial classeSocial = ClasseSocial.fromInt(res.getInt("classe_social"));
                            Genero genero = Genero.fromInt(res.getInt("genero"));
                            TipoUsuario tipo = TipoUsuario.fromInt(res.getInt("tipo_usuario"));

                            Usuario usuario = new Usuario(isUsuario, nome, numeroCelular, senha, etinia, dataNascimento, classeSocial, genero, tipo);

                            System.out.println("Usuário encontrado: " + usuario.getNomeUsuario());

                            return usuario;
                        }
                    } catch (SQLException e) {
                        throw new DataBaseException("Erro: " + e.getCause());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Usuario getUsuarioPorId(Integer idUsuario) throws DataBaseException {
        Connection con = null;

        try {
            String sql =  String.format("SELECT * FROM USUARIO WHERE id_usuario = '%s'", idUsuario);

            con = ConexaoBancoDeDados.getConnection();

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(sql);
            res.next();
            int isUsuario = res.getInt("id_usuario");
            String nome = res.getString("nome_usuario");
            String numeroCelular = res.getString("celular_usuario");
            String senha = res.getString("senha_usuario");
            Etnia etinia = Etnia.fromInt(res.getInt("etnia"));
            LocalDate dataNascimento = res.getDate("data_nascimento").toLocalDate();
            ClasseSocial classeSocial = ClasseSocial.fromInt(res.getInt("classe_social"));
            Genero genero = Genero.fromInt(res.getInt("genero"));
            TipoUsuario tipo = TipoUsuario.fromInt(res.getInt("tipo_usuario"));

            Usuario usuario = new Usuario(isUsuario, nome, numeroCelular, senha, etinia, dataNascimento, classeSocial, genero, tipo);

            return usuario;
        } catch (SQLException e) {
            System.out.println("Erro durante a busca de usuário.");
            return null;
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
    public boolean removerUsuario(Integer id) throws DataBaseException {
        Connection con = null;

        try {
            String sql = String.format("DELETE FROM USUARIO WHERE id_usuario = '%s'", id);

            con = ConexaoBancoDeDados.getConnection();

            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(sql);

            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
}
