package com.meong.meongtwork.service;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.BoardEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto, UserEntity userEntity) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto, userEntity);
        boardRepository.save(boardEntity);
    }

    public BoardEntity findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    public BoardEntity findByIdWithOwner(Long id, Long loginUserId) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!boardEntity.getUser().getId().equals(loginUserId)) {
            throw new IllegalArgumentException("해당 게시글에 대한 권한이 없습니다.");
        }

        return boardEntity;
    }

    public void update(BoardDto boardDto, Long loginUserId) {
        BoardEntity boardEntity = findByIdWithOwner(boardDto.getId(), loginUserId);
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setTitle(boardDto.getTitle());

        boardRepository.save(boardEntity);
    }

    public void delete(BoardDto boardDto, Long loginUserId) {
        BoardEntity boardEntity = findByIdWithOwner(boardDto.getId(), loginUserId);
        boardRepository.delete(boardEntity);
    }

    public Page<BoardEntity> findAllByUserFollow(Set<Long> followedUserIds, Pageable pageable) {
        return boardRepository.findAllByUserIds(followedUserIds, pageable);
    }

    public List<BoardEntity> findAllByUserId(Long userId) {
        return boardRepository.findAllByUserId(userId);
    }
}
