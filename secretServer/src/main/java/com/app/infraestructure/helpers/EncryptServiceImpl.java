package com.app.infraestructure.helpers;

import com.app.domain.model.token.gateway.EncryptService;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;
@Service
public class EncryptServiceImpl implements EncryptService {
    JsonWebKey jwk = JsonWebKey.Factory.newJwk(SimetricKey.jwkJson);

    public EncryptServiceImpl() throws JoseException {
    }

    @Override
    public String encrypt(String clave) throws JoseException {
        JsonWebEncryption senderJwe = new JsonWebEncryption();
        senderJwe.setPlaintext(clave);
        senderJwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.DIRECT);
        senderJwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        senderJwe.setKey(jwk.getKey());
        String compactSerialization = senderJwe.getCompactSerialization();
        return compactSerialization;
    }

}
