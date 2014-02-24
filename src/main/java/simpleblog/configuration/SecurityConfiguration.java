package simpleblog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/").hasRole("USER")
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
				.withUser("spring")
				.password("boot")
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
