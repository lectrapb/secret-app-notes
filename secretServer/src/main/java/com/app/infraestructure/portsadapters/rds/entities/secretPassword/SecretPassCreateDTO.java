package com.app.infraestructure.portsadapters.rds.entities.secretPassword;

import lombok.Data;

@Data
public class SecretPassCreateDTO {

    private String id;
    private String name;
    private String username;
    private String password;
    private String URI;
    private String user_uid;
}
