package com.app.domain.model.user.valueobject;

import com.app.config.BusinessException;
import com.app.domain.model.util.Constant;
import com.app.domain.model.util.ValueObject;
import lombok.Data;

@Data
public class PasswordUser implements ValueObject {

    private final Integer LENGTH_PASS = 5;

    private final String  password;

    public PasswordUser(String password) {
        if(password == null){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }else if( password.length() < LENGTH_PASS){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        this.password = password;
    }

    @Override
    public String value() {
        return password;
    }
}
