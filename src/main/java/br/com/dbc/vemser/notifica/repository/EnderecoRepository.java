package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.irepository.IEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EnderecoRepository implements IEnderecoRepository {
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public List<Endereco> listarTodosEnderecos() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    SELECT * FROM ENDERECO
                    """;

            stmt = con.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();


            while (resultSet.next())
                enderecos.add(
                    new Endereco(
                        resultSet.getInt("ID_ENDERECO"), TipoEndereco.ofTipo(resultSet.getInt("TIPO_ENDERECO")), resultSet.getString("LOGRADOURO"),
                        resultSet.getInt("NUMERO"), resultSet.getString("COMPLEMENTO"), resultSet.getString("CEP"),
                        resultSet.getString("CIDADE"), resultSet.getString("ESTADO"), resultSet.getString("PAIS"), resultSet.getInt("ID_USUARIO")));
            return enderecos;

        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
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

    public Endereco obterEnderecoById(Integer id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    SELECT * FROM ENDERECO e WHERE E.ID_ENDERECO = ?
                    """;
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next())
                return new Endereco(
                    resultSet.getInt("ID_ENDERECO"), TipoEndereco.ofTipo(resultSet.getInt("TIPO_ENDERECO")), resultSet.getString("LOGRADOURO"),
                    resultSet.getInt("NUMERO"), resultSet.getString("COMPLEMENTO"), resultSet.getString("CEP"),
                    resultSet.getString("CIDADE"), resultSet.getString("ESTADO"), resultSet.getString("PAIS"), resultSet.getInt("ID_USUARIO"));

            throw new Exception("Falha ao obter endereço.");
        } catch (Exception e){
            throw new Exception(e);
        }finally {
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

    public List<Endereco> obterEnderecosByIdUsuario(Integer id) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                   SELECT * FROM ENDERECO e WHERE e.ID_USUARIO = ?
                    """;

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();

            while (resultSet.next())
                enderecos.add(new Endereco(resultSet.getInt("ID_ENDERECO"), TipoEndereco.ofTipo(resultSet.getInt("TIPO_ENDERECO")), resultSet.getString("LOGRADOURO"),
                        resultSet.getInt("NUMERO"), resultSet.getString("COMPLEMENTO"), resultSet.getString("CEP"),
                        resultSet.getString("CIDADE"), resultSet.getString("ESTADO"), resultSet.getString("PAIS"), resultSet.getInt("ID_USUARIO")));

            return enderecos;
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

    public Endereco adicionarEndereco(Endereco endereco) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    INSERT INTO ENDERECO c (ID_ENDERECO, TIPO_ENDERECO, LOGRADOURO, NUMERO, COMPLEMENTO,
                    CEP, CIDADE, ESTADO, PAIS, ID_USUARIO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            Integer proximoId = this.getProximoIdDoEndereco(con);
            endereco.setIdEndereco(proximoId);

            stmt = con.prepareStatement(sql);

            Endereco e = this.cepRequest(endereco);

            stmt.setInt(1, e.getIdEndereco());
            stmt.setInt(2, e.getTipoEndereco().getTipo());
            stmt.setString(3, e.getLogradouro());
            stmt.setInt(4, e.getNumero());
            stmt.setString(5, e.getComplemento());
            stmt.setString(6, e.getCep());
            stmt.setString(7, e.getCidade());
            stmt.setString(8, e.getEstado());
            stmt.setString(9, e.getPais());
            stmt.setInt(10, e.getIdPessoa());


            int res = stmt.executeUpdate();

            if (res > 0)
                return e;

            throw new RegraDeNegocioException("Falha ao adicionar usuário.");

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

    public Endereco atualizarEndereco(Integer id, Endereco endereco) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE ENDERECO e
                    SET e.TIPO_ENDERECO=?, e.LOGRADOURO=?, e.NUMERO=?, e.COMPLEMENTO=?, e.CEP=?,
                    e.CIDADE=?, e.ESTADO=?, e.PAIS=?
                    WHERE e.ID_ENDERECO = ?
                    """;

            stmt = con.prepareStatement(sql);

            Endereco e = this.cepRequest(endereco);
            e.setIdEndereco(id);

            stmt.setInt(1, e.getTipoEndereco().getTipo());
            stmt.setString(2, e.getLogradouro());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getComplemento());
            stmt.setString(5, e.getCep());
            stmt.setString(6, e.getCidade());
            stmt.setString(7, e.getEstado());
            stmt.setString(8, e.getPais());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();

            if (res > 0)
                return e;

            throw new RegraDeNegocioException("Falha ao atualizar endereço.");

        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
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
    public void removerEndereco(Integer id) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = """
                    DELETE FROM ENDERECO e WHERE e.ID_ENDERECO = ?
                    """;

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next())
                throw new RegraDeNegocioException("Falha ao remover endereço.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
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
    private Integer getProximoIdDoEndereco(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_ENDERECO.NEXTVAL mysequence from DUAL";
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    private Endereco cepRequest(Endereco endereco){
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = """
                https://viacep.com.br/ws/%s/json/
                """.formatted(endereco.getCep());
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        String[] jason = Objects.requireNonNull(response.getBody()).replace("[{}\\\"]", "").split(",");

        List<String> el = new ArrayList<>();
        for(String item: jason)
            el.add(item.replace("\\", "").split(": ")[1]);

        return new Endereco(endereco.getIdEndereco(), endereco.getTipoEndereco(), el.get(1).replace("\"", ""),
                endereco.getNumero(), endereco.getComplemento(), endereco.getCep(), el.get(4).replace("\"", ""),
                el.get(5).replace("\"", ""), endereco.getPais(), endereco.getIdPessoa());
    }
}
