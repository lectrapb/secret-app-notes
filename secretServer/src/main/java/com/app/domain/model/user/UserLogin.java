package com.app.domain.model.user;

import com.app.domain.model.user.valueobject.EmailUser;
import com.app.domain.model.user.valueobject.PasswordUser;
import lombok.Data;


@Data
public class UserLogin {

    private EmailUser email;
    private PasswordUser password;


    public UserLogin(EmailUser email, PasswordUser password) {

        this.email = email;
        this.password = password;
    }
}
