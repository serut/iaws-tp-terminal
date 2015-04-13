package fr.ups.dl.iaws.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cédric Rohaut (@Oxynos) on 13/04/2015 13:59.
 */
public class Cinema {
    private List<Salle> salles;
    private String ville;
    private String nom;

    public Cinema(List<Salle> salles, String ville, String nom) {
        this.salles = salles;
        this.ville = ville;
        this.nom = nom;
    }

    public Cinema(String ville, String nom) {
        this.salles = new ArrayList<>();
        this.ville = ville;
        this.nom = nom;
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public String getVille() {
        return ville;
    }

    public String getNom() {
        return nom;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void ajouterSalle(Salle salle) {
        salle.setVille(ville);
        salles.add(salle);
    }

    @Override
    public String toString() {
        return "Cinema " + nom + " à " + ville + "\nListe des salles :\n" + salles;
    }
}
