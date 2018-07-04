package testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
    public ThreadExecutor threadExecutor() {
	return new ThreadExecutor(conc_users);
    }
    
    @Bean
    public ThreadRun threadRun() {
	return new ThreadRun();
    }
}
