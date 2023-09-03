package io.vamani.application.config;

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
@EntityScan(basePackages = "io.vamani.application.db2.domain")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableJpaRepositories(transactionManagerRef = "db2TransactionManager", entityManagerFactoryRef = "db2EntityManagerFactory", basePackages = "io.vamani.application.db2.repository")
@EnableTransactionManagement
public class DB2DbConfig {

    @Autowired
    private Environment env;

    /*@Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }*/

    @Bean(name = "db2DataSource")
    @ConfigurationProperties("db2.datasource")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSource db2DataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        return dataSource;
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
        EntityManagerFactoryBuilder builder) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("db2.jpa.database-platform"));
        properties.setProperty("hibernate.connection.provider_disables_autocommit", "true");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        LocalContainerEntityManagerFactoryBean emf = builder
            .dataSource(dataSource)
            .packages("io.vamani.application.db2.domain")
            .persistenceUnit("db2")
            .build();
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean(name = "db2TransactionManager")
    @ConfigurationProperties("db2.jpa")
    public JpaTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") final EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
