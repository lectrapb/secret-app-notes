package com.app.domain.model.user;


import lombok.Data;

@Data
public class User {

    private String uid;
    private String name;
    private String email;
    private String password;
    private String image;
    private String role;
    private boolean google;

}
