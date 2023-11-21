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

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    public static BoardEntity toSaveEntity(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setContent(boardDto.getContent());
        boardEntity.setUserId("root");
        boardEntity.setTitle(boardDto.getTitle());

        return boardEntity;
    }
}
