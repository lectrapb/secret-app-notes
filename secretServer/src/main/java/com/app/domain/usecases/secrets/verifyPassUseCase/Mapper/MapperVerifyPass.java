package com.app.domain.usecases.secrets.verifyPassUseCase.Mapper;

import com.app.config.BusinessException;
import com.app.domain.model.util.Constant;
import com.app.domain.model.verifyPass.VerifyPassRequestDTO;
import com.app.domain.model.verifyPass.VerifyPassword;

public class MapperVerifyPass {

    public static VerifyPassword toVerifyPass(VerifyPassRequestDTO verifyPassRequestDTO){
        VerifyPassword verifyPassword = new VerifyPassword();
        if(verifyPassRequestDTO.getPassword() == null || verifyPassRequestDTO.getPassword() == ""){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        verifyPassword.setPassword(verifyPassRequestDTO.getPassword());
        return verifyPassword;
    }
}
