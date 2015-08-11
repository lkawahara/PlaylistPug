package edu.neumont.spring.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Inject UserDetailsService userDetailsService;
	
	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder("secret");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable();
		
//		http
//		.authorizeRequests()
//			.antMatchers("/register", "/resources/**").permitAll()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.loginPage("/login")
//			.permitAll()
//			.and()
//		.logout()
//			.permitAll();
	}
}
