package com.project.models.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.project.models.service.impl.CustomUserDetailsService;
import com.project.models.service.security.CryptEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private CryptEncoder pwdEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf(csrf->csrf.disable())
	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.securityMatcher("/**").authorizeHttpRequests(config ->
				config
					.requestMatchers("/**").permitAll()
					.anyRequest().authenticated()
		);
    	
    	return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(pwdEncoder.getEncoder());
    }
}