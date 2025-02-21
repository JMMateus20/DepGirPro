package com.backend.tlg.depgirpro.services.auth;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CryptoKeyGenerator {

    @Value("${security.jwt.key-size-bits}")
    private int keySizeBits;

    public String generateBase64CryptoKey() {
        byte[] cryptoKey=generateRandomBytes(keySizeBits / 8);
        return Base64.toBase64String(cryptoKey);
    }


    private byte[] generateRandomBytes(int length) {
        try {
            SecureRandom secureRandom= new SecureRandom();
            byte[] randomBytes=new byte[length];
            secureRandom.nextBytes(randomBytes);
            return randomBytes;
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar bytes aleatorios " + e.getLocalizedMessage() + ", " + e.getMessage());

        }

    }
}
