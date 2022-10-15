package com.app.domain.usecases.auth.domain;

import com.app.domain.model.user.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UserMother {

    public static Mono<User> dataOk(){

     return Mono.just(new User()).map(user -> {
            user.setUid(UUID.randomUUID().toString());
            user.setPassword(ConstanTest.PASSWORD_LOGIN);
            user.setEmail(ConstanTest.EMAIL_LOGIN);
            return user;
        });
    }
}
