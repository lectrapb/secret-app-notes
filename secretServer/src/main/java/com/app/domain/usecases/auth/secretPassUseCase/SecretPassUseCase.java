package com.app.domain.usecases.auth.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretPasswordRepository;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPasswordRequestDTO;
import com.app.domain.model.secretPassword.secretPasswordResponseDTO;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretPassUseCase {

    private SecretPasswordRepository secretPasswordRepository;

    public Mono<secretPasswordResponseDTO> registerPassword(secretPasswordRequestDTO secretPasswordRequestDTO){
        System.out.println("usecase" + secretPasswordRequestDTO);
        return Mono.fromCallable(()-> secretPasswordRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperSecPass::toSecretPass)
                //.onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretPasswordRepository::save)
                .flatMap(requestDTO -> prepareOkResponse());

    }

    public Mono<secretPasswordResponseDTO> deletePassword(secretDeleteRequestDTO deleteRequestDTO){
        System.out.println("usecase_delete" + deleteRequestDTO);
        return Mono.fromCallable(()-> deleteRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperSecPass::toDeletePass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretId -> secretPasswordRepository.delete(secretId.getId()))
                .flatMap(requestDTO -> prepareOkResponse());

    }

    public Mono<secretPasswordResponseDTO> updatePassword(secretUpdateRequestDTO updateRequestDTO){
        return Mono.fromCallable(()-> updateRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperSecPass::toUpdatePass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretPasswordRepository::update)
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
