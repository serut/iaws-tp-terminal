package fr.ups.dl.iaws.model;

/**
 * Created by Cédric Rohaut (@Oxynos) on 23/03/15 16:16.
 */
public class Salle {
    private int id;
    private int numero;
    private String ville;

    public Salle() {
        this.id = 0;
        this.numero = 0;
        this.ville = "VILLE";
    }

    public Salle(int id, int numero, String ville) {
        this.id = id;
        this.numero = numero;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Salle[" + id + "] n°" + numero + " à " + ville;
    }
}
