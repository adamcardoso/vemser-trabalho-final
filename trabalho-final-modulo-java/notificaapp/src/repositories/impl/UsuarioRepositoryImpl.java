package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;
import repositories.interfaces.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements Repository<Integer, Usuario> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Usuario adicionar(Usuario object) throws DataBaseException {

        return null;
    }

    @Override
    public boolean remover(Integer id) throws DataBaseException {
        return false;
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws DataBaseException {
        return false;
    }

    @Override
    public List<Usuario> listarUsuariosNoBanco() throws DataBaseException  {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            try {
                while (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(res.getInt("id_usuario"));
                    usuario.setNomeUsuario(res.getString("nome_usuario"));
                    usuario.setNumeroCelular(res.getString("numero_celular"));
                    usuario.setSenhaUsuario(res.getString("senha_usuario"));
                    usuario.setEtniaUsuario(Etnia.fromInt(res.getInt("etnia_usuario")));
                    usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                    usuario.setClasseSocial(ClasseSocial.fromInt(res.getInt("classe_social")));
                    usuario.setGeneroUsuario(Genero.fromInt(res.getInt("genero_usuario")));
                    usuario.setTipoUsuario(TipoUsuario.fromInt(res.getInt("tipo_usuario")));
                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                System.out.println("entro try");
                throw new DataBaseException (e.getCause());
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario fazerLogin(String nomeUsuario, String senha) throws DataBaseException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = String.format("SELECT nome_usuario, senha_usuario, tipo_usuario FROM USUARIO WHERE nome_usuario = '%s' AND senha_usuario = '%s'", nomeUsuario, senha);
            ResultSet res = stmt.executeQuery(sql);

            try {
                if (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNomeUsuario(res.getString("nome_usuario"));
                    usuario.setSenhaUsuario(res.getString("senha_usuario"));

                    // Mapear o valor do banco de dados para o enum TipoUsuario
                    int valorTipoUsuario = res.getInt("tipo_usuario");
                    TipoUsuario tipoUsuario = TipoUsuario.fromInt(valorTipoUsuario);
                    usuario.setTipoUsuario(tipoUsuario);

                    return usuario;
                }
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
