package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class LoginRepository {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Usuario findByEmail(String email, String senha) throws RegraDeNegocioException {
        Usuario usuario = new Usuario();
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO WHERE EMAIL_USUARIO = ? AND SENHA_USUARIO = ?";

            stmt = con.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer idUsuario = resultSet.getInt("ID_USUARIO");
                String nomeUsuario = resultSet.getString("NOME_USUARIO");
                String celularUsuario = resultSet.getString("CELULAR_USUARIO");
                String senhaUsuario = resultSet.getString("SENHA_USUARIO");
                String emailUsuario = resultSet.getString("EMAIL_USUARIO");
                Etnia etnia = Etnia.fromInt(resultSet.getInt("ETNIA"));
                LocalDate dataNascimento = resultSet.getDate("DATA_NASCIMENTO").toLocalDate();
                ClasseSocial classeSocial = ClasseSocial.fromInt(resultSet.getInt("CLASSE_SOCIAL"));
                Genero genero = Genero.fromInt(resultSet.getInt("GENERO"));
                TipoUsuario tipoUsuario = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO"));
                boolean isAdmin = TipoUsuario.fromInt(resultSet.getInt("TIPO_USUARIO")).equals(TipoUsuario.ADMIN);

                usuario = new Usuario(idUsuario, nomeUsuario, emailUsuario, celularUsuario, senhaUsuario, etnia, dataNascimento, classeSocial, genero, tipoUsuario, isAdmin);
            }
            if (usuario!=null){
                return usuario;
            }
            return null;
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
}
