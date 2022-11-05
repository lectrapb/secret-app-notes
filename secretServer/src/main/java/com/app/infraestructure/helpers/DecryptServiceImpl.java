package com.app.infraestructure.helpers;

import com.app.domain.model.token.gateway.DecryptService;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

@Service
public class DecryptServiceImpl implements DecryptService{
    JsonWebKey jwk = JsonWebKey.Factory.newJwk(SimetricKey.jwkJson);

    public DecryptServiceImpl() throws JoseException {
    }

    public String decrypt(String clave) throws JoseException {
        JsonWebEncryption receiverJwe = new JsonWebEncryption();

        // Set the algorithm constraints based on what is agreed upon or expected from the sender
        AlgorithmConstraints algConstraints = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.DIRECT);
        receiverJwe.setAlgorithmConstraints(algConstraints);
        AlgorithmConstraints encConstraints = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        receiverJwe.setContentEncryptionAlgorithmConstraints(encConstraints);

        // Set the compact serialization on new Json Web Encryption object
        receiverJwe.setCompactSerialization(clave);

        // Symmetric encryption, like we are doing here, requires that both parties have the same key.
        // The key will have had to have been securely exchanged out-of-band somehow.
        receiverJwe.setKey(jwk.getKey());

        // Get the message that was encrypted in the JWE. This step performs the actual decryption steps.
        String plaintext = receiverJwe.getPlaintextString();

        // And do whatever you need to do with the clear text message.
        //System.out.println("plaintext: " + plaintext);
        return plaintext;
    }
}
