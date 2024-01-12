package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import helpers.ConversorDateHelper;
import models.Denuncia;
import models.Usuario;
import repositories.interfaces.Repository;

import java.sql.*;

public class DenunciaRepositoryImpl implements Repository<Integer, Denuncia> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_pessoa2.nextval mysequence from DUAL";

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

            String sql = """
                    INSERT INTO denuncia d (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario)
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            Integer proximoId = this.getProximoId(connection);
            d.setIdDenuncia(proximoId);

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, d.getIdDenuncia());
            stmt.setString(2, d.getTitulo());
            stmt.setString(3, d.getDescricao());
            stmt.setDate(4, ConversorDateHelper.LocalDateTimeToDate(d.getDataHora()));
            stmt.setString(5, d.getStatusDenuncia().getValor());
            stmt.setString(6, d.getCategoria().getValor());
            stmt.setInt(7, d.getCurtidas());
            stmt.setInt(8, d.getValidarDenuncia());
            stmt.setString(9, d.getTipoDenuncia().getValor());
            stmt.setLong(10, d.getIdUsuario());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPessoa.res=" + res);
            return d;
        } catch (SQLException e) {
            throw new DataBaseException(e.getCause());
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
    public boolean remover(Integer id) throws DataBaseException {
        return false;
    }

    @Override
    public boolean editar(Integer id, Denuncia denuncia) throws DataBaseException {

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DENUNCIA SET ");
            sql.append(" descricao = ?,");
            sql.append(" data_hora = ?,");
            sql.append(" status_denuncia = ?,");
            sql.append(" categoria = ?,");
            sql.append(" curtida = ?,");
            sql.append(" validar_denuncia = ?,");
            sql.append(" id_usuario = ?,");
            sql.append(" tipo_denuncia = ?,");
            sql.append(" titulo = ?");
            sql.append(" WHERE id_denuncia = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, denuncia.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(denuncia.getDataHora()));
//            stmt.setInt(3, denuncia.getStatusDenuncia().getIdSituacao());
//            stmt.setInt(4, denuncia.getCategoria().getIdCategoria());
            stmt.setInt(5, denuncia.getCurtidas());
            stmt.setInt(6, denuncia.getValidarDenuncia());
            stmt.setInt(7, denuncia.getUsuario().getIdUsuario());
//            stmt.setBoolean(8, denuncia.getTipoDenuncia());
            stmt.setString(9, denuncia.getTitulo());
            stmt.setInt(10, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarDenuncia.res=" + res);

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
    public Usuario listarUsuario(int idUsuario) throws DataBaseException {
        return null;
    }
}
