package com.app.domain.model.secretPassword.gateway;

import com.app.domain.model.secretPassword.secretPassword;
import reactor.core.publisher.Mono;

public interface SecretPasswordRepository {

    Mono<Void> save(secretPassword secretPassword);
}
