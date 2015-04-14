package fr.ups.dl.iaws.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by manantsoa on 18/03/15.
 */
public class Film {
    String id;
    String title;
    int year;

    @JsonCreator
    public Film(String id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String toString() {return "OMDBId: " + id + " Title: " + title + " Year: " + year;}

    public String getUrl() {
        return "http://www.omdbapi.com/?i=" + id + "&r=xml";
    }
}
