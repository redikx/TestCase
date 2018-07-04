package testcase;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TaskExecutor {
    
    
    private int users;
    
    public TaskExecutor(int users) {
	this.users = users;
    }
    
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(users);
	executor.setMaxPoolSize(users);
	executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
 
        return executor;
    
    }

    public int getUsers() {
        return users;
    }
    
    
}