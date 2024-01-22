package br.com.dbc.vemser.notifica.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
//    private static final String SERVER = "vemser-dbc.dbccompany.com.br";
//    private static final String PORT = "25000";
//    private static final String DATABASE = "xe";
//    private static final String USER = "VS_13_EQUIPE_7";
//    private static final String PASS = "oracle";

    private static final String SERVER = "localhost";
    private static final String PORT = "1521";
    private static final String DATABASE = "xe";
    private static final String USER = "VEM_SER";
    private static final String PASS = "oracle";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        return dataSource;
    }
}
