package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.*;
import com.app.domain.model.secretPassword.gateway.SecretPasswordRepository;
import com.app.domain.model.token.gateway.EncryptService;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.mapper.MapperCreatePass;
import lombok.AllArgsConstructor;
import org.jose4j.lang.JoseException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretCreatePassUseCase {

    private SecretPasswordRepository secretPasswordRepository;
    private EncryptService encryptService;

    public Mono<secretPasswordResponseDTO> registerPassword(secretPasswordRequestDTO secretPasswordRequestDTO){
        System.out.println("usecase" + secretPasswordRequestDTO);
        return Mono.fromCallable(()-> secretPasswordRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(data -> {
                            try {
                                data.setPassword(encryptService.encrypt(data.getPassword()));
                            } catch (JoseException e) {
                                throw new BusinessException(e.getMessage());
                            }
                            return data;
                        })
                .map(MapperCreatePass::toSecretPass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretPasswordRepository::save)
                .flatMap(requestDTO -> prepareOkResponse());

    }

    private Mono<secretPasswordResponseDTO> prepareOkResponse() {

        return Mono.fromCallable(secretPasswordResponseDTO::new)
                .map(dto -> {
                    dto.setCode(Constant.SUCCESSFUL_SECRET_PASSWORD_CODE);
                    return dto;
                });
    }

}
