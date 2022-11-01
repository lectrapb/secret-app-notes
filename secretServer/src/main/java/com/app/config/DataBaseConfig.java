package com.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bd.sp")
@Data
public class DataBaseConfig {

    private String createUser;
    private String createSecretPass;
    private String deleteSecretPass;
    private String updateSecretPass;
    private String searchUserByEmail;
    private String selectSecretPass;
    private String createSecretNote;
    private String selectSecretNote;
    private String updateSecretNote;
    private String deleteSecretNote;
    private String deleteSecretAll;
}
