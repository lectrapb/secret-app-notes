package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.config.BusinessException;
import com.app.config.PasswordValidate;
import com.app.domain.model.secretPassword.*;
import com.app.domain.model.util.Constant;
import com.app.domain.model.util.ValidatePass;
import com.enzoic.client.Enzoic;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class MapperCreatePass {

    public static secretPassword toSecretPass(secretPasswordRequestDTO requestDTO){
        secretPassword password = new secretPassword();

        if(requestDTO.getName() == "" || requestDTO.getName() == null){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        try {
            if (ValidatePass.validate(requestDTO.getPassword())){
                throw  new BusinessException(Constant.ERROR_INSECURE_PASSWORD_CODE);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
