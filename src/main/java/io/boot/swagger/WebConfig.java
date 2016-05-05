package io.boot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.RelProvider;

@Configuration
public class WebConfig {

	@Bean
	RelProvider relProvider() {
	    return new RelProvider() {
			
			@Override
			public boolean supports(Class<?> delimiter) {
				return true;
			}
			
			@Override
			public String getItemResourceRelFor(Class<?> type) {
				return type.getSimpleName();
			}
			
			@Override
			public String getCollectionResourceRelFor(Class<?> type) {
				return type.getSimpleName().toLowerCase() + "s";
			}
		};
	}
	
	
}
