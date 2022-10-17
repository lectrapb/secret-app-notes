package com.app.infraestructure.portsadapters.rds.adapter.verifyPass;

import com.app.domain.model.util.Constant;
import com.app.domain.model.verifyPass.gateway.VerifyPass;
import com.enzoic.client.Enzoic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class VerifyPassAdapter implements VerifyPass {
    
    private final Enzoic enzoic;

    @Autowired
    public VerifyPassAdapter(Enzoic enzoic) {
        this.enzoic = enzoic;
    }

    @Override
    public Mono<String> validatePass(String password) {
        try {
            if(enzoic.CheckPassword(password)){
                return Mono.just(Constant.SUCCESSFUL_INSECURE_PASSWORD_CODE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Mono.just(Constant.SUCCESSFUL_VERIFY_PASSWORD_CODE);
    }
}
