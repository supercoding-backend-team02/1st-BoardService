package com.supercoding.boardservice.controller;

import com.supercoding.boardservice.dto.posts.PostCreate;
import com.supercoding.boardservice.dto.posts.PostUpdate;
import com.supercoding.boardservice.service.PostService;
import com.supercoding.boardservice.service.exceptions.NotFoundException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @ApiOperation("게시물 전체 조회")
    @GetMapping("/posts")
    public ResponseEntity findAllPost() {
        // TODO AOP 예외처리
        try {
            return new ResponseEntity(postService.findAllPost(), HttpStatus.OK);
        } catch (NotFoundException nfe) {
            log.error(nfe.getMessage());
            return new ResponseEntity(nfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("게시물 생성")
    @PostMapping("/posts")
    public ResponseEntity createPost(@RequestBody PostCreate postCreate) {
        try {
            Long createPostId = postService.savePost(postCreate);
            return new ResponseEntity("게시글 ID: " + String.valueOf(createPostId) + " 생성 완료", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("작성자 이메일을 통해 특정 게시물들을 검색")
    @GetMapping("/posts/search")
    public ResponseEntity findAllPostByEmail(@RequestParam String author_email) {
        try {
            return new ResponseEntity(postService.findAllPostByEmail(author_email), HttpStatus.OK);
        } catch (NotFoundException nfe) {
            log.error(nfe.getMessage());
            return new ResponseEntity(nfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("게시물 수정")
    @PutMapping("/posts/{post_id}")
    public ResponseEntity updatePostById(@PathVariable("post_id") String postId, @RequestBody PostUpdate postUpdate) {
        try {
            return new ResponseEntity(postService.updatePost(postId, postUpdate), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
