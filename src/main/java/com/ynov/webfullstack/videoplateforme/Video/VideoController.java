package com.ynov.webfullstack.videoplateforme.Video;

import com.ynov.webfullstack.videoplateforme.Tag.Tag;
import com.ynov.webfullstack.videoplateforme.Tag.TagDTO;
import com.ynov.webfullstack.videoplateforme.Tag.TagRepository;
import com.ynov.webfullstack.videoplateforme.Video.Video;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class VideoController {

    private final VideoRepository repository;
    private final TagRepository tagRepository;

    VideoController(VideoRepository videoRepository, TagRepository tagRepository) {
        this.repository = videoRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/videos")
    List<Video> all() {
        return repository.findAll();
    }

    @GetMapping("/video")
    Video one(@RequestParam("videoTitre") String videoTitre) {
        Optional<Video> videof = repository.findByTitre(videoTitre);
        if (videof.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune vidéo de titre" + videoTitre + " trouvée");
        }
        return videof.get();
    }


    @PostMapping("/video")
    Video newVideo(@RequestBody VideoDTO video) {
        if (video.getTitre() != null) {
            if (repository.findByTitre(video.getTitre()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Le video " + video.getTitre() + " existe déjà");
            }
        }
        if (video.getId() != null) {
            if (repository.findById(video.getId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Le video " + video.getId() + " existe déjà");
            }
        }
        Video videoS = new Video(video.getTitre(), video.getShortDescription(), video.getLongDescription());
        if (video.getTags() != null) {
            List<Tag> tags = new ArrayList<>();
            for (Tag tag : video.getTags()) {
                if (tagRepository.findByTitre(tag.getTitre()).isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le tag " + tag.getTitre() + " n'existe pas");
                }
                tags.add(tagRepository.findByTitre(tag.getTitre()).get());
            }
        videoS.setTags(tags);
        }
        return repository.save(videoS);
    }

    @DeleteMapping("/video")
    void deleteVideo(@RequestParam("id") UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune vidéo d'ID" + id + " trouvée");
        }
        repository.deleteById(id);
    }

    @PutMapping("/video")
    Video updateVideoTag(@RequestBody VideoDTO video) {
        Optional<Video> videof = repository.findById(video.getId());
        if (videof.isEmpty() & repository.findByTitre(video.getTitre()).isPresent()) {
            videof = repository.findByTitre(video.getTitre());
        }
        if (videof.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune vidéo de titre" + video.getTitre() + " ou d'id " + video.getId() + " trouvée");
        }
        Video videoToSave = videof.get();
        if (video.getTags() != null) {
            List<Tag> tags = videoToSave.getTags();
            for (Tag tag : video.getTags()) {
                if (tag.getTitre() == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le titre du tag ne peut pas être vide");
                }
                if (tagRepository.findByTitre(tag.getTitre()).isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le tag " + tag.getTitre() + " n'existe pas");
                }
                tags.add(tagRepository.findByTitre(tag.getTitre()).get());
                System.out.println(tags);
            }
            videoToSave.setTags(tags);
        }
        return repository.save(videoToSave);
    }

    @GetMapping("/videosByTag")
    List<Video> videosByTag(@RequestParam("tagTitre") String tagTitre) {
        List<Tag> tags = new ArrayList<>();
        if (tagRepository.findByTitre(tagTitre).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le tag " + tagTitre + " n'existe pas");
        }
        Tag tag = tagRepository.findByTitre(tagTitre).get();
        List<Video> videos = repository.findByTagsContaining(tag);
        if (videos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune vidéo trouvée");
        }
        return videos;
    }

    @GetMapping("/videosByTitleOrShortDescription")
    List<Video> videosByTitleOrShortDescription(@RequestParam("titre") String titre, @RequestParam("shortDescription") String shortDescription) {
        List<Video> videos = repository.findByTitreContainsOrShortDescriptionContains(titre, shortDescription);
        if (videos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune vidéo trouvée");
        }
        return videos;
    }
}
