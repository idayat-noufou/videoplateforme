package com.ynov.webfullstack.videoplateforme.Video;

import com.ynov.webfullstack.videoplateforme.Tag.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String titre;
    @NotNull
    private String shortDescription;
    @NotNull
    private String longDescription;
    @ManyToMany(mappedBy = "videos")
    private List<Tag> tags;

    public Video() {
        super();
    }

    public Video(String titre, String shortDescription, String longDescription) {
        this.titre = titre;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
