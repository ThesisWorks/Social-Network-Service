package com.meong.meongtwork.dto;

import com.meong.meongtwork.entity.BoardEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
	private Long id;
	private String intro = "소개를 작성하세요";
	List<BoardEntity> boards;
}
