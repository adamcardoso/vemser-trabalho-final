package br.com.dbc.vemser.notifica.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteBd {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@vemser-dbc.dbccompany.com.br:25000:xe";
        String user = "VS_13_EQUIPE_7";
        String password = "oracle";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao tentar conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
}
