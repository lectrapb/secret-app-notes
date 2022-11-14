package com.app.domain.model.user;

import lombok.Data;

@Data
public class UserRemoveRequestDTO {

    private String uid;
    private String token;
}
