package simpleblog;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) throws Exception {
		Properties defaultProperties = new Properties();
		defaultProperties.setProperty("spring.thymeleaf.cache", "false");
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebEnvironment(true);
		application.setDefaultProperties(defaultProperties);
		application.run(args);
	}
	
}
