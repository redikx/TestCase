package testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan("testcase")
@PropertySource("classpath:app.properties")

public class ApplicationConfig {

    @Value("${users}") 
    private int conc_users;
    
    @Bean
    public Server server() {
	return new Server("10.242.44.22",31015);
    }
    
    @Bean 
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(conc_users);
        executor.setMaxPoolSize(conc_users);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
    
}
