package com.app.domain.model.user.valueobject;

import com.app.config.BusinessException;
import com.app.domain.model.util.Constant;
import com.app.domain.model.util.ValueObject;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class EmailUser implements ValueObject {

    private static final String MAIL_REGEX = "^(.+)@(.+)$";
    private static final Pattern PATTERN_MAIL = Pattern.compile(MAIL_REGEX);

    private final  String email;

    public EmailUser(String email) {
        if(email == null){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }else if(!PATTERN_MAIL.matcher(email).find()){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        this.email = email;
    }

    @Override
    public String value() {
        return email;
    }
}
