package com.supercoding.boardservice.service;

import com.supercoding.boardservice.dto.posts.PostCreate;
import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.dto.posts.PostUpdate;
import com.supercoding.boardservice.repository.posts.PostEntity;
import com.supercoding.boardservice.repository.posts.PostJpaRepository;
import com.supercoding.boardservice.repository.users.UserEntity;
import com.supercoding.boardservice.repository.users.UserRepository;
import com.supercoding.boardservice.service.exceptions.NotAcceptException;
import com.supercoding.boardservice.service.exceptions.NotFoundException;
import com.supercoding.boardservice.service.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostJpaRepository postJpaRepository;
    private final UserRepository userRepository;
    public List<PostDto> findAllPost() {
        List<PostEntity> postEntities = postJpaRepository.findAll();
        if (postEntities.isEmpty()) throw new NotFoundException("게시글이 조회되지 않습니다.");

        return postEntities.stream().map(PostMapper.INSTANCE::postEntityToPostDto).collect(Collectors.toList());
    }

    public List<PostDto> findAllPostByEmail(String email) {
        UserEntity userEntityByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("이메일 " + email + "로 등록된 유저는 존재하지 않습니다."));

        Long userId = userEntityByEmail.getUser_id();
        List<PostEntity> postEntitiesById = postJpaRepository.findAllByUserId(userId);
        if (postEntitiesById.isEmpty()) throw new NotFoundException(("유저 id " + String.valueOf(userId) + "가 작성한 게시글은 존재하지 않습니다."));
        return postEntitiesById.stream().map(PostMapper.INSTANCE::postEntityToPostDto).collect(Collectors.toList());
    }

    public Long savePost(PostCreate postCreate) {
        PostEntity postEntity = PostMapper.INSTANCE.postCreateToPostEntity(null, postCreate);
        log.info("postEntity : {}", postEntity);
        PostEntity postEntityCreated;

        try {
            postEntityCreated = postJpaRepository.save(postEntity);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new NotAcceptException("게시글 저장 도중 에러 발생");
        }

        return postEntityCreated.getPostId();
    }

    public PostEntity updatePost(String postId, PostUpdate postUpdate) {
        Long userId = Long.valueOf(postId);

        PostEntity postEntityUpdated = postJpaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(("유저 id " + String.valueOf(userId) + "가 작성한 게시글은 존재하지 않습니다.")));

        postEntityUpdated.setPostEntity(postUpdate);
        return postEntityUpdated;
    }
}
