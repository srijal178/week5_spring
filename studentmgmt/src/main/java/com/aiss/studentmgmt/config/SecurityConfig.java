  package com.aiss.studentmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
     BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   
    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
                .requestMatchers("/tasks/**").authenticated() 
                .anyRequest().permitAll()) 
            .formLogin(formLogin -> formLogin
                .loginPage("/login") 
                .loginProcessingUrl("/login") 
                .defaultSuccessUrl("/tasks/") 
                .failureUrl("/login?error=true") 
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/") 
                .permitAll()) 
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) 

                .sessionFixation().migrateSession() 
                .maximumSessions(1)); 

		return http.build();
	}
}
