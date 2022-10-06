package com.app.infraestructure.helpers;

import com.app.domain.model.token.KeyPair;
import com.app.domain.model.token.Token;
import com.app.domain.model.token.gateway.TokenService;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class TokenServiceImpl implements TokenService {

    private KeyPair keyPair = KeyFactoryToken.getKeyPair();
    private static  final String AUDIENCE = "app-jwt";
    private static  final String USER = "service-name";

    @Override
    public String createToken(String uuid) {
        try{
            // Create the Claims, which will be the content of the JWT
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(USER);  // who creates the token and signs it
            claims.setAudience(AUDIENCE); // to whom the token is intended to be sent
            claims.setExpirationTimeMinutesInTheFuture(1); // time when the token will expire (1 minutes from now)
            claims.setGeneratedJwtId(); // a unique identifier for the token
            claims.setIssuedAtToNow();  // when the token was issued/created (now)
            claims.setNotBeforeMinutesInThePast(1); // time before which the token is not yet valid (2 minutes ago)
            claims.setSubject("subject"); // the subject/principal is whom the token is about
            claims.setClaim("uuid", uuid); // additional claims/attributes about the subject can be added

            JsonWebSignature jws = new JsonWebSignature();

            // The payload of the JWS is JSON content of the JWT Claims
            jws.setPayload(claims.toJson());
            // The JWT is signed using the private key
            jws.setKey(keyPair.getPrivateKey());
            // Set the Key ID (kid) header because it's just the polite thing to do.
            jws.setKeyIdHeaderValue("k1");

            // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

            return jws.getCompactSerialization();

        }catch(Exception ex ){
            System.out.println("Error generando token"+ex.getMessage());
        }
        return null;
    }

    @Override
    public Token validateToken(String token) {

        Token tokenData = new Token();
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer(USER) // whom the JWT needs to have been issued by
                .setExpectedAudience(AUDIENCE) // to whom the JWT is intended for
                .setVerificationKey(keyPair.getPublicKey()) // verify the signature with the public key
                .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                        AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256) // which is only RS256 here
                .build(); // create the JwtConsumer instance

        try
        {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            String uid = jwtClaims.getClaimValue("uid", String.class);
            tokenData.setValid(true);
            tokenData.setUid(uid);
            return tokenData;
        }
        catch (InvalidJwtException e)
        {
            System.out.println("Invalid JWT! " + e);
            if (e.hasExpired())
            {
                try {
                    System.out.println("JWT expired at " + e.getJwtContext().getJwtClaims().getExpirationTime());
                } catch (MalformedClaimException malformedClaimException) {
                    malformedClaimException.printStackTrace();
                }
            }

            // Or maybe the audience was invalid
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID))
            {
                try {
                    System.out.println("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
                } catch (MalformedClaimException malformedClaimException) {
                    malformedClaimException.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }

        tokenData.setValid(false );
        return tokenData;
    }
}
