package com.meong.meongtwork.dto;

import com.meong.meongtwork.entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String username;
	private String password;

	private UserEntity toEntity() {
		return UserEntity.builder()
				.username(username)
				.password(password)
				.build();
	}
}
