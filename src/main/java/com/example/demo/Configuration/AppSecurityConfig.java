package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Service
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] AUTH_WHITELIST = { // -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**"
			// other public endpoints of your API may be appended to this array
	};

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService); // //
		// .setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/signup**","/login").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/books/**").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/books/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.PUT, "/books/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.POST, "/publisher/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.GET, "/publisher/**").hasAnyAuthority("Admin")

//				 .antMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("Admin")
//				 .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("Admin")
//				 .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.DELETE, "/books/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.PUT, "/customer/**").hasAnyAuthority("User")
				
				.antMatchers(HttpMethod.GET, "/orderItems/**").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.DELETE, "/orderItems/**").hasAnyAuthority("User","Admin")

				.antMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.GET, "/category/**").permitAll()
				.antMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.DELETE, "/category/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.PUT, "/category/**").hasAnyAuthority("Admin")

				.antMatchers(HttpMethod.POST, "/orderItems/**").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.PUT, "/orderItems/**").hasAnyAuthority("User","Admin")

				
				.antMatchers(HttpMethod.DELETE, "/customer/**").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.POST, "/customer").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.PUT, "/confirmorder/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.POST, "/feedback/**").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.GET, "/feedback/{id}").hasAnyAuthority("User","Admin")
				.antMatchers(HttpMethod.PUT, "/feedback/**").hasAnyAuthority("User","Admin")
//				.antMatchers(HttpMethod.GET, "/feedback").hasAnyAuthority("Admin")

				
				.anyRequest().authenticated().and()
				.formLogin().permitAll().and().httpBasic();
	}
}
