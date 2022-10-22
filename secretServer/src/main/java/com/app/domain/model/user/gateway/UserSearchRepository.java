package com.app.domain.model.user.gateway;

import com.app.domain.model.user.User;
import reactor.core.publisher.Mono;


public interface UserSearchRepository {

    Mono<User> findByEmail(String email);

    Mono<User> findById(String id);
    
}
