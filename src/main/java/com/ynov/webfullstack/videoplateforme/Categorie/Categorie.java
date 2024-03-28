package com.ynov.webfullstack.videoplateforme.Categorie;

import java.util.List;
import java.util.UUID;

import com.ynov.webfullstack.videoplateforme.Utilisateur.Utilisateur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false, unique=true)
    private String titre;

    @Column(unique=true)
    private String description;

    @OneToMany(mappedBy = "categorie")
    private List <Utilisateur> utilisateurs;

    public Categorie() {
    }

    public Categorie (String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
