package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretUpdatePassRepository;
import com.app.domain.model.secretPassword.secretPasswordResponseDTO;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.mapper.MapperUpdatePass;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretUpdateUseCase {

    private SecretUpdatePassRepository updatePassRepository;

    public Mono<secretPasswordResponseDTO> updatePassword(secretUpdateRequestDTO updateRequestDTO){
        return Mono.fromCallable(()-> updateRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperUpdatePass::toUpdatePass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .flatMap(updatePassRepository::update)
                .flatMap(requestDTO -> prepareOkResponse(requestDTO));

    }

    private Mono<secretPasswordResponseDTO> prepareOkResponse(String id) {

        return Mono.fromCallable(secretPasswordResponseDTO::new)
                .map(dto -> {
                    if(id.equals(Constant.SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE)){
                        dto.setCode(Constant.SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE);
                        return dto;
                    }
                    dto.setCode(Constant.SUCCESSFUL_UPDATE_PASSWORD_CODE);
                    return dto;
                });
    }
}
