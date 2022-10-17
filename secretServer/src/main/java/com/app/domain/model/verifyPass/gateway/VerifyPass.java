package com.app.domain.model.verifyPass.gateway;

import reactor.core.publisher.Mono;

public interface VerifyPass {

    Mono<String> validatePass(String password);
}
