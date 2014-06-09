package simpleblog

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@PropertySource("classpath:/simpleblog/configuration/webmvc-config.properties")
class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry
			.addResourceHandler("/static/**")
			.addResourceLocations("classpath:/static/");
	}
	
	@Bean
	DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	Filter characterEncodingFilter() {
		 CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		 characterEncodingFilter.setEncoding("UTF-8");
		 return characterEncodingFilter;
	}
	
}

