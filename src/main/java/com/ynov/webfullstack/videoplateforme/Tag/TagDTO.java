package com.ynov.webfullstack.videoplateforme.Tag;

import java.util.UUID;

public class TagDTO{
    private UUID id;
    private String titre;

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
}
