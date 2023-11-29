package com.meong.meongtwork.service;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.BoardEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto, UserEntity userEntity) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto, userEntity);
        boardRepository.save(boardEntity);
    }

    public BoardEntity findByIdWithOwner(Long id, Long ownerId) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!boardEntity.getUser().getId().equals(ownerId)) {
            throw new IllegalArgumentException("해당 게시글에 대한 권한이 없습니다.");
        }

        return boardEntity;
    }
}
