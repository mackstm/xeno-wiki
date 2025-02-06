package es.ies.puerto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private JwtFilter jwtAuthFilter;

	@Autowired
	public void setJwtAuthFilter(JwtFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.cors(cors->cors.disable())
			.csrf(csrf -> csrf.disable() )
				.authorizeHttpRequests(auth -> auth
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					
					
					.requestMatchers(
					"/", "/swagger-ui.html", 
					"/swagger-ui/**", "/v2/**", 
					"/configuration/**",	"/swagger*/**", 
					"/webjars/**", "/api/login", 
					"/api/register", "/v3/**",
					"/websocket*/**", "/index.html", "/api/v1/**",
					"/api/confirmation"
					).permitAll()
					.requestMatchers("/api/v2/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/api/v3/**").hasRole("ADMIN")
					.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.getOrBuild();
	}
}