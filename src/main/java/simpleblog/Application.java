package simpleblog;

import io.github.jhipster.loaded.JHipsterReloaderAutoConfiguration;
import io.github.jhipster.loaded.reloader.SpringReloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends JHipsterReloaderAutoConfiguration {
	
	@Bean
	public SpringReloader springReloader() {
		return new SpringReloader();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
