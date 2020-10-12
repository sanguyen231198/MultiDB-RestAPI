package com.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = {"com.example.repository.postgres"})
public class DataSourcePostgresConfig {

//    @Primary
    @Bean(name = "secondaryDataSourceProperties")
    @ConfigurationProperties(prefix ="spring.datasource-secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

//    @Primary
    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties("spring.datasource-secondary.configuration")
    public DataSource secondaryDataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
        return secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

//    @Primary
    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder, @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

        Map<String, String> secondaryJpaProperties = new HashMap<>();
        secondaryJpaProperties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        secondaryJpaProperties.put("spring.jpa.hibernate.ddl-auto", "update");

        return secondaryEntityManagerFactoryBuilder
                .dataSource(secondaryDataSource)
                .packages("com.example.entity.postgres")
                .persistenceUnit("secondaryDataSource")
                .properties(secondaryJpaProperties)
                .build();
    }

//    @Primary
    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {

        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}