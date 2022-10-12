package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretCreateNoteRepository;
import com.app.domain.model.secretNote.secretNoteRequestDTO;
import com.app.domain.model.secretNote.secretNoteResponseDTO;
import com.app.domain.model.secretPassword.secretPasswordResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretNoteUseCase.mapper.MapperCreateNote;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretCreateUseCase {

    private SecretCreateNoteRepository secretCreateNoteRepository;

    public Mono<secretNoteResponseDTO> registerNote(secretNoteRequestDTO secretNoteRequestDTO){
        return Mono.fromCallable(()-> secretNoteRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperCreateNote::toSecretNote)
                .map(secretCreateNoteRepository::save)
                .flatMap(requestDTO -> prepareOkResponse());
    }

    private Mono<secretNoteResponseDTO> prepareOkResponse() {

        return Mono.fromCallable(secretNoteResponseDTO::new)
                .map(dto -> {
                    dto.setCode(Constant.SUCCESSFUL_SECRET_NOTE_CODE);
                    return dto;
                });
    }
}
