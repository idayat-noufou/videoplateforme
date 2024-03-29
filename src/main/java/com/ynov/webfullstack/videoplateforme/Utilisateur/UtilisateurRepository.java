package com.ynov.webfullstack.videoplateforme.Utilisateur;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID>{
    List<Utilisateur> findByCategorieId(UUID id);
    List<Utilisateur> findByUsernameContainingIgnoreCase(String username);
    

     
    
}