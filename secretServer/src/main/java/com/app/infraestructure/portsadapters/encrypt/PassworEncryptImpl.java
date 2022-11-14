package com.app.infraestructure.portsadapters.encrypt;

import com.app.domain.model.password.PasswordEncryptService;
import org.springframework.stereotype.Service;

@Service
public class PassworEncryptImpl implements PasswordEncryptService {
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    public boolean checkPassword(String currentPass, String encryptPass) {
        return BCrypt.checkpw(currentPass, encryptPass);
    }
}
