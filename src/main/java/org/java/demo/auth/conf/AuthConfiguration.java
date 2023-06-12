package org.java.demo.auth.conf;

import org.java.demo.auth.serv.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
				http.csrf(c -> c.disable())
						.authorizeHttpRequests(a -> a
						.requestMatchers("/api/v1/**").permitAll()
//						.requestMatchers("/**").hasAuthority("ADMIN")
						.requestMatchers("/**").hasAnyAuthority("SUPERADMIN", "ADMIN")
				        
				).formLogin(f -> f.permitAll()
				).logout(l -> l.logoutSuccessUrl("/")
				).build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	    return new UserServ();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	 
	    return authProvider;
	}
}