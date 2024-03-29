package com.ynov.webfullstack.videoplateforme.Video;

import com.ynov.webfullstack.videoplateforme.Tag.Tag;

import java.util.List;
import java.util.UUID;

public class VideoDTO {
    private UUID id;
    private String titre;
    private String shortDescription;
    private String longDescription;
    private List<Tag> tags;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
