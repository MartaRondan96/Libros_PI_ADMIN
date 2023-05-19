package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home", "/auth/**", "/about/**", "/error/**", "/webjars/**",
						"/css/**","/js/**","/assets/**", "/files/**","/icons/**", "/images/**", "/photos/**")
				.permitAll().requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN").anyRequest()
				.authenticated())
				.formLogin((form) -> form.loginPage("/auth/login").defaultSuccessUrl("/admin/users/listUsers").permitAll())
				.logout((logout) -> logout.permitAll().logoutUrl("/auth/logout")
						.logoutSuccessUrl("/auth/login?logout"));
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}