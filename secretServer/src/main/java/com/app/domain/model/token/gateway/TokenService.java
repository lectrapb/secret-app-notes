package com.app.domain.model.token.gateway;

import com.app.domain.model.token.Token;
import reactor.core.publisher.Mono;

public interface TokenService {

    String createToken(String uuid);
    Token validateToken(String token);
}
