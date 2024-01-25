package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.entity.Estatistica;
import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EstatisticaRepository {
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Optional<HashMap<String, List<Estatistica>>> obterEstatistica(List<String> colunas) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;

        try{
            con = conexaoBancoDeDados.getConnection();

            HashMap<String, List<Estatistica>> map = new HashMap<>();
            for (String coluna : colunas) {
                String sql = """
                        SELECT
                            %s,
                            COUNT(u.ID_USUARIO) AS TOTAL,
                            COUNT(u.ID_USUARIO) / (SELECT COUNT(u.ID_USUARIO) FROM USUARIO) * 100 AS PERCENTUAL
                        FROM
                            USUARIO u
                        GROUP BY
                            %s
                        ORDER BY %s ASC
                        """.formatted(coluna, coluna, coluna);

                stmt = con.prepareStatement(sql);

                ResultSet resultSet = stmt.executeQuery();

                List<Estatistica> estatisticas = new ArrayList<>();
                while (resultSet.next()) {
                    switch (coluna.toUpperCase()) {
                        case "ETNIA":
                            estatisticas.add(new Estatistica(
                                    Etnia.fromInt(resultSet.getInt(coluna)).name(),
                                    resultSet.getInt("TOTAL"), resultSet.getDouble("PERCENTUAL")));
                            break;
                        case "GENERO":
                            estatisticas.add(new Estatistica(
                                    Genero.fromInt(resultSet.getInt(coluna)).name(),
                                    resultSet.getInt("TOTAL"), resultSet.getDouble("PERCENTUAL")));
                            break;
                        case "CLASSE_SOCIAL":
                            estatisticas.add(new Estatistica(
                                    ClasseSocial.fromInt(resultSet.getInt(coluna)).name(),
                                    resultSet.getInt("TOTAL"), resultSet.getDouble("PERCENTUAL")));
                            break;
                        default:
                            estatisticas.add(new Estatistica());
                    }
                }
                map.put(coluna, estatisticas);
            }
            return Optional.of(map);
        } catch (Exception e){
            throw new Exception(e.getCause());
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
}
