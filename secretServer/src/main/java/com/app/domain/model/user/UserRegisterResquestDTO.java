package com.app.domain.model.user;

import lombok.Data;

@Data
public class UserRegisterResquestDTO {

    private String name;
    private String email;
    private String password;
    private String image;
    private String role;
    private boolean google;

}
