package simpleblog

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass=true)
class Application {

	static main(args) {
		SpringApplication.run(Application, args);
	}

}
