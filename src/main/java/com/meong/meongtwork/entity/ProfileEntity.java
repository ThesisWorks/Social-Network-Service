package com.meong.meongtwork.entity;

import com.meong.meongtwork.dto.ProfileDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "profile_table")
public class ProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 32)
	private String introduction;

	@Getter
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static ProfileEntity toSaveEntity(ProfileDto profileDto, UserEntity user) {
		ProfileEntity profileEntity = new ProfileEntity();
		profileEntity.setIntroduction(profileDto.getIntro());
		profileEntity.setUser(user);
		return profileEntity;
	}
}