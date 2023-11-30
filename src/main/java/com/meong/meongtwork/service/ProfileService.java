package com.meong.meongtwork.service;

import com.meong.meongtwork.dto.ProfileDto;
import com.meong.meongtwork.entity.ProfileEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;

	public void save(ProfileDto profileDto, UserEntity userEntity) {
		ProfileEntity profileEntity = ProfileEntity.toSaveEntity(profileDto, userEntity);
		profileRepository.save(profileEntity);
	}

	public ProfileEntity loadProfileByUserId(Long userId) {
		return profileRepository.findByUserId(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저 profile은 존재하지 않습니다."));
	}

	public void validateAuthorization(Long profileId, Long accessId){
		ProfileEntity profileEntity = loadProfileByUserId(accessId);
		if (!Objects.equals(profileEntity.getId(), profileId))
			throw new IllegalArgumentException("수정할 권한이 없습니다.");
	}

	public void profileUpdate(ProfileDto profileDto, UserEntity userEntity) {
		ProfileEntity profileEntity = loadProfileByUserId(userEntity.getId());
		profileEntity.setIntroduction(profileDto.getIntro());
		profileRepository.save(profileEntity);
	}
}
