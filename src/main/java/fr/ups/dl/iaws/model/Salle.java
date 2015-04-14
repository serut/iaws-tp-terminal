package fr.ups.dl.iaws.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Cédric Rohaut (@Oxynos) on 23/03/15 16:16.
 */
public class Salle {
    private int id;
    private int numero;
    private String ville;
    private boolean isImax;
    private boolean is3D;

    public Salle() {
        this.id = 0;
        this.numero = 0;
        this.ville = "VILLE";
    }

    @JsonCreator
    public Salle(int id, int numero, String ville, boolean isImax, boolean is3D) {
        this.id = id;
        this.numero = numero;
        this.ville = ville;
        this.is3D = is3D;
        this.isImax = isImax;
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

    public boolean is3D() {
        return is3D;
    }

    public void set3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isImax() {
        return isImax;
    }

    public void setImax(boolean isImax) {
        this.isImax = isImax;
    }

    @Override
    public String toString() {
        return "Salle[" + id + "] n°" + numero + " à " + ville;
    }
}
