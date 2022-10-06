package com.app.infraestructure.helpers;

import com.app.domain.model.token.KeyPair;

import java.io.BufferedReader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyFactoryToken {

    public static final  String  PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCU4z6uC3DWEhx7\n" +
            "xT82gXJfcKggaQibgxG5s3LVa3t8ylYAmfrgOfjMEil3JLbtogtp7tp6xKq0oI3U\n" +
            "+RRgoenD5DvpQ5Qt3C4CRfy3Tmn1gPMyOnBNgtMQOumtdMSDBNzXY3NfECx1m/TH\n" +
            "GUZAyKJ40lAagW59gteoAoboV3Z+eeHzt1M9/4drmwehVwDi4v8mbe+D9dJQ+pBq\n" +
            "jT3l04xzzSW7j/19VtouYDRf0oSj30BuFM0c4EP2pnf3PyzheDbQ5WJGSXttECYT\n" +
            "ualhw80gRyKl8EtedpAVO8tQ+zpL2s+kWhaHymsBN4a9A2m7Bq9JUGoYQlQFcy8x\n" +
            "9toW6+/zAgMBAAECggEARZzih5nNzil1q+ETpzZq9XjFAzJY1e04cm0WufahLD2m\n" +
            "bG2SQRiZ3UB4pr5B0WW8lHEbMd+WORIAfp1fC7dohzDFWlFAjRivYly6xjIhlKoQ\n" +
            "C2ILdBJBAQuqinFYGDJD12kmXkI/p1Wd4yFMGao1XOxoi5wMvx5YGrPjw+pw+rzn\n" +
            "diXDacOXs19G0b1KVvBimm59z8ZdjKmt6U0RAEFqFE0DHN3ePezq5S1K+5WEd9M0\n" +
            "LnRZyv5q9kl0UzFDZh5WuNaRoQGDPMd97u+IMRKfkZKsRLsenkzdfAfJ9VgdI4Z9\n" +
            "X0B9XWhKJbfbgvG+okaizdJ6AwptpWu+cBGfWNRIwQKBgQDFvaYEnUwjckQFHBHK\n" +
            "KN4NUt1izkOkudJs+V8V5IvYRLsrBzjqxD6/yJQFpE3phMR+hGruu0qxFQkWFUxe\n" +
            "9ppzU636SEyfLdtbtBVJqqbcCXohL/BX/7CT17WCd2sCoDHlKFdXtzYh3tifcYLH\n" +
            "i9bk78caPqNrzM4vjzQCtoYgsQKBgQDAwOjxD46ijuURpomJXi4aWxmDFUVB1VIw\n" +
            "wEXhcrs6Sx2dHirVvI9ZW1HFOVVRm2O1lZQ2LR2vtx69MUDUtN1bB9/Yv6c9rNhd\n" +
            "2C+Z8QDU6ng1WP94tq8pmRiwkgf3rYA9EBrElIveNnR7OwROAPVu6ez3YST96TKo\n" +
            "0SDZ/wDj4wKBgHCz8/DDWocFdIFZC0am3lDHvwi2uVUk2CtmQmyu84nTOr5+glFE\n" +
            "guM6RiulBwcmtfvKK0xgEWU2sRQWFY42jbdXpVx2Yr2DHHh6/nsbOblF6YcK1LjD\n" +
            "MfhBxOFUgPhhJ68NvCJxePf7FIpj3v8u089uXpO1yd0CQS5YN3OoZnAxAoGBAL/6\n" +
            "IsedIAnl2l53CPsJE0/qtgEpmT4VcfPePHtrovBPSiJnnnAcioWL/2g4TzKlWmzL\n" +
            "mr9OtgeBkhsKyDBSJzl4IUmmsLIryZ/GJrrjH1nwYWN4zZF0GgeLVNzfxRG4+0VW\n" +
            "GeL6tEfQ4ritsf7i04/xarOETKybq3oaN+cHJOLTAoGBAL0P2NB4q9ykBCprW2ug\n" +
            "4Z2QAjscND4uXi16kPEIAF39MsOYJfMOdMuiq1MqsqQJnO8iflgbeLhrfE0XB5dm\n" +
            "CRz12ecocsj5FOakqTFAYZ1AcziWEm6MFFScM2xnG8zTjoYZ3yo3WIv0iEhAjPYZ\n" +
            "TS7z+MSfhpjQT8mvAA8rc5aj\n" +
            "-----END PRIVATE KEY-----";

    public static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlOM+rgtw1hIce8U/NoFy\n" +
            "X3CoIGkIm4MRubNy1Wt7fMpWAJn64Dn4zBIpdyS27aILae7aesSqtKCN1PkUYKHp\n" +
            "w+Q76UOULdwuAkX8t05p9YDzMjpwTYLTEDrprXTEgwTc12NzXxAsdZv0xxlGQMii\n" +
            "eNJQGoFufYLXqAKG6Fd2fnnh87dTPf+Ha5sHoVcA4uL/Jm3vg/XSUPqQao095dOM\n" +
            "c80lu4/9fVbaLmA0X9KEo99AbhTNHOBD9qZ39z8s4Xg20OViRkl7bRAmE7mpYcPN\n" +
            "IEcipfBLXnaQFTvLUPs6S9rPpFoWh8prATeGvQNpuwavSVBqGEJUBXMvMfbaFuvv\n" +
            "8wIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    public static KeyPair getKeyPair(){

        KeyPair keyPair = new KeyPair();
        try{
            keyPair.setPrivateKey(getPrivateKey());
            keyPair.setPublicKey(getPublicKey());
        }catch(Exception ex){
            System.out.println("Error"+ex.getMessage());
        }
        return keyPair;
    }



    private static PrivateKey getPrivateKey(){

        try{
            StringBuilder pkcs8Lines = new StringBuilder();
            BufferedReader rdr = new BufferedReader(new StringReader(PRIVATE_KEY));
            String line;
            while ((line = rdr.readLine()) != null) {
                pkcs8Lines.append(line);
            }
            String pkcs8Pem = pkcs8Lines.toString();
            pkcs8Pem = pkcs8Pem.replace("-----BEGIN PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\s+","");

            byte [] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            java.security.KeyFactory keyFactory= java.security.KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }catch(Exception ex){
            System.out.println("Error"+ex.getMessage());
        }

        return null;
    }

    private static PublicKey getPublicKey(){
        try{
            StringBuilder lines = new StringBuilder();
            BufferedReader rdr = new BufferedReader(new StringReader(PUBLIC_KEY));
            String line;
            while ((line = rdr.readLine()) != null) {
                lines.append(line);
            }
            String publicKeyPEM = lines.toString();
            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replaceAll("\\s+","");

            // decode to get the binary DER representation
            byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);

            java.security.KeyFactory keyFactory= java.security.KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyDER));

        }catch(Exception ex){

        }
        return  null;
    }

}
