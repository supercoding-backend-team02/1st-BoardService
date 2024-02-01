package com.supercoding.boardservice.repository.users;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    private static final Logger log = LoggerFactory.getLogger(UserEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "create_dt", updatable = false)
    private LocalDateTime createDt;

    @PrePersist
    public void prePersist() {
        log.info("데이터 저장 전 createDt 설정");
        this.createDt = LocalDateTime.now();
    }
}
