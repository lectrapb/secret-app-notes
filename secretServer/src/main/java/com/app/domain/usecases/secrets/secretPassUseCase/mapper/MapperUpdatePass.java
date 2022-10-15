package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import com.app.domain.model.util.Constant;

public class MapperUpdatePass {

    public static secretPassword toUpdatePass(secretUpdateRequestDTO requestDTO){
        secretPassword password = new secretPassword();
        if(requestDTO.getId() == null || requestDTO.getId() == "" || requestDTO.getName() == null || requestDTO.getName() == ""){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        password.setId(requestDTO.getId());
        password.setName(requestDTO.getName());
        password.setUsername(requestDTO.getUsername());
        password.setPassword(requestDTO.getPassword());
        password.setURI(requestDTO.getURI());

        return  password;
    }
}
