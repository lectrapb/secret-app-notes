package com.app.domain.usecases.auth.domain;

import com.app.domain.model.token.Token;

import java.util.UUID;

public class TokenMother {

    public static Token tokenOk(){

        Token token = new Token();
        token.setValid(true);
        token.setUid(ConstanTest.UID);
        return token;
    }

    public static Token uidBad(){

        Token token = new Token();
        token.setValid(true);
        token.setUid(UUID.randomUUID().toString());
        return token;
    }
}
