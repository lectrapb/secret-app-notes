package com.app.domain.model.password;

public interface PasswordEncryptService {

    String encryptPassword(String password);
    boolean checkPassword(String currentPass, String encryptPass);
}
