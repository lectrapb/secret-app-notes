package com.app.domain.model.util;

import com.app.config.PasswordValidate;
import com.enzoic.client.Enzoic;

import java.io.IOException;

public class ValidatePass {

    public static boolean validate(String password) throws IOException {
        PasswordValidate passwordValidate = new PasswordValidate();
        Enzoic enzoic = new Enzoic(passwordValidate.getApi_key(), passwordValidate.getApi_secret());
        if(enzoic.CheckPassword(password)){
            return true;
        }
        return false;
    }
}
