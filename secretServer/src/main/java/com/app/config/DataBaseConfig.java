package com.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bd.sp")
@Data
public class DataBaseConfig {

    private String createUser;
    private String searchUserByEmail;
}
