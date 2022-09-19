package com.app.domain.usecases.auth.registerUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserRegisterResquestDTO;
import com.app.domain.model.util.Constant;

import java.util.UUID;

public class MapperRegister {
    public static User toUser(UserRegisterResquestDTO requestDTO) {

           User user = new User();
            try{
                user.setUid(UUID.randomUUID().toString());
                user.setName(requestDTO.getName());
                user.setEmail(requestDTO.getEmail());
                user.setPassword(requestDTO.getPassword());
                user.setGoogle(requestDTO.isGoogle());
                user.setRole(requestDTO.getRole());
                user.setImage(requestDTO.getImage());
            }catch(Exception ex ){
                throw  new BusinessException(Constant.MISSING_ARGUMENTS_DESCRIPTION);
            }

            return user;
    }
}
