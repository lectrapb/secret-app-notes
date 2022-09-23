package com.app.domain.model.user.gateway;

import com.app.domain.model.user.User;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserSignUpRepository {

    Mono<Void> save(User user);

}
