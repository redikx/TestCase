package testcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("testcase")
public class ApplicationConfig {

    @Bean
    public Server server() {
	return new Server("10.242.44.22",31015);
    }
    
}
