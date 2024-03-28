package com.ynov.webfullstack.videoplateforme.Categorie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(CategorieRepository repository) {
        return args -> {
            Categorie categorie = new Categorie("Utilisateur", "Rôle par défaut");
            repository.save(categorie);
        };
    }
}
