package com.project.models.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.models.service.impl.CustomUserDetailsService;
import com.project.models.service.security.CryptEncoder;
import com.project.models.service.security.SessionFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private CryptEncoder pwdEncoder;
    
    @Bean
    public SessionFilter sessionFilter() {
    	return new SessionFilter();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf(csrf->csrf.disable())
	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
			.securityMatcher("/**").authorizeHttpRequests(config ->
				config
					.requestMatchers("/**").permitAll()
					.anyRequest().authenticated()
		);
    	
		http.addFilterBefore(sessionFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.cors(Customizer.withDefaults());
    	
    	return http.build();
    }
    
    @Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		builder.userDetailsService(userDetailsService)
			.passwordEncoder(pwdEncoder.getEncoder());
		
		return builder.build();
	}
}