package com.app.infraestructure.portsadapters.rds.adapter.verifyPass;

import com.app.domain.model.secretPassword.gateway.SecretPassVerify;
import com.enzoic.client.Enzoic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VerifyPassAdapter implements SecretPassVerify {
    
    private final Enzoic enzoic;

    @Autowired
    public VerifyPassAdapter(Enzoic enzoic) {
        this.enzoic = enzoic;
    }

    @Override
    public boolean validatePass(String password) {
        try {
            if(enzoic.CheckPassword(password)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
