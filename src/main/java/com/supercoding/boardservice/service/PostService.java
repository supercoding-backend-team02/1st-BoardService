package com.supercoding.boardservice.service;

import com.supercoding.boardservice.dto.posts.PostCreateDto;
import com.supercoding.boardservice.dto.posts.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    public List<PostDto> findAllPost() {
        return null;
    }

    public List<PostDto> findAllPostByEmail() {
        return null;
    }

    public void createPost(PostCreateDto postCreateDto) {

    }

    public void updatePostById(Integer postId) {
    }
}
