package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import helpers.ConversorDateHelper;
import models.Denuncia;
import models.Usuario;
import models.enums.Categoria;
import models.enums.StatusDenuncia;
import repositories.interfaces.DenunciaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DenunciaRepositoryImpl implements DenunciaRepository<Integer, Denuncia> {
    @Override
    public Integer getProximoIdDaDenuncia(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_PESSOA2.NEXTVAL mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Denuncia adicionar(Denuncia d) throws DataBaseException {
        Connection connection = null;
        try {
            connection = ConexaoBancoDeDados.getConnection();

            String sql = "INSERT INTO DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Integer proximoId = this.getProximoIdDaDenuncia(connection);
            d.setIdDenuncia(proximoId);

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, d.getIdDenuncia());
            stmt.setString(2, d.getTitulo());
            stmt.setString(3, d.getDescricao());
            stmt.setDate(4, ConversorDateHelper.LocalDateTimeToDate(d.getDataHora()));
            stmt.setString(5, String.valueOf(d.getStatusDenuncia().getIdStatusDenuncia()));
            stmt.setString(6, String.valueOf(d.getCategoria().getIdCategoria()));
            stmt.setInt(7, d.getCurtidas());
            stmt.setInt(8, d.getValidarDenuncia());
            stmt.setString(9, String.valueOf(d.getTipoDenuncia().getIdTipoDenuncia()));
            stmt.setLong(10, d.getIdUsuario());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPessoa.res=" + res);
            return d;
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
    public boolean removerDenuncia(Integer idDenuncia) throws DataBaseException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "DELETE FROM DENUNCIA WHERE id_denuncia = ?";
            System.out.println("SQL Executado: " + sql);

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, idDenuncia);

                int res = stmt.executeUpdate();
                System.out.println("removerDenunciaPorId.res=" + res);
                return res > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover denúncia!");
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
    public boolean editar(Integer id, Denuncia denuncia) throws DataBaseException {

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DENUNCIA SET ");
            sql.append(" descricao = ?,");
            sql.append(" categoria = ?,");
            sql.append(" titulo = ?");
            sql.append(" WHERE id_denuncia = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, denuncia.getDescricao());
            stmt.setInt(2, denuncia.getCategoria().getIdCategoria());
            stmt.setString(3, denuncia.getTitulo());
            stmt.setInt(4, id);


            // Executa-se a consulta
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new DataBaseException("Erro: " + e.getCause());
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
    public Usuario listarUsuarioDaDenuncia(int idUsuario) throws DataBaseException {
        return null;
    }

    public List<Denuncia> obterTodos() {
        Connection connection = null;
        try {
            connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM DENUNCIA";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            List<Denuncia> denuncias = new ArrayList<>();

            while (res.next()) {
                denuncias.add(new Denuncia(
                        res.getInt("id_denuncia"),
                        res.getString("titulo"),
                        res.getString("descricao"),
                        StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                        Categoria.fromInt(res.getInt("categoria"))
                ));
            }


            return denuncias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
