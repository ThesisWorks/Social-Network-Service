package com.meong.meongtwork.repository;

import com.meong.meongtwork.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findById(Long id);

    @Query("SELECT b FROM BoardEntity b WHERE b.user.id = :userId")
    List<BoardEntity> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM BoardEntity b WHERE b.user.id IN :userIds")
    Page<BoardEntity> findAllByUserIds(@Param("userIds") Set<Long> userIds, Pageable pageable);
}
