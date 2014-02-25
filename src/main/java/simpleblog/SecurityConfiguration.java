package simpleblog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/simpleblog/configuration/security-config.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${default.username}")
	String defaultUsername;
	
	@Value("${default.password}")
	String defaultPassword;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/**").hasRole("USER")
		.and()
			.formLogin()
				.loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("usr")
                .passwordParameter("pwd")
                .permitAll()
        .and()
        	.logout()
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/login")
        		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser(defaultUsername)
				.password(defaultPassword)
				.roles("USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/webjars/**")
		.and()
			.ignoring()
				.antMatchers("/static/**");
	}

}
