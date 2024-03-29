package com.ynov.webfullstack.videoplateforme.Video;

import com.ynov.webfullstack.videoplateforme.Tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
    Optional<Video> findByTitre(String titre);
    List<Video> findByTagsContaining(Tag tag);
    List<Video> findByTitreContainsOrShortDescriptionContains(String titre, String shortDescription);
}
