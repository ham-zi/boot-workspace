package com.kh.semi.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kh.semi.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// return http.formLogin().disable().build(); <-- 구시대 문법
		
		// 신세대 문법
		/*
		return http.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				t.disable();
			}
		}).build();
		*/
		
		// 신세대 문법 // 간결하게
		
		//1 . return http.formLogin(t -> t.disable()).build();
		
		return http.formLogin(AbstractHttpConfigurer::disable)
				   .csrf(AbstractHttpConfigurer::disable)
				   .cors(AbstractHttpConfigurer::disable)
				   .authorizeHttpRequests(requests -> {
					   // POST방식으로 /members라는 요청이 오면 권한 체크 안하고 전부 허용
					   requests.requestMatchers(HttpMethod.POST, "/api/members", "/api/auth/login").permitAll();
					   // Patch방식으로 /api/members라는 요청이 오면 로그인 인증이 된건가?? 체크
					   requests.requestMatchers(HttpMethod.PATCH, "/api/members").authenticated();
					   requests.requestMatchers(HttpMethod.DELETE, "/api/members").authenticated();
					   
				   }).sessionManagement(manager -> 
				   						manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
		
				
		// 오히려 더 객체를  더 부르고 복잡한데 왜 굳이 새로운 문법을 낸걸까?
		// 기존 문법에 에 무슨 문제가 있었나??
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
