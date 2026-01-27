package com.techmatrix18.security;

import org.springframework.stereotype.Component;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Provides RSA private key for JWT signing.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 27.01.2026
 */
@Component
public class JwtKeyProvider {

    private final RSAPrivateKey privateKey;

    public JwtKeyProvider() throws Exception {
        this.privateKey = this.loadPrivateKey(Paths.get("jwt-private.pem"));
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    private RSAPrivateKey loadPrivateKey(Path path) throws Exception {
        String key = Files.readString(path)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}

