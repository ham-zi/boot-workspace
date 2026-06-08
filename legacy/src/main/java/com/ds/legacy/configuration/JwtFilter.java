package com.ds.legacy.configuration;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ds.legacy.auth.model.vo.CustomUserDetails;
import com.ds.legacy.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String uri = request.getRequestURI();
		return uri.equals("/api/auth/login") || uri.equals("/api/auth/refresh");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("awerawer");
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorization.substring(7);
		try {
			Claims claims = jwtUtil.parseJwt(token);
			String username = claims.getSubject();
			//log.info("토큰 소유주의 PK: {}", username);
			
			CustomUserDetails user = (CustomUserDetails)userDetailsService.loadUserByUsername(username);
			//log.info("유저 디테일즈 : {}", user);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			//세부 설정 관련 사용자의 IP주소, MAC주소, sessionId등을 포함시켜서 세팅
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// 요렇게 담아주면 현재 요청이 만료될때까지 Authentication에 담겨있는 사용자의 정보를 사용할 수 있음
			
		} catch (ExpiredJwtException e) {
			response.setStatus(401);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write("토큰만료");
			return;
		} catch (JwtException e) {
			response.setStatus(401);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write("토큰만료");
			return;
		}
		filterChain.doFilter(request, response);
	
	}

}
