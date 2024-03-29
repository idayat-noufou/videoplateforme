package com.ynov.webfullstack.videoplateforme.Tag;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface TagRepository extends JpaRepository<Tag, UUID>{
    Optional<Tag> findByTitre(String titre);
    List<Tag> findByTitreContains(String titre);
}
