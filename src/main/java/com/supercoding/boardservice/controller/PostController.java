package com.supercoding.boardservice.controller;

import com.supercoding.boardservice.dto.posts.PostCreateDto;
import com.supercoding.boardservice.dto.posts.PostDataDto;
import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @ApiOperation("게시물 전체 조회")
    @GetMapping("/posts")
    public List<PostDto> findAllPost() {
        return postService.findAllPost();
    }

    @ApiOperation("게시물 생성")
    @PostMapping("/posts")
    public void createPost(@RequestBody PostCreateDto postCreateDto) {
        postService.createPost(postCreateDto);
    }

    @ApiOperation("작성자 이메일을 통해 특정 게시물들을 검색")
    @GetMapping("/posts/search")
    public List<PostDto> findAllPostByEmail(@RequestParam String author_email) {
        return postService.findAllPostByEmail();
    }

    @ApiOperation("게시물 수정")
    @PutMapping("/posts/{post_id}")
    public void updatePostById(@PathVariable("post_id") Integer postId) {
        postService.updatePostById(postId);
    }
}
