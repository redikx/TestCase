package testcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("testcase")
@PropertySource("classpath:app.properties")
public class ApplicationConfig {

    @Bean
    public Server server() {
	return new Server("10.242.44.22",31015);
    }
    
    @Bean
    public ThreadConfig threadConfig() {
	return new ThreadConfig();
    }
}
