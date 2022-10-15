package com.app.domain.model.token;


import lombok.Data;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
public class KeyPair {

    private PrivateKey privateKey;
    private PublicKey publicKey;
}
