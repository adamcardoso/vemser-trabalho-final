package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DenunciaRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public List<DenunciaDTO> listarTodasDenuncias() throws SQLException {
        try (Connection connection = conexaoBancoDeDados.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM DENUNCIA")) {

            List<DenunciaDTO> denuncias = new ArrayList<>();

            while (res.next()) {
                denuncias.add(new DenunciaDTO(
                        res.getInt("id_denuncia"),
                        res.getInt("id_usuario"),
                        res.getString("descricao"),
                        res.getString("titulo"),
                        StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                        Categoria.fromInt(res.getInt("categoria")),
                        TipoDenuncia.fromInt(res.getInt("tipo_denuncia"))
                ));
            }
            return denuncias;
        }
    }

    public List<Denuncia> listByTitulo(String titulo) throws SQLException {
        try (Connection connection = conexaoBancoDeDados.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DENUNCIA WHERE titulo LIKE ?");
        ) {
            stmt.setString(1, "%" + titulo + "%");

            try (ResultSet res = stmt.executeQuery()) {
                List<Denuncia> listByTitulo = new ArrayList<>();

                while (res.next()) {
                    listByTitulo.add(new Denuncia(
                            res.getInt("id_denuncia"),
                            res.getString("descricao"),
                            res.getString("titulo"),
                            StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                            Categoria.fromInt(res.getInt("categoria")),
                            TipoDenuncia.fromInt(res.getInt("tipo_denuncia")),
                            res.getInt("id_usuario")
                    ));
                }

                return listByTitulo;
            }
        }
    }

    public List<Denuncia> listByIdUsuario(Integer idUsuario) throws SQLException {
        try (Connection connection = conexaoBancoDeDados.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DENUNCIA WHERE id_usuario = ?")) {

            stmt.setInt(1, idUsuario);

            try (ResultSet res = stmt.executeQuery()) {
                List<Denuncia> listByIdUsuario = new ArrayList<>();

                while (res.next()) {
                    listByIdUsuario.add(new Denuncia(
                            res.getInt("id_denuncia"),
                            res.getString("descricao"),
                            res.getString("titulo"),
                            StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                            Categoria.fromInt(res.getInt("categoria")),
                            TipoDenuncia.fromInt(res.getInt("tipo_denuncia")),
                            res.getInt("id_usuario")
                    ));
                }

                return listByIdUsuario;
            }
        }
    }

    public Denuncia obterDenunciaById(Integer idDenuncia) throws SQLException {
        try (Connection connection = conexaoBancoDeDados.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DENUNCIA WHERE id_denuncia = ?")) {

            stmt.setInt(1, idDenuncia);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return new Denuncia(
                            res.getInt("id_denuncia"),
                            res.getString("descricao"),
                            res.getString("titulo"),
                            StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                            Categoria.fromInt(res.getInt("categoria")),
                            TipoDenuncia.fromInt(res.getInt("tipo_denuncia")),
                            res.getInt("id_usuario")
                    );
                }
                return null;
            }
        }
    }

    public Denuncia criarDenuncia(Denuncia denuncia, int idUsuario) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            con.setAutoCommit(false);

            Integer proximoId = getProximoIdDaDenuncia(con);
            denuncia.setIdDenuncia(proximoId);
            denuncia.setIdUsuario(idUsuario);

            String sql = "INSERT INTO DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, tipo_denuncia, id_usuario) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, proximoId);
            stmt.setString(2, denuncia.getTitulo());
            stmt.setString(3, denuncia.getDescricao());
            stmt.setInt(4, denuncia.getStatusDenuncia().getIdStatusDenuncia());
            stmt.setInt(5, denuncia.getCategoria().getIdCategoria());
            stmt.setInt(6, denuncia.getCurtidas());
            stmt.setInt(7, denuncia.getTipoDenuncia().getIdTipoDenuncia());
            stmt.setInt(8, denuncia.getIdUsuario());

            int res = stmt.executeUpdate();

            if (res > 0) {
                con.commit();
                return denuncia;
            }

            con.rollback();
            throw new SQLException("Falha ao adicionar denúncia.");

        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Denuncia editarDenuncia(Integer idDenuncia, Denuncia denuncia, Integer idUsuario) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            con.setAutoCommit(false);

            String sql = "UPDATE DENUNCIA SET titulo = ?, descricao = ?, status_denuncia = ?, categoria = ?, curtida = ?, tipo_denuncia = ? WHERE id_denuncia = ? AND id_usuario = ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, denuncia.getTitulo());
            stmt.setString(2, denuncia.getDescricao());
            stmt.setInt(3, denuncia.getStatusDenuncia().getIdStatusDenuncia());
            stmt.setInt(4, denuncia.getCategoria().getIdCategoria());
            stmt.setInt(5, denuncia.getCurtidas());
            stmt.setInt(6, denuncia.getTipoDenuncia().getIdTipoDenuncia());
            stmt.setInt(7, idDenuncia);
            stmt.setInt(8, idUsuario);

            int res = stmt.executeUpdate();

            if (res > 0) {
                con.commit();
                denuncia.setIdDenuncia(idDenuncia);
                denuncia.setIdUsuario(idUsuario);
                return denuncia;
            }

            con.rollback();
            throw new SQLException("Falha ao editar denúncia.");

        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            con.setAutoCommit(false);

            String sqlVerificarUsuario = "SELECT id_usuario FROM DENUNCIA WHERE id_denuncia = ?";
            try (PreparedStatement stmtVerificarUsuario = con.prepareStatement(sqlVerificarUsuario)) {
                stmtVerificarUsuario.setInt(1, idDenuncia);
                try (ResultSet rs = stmtVerificarUsuario.executeQuery()) {
                    if (rs.next()) {
                        int idUsuarioDenuncia = rs.getInt("id_usuario");

                        if (idUsuarioDenuncia != idUsuario) {
                            System.err.println("Denúncia não encontrada no seu Perfil!");
                            return false;
                        }
                    } else {
                        System.err.println("Denúncia não encontrada!");
                        return false;
                    }
                }
            }

            String sqlDeleteComentario = "DELETE FROM COMENTARIO WHERE id_denuncia = ?";
            try (PreparedStatement stmtComentario = con.prepareStatement(sqlDeleteComentario)) {
                stmtComentario.setInt(1, idDenuncia);
                stmtComentario.executeUpdate();
            }

            String sqlDeleteLocalizacao = "DELETE FROM LOCALIZACAO WHERE id_denuncia = ?";
            try (PreparedStatement stmtLocalizacao = con.prepareStatement(sqlDeleteLocalizacao)) {
                stmtLocalizacao.setInt(1, idDenuncia);
                stmtLocalizacao.executeUpdate();
            }

            String sqlDeleteDenuncia = "DELETE FROM DENUNCIA WHERE id_denuncia = ?";
            stmt = con.prepareStatement(sqlDeleteDenuncia);
            stmt.setInt(1, idDenuncia);
            int res = stmt.executeUpdate();

            if (res > 0) {
                con.commit();
                return true;
            }

            con.rollback();
            return false;

        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getProximoIdDaDenuncia(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_DENUNCIA.NEXTVAL mysequence from DUAL";

        try (Statement stmt = connection.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        }
    }
}
