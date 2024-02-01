package com.supercoding.boardservice.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private PostData postData;
    private String author;
    private LocalDateTime createdAt;
}
