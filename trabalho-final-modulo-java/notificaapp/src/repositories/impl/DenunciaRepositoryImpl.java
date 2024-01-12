package repositories.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;
import repositories.interfaces.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DenunciaRepositoryImpl implements Repository<Integer, Denuncia> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Denuncia adicionar(Denuncia object) throws DataBaseException {
        return null;
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
            sql.append(" fk_id_usuario = ?,");
            sql.append(" tipo_denuncia = ?,");
            sql.append(" titulo = ?,");
            sql.append(" WHERE id_denuncia = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, denuncia.getDescricao());
            stmt.setString(2, denuncia.getDataHora().toString());
            stmt.setInt(3, denuncia.getStatusDenuncia().getIdSituacao());
            stmt.setInt(4, denuncia.getCategoria().getIdCategoria());
            stmt.setInt(5, denuncia.getCurtidas());
            stmt.setInt(6, denuncia.getValidarDenuncia());
            stmt.setInt(7, denuncia.getUsuario().getIdUsuario());
            stmt.setBoolean(8, denuncia.getTipoDenuncia());
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
