package com.app.domain.model.user;

import lombok.Data;

@Data
public class UserLoginRequestDTO {

    private String email;
    private String password;
}
