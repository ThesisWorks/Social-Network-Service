package com.meong.meongtwork.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private Long id;
	private String content;
	private Long userId;
	private String title;
	private LocalDateTime boardCreatedTime;
	private LocalDateTime boardUpdatedTime;
}
