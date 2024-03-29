package com.ynov.webfullstack.videoplateforme.Categorie;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CategorieRepository extends JpaRepository<Categorie, UUID>{
    Categorie findByTitre(String name);
}
