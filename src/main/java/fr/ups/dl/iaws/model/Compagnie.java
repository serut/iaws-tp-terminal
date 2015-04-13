package fr.ups.dl.iaws.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cédric Rohaut (@Oxynos) on 13/04/2015 13:59.
 */
public class Compagnie {
    private String nom;
    private List<Cinema> cinemas;

    public Compagnie(List<Cinema> cinemas) {
        this.nom = "UGmont";
        this.cinemas = cinemas;
    }

    public Compagnie() {
        this.nom = "UGmont";
        this.cinemas = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    @Override
    public String toString() {
        return "Compagnie " + nom + "\nListe des cinémas :\n" + cinemas;
    }
}
