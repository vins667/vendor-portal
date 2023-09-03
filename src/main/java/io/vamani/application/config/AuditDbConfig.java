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
@EntityScan(basePackages = "io.vamani.application.audit.domain")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableJpaRepositories(transactionManagerRef = "auditTransactionManager", entityManagerFactoryRef = "auditEntityManagerFactory", basePackages = "io.vamani.application.audit.repository")
@EnableTransactionManagement
public class AuditDbConfig {

    @Autowired
    private Environment env;

    @Bean(name = "auditDataSource")
    @ConfigurationProperties("audit.datasource")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSource auditDataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("audit.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("audit.datasource.url"));
        dataSource.setUsername(env.getProperty("audit.datasource.username"));
        dataSource.setPassword(env.getProperty("audit.datasource.password"));
        return dataSource;
    }

    @Bean(name = "auditEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean auditEntityManagerFactory(
        EntityManagerFactoryBuilder builder) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("audit.jpa.database-platform"));
        properties.setProperty("hibernate.connection.provider_disables_autocommit", "true");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("audit.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("audit.datasource.url"));
        dataSource.setUsername(env.getProperty("audit.datasource.username"));
        dataSource.setPassword(env.getProperty("audit.datasource.password"));
        //DataSource dataSource = new DataSourceProperties().initializeDataSourceBuilder().build();
        LocalContainerEntityManagerFactoryBean emf = builder
            .dataSource(dataSource)
            .packages("io.vamani.application.audit.domain")
            .persistenceUnit("audit")
            .build();
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean(name = "auditTransactionManager")
    @ConfigurationProperties("audit.jpa")
    public JpaTransactionManager auditTransactionManager(@Qualifier("auditEntityManagerFactory") final EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
