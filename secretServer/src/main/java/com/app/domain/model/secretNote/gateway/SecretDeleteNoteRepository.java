package com.app.domain.model.secretNote.gateway;

import reactor.core.publisher.Mono;

public interface SecretDeleteNoteRepository {
    Mono<String> delete(String id);
}
