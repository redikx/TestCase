package testcase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan("testcase")
@PropertySource(value="classpath:app.properties")

public class ThreadConfig {
    
    @Value("${users}")
    private String conc_users;
    public int getConc_users() {
        return 10;
    }
    

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(10);
	executor.setMaxPoolSize(10);
	executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
 
        return executor;
    
    }

}
