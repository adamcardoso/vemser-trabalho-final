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
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository<Integer, Usuario> {
    @Override
    public Integer getProximoIdDoUsuario(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Usuario adicionarUsuario(Usuario object) throws DataBaseException {
        return null;
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
            System.out.println("editarUsuario.res=" + res);

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
                    usuario.setIdUsuario(res.getInt("id_usuario"));
                    usuario.setNomeUsuario(res.getString("nome_usuario"));
                    usuario.setNumeroCelular(res.getString("numero_celular"));
                    usuario.setSenhaUsuario(res.getString("senha_usuario"));
                    usuario.setEtniaUsuario(Etnia.fromInt(res.getInt("etnia_usuario")));
                    usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                    usuario.setClasseSocial(ClasseSocial.fromInt(res.getInt("classe_social")));
                    usuario.setGeneroUsuario(Genero.fromInt(res.getInt("genero_usuario")));
                    usuario.setTipoUsuario(TipoUsuario.fromInt(res.getInt("tipo_usuario")));
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
                            Usuario usuario = new Usuario();
                            usuario.setNomeUsuario(res.getString("nome_usuario"));
                            usuario.setSenhaUsuario(res.getString("senha_usuario"));
                            usuario.setIdUsuario(res.getInt("id_usuario"));
                            int valorTipoUsuario = res.getInt("tipo_usuario");
                            TipoUsuario tipoUsuario = TipoUsuario.fromInt(valorTipoUsuario);
                            usuario.setTipoUsuario(tipoUsuario);

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
    public boolean usuarioExiste(int idUsuario) throws DataBaseException {
        Connection con = null;

        try {
            String sql =  String.format("SELECT id_usuario FROM USUARIO WHERE id_usuario = '%s'", idUsuario);

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
