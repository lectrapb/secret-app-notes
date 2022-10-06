package com.app.domain.usecases.auth.domain;

import com.app.domain.model.user.UserLoginRequestDTO;

public class LoginReqMother {

    public static UserLoginRequestDTO dataNull() {
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setEmail(null);
        requestDTO.setPassword(null);
        return requestDTO;
    }

    public static UserLoginRequestDTO dataOk() {
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setEmail(ConstanTest.EMAIL_LOGIN);
        requestDTO.setPassword(ConstanTest.PASSWORD_LOGIN);
        return requestDTO;
    }


}
