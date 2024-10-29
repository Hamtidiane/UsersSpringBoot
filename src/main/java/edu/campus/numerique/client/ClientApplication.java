package edu.campus.numerique.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

//Les repositories sont des interfaces qui gèrent les opérations CRUD pour les entités. Créez un repository pour l’entité Client