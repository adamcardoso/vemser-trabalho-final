package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;
import models.enums.*;
import repositories.interfaces.AdminRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminRepositoryImpl implements AdminRepository {


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
        } else {
            throw new DataBaseException("Acesso negado. Perfil de administrador requerido.");
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

                String sql = "SELECT * FROM DENUNCIA";
                try (ResultSet res = stmt.executeQuery(sql)) {
                    while (res.next()) {
                        Denuncia denuncia = new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getString("titulo"),
                                res.getString("descricao"),
                                StatusDenuncia.getEnum(res.getString("status_denuncia"))
                        );
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
}
