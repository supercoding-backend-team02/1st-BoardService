package com.supercoding.boardservice.repository.posts;

import com.supercoding.boardservice.repository.users.UserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "write_dt", updatable = false)
    private LocalDateTime writeDt;

    @LastModifiedDate
    @Column(name = "update_dt", updatable = false)
    private LocalDateTime updateDt;

    @Column(name = "cnt_like")
    private Integer cntLike;

    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
}