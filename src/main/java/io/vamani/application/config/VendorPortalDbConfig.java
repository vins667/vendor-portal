package io.vamani.application.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.jhipster.config.JHipsterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "io.vamani.application.vendorportal.domain")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableJpaRepositories(transactionManagerRef = "vendorportalTransactionManager", entityManagerFactoryRef = "vendorportalEntityManagerFactory", basePackages = "io.vamani.application.vendorportal.repository")
@EnableTransactionManagement
public class VendorPortalDbConfig {

    @Autowired
    private Environment env;

    /*@Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSourceProperties vendorportalDataSourceProperties() {
        return new DataSourceProperties();
    }*/

    @Bean(name = "vendorportalDataSource")
    @ConfigurationProperties("vendorportal.datasource")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSource vendorportalDataSource() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("vendorportal.datasource.url"));
        config.setUsername(env.getProperty("vendorportal.datasource.username"));
        config.setPassword(env.getProperty("vendorportal.datasource.password"));
        config.setAutoCommit(false);
        return new HikariDataSource( config );
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("vendorportal.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("vendorportal.datasource.url"));
        dataSource.setUsername(env.getProperty("vendorportal.datasource.username"));
        dataSource.setPassword(env.getProperty("vendorportal.datasource.password"));
        return dataSource;*/
    }

    @Bean(name = "vendorportalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean vendorportalEntityManagerFactory(
        EntityManagerFactoryBuilder builder) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("vendorportal.jpa.database-platform"));
        properties.setProperty("hibernate.connection.provider_disables_autocommit", "true");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("vendorportal.datasource.url"));
        config.setUsername(env.getProperty("vendorportal.datasource.username"));
        config.setPassword(env.getProperty("vendorportal.datasource.password"));
        config.setAutoCommit(false);
        HikariDataSource dataSource = new HikariDataSource( config );
        //DataSource dataSource = new DataSourceProperties().initializeDataSourceBuilder().build();
        LocalContainerEntityManagerFactoryBean emf = builder
            .dataSource(dataSource)
            .packages("io.vamani.application.vendorportal.domain")
            .persistenceUnit("vendorportal")
            .build();
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean(name = "vendorportalTransactionManager")
    @ConfigurationProperties("vendorportal.jpa")
    public JpaTransactionManager vendorportalTransactionManager(@Qualifier("vendorportalEntityManagerFactory") final EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
