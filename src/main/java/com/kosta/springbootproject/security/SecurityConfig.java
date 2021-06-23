package com.kosta.springbootproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MemberService memberservice;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config..........");
		http.authorizeRequests()
				.antMatchers("/auth/**","/search/**", "/fragments/**", "/user/**", "/courseInfo").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/course/enroll/**").hasRole("USER")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/login")
				.defaultSuccessUrl("/loginSuccess")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/auth/login")
				.invalidateHttpSession(true)
			.and().csrf().disable();
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	}

}
