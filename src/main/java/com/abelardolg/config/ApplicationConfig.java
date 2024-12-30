package com.abelardolg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource  driverManager = new DriverManagerDataSource();

        driverManager.setDriverClassName("com.mysql.cj.jdbc.driver");
        driverManager.setUrl("jdbc:mysql://localhost:3307/batch_chunk");
        driverManager.setUsername("root");
        driverManager.setPassword("CY$Fzw10Am");

        return driverManager;
    }
}
