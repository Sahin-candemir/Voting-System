package com.shnc.VotingSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shnc.VotingSystem.security.CustomUserDetailsService;
import com.shnc.VotingSystem.security.JwtAuthenticationEntryPoint;
import com.shnc.VotingSystem.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private CustomUserDetailsService customUserDeatisService;

    public SecurityConfig(CustomUserDetailsService customUserDeatisService) {
		super();
		this.customUserDeatisService = customUserDeatisService;
	}

    
	@Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return  new JwtAuthenticationFilter();
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
		 .cors()
		 .and()
         .csrf().disable()
         .exceptionHandling()
         .authenticationEntryPoint(authenticationEntryPoint)
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests()   //requestleri nasıl authorize edeceği
         .antMatchers(HttpMethod.GET, "/**").permitAll()
         .antMatchers("/votes/**").permitAll()
         .antMatchers("/options").permitAll()
         .antMatchers("/transaction/**").permitAll()
         .antMatchers("/auth/**").permitAll()
         .antMatchers("/v3/api-docs/**").permitAll()
         .antMatchers("/swagger-ui/**").permitAll()
         .antMatchers("/swagger-resources/**").permitAll()
         .antMatchers("/swagger-ui.html/**").permitAll()
         .antMatchers("/webjars/**").permitAll()
         .anyRequest()
         .authenticated();
         
		 
		 http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

	/*protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDeatisService).passwordEncoder(passwordEncoder());
	}
	*/
	

}
