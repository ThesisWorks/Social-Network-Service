package com.meong.meongtwork.service;

import com.meong.meongtwork.entity.FollowEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
	private final FollowRepository followRepository;
	public void saveFollow(UserEntity profileId, UserEntity myId) {
		FollowEntity followEntity = new FollowEntity(profileId, myId);
		followRepository.save(followEntity);
	}

	public List<FollowEntity> loadFollowingByUserId(Long userId) {
		return followRepository.findAllByFollowingUserId(userId);
	}
	public List<FollowEntity> loadFollowerByUserId(Long userId) {
		return followRepository.findAllByFollowerUserId(userId);
	}
}
