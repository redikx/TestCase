package testcase;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import datamodel.RunsDAO;
import datamodel.Test_TableDAO;

@Configuration
@ComponentScan("testcase")
@PropertySource("classpath:app.properties")

public class ApplicationConfig {

    @Value("${users}") 
    private int conc_users;
    
    @Value("${server.ip}")
    private String server_ip;
    
    @Value("${server.port}")
    private int server_port;
    
    @Bean
    public Server server() {
	return new Server(server_ip,server_port);
    }

    
    @Bean 
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(conc_users);
        executor.setMaxPoolSize(conc_users);
        executor.setThreadNamePrefix("executor_thread:");
        executor.initialize();
        return executor;
    }
    
    @Bean
    public TestConfig testconfig() {
	return new TestConfig();
    }
    

    @Bean
    public Test_TableDAO test_TableDAO() {
	return new Test_TableDAO();
    }
    
    @Bean 
    public RunsDAO runsDAO() {
    	return new RunsDAO();
    }
}
