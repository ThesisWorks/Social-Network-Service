package com.meong.meongtwork.service;

import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserEntity loadUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
	}
}
