package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;

public class MapperUpdatePass {

    public static secretPassword toUpdatePass(secretUpdateRequestDTO requestDTO){
        secretPassword password = new secretPassword();

        password.setId(requestDTO.getId());
        password.setName(requestDTO.getName());
        password.setUsername(requestDTO.getUsername());
        password.setPassword(requestDTO.getPassword());
        password.setURI(requestDTO.getURI());

        return  password;
    }
}
