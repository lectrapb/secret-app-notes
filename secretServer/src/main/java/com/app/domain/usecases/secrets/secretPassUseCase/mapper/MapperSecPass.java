package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.domain.model.secretPassword.*;

import java.util.UUID;

public class MapperSecPass {

    public static secretPassword toSecretPass(secretPasswordRequestDTO requestDTO){
        secretPassword password = new secretPassword();
        //validación si es null
        password.setId(UUID.randomUUID().toString());
        password.setName(requestDTO.getName());
        password.setUsername(requestDTO.getUsername());
        password.setPassword(requestDTO.getPassword());
        password.setURI(requestDTO.getURI());
        password.setUser_uid(requestDTO.getUser_uid());

        return  password;
    }
}
