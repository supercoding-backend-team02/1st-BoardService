package com.supercoding.boardservice.repository.posts;

import com.supercoding.boardservice.dto.posts.PostUpdate;
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
@ToString
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

    @Column(name = "body", length = 50, nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "write_dt", updatable = false)
    private LocalDateTime writeDt;

    @LastModifiedDate
    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    @Column(name = "cnt_like", nullable = false, columnDefinition = "DEFAULT 0 CHECK(cnt_like) >= 0")
    private Integer cntLike;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // writeDt 현재 시간으로 초기화
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.writeDt = now;
    }

    public void setPostEntity(PostUpdate postUpdate) {
        this.title = postUpdate.getTitle();
        this.body = postUpdate.getContent();
        this.updateDt = LocalDateTime.now();
    }
}