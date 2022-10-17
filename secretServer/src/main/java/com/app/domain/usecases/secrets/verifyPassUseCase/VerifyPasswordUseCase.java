package com.app.domain.usecases.secrets.verifyPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.secretPasswordResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.model.verifyPass.VerifyPassRequestDTO;
import com.app.domain.model.verifyPass.VerifyPassResponseDTO;
import com.app.domain.model.verifyPass.gateway.VerifyPass;
import com.app.domain.usecases.secrets.verifyPassUseCase.Mapper.MapperVerifyPass;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class VerifyPasswordUseCase {

    private final VerifyPass verifyPass;

    public Mono<VerifyPassResponseDTO> verifyPassword(VerifyPassRequestDTO verifyPassRequestDTO){
        return Mono.fromCallable(()-> verifyPassRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperVerifyPass::toVerifyPass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .flatMap(passVerify ->{
                    return verifyPass.validatePass(passVerify.getPassword());
                })
                .flatMap(o -> prepareOkResponse(o));
    }

    private Mono<VerifyPassResponseDTO> prepareOkResponse(String password) {
        return Mono.fromCallable(VerifyPassResponseDTO::new)
                .map(dto -> {
                    if(password.equals(Constant.SUCCESSFUL_INSECURE_PASSWORD_CODE)){
                        dto.setCode(Constant.SUCCESSFUL_INSECURE_PASSWORD_CODE);
                        return dto;
                    }
                    dto.setCode(Constant.SUCCESSFUL_VERIFY_PASSWORD_CODE);
                    return dto;
                });
    }
}
