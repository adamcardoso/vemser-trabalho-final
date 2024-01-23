package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.DataSourceConfig;
import br.com.dbc.vemser.notifica.entity.Comentario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ComentarioRepository {
    private final DataSourceConfig dataSourceConfig;

    public Optional<Comentario> obterComentarioById(Integer id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                   SELECT * FROM COMENTARIO c WHERE ID_COMENTARIO = ?
                    """;

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next())
                return Optional.of(new Comentario(resultSet.getInt("ID_COMENTARIO"), resultSet.getString("COMENTARIO"),
                        resultSet.getInt("CURTIDA"), resultSet.getInt("ID_DENUNCIA"), resultSet.getInt("ID_USUARIO")));

            return Optional.empty();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Optional<List<Comentario>> listarComentariosByIdDenuncia(Integer id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                   SELECT * FROM COMENTARIO c WHERE ID_DENUNCIA = ?
                    """;

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            List<Comentario> comentarios = new ArrayList<>();

            while (resultSet.next())
                comentarios.add(
                        new Comentario(resultSet.getInt("ID_COMENTARIO"), resultSet.getString("COMENTARIO"),
                                resultSet.getInt("CURTIDA"), resultSet.getInt("ID_DENUNCIA"), resultSet.getInt("ID_USUARIO")));

            return Optional.of(comentarios);

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public Optional<Comentario> criarComentario(Comentario comentario) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                    INSERT INTO COMENTARIO c (ID_COMENTARIO, COMENTARIO, CURTIDA, ID_DENUNCIA, ID_USUARIO)
                    VALUES (?, ?, ?, ?, ?)
                    """;
            Integer proximoId = this.getProximoIdDoUsuario(con);
            comentario.setIdComentario(proximoId);

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, comentario.getIdComentario());
            stmt.setString(2, comentario.getComentario());
            stmt.setInt(3, comentario.getCurtida());
            stmt.setInt(4, comentario.getIdDenuncia());
            stmt.setInt(5, comentario.getIdUsuario());

            int res = stmt.executeUpdate();

            if (res > 0)
                return Optional.of(comentario);

            throw new Exception("Falha ao adicionar comentario.");

        } catch (Exception e){
            throw new Exception(e);
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Optional<Comentario> editarComentario(Integer id, Comentario comentario) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                    UPDATE COMENTARIO c
                    SET c.COMENTARIO = ?
                    WHERE c.ID_COMENTARIO = ?
                    """;

            stmt = con.prepareStatement(sql);


            stmt.setString(1, comentario.getComentario());
            stmt.setInt(2, id);

            int res = stmt.executeUpdate();

            if (res > 0)
                return this.obterComentarioById(id);

            throw new Exception("Falha ao atualizar comentário.");

        } catch (Exception e){
            throw new Exception();
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Optional<Boolean> deletarComentario(Integer id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                    DELETE FROM COMENTARIO c WHERE c.ID_COMENTARIO = ?
                    """;

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next())
                return Optional.of(true);

            return Optional.empty();
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Integer getProximoIdDoUsuario(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_COMENTARIO.NEXTVAL mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next())
            return res.getInt("mysequence");

        return null;
    }
}
