package fr.ups.dl.iaws.model;

/**
 * Created by Cédric Rohaut (@Oxynos) on 14/04/2015 12:31.
 */
public class FilmSalle {
    private int idSalle;
    private String idFilm;

    public FilmSalle(int idSalle, String idFilm) {
        this.idSalle = idSalle;
        this.idFilm = idFilm;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }
}
