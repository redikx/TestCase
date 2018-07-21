package testcase;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement

public class HibernateConfig {
 
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan("datamodel");
	sessionFactory.setHibernateProperties(hibernateProperties());
	
	return sessionFactory;
    }
    
    @Bean
    public DataSource dataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost/apptestcasedb?useSSL=false&serverTimezone=UTC");
	dataSource.setUsername("app1user");
	dataSource.setPassword("Radek_01");
	
	return dataSource;
    }
    
    
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    
    private final Properties hibernateProperties() {
	Properties hibernateProperties = new Properties();
	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
	return hibernateProperties;
    }
    
 
    
}
