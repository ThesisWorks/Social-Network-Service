package com.meong.meongtwork.entity;

import com.meong.meongtwork.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@Column(nullable = false)
	private String title;

	@Column(name = "image_path", nullable = true)
	private String imagePath;

	public static BoardEntity toSaveEntity(BoardDto boardDto, UserEntity user) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setContent(boardDto.getContent());
		boardEntity.setUser(user);
		boardEntity.setTitle(boardDto.getTitle());

		boardEntity.setImagePath(boardDto.getImagePath());

		return boardEntity;
	}
}
