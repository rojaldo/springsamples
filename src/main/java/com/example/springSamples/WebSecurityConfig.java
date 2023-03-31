package com.example.springSamples;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springSamples.entities.UserEntity;
import com.example.springSamples.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserService userService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home", "/signup", "/greeting").permitAll()
				.requestMatchers(HttpMethod.POST, "/users/signup").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		List<UserEntity> users = userService.findAll();

		List<UserDetails> usersDetails = new ArrayList<UserDetails>();

		UserDetails otheruser =
		User.withDefaultPasswordEncoder()
		   .username("user")
		   .password("password")
		   .roles("USER")
		   .build();

		usersDetails.add(otheruser);

		for (UserEntity user : users) {
			UserDetails myUser =
			 User.withDefaultPasswordEncoder()
				.username(user.getUserLogin())
				.password(user.getUserPassword())
				.roles("USER")
				.build();
				usersDetails.add(myUser);
		}


		return new InMemoryUserDetailsManager(usersDetails);
	}
}
