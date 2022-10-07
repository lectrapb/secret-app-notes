package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPassword;

public class MapperDeletePass {

    public static secretPassword toDeletePass(secretDeleteRequestDTO requestDTO){
        //exception
        secretPassword password = new secretPassword();

        password.setId(requestDTO.getId());
        return  password;
    }

}
