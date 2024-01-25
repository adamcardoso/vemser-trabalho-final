package br.com.dbc.vemser.notifica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    private static final String SERVER = "10.0.20.80";
    private static final String PORT = "1521";
    private static final String DATABASE = "xe";
    private static final String USER = "VS_13_EQUIPE_7";
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
