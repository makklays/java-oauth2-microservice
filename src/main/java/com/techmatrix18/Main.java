package com.techmatrix18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.*;

/**
 * Main class to start the service discovery microservice application.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello Java OAuth2 microservice!");

        // generate private key and public key for JWT
        /*KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        System.out.println("--------- private key --------------------");
        System.out.println(privateKey);
        System.out.println("--------- public key ---------------------");
        System.out.println(publicKey);
        System.out.println("------------------------------------------");*/

        SpringApplication.run(Main.class, args);
    }
}

