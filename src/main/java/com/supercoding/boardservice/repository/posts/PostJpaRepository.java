package com.supercoding.boardservice.repository.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByUserId(Long userId);
}
