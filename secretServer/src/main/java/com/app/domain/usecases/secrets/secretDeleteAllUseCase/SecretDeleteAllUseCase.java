package com.app.domain.usecases.secrets.secretDeleteAllUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretDeleteAll.gateway.SecretDeleteAllRepository;
import com.app.domain.model.secretDeleteAll.secretDeleteAllRequestDTO;
import com.app.domain.model.secretDeleteAll.secretDeleteAllResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretDeleteAllUseCase.Mapper.MapperDeleteAll;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretDeleteAllUseCase {

    private SecretDeleteAllRepository deleteAllRepository;

    public Mono<secretDeleteAllResponseDTO> deleteAllSecret(secretDeleteAllRequestDTO deleteAllRequestDTO){
        return Mono.fromCallable(() -> deleteAllRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperDeleteAll::toDeleteAll)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .flatMap(deleteSecret -> {
                    return deleteAllRepository.deleteAll(deleteSecret.getUser_uid());
                })
                .flatMap(requestDelDTO -> prepareOkResponse(requestDelDTO));
    }

    private Mono<secretDeleteAllResponseDTO> prepareOkResponse(String id) {
        return Mono.fromCallable(secretDeleteAllResponseDTO::new)
                .map(dto -> {
                    if(id.equals(Constant.SUCCESSFUL_DELETE_ZERO_NOTE_CODE)){
                        dto.setCode(Constant.SUCCESSFUL_DELETE_ZERO_NOTE_CODE);
                        return dto;
                    }
                    dto.setCode(Constant.SUCCESSFUL_DELETE_NOTE_CODE);
                    return dto;
                });
    }
}
