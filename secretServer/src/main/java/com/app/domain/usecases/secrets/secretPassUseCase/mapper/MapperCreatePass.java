package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.*;
import com.app.domain.model.secretPassword.gateway.SecretPassVerify;
import com.app.domain.model.util.Constant;

import java.util.UUID;

public class MapperCreatePass {

    public static secretPassword toSecretPass(secretPasswordRequestDTO requestDTO, SecretPassVerify passVerify){
        secretPassword password = new secretPassword();

        if(requestDTO.getName() == "" || requestDTO.getName() == null){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }

        if (passVerify.validatePass(requestDTO.getPassword())){
            throw  new BusinessException(Constant.ERROR_INSECURE_PASSWORD_CODE);
        }


        password.setId(UUID.randomUUID().toString());
        password.setName(requestDTO.getName());
        password.setUsername(requestDTO.getUsername());
        password.setPassword(requestDTO.getPassword());
        password.setURI(requestDTO.getURI());
        password.setUser_uid(requestDTO.getUser_uid());

        return  password;
    }

}
