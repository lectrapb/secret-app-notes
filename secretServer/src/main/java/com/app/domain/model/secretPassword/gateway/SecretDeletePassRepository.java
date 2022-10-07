package com.app.domain.model.secretPassword.gateway;

import reactor.core.publisher.Mono;

public interface SecretDeletePassRepository {
    Mono<Void> delete(String id);
}
