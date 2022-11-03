package com.app.domain.model.token.gateway;

import org.jose4j.lang.JoseException;

public interface EncryptService {
    String encrypt(String clave) throws JoseException;
}
