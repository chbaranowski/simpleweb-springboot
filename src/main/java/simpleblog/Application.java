package simpleblog;

import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	public Filter characterEncodingFilter() {
		 CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		 characterEncodingFilter.setEncoding("UTF-8");
		 return characterEncodingFilter;
	}
	
	public static void main(String[] args) throws Exception {
		Properties defaultProperties = new Properties();
		defaultProperties.setProperty("spring.thymeleaf.cache", "false");
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebEnvironment(true);
		application.setDefaultProperties(defaultProperties);
		application.run(args);
	}

}
