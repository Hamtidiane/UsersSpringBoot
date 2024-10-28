package edu.campus.numerique.client;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class Client {
    private int id ;
    private String nom ;
    private String prenom ;
    private LocalDate dateNaissance ;
    private String numeroPermis ;



    public Client(int id, String nom, String prenom, LocalDate dateNaissance, String numeroPermis) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroPermis = numeroPermis;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(String numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

}
