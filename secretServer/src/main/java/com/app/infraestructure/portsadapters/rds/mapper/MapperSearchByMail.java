package com.app.infraestructure.portsadapters.rds.mapper;

import com.app.domain.model.user.User;
import com.app.infraestructure.portsadapters.rds.entities.UserValidateDTO;

public class MapperSearchByMail {


    public static User toModel(UserValidateDTO dto ){

        User user = new User();

        user.setUid(dto.getUuid());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setImage(dto.getImageProfile());
        user.setGoogle(Boolean.parseBoolean(dto.getGoogle()));

        return user;
    }
}
