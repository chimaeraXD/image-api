package com.example.image_api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

@Bean
@Primary
@ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {

    //TODO: uses the config values in application.properties
    return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/imageapi")
            .username("root")
            .password("Umineko7890!")
            .build();
    }

    @Bean
    public NamedParameterJdbcTemplate applicationDataConnection(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
