package com.ynov.webfullstack.videoplateforme.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TagController {

    private final TagRepository repository;

    TagController(TagRepository tagRepository) {
        this.repository = tagRepository;
    }

    @GetMapping("/tags")
    List<Tag> all() {
        return repository.findAll();
    }

    @GetMapping("/tag")
    Tag one(@RequestParam("tagTitre") String tagTitre) {
        Optional<Tag> tagf = repository.findByTitre(tagTitre);
        if (tagf.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun tag " + tagTitre + " trouvé");
        }
        return tagf.get();
    }

    @PostMapping("/tag")
    Tag newTag(@RequestBody TagDTO tag) {
        if (tag.getTitre() != null) {
            if (repository.findByTitre(tag.getTitre()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Le tag " + tag.getTitre() + " existe déjà");
            }
        }
        if (tag.getId() != null) {
            if (repository.findById(tag.getId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Le tag " + tag.getId() + " existe déjà");
            }
        }
        Tag tagS = new Tag(tag.getTitre());
        return repository.save(tagS);
    }


    @DeleteMapping("/tag")
    void deleteTag(@RequestParam UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun tag d'ID" + id + " trouvé");
        }
        repository.deleteById(id);
    }

    @GetMapping("/findByTitreLike")
    List<Tag> findByTitreLike(@RequestParam("tagTitre") String tagTitre) {
        return repository.findByTitreContains(tagTitre);
    }

}
