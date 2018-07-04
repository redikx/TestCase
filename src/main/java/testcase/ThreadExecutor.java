package testcase;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ThreadExecutor {
    
    
    private int users;
    
    public ThreadExecutor(int users) {
	this.users = users;
    }
    
    public TaskExecutor threadPoolTaskExecutor() {
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