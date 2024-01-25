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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DenunciaRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Optional<List<DenunciaDTO>> listarTodasDenuncias() {
        try (Connection connection = conexaoBancoDeDados.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA";

            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(sql)) {

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

                return Optional.of(denuncias);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Denuncia>> listByTitulo(String titulo) {
        try (Connection connection = conexaoBancoDeDados.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA WHERE titulo LIKE ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

                    return Optional.of(listByTitulo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Denuncia>> ListByIdUsuario(Integer idUsuario) {
        try (Connection connection = conexaoBancoDeDados.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA WHERE id_usuario = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);

                try (ResultSet res = stmt.executeQuery()) {
                    List<Denuncia> ListByIdUsuario = new ArrayList<>();

                    while (res.next()) {
                        ListByIdUsuario.add(new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getString("descricao"),
                                res.getString("titulo"),
                                StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                                Categoria.fromInt(res.getInt("categoria")),
                                TipoDenuncia.fromInt(res.getInt("tipo_denuncia")),
                                res.getInt("id_usuario")
                        ));
                    }

                    return Optional.of(ListByIdUsuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Denuncia> obterDenunciaById(Integer idDenuncia) {
        try (Connection connection = conexaoBancoDeDados.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA WHERE id_denuncia = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idDenuncia);

                try (ResultSet res = stmt.executeQuery()) {
                    if (res.next()) {
                        return Optional.of(new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getString("descricao"),
                                res.getString("titulo"),
                                StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                                Categoria.fromInt(res.getInt("categoria")),
                                TipoDenuncia.fromInt(res.getInt("tipo_denuncia")),
                                res.getInt("id_usuario")
                        ));
                    }
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Denuncia> criarDenuncia(Denuncia denuncia, int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "INSERT INTO DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, tipo_denuncia, id_usuario) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

            Integer proximoId = getProximoIdDaDenuncia(con);
            denuncia.setIdDenuncia(proximoId);
            denuncia.setIdUsuario(idUsuario);

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
                return Optional.of(denuncia);
            }

            throw new Exception("Falha ao adicionar denúncia.");

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Optional<Denuncia> editarDenuncia(Integer idDenuncia, Denuncia denuncia) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "UPDATE DENUNCIA SET titulo = ?, descricao = ?, status_denuncia = ?, categoria = ?, curtida = ?, tipo_denuncia = ? WHERE id_denuncia = ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, denuncia.getTitulo());
            stmt.setString(2, denuncia.getDescricao());
            stmt.setInt(3, denuncia.getStatusDenuncia().getIdStatusDenuncia());
            stmt.setInt(4, denuncia.getCategoria().getIdCategoria());
            stmt.setInt(5, denuncia.getCurtidas());
            stmt.setInt(6, denuncia.getTipoDenuncia().getIdTipoDenuncia());
            stmt.setInt(7, idDenuncia);

            int res = stmt.executeUpdate();

            if (res > 0) {
                denuncia.setIdDenuncia(idDenuncia);
                return Optional.of(denuncia);
            }

            throw new Exception("Falha ao editar denúncia.");

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<Boolean> deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sqlVerificarUsuario = "SELECT id_usuario FROM DENUNCIA WHERE id_denuncia = ?";
            try (PreparedStatement stmtVerificarUsuario = con.prepareStatement(sqlVerificarUsuario)) {
                stmtVerificarUsuario.setInt(1, idDenuncia);
                try (ResultSet rs = stmtVerificarUsuario.executeQuery()) {
                    if (rs.next()) {
                        int idUsuarioDenuncia = rs.getInt("id_usuario");

                        if (idUsuarioDenuncia != idUsuario) {
                            System.err.println("Denúncia não encontrada no seu Perfil!");
                            return Optional.empty();
                        }
                    } else {
                        System.err.println("Denúncia não encontrada!");
                        return Optional.empty();
                    }
                }
            }

            String sqlDeleteComentario = "DELETE FROM COMENTARIO WHERE id_denuncia = ?";
            try (PreparedStatement stmtComentario = con.prepareStatement(sqlDeleteComentario)) {
                stmtComentario.setInt(1, idDenuncia);
                stmtComentario.executeUpdate();
            }

            // Exclui registros dependentes em LOCALIZACAO
            String sqlDeleteLocalizacao = "DELETE FROM LOCALIZACAO WHERE id_denuncia = ?";
            try (PreparedStatement stmtLocalizacao = con.prepareStatement(sqlDeleteLocalizacao)) {
                stmtLocalizacao.setInt(1, idDenuncia);
                stmtLocalizacao.executeUpdate();
            }

            // Exclui o registro principal em DENUNCIA
            String sqlDeleteDenuncia = "DELETE FROM DENUNCIA WHERE id_denuncia = ?";
            try (PreparedStatement stmtDenuncia = con.prepareStatement(sqlDeleteDenuncia)) {
                stmtDenuncia.setInt(1, idDenuncia);
                int res = stmtDenuncia.executeUpdate();
            }

        } catch (SQLException e) {
            // Em caso de erro, faz rollback das alterações no banco de dados
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Erro ao remover denúncia!");
            throw new Exception("Erro: " + e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.of(true);
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
