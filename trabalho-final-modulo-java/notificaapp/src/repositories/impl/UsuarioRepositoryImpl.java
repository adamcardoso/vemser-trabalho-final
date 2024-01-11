package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.Etnia;
import repositories.interfaces.UsuarioRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository<Integer, Usuario> {
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
    public List<Usuario> listarUsuariosNoBanco() throws DataBaseException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_7.USUARIO";

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
                    usuarios.add(usuario);
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
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
