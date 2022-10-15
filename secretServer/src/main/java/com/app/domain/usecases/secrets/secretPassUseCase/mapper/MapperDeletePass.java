package com.app.domain.usecases.secrets.secretPassUseCase.mapper;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.util.Constant;

public class MapperDeletePass {

    public static secretPassword toDeletePass(secretDeleteRequestDTO requestDTO){
        secretPassword password = new secretPassword();
        if(requestDTO.getId() == null || requestDTO.getId() == ""){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        password.setId(requestDTO.getId());
        return  password;
    }

}
