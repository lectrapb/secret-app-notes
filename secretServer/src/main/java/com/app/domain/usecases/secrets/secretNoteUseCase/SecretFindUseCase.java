package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretFindNoteRepository;
import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.secretNote.secretFindNoteResponseDTO;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class SecretFindUseCase {

    private SecretFindNoteRepository noteRepository;

    public Mono<List<secretFindNoteResponseDTO>> findNote(secretFindNoteRequestDTO noteRequestDTO){
        return Mono.fromCallable(()-> {
                    if(noteRequestDTO.getPage() == "" || noteRequestDTO.getRank() == "" || noteRequestDTO.getUser() == ""
                    || noteRequestDTO.getPage() == null || noteRequestDTO.getRank() == null ||
                            noteRequestDTO.getUser() == null){
                        throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
                    }
                    return noteRequestDTO;
                })
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .flatMap(RequestDTO -> {
                    return noteRepository.find(RequestDTO);
                });
    }
}
