package com.app.domain.usecases.auth.signUpUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserSignUpResquestDTO;
import com.app.domain.model.util.Constant;

import java.util.UUID;

public class MapperSignUp {
    public static User toUser(UserSignUpResquestDTO requestDTO) {

             User user = new User();

            if(requestDTO.getEmail() != null){
                user.setUid(UUID.randomUUID().toString());
                user.setName(requestDTO.getName());
                user.setEmail(requestDTO.getEmail());
                user.setPassword(requestDTO.getPassword());
                user.setGoogle(requestDTO.isGoogle());
                user.setRole(requestDTO.getRole());
                user.setImage(requestDTO.getImage());
            }else {
                throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
            }

            return user;
    }
}
