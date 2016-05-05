package io.boot.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)
			.groupName("hotel")
				.apiInfo(apiInfo()).select()
				.paths(regex("/hotel.*"))
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
		  .title("Demo REST API for a Hotel")
		  .description("The API provides resources to manage a hotel.")
		.build();
	}
	
	@Bean
	SecurityConfiguration security() {
	    return new SecurityConfiguration(
	        "test-app-client-id",
	        "test-app-client-secret",
	        "test-app-realm",
	        "test-app",
	        "apiKey",
	        ApiKeyVehicle.HEADER, 
	        "Authorization", 
	        "," /*scope separator*/);
	}
	
}
