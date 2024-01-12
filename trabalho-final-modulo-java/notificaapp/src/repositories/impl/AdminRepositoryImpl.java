package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl {
    public List<Usuario> listarTodosUsuarios() throws DataBaseException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO";
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
