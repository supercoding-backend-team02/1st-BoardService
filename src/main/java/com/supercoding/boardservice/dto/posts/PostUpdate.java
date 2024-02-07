package com.supercoding.boardservice.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdate {
    private String title;
    private String content;
}
