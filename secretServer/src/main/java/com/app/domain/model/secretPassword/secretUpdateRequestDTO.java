package com.app.domain.model.secretPassword;

import lombok.Data;

@Data
public class secretUpdateRequestDTO {
    private String id;
    private String name;
    private String username;
    private String password;
    private String URI;
}
