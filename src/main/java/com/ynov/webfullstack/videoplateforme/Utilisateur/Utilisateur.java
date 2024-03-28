package com.ynov.webfullstack.videoplateforme.Utilisateur;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.webfullstack.videoplateforme.Categorie.Categorie;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false, unique=true)
    private String username;

   
    @JsonIgnore
    @Column(nullable=false)
    private String password; 

    @ManyToOne()
    @JoinColumn(name="categorie_id",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private Categorie categorie;

    public Utilisateur() {

    }

    public Utilisateur(String username) {
        this.username = username;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setPassword(String password) {
        this.password =password;
    }
}
