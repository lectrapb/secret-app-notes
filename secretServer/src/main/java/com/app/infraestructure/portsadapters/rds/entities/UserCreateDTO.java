package com.app.infraestructure.portsadapters.rds.entities;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String uuid;
    private String name;
    private String email;
    private String imageProfile;
    private String google;
    private String role;
}
