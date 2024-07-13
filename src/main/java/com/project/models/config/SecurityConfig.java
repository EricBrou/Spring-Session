package com.project.models.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private CryptEncoder pwdEncoder;
    
    @Bean
    protected SessionFilter sessionFilter() {
    	return new SessionFilter();
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http.csrf(csrf->csrf.disable())
	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
			.securityMatcher("/**").authorizeHttpRequests(config ->
				config
					.requestMatchers("/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/rest/user/save").permitAll()
					.requestMatchers(HttpMethod.POST, "/rest/login").permitAll()
					.requestMatchers(HttpMethod.GET, "/rest/user/list").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/user/get/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/update/**").hasAnyRole("ADMIN", "USER", "SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/delete/**").hasAnyRole("ADMIN", "USER", "SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/product/list").hasAnyRole("ADMIN", "USER", "SELLER")
					.requestMatchers(HttpMethod.GET, "/rest/product/get/**").hasAnyRole("ADMIN","USER","SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/product/save").hasAnyRole("ADMIN", "SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/product/update/**").hasAnyRole("ADMIN", "SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/product/delete/**").hasAnyRole("ADMIN", "SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/role/list").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/role/get/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/role/save").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/role/update/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/role/delete/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/rest/cart/list").hasAnyRole("ADMIN","USER","SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/cart/add/**").hasAnyRole("ADMIN","USER","SELLER")
					.requestMatchers(HttpMethod.POST, "/rest/cart/delete/**").hasAnyRole("ADMIN","USER","SELLER")
					.anyRequest().authenticated()
		);
    	
    	http.addFilterBefore(sessionFilter(), UsernamePasswordAuthenticationFilter.class);
    	
		http.cors(Customizer.withDefaults());
    	
    	return http.build();
    }
    
    @Bean
	protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		builder.userDetailsService(userDetailsService)
			.passwordEncoder(pwdEncoder.getEncoder());
		
		return builder.build();
	}
}