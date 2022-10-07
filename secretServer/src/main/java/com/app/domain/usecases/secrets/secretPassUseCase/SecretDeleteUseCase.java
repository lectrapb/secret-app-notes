package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretDeletePassRepository;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPasswordResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.mapper.MapperDeletePass;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretDeleteUseCase {

    private SecretDeletePassRepository deletePassRepository;

    public Mono<secretPasswordResponseDTO> deletePassword(secretDeleteRequestDTO deleteRequestDTO){
        System.out.println("usecase_delete" + deleteRequestDTO);
        return Mono.fromCallable(()-> deleteRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperDeletePass::toDeletePass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .map(secretId -> deletePassRepository.delete(secretId.getId()))
                .flatMap(requestDTO -> prepareOkResponse());

    }

    private Mono<secretPasswordResponseDTO> prepareOkResponse() {

        return Mono.fromCallable(secretPasswordResponseDTO::new)
                .map(dto -> {
                    dto.setCode(Constant.SUCCESSFUL_DELETE_PASSWORD_CODE);
                    return dto;
                });
    }
}
