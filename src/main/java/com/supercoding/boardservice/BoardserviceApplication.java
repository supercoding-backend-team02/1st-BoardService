package com.supercoding.boardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoardserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BoardserviceApplication.class, args);
    }


}
