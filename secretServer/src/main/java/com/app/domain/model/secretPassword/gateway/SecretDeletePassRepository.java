package com.app.domain.model.secretPassword.gateway;

import reactor.core.publisher.Mono;

public interface SecretDeletePassRepository {
    Mono<String> delete(String id);
}
