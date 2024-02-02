package com.supercoding.boardservice.repository.users;

import com.supercoding.boardservice.repository.posts.PostEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long user_id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime create_dt;

    @PrePersist
    public void prePersist() {
        this.create_dt = LocalDateTime.now();
    }





}
