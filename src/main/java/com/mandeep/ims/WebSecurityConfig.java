package com.mandeep.ims;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${application.username}")
	private String username;

	@Value("${application.password}")
	private String password;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/wallPage").hasAnyRole("ADMIN", "USER").and().authorizeRequests()
				.antMatchers("/login", "/resource/**").permitAll().and().formLogin().loginPage("/login")
				.usernameParameter(username).passwordParameter(password).permitAll().loginProcessingUrl("/doLogin")
				.successForwardUrl("/postLogin").failureUrl("/loginFailed").and().logout().logoutUrl("/doLogout")
				.logoutSuccessUrl("/logout").permitAll().and().csrf().disable();
	}

}
