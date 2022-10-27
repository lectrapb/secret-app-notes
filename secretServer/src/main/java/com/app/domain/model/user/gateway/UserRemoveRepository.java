package com.app.domain.model.user.gateway;


import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserRemoveRepository {

    Mono<Void> deleteById(String id);
}
