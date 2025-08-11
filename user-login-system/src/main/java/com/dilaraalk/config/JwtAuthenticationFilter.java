package com.dilaraalk.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dilaraalk.entities.User;
import com.dilaraalk.repository.UserRepository;
import com.dilaraalk.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class  JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		String token = authHeader.substring(7);
		String userName = jwtUtil.extractUsername(token);
		
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			Optional<User> userOptional = userRepository.findByUserName(userName);
			if (userOptional.isPresent() && jwtUtil.validateToken(token, userName)) {
				User user = userOptional.get();
				
				List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				
				
				
					UsernamePasswordAuthenticationToken authToken =
					    new UsernamePasswordAuthenticationToken(user, null, authorities);

				
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
