package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretDeleteNoteRepository;
import com.app.domain.model.secretNote.secretDeleteNoteRequestDTO;
import com.app.domain.model.secretNote.secretNoteResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretNoteUseCase.mapper.MapperDeleteNote;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretDeleteNoteUseCase {

    private SecretDeleteNoteRepository deleteNoteRepository;

    public Mono<secretNoteResponseDTO> deleteNote(secretDeleteNoteRequestDTO deleteRequestDTO){
        return Mono.fromCallable(()-> deleteRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperDeleteNote::toDeletePass)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .flatMap(secretId -> {
                    return deleteNoteRepository.delete(secretId.getId());
                })
                .flatMap(requestDTO -> prepareOkResponse(requestDTO));

    }

    private Mono<secretNoteResponseDTO> prepareOkResponse(String id) {
        return Mono.fromCallable(secretNoteResponseDTO::new)
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
