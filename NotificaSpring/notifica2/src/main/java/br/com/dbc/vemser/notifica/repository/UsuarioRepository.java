package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.DataSourceConfig;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.*;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UsuarioRepository {
    private final DataSourceConfig dataSourceConfig;

    public Usuario obterUsuarioById(int idUsuario) throws Exception {
        Usuario usuario = new Usuario();
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, idUsuario);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nomeUsuario = resultSet.getString("NOME_USUARIO");
                String celularUsuario = resultSet.getString("CELULAR_USUARIO");
                String senhaUsuario = resultSet.getString("SENHA_USUARIO");
                Etnia etnia = Etnia.fromInt(resultSet.getInt("ETNIA"));
                LocalDate dataNascimento = resultSet.getDate("DATA_NASCIMENTO").toLocalDate();
                ClasseSocial classeSocial = ClasseSocial.fromInt(resultSet.getInt("CLASSE_SOCIAL"));
                Genero genero = Genero.fromInt(resultSet.getInt("GENERO"));
                TipoUsuario tipoUsuario = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO"));
                Boolean isAdmin = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO")).equals(TipoUsuario.ADMIN);

                usuario = new Usuario(idUsuario, nomeUsuario, celularUsuario, senhaUsuario, etnia, dataNascimento, classeSocial, genero, tipoUsuario, isAdmin);
            }

            return usuario;
        } catch (Exception e) {
            throw new RegraDeNegocioException(e.getMessage());
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


    public List<Usuario> listarUsuarios() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = dataSourceConfig.dataSource().getConnection();

            String sql = "SELECT * FROM USUARIO";

            stmt = con.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                Integer idUsuario = resultSet.getInt("ID_USUARIO");
                String nomeUsuario = resultSet.getString("NOME_USUARIO");
                String celularUsuario = resultSet.getString("CELULAR_USUARIO");
                String senhaUsuario = resultSet.getString("SENHA_USUARIO");
                Etnia etnia = Etnia.fromInt(resultSet.getInt("ETNIA"));
                LocalDate dataNascimento = resultSet.getDate("DATA_NASCIMENTO").toLocalDate();
                ClasseSocial classeSocial = ClasseSocial.fromInt(resultSet.getInt("CLASSE_SOCIAL"));
                Genero genero = Genero.fromInt(resultSet.getInt("GENERO"));
                TipoUsuario tipoUsuario = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO"));
                Boolean isAdmin = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO")).equals(TipoUsuario.ADMIN);

                usuarios.add(new Usuario(idUsuario, nomeUsuario, celularUsuario, senhaUsuario, etnia, dataNascimento, classeSocial, genero, tipoUsuario, isAdmin));
            }
            return usuarios;
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


    public Usuario criarUsuario(Usuario usuario) throws RegraDeNegocioException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSourceConfig.dataSource().getConnection();

            String sql = """
                    INSERT INTO USUARIO
                    (ID_USUARIO, NOME_USUARIO, CELULAR_USUARIO, SENHA_USUARIO, DATA_NASCIMENTO, ETNIA, CLASSE_SOCIAL, GENERO, TIPO_USUARIO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";

            Integer proximoId = this.getProximoIdDoUsuario(con);
            usuario.setIdUsuario(proximoId);

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getNumeroCelular());
            stmt.setString(4, usuario.getSenhaUsuario());
            stmt.setDate(5, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(6, String.valueOf(usuario.getEtniaUsuario().getIdEtnia()));
            stmt.setString(7, String.valueOf(usuario.getClasseSocial().getIdClasseSocial()));
            stmt.setString(8, String.valueOf(usuario.getGeneroUsuario().getIdGenero()));
            stmt.setString(9, String.valueOf(usuario.getTipoUsuario().getIdTipoUsuario()));

            int res = stmt.executeUpdate();

            if (res > 0) {
                return usuario;
            } else {
                throw new RegraDeNegocioException("Erro ao adicionar usuário");
            }

        } catch (SQLException e) {
            throw new RegraDeNegocioException(e.getMessage());
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

    public Usuario atualizarUsuario(Integer id, Usuario usuario) throws RegraDeNegocioException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSourceConfig.dataSource().getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" nome_usuario = ?,");
            sql.append(" celular_usuario = ?,");
            sql.append(" senha_usuario = ?,");
            sql.append(" data_nascimento = ?,");
            sql.append(" etnia = ?,");
            sql.append(" classe_social = ?,");
            sql.append(" genero = ?,");
            sql.append(" tipo_usuario = ?");
            sql.append(" WHERE id_usuario = ? ");

            stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setLong(2, Long.parseLong(usuario.getNumeroCelular()));
            stmt.setString(3, usuario.getSenhaUsuario());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setInt(5, usuario.getEtniaUsuario().getIdEtnia());
            stmt.setInt(6, usuario.getClasseSocial().getIdClasseSocial());
            stmt.setInt(7, usuario.getGeneroUsuario().getIdGenero());
            stmt.setInt(8, usuario.getTipoUsuario().getIdTipoUsuario());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();

            if (res > 0) {
                usuario.setIdUsuario(id);
                return usuario;
            } else {
                throw new RegraDeNegocioException("Erro ao atualizar usuário");
            }
        } catch (SQLException e) {
            throw new RegraDeNegocioException(e.getMessage());
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


    public boolean removerUsuario(Integer idUsuario) throws RegraDeNegocioException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSourceConfig.dataSource().getConnection();
            con.setAutoCommit(false);


            // Exclui registros dependentes em COMENTARIO
            String sqlDeleteComentario = "DELETE FROM COMENTARIO WHERE id_usuario = ?";
            try (PreparedStatement stmtComentario = con.prepareStatement(sqlDeleteComentario)) {
                stmtComentario.setInt(1, idUsuario);
                stmtComentario.executeUpdate();

            }

            // Exclui o registro principal em DENUNCIA
            String sqlDeleteDenuncia = "DELETE FROM DENUNCIA WHERE id_usuario = ?";
            try (PreparedStatement stmtDenuncia = con.prepareStatement(sqlDeleteDenuncia)) {
                stmtDenuncia.setInt(1, idUsuario);
                stmtDenuncia.executeUpdate();
            }

            String sqlDeleteUsuario = "DELETE FROM USUARIO WHERE id_usuario = ?";
            try (PreparedStatement stmtDenuncia = con.prepareStatement(sqlDeleteUsuario)) {
                stmtDenuncia.setInt(1, idUsuario);
                int res = stmtDenuncia.executeUpdate();

                con.commit();

                return res > 0;
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RegraDeNegocioException(e.getMessage());
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

    public Integer getProximoIdDoUsuario(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_USUARIO.NEXTVAL mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }
}
