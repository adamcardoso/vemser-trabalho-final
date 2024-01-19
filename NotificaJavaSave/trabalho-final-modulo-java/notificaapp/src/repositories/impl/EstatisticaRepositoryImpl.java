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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstatisticaRepositoryImpl {
    public List<Usuario> listarTodosUsuarios() throws DataBaseException {
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
                        LocalDate dataNascimento = res.getDate("data_nascimento").toLocalDate();
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
    }
}
