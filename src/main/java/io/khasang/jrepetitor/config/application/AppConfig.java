package io.khasang.jrepetitor.config.application;

import io.khasang.jrepetitor.DAO.CatsDAO;
import io.khasang.jrepetitor.DAO.CatsDAOImpl;
import io.khasang.jrepetitor.model.Cat;
import io.khasang.jrepetitor.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = "classpath:util.properties")
public class AppConfig {

    @Bean
    public Cat cat(){
        return new Cat("barsik",1,"angry");
    }

    @Autowired
    Environment environment;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.user"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public CreateTable createTable(){
        return new CreateTable(getJdbcTemplate());
    }

    @Bean
    public CatsDAO catsDAO(){
        return new CatsDAOImpl(getJdbcTemplate());
    }
}
