package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.*;
import com.app.domain.model.secretPassword.gateway.SecretPasswordRepository;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.mapper.MapperCreatePass;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretCreatePassUseCase {

    private SecretPasswordRepository secretPasswordRepository;

    public Mono<secretPasswordResponseDTO> registerPassword(secretPasswordRequestDTO secretPasswordRequestDTO){
        System.out.println("usecase" + secretPasswordRequestDTO);
        return Mono.fromCallable(()-> secretPasswordRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperCreatePass::toSecretPass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretPasswordRepository::save)
                .flatMap(requestDTO -> prepareOkResponse());

        //Test nulos, camino feliz, nulo en campos no nulos

    }

    private Mono<secretPasswordResponseDTO> prepareOkResponse() {

        return Mono.fromCallable(secretPasswordResponseDTO::new)
                .map(dto -> {
                    dto.setCode(Constant.SUCCESSFUL_SECRET_PASSWORD_CODE);
                    return dto;
                });
    }

}
