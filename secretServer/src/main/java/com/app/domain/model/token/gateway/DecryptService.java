package com.app.domain.model.token.gateway;

import org.jose4j.lang.JoseException;

public interface DecryptService {
    String decrypt(String clave) throws JoseException;
}
