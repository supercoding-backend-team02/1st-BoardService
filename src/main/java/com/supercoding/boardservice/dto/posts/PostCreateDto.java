package com.supercoding.boardservice.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateDto {
    private PostDataDto postDataDto;
    private String author;
}
