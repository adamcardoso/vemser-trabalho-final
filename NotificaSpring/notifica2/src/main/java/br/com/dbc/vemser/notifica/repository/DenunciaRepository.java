package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;

import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DenunciaRepository {

    private final DataSource dataSource;

    @Autowired
    public DenunciaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Denuncia> list() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA";

            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(sql)) {

                List<Denuncia> denuncias = new ArrayList<>();

                while (res.next()) {
                    denuncias.add(new Denuncia(
                            res.getInt("id_denuncia"),
                            res.getInt("id_usuario"),
                            res.getString("titulo"),
                            res.getString("descricao"),
                            StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                            Categoria.fromInt(res.getInt("categoria")),
                            TipoDenuncia.fromInt(res.getInt("tipo_denuncia"))
                    ));
                }

                return denuncias;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Denuncia> listByTitulo(String titulo) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA WHERE titulo LIKE ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + titulo + "%");

                try (ResultSet res = stmt.executeQuery()) {
                    List<Denuncia> listByTitulo = new ArrayList<>();

                    while (res.next()) {
                        listByTitulo.add(new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getInt("id_usuario"),
                                res.getString("titulo"),
                                res.getString("descricao"),
                                StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                                Categoria.fromInt(res.getInt("categoria")),
                                TipoDenuncia.fromInt(res.getInt("tipo_denuncia"))
                        ));
                    }

                    return listByTitulo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Denuncia criarDenuncia(Denuncia denuncia, int idUsuario) throws RegraDeNegocioException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO DENUNCIA (id_denuncia, titulo, descricao, data_hora, status_denuncia, categoria, curtida, validar_denuncia, tipo_denuncia, id_usuario) VALUES (SEQ_DENUNCIA.NEXTVAL, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, denuncia.getTitulo());
                stmt.setString(2, denuncia.getDescricao());
                stmt.setInt(3, denuncia.getStatusDenuncia().getIdStatusDenuncia());
                stmt.setInt(4, denuncia.getCategoria().getIdCategoria());
                stmt.setInt(5, denuncia.getCurtidas());
                stmt.setInt(6, denuncia.getValidarDenuncia());
                stmt.setInt(7, denuncia.getTipoDenuncia().getIdTipoDenuncia());
                stmt.setInt(8, idUsuario);

                stmt.executeUpdate();


            return denuncia;
            } catch (SQLException e) {
                throw new RegraDeNegocioException("Erro ao executar a consulta SQL");
            }
        } catch (SQLException e) {

            throw new RegraDeNegocioException("Erro ao obter a conexão com o banco de dados");
        }
    }

    public List<Denuncia> ListByIdUsuario(String idUsuario) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM DENUNCIA WHERE id_usuario = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, idUsuario);

                try (ResultSet res = stmt.executeQuery()) {
                    List<Denuncia> ListByIdUsuario = new ArrayList<>();

                    while (res.next()) {
                        ListByIdUsuario.add(new Denuncia(
                                res.getInt("id_denuncia"),
                                res.getInt("id_usuario"),
                                res.getString("titulo"),
                                res.getString("descricao"),
                                StatusDenuncia.fromInt(res.getInt("status_denuncia")),
                                Categoria.fromInt(res.getInt("categoria")),
                                TipoDenuncia.fromInt(res.getInt("tipo_denuncia"))
                        ));
                    }

                    return ListByIdUsuario;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

