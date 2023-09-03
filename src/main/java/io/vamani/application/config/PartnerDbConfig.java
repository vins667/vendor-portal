package io.vamani.application.config;

import io.github.jhipster.config.JHipsterConstants;
import io.vamani.application.mssql.domain.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "io.vamani.application.mssql.domain")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableJpaRepositories(transactionManagerRef = "partnerTransactionManager", entityManagerFactoryRef = "partnerEntityManagerFactory", basePackages = "io.vamani.application.mssql.repository")
@EnableTransactionManagement
public class PartnerDbConfig {

    @Autowired
    private Environment env;

    /*@Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSourceProperties partnerDataSourceProperties() {
        return new DataSourceProperties();
    }*/

    @Bean(name = "partnerDataSource")
    @ConfigurationProperties("partner.datasource")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public DataSource partnerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("partner.datasource.url"));
        dataSource.setUsername(env.getProperty("partner.datasource.username"));
        dataSource.setPassword(env.getProperty("partner.datasource.password"));
        return dataSource;
    }

    @Bean(name = "partnerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean partnerEntityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("partner.datasource.url"));
        dataSource.setUsername(env.getProperty("partner.datasource.username"));
        dataSource.setPassword(env.getProperty("partner.datasource.password"));
        //DataSource dataSource = new DataSourceProperties().initializeDataSourceBuilder().build();
        LocalContainerEntityManagerFactoryBean emf = builder
            .dataSource(dataSource)
            .packages("io.vamani.application.mssql.domain")
            .persistenceUnit("partner")
            .build();
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean(name = "partnerTransactionManager")
    @ConfigurationProperties("partner.jpa")
    public JpaTransactionManager partnerTransactionManager(@Qualifier("partnerEntityManagerFactory") final EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
