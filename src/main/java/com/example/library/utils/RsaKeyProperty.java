package com.example.library.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Component
public class RsaKeyProperty {
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public RsaKeyProperty(){
        KeyPair keyPair=KeyGeneratorUtility.generatorRsaKey();
        this.rsaPublicKey=(RSAPublicKey) keyPair.getPublic();
        this.rsaPrivateKey=(RSAPrivateKey) keyPair.getPrivate();
    }
}
