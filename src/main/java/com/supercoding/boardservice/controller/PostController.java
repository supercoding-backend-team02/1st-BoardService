package com.supercoding.boardservice.controller;

import com.supercoding.boardservice.dto.posts.PostCreate;
import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.dto.posts.PostUpdate;
import com.supercoding.boardservice.repository.posts.PostEntity;
import com.supercoding.boardservice.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @ApiOperation("게시물 전체 조회")
    @GetMapping("/posts")
    public List<PostDto> findAllPost() {
        return postService.findAllPost();
    }

    @ApiOperation("게시물 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public String createPost(@RequestBody PostCreate postCreate) {
        Long createPostId = postService.savePost(postCreate);
        return "게시글 ID: " + String.valueOf(createPostId) + " 생성 완료";
    }

    @ApiOperation("작성자 이메일을 통해 특정 게시물들을 검색")
    @GetMapping("/posts/search")
    public List<PostDto> findAllPostByEmail(
            @ApiParam(name = "author_email", value = "user email", example = "2@a.com") @RequestParam String author_email
    ) {
        return postService.findAllPostByEmail(author_email);
    }

    @ApiOperation("게시물 수정")
    @PutMapping("/posts/{post_id}")
    public PostEntity updatePostById(
            @ApiParam(name = "post_id", value = "post ID", example = "7") @PathVariable("post_id") String postId,
            @RequestBody PostUpdate postUpdate) {
        return postService.updatePost(postId, postUpdate);
    }
}
