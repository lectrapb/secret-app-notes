package com.app.infraestructure.helpers;

import com.app.domain.model.token.gateway.EncryptService;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import java.security.Key;

public class EncryptServiceImpl implements EncryptService {
    Key key = new AesKey(ByteUtil.randomBytes(16));

    @Override
    public String encrypt(String clave) throws JoseException {
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setPayload(clave);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        String serializedJwe = jwe.getCompactSerialization();
        System.out.println("Serialized Encrypted JWE: " + serializedJwe);
        return null;
    }
}
