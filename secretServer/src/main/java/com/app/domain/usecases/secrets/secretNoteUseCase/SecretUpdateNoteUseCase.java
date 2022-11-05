package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretUpdateNoteRepository;
import com.app.domain.model.secretNote.secretNoteResponseDTO;
import com.app.domain.model.secretNote.secretUpdateNoteRequestDTO;
import com.app.domain.model.token.gateway.EncryptService;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretNoteUseCase.mapper.MapperUpdateNote;
import lombok.AllArgsConstructor;
import org.jose4j.lang.JoseException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecretUpdateNoteUseCase {

    private SecretUpdateNoteRepository noteRepository;
    private EncryptService encryptService;

    public Mono<secretNoteResponseDTO> updateNote(secretUpdateNoteRequestDTO updateNoteRequestDTO){
        return Mono.fromCallable(()->updateNoteRequestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(data -> {
                    try {
                        data.setNotes(encryptService.encrypt(data.getNotes()));
                    } catch (JoseException e) {
                        throw new BusinessException(e.getMessage());
                    }
                    return data;
                })
                .map(MapperUpdateNote::toUpdateNote)
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .flatMap(noteRepository::update)
                .flatMap(requestDTO -> prepareOkResponse(requestDTO));
    }

    private Mono<secretNoteResponseDTO> prepareOkResponse(String id) {

        return Mono.fromCallable(secretNoteResponseDTO::new)
                .map(dto -> {
                    if(id.equals(Constant.SUCCESSFUL_UPDATE_ZERO_NOTE_CODE)){
                        dto.setCode(Constant.SUCCESSFUL_UPDATE_ZERO_NOTE_CODE);
                        return dto;
                    }
                    dto.setCode(Constant.SUCCESSFUL_UPDATE_NOTE_CODE);
                    return dto;
                });
    }
}
