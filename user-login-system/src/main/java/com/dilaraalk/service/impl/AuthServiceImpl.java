package com.dilaraalk.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dilaraalk.dto.DtoLoginRequest;
import com.dilaraalk.dto.DtoUserRegisterRequest;
import com.dilaraalk.entities.User;
import com.dilaraalk.repository.UserRepository;
import com.dilaraalk.service.IAuthService;
import com.dilaraalk.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	@Override
	public void register(DtoUserRegisterRequest request) {
		
		if (userRepository.findByUserName(request.getUserName()).isPresent()) {
			throw new RuntimeException("Kullanıcı zaten var");
		}
		
		User user = new User();
		user.setUserName(request.getUserName());
		//parolayı BCrypt ile encode ediyoruz
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(List.of("ROLE_USER"));
		
		userRepository.save(user);
		
	}

	@Override
	public String login(DtoLoginRequest request) {
	
		Optional<User> optionalUser = userRepository.findByUserName(request.getUserName());
		if (!optionalUser.isPresent()) {
			throw new RuntimeException("Kullanıcı bulunamadı!");
		}
		
		User user = optionalUser.get();
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Şifre hatalı!");
		}
		
		
		return jwtUtil.generateToken(user.getUserName(), user.getRoles());
	}

}
