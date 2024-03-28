package com.ynov.webfullstack.videoplateforme.Categorie;
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

@RestController
public class CategorieController {
     private final CategorieRepository repository;
    CategorieController(CategorieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    public List<Categorie> all() {
        return repository.findAll();
    }

    @GetMapping("/categories/{id}") 
    public Categorie getById(@RequestParam UUID id) {
        Categorie categorie = repository.findById(id).orElse(null);
        if(categorie != null) return categorie;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cound not find category with id " + id);
    }

    
     @PostMapping("/categories")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie categ= categorie;
        if (categorie.getId() == null) categ = new Categorie(categorie.getTitre(), categorie.getDescription());
        if(repository.existsById(categorie.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot override already existing category");

        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(categ));
    }
    @DeleteMapping("/categories/{id}")
    public void deleteImage(@PathVariable UUID id) {
        if (repository.findById(id).orElse(null) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }
    
}
