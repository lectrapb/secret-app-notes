package com.app.domain.model.secretPassword;

import lombok.Data;

@Data
public class secretPasswordRequestDTO {
    private String name;
    private String username;
    private String password;
    private String URI;
    private String user_uid;
}
