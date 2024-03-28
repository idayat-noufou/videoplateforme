package com.ynov.webfullstack.videoplateforme.Utilisateur;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ynov.webfullstack.videoplateforme.Categorie.Categorie;
import com.ynov.webfullstack.videoplateforme.Categorie.CategorieRepository;

@RestController
public class UtilisateurController {
    private final UtilisateurRepository repository;
    private final CategorieRepository repositoryCategorie;
    UtilisateurController(UtilisateurRepository repository, CategorieRepository repositoryCategorie) {
        this.repository = repository;
        this.repositoryCategorie = repositoryCategorie;
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> all() {
        return repository.findAll();
    }

    @GetMapping("/utilisateurs/{id}") 
    public Utilisateur getById(@RequestParam UUID id) {
        Utilisateur utilisateur = repository.findById(id).orElse(null);
        if(utilisateur != null) return utilisateur;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cound not find user with id " + id);
    }

    
    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur user= utilisateur;
        if (utilisateur.getId() == null) user = new Utilisateur(utilisateur.getUsername());
        user.setPassword("passuser");
        Categorie categorie = repositoryCategorie.findByTitre("Utilisateur");
        user.setCategorie(categorie);
        if(repository.existsById(utilisateur.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot override already existing user");

        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(user));
    }

    @DeleteMapping("/utilisateurs/{id}")
    public void deleteImage(@PathVariable UUID id) {
        if (repository.findById(id).orElse(null) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    @GetMapping("/utilisateursByRole") 
    public List <Utilisateur> getByRoleId(@RequestParam UUID roleId) {
        List <Utilisateur> utilisateurs = repository.findByCategorieId(roleId);
        if(utilisateurs != null) return utilisateurs;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cound not find user with roleId " + roleId);
    }

    @GetMapping("/utilisateursByUsername") 
    public List<Utilisateur> findByUsername(@RequestParam String username) {
        List <Utilisateur> utilisateurs= repository.findByUsername(username);
        if(utilisateurs != null) return utilisateurs;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cound not find user with username " + username);
    }

}
