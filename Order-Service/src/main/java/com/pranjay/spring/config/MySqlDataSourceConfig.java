package com.pranjay.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManager",
basePackages = {"com.pranjay.spring.repository"}, transactionManagerRef = "mySqlTrancationalManager")
public class MySqlDataSourceConfig {

    @Bean("mySqlproperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties mySqlDS(){
        return new DataSourceProperties();
    }

    @Bean(name = "mySqlDataSource")
    public DataSource mySqlDataSource(@Qualifier("mySqlproperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean(name = "mySqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
            @Qualifier("mySqlDataSource") DataSource dataSource, Environment env){

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.pranjay.spring.models");
        entityManagerFactoryBean.setPersistenceUnitName("entityManagerFactory");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.ddl-auto", "update");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.jdbc.batch_size", 100);
        props.put("hibernate.format_sql", true);
        //props.put("hibernate.generate_statistics", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("show-sql", true);
        entityManagerFactoryBean.setJpaPropertyMap(props);
        return entityManagerFactoryBean;

    }

    @Primary
    @Bean(name = "mySqlTrancationalManager")
    public PlatformTransactionManager mySqlTrancationalManager(
            @Qualifier("mySqlEntityManager")EntityManagerFactory entityManagerFactory ){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
