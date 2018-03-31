package io.khasang.jrepetitor.config.application;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:hibernate.properties")
public class HibernateConfig {

    @Autowired
    Environment environment;
    @Bean
    public DataSource dataSource(){
        Locale.setDefault(Locale.ENGLISH);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("hibernate.driver"));
        dataSource.setUrl(environment.getProperty("hibernate.url"));
        dataSource.setUsername(environment.getProperty("hibernate.user"));
        dataSource.setPassword(environment.getProperty("hibernate.password"));

        return dataSource;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("io.khasang.jrepetitor.entity");
        sessionFactory.setHibernateProperties(properties());
        return sessionFactory;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",(environment.getProperty("hibernate.dialect")));
        properties.put("hibernate.show_sql",(environment.getProperty("hibernate.show_sql")));
        properties.put("hibernate.format_sql",(environment.getProperty("hibernate.format_sql")));
        properties.put("hibernate.hbm2ddl.auto",(environment.getProperty("hibernate.hbm2ddl.auto")));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
