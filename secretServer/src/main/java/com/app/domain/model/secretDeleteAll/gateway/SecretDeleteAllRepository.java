package com.app.domain.model.secretDeleteAll.gateway;

import reactor.core.publisher.Mono;

public interface SecretDeleteAllRepository {
    Mono<String> deleteAll(String id);
}
