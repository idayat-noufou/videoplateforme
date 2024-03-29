package com.ynov.webfullstack.videoplateforme.Tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.webfullstack.videoplateforme.Video.Video;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titre;
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Video> videos;

    public Tag() {
        super();
    }

    public Tag(String titre) {
        this.titre = titre;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
