package com.myprojects.manufacturingworkspace.webmodel.config;

import java.io.IOException;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class BeanConfig {

	//SessionFactory to hibernate
	@Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.myprojects.manufacturingworkspace"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }
	

	//datasource mysql database
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/ubaworkspace?serverTimezone=Asia/Yekaterinburg&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("uspenskiy131");
        return dataSource;
    }

    //properties for hibernate
    @SuppressWarnings("serial")
	Properties hibernateProperties() {
        return new Properties() {
			{
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.enable_lazy_load_no_trans", "true");
            }
        };
    }
}