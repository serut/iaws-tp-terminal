package fr.ups.dl.iaws.model;

/**
 * Created by Cédric Rohaut (@Oxynos) on 14/04/2015 12:31.
 */
public class FilmSalle {
    private int idSalle;
    private int idFilm;

    public FilmSalle(int idSalle, int idFilm) {
        this.idSalle = idSalle;
        this.idFilm = idFilm;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }
}
