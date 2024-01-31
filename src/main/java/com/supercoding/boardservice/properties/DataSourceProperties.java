package com.supercoding.boardservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * db 정보 설정
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {
    private String username = "root";
    private String password = "12341234";
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/boardservice?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
}