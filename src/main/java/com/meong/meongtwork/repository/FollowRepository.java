package com.meong.meongtwork.repository;

import com.meong.meongtwork.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
	List<FollowEntity> findAllByFollowerUserId(Long id);
	List<FollowEntity> findAllByFollowingUserId(Long id);
}
