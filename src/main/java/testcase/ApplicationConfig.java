package testcase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import datamodel.Run_CasesDAO;
import datamodel.RunsDAO;

@Configuration
@ComponentScan("testcase")
@PropertySource("classpath:app.properties")

public class ApplicationConfig {

	@Value("${test.cases}")
	private String[] testCasesProp;
	
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
    public RunsDAO runsDAO() {
    	return new RunsDAO();
    }

    @Bean
    public Run_CasesDAO run_casedDAO() {
    	return new Run_CasesDAO();
    }
}
