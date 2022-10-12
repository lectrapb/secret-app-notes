package com.app.domain.model.secretNote.gateway;

import com.app.domain.model.secretNote.secretNote;
import reactor.core.publisher.Mono;

public interface SecretCreateNoteRepository {

    Mono<Void> save(secretNote secretNote);
}
