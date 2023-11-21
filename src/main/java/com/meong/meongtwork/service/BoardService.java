package com.meong.meongtwork.service;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.BoardEntity;
import com.meong.meongtwork.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }
}
