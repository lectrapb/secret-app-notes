package com.app.domain.model.secretPassword.gateway;

import com.app.domain.model.secretPassword.secretPassword;
import reactor.core.publisher.Mono;

public interface SecretUpdatePassRepository {
    Mono<String> update(secretPassword password);
}
