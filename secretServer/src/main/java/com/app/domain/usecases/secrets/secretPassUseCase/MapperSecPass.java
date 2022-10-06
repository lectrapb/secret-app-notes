package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.secretPassword.secretPasswordRequestDTO;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;

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

    public static secretPassword toDeletePass(secretDeleteRequestDTO requestDTO){
        //exception
        secretPassword password = new secretPassword();

        password.setId(requestDTO.getId());
        return  password;
    }

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
