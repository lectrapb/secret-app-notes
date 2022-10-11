package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretSearchPass;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class SecretSearchUseCase {

    private SecretSearchPass secretSearchPass;


    public Mono<List<secretFindResponseDTO>> findPassword(secretFindRequestDTO findRequestDTO){
        return Mono.fromCallable(()-> findRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .flatMap(requestDTO -> {
                    return secretSearchPass.find(requestDTO);
                });

    }
}