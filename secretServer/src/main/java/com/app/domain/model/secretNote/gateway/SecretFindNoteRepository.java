package com.app.domain.model.secretNote.gateway;

import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.secretNote.secretFindNoteResponseDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SecretFindNoteRepository {
    Mono<List<secretFindNoteResponseDTO>> find(secretFindNoteRequestDTO noteRequestDTO);
}
