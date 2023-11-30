package com.meong.meongtwork.service;

import com.meong.meongtwork.dto.AddUserRequest;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 회원가입 시 비밀번호를 암호화한 후 DB에 저장
	public Long save(AddUserRequest dto) {
		return userRepository.save(UserEntity.builder()
				.username(dto.getUsername())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
	}
}
