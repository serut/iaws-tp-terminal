package fr.ups.dl.iaws;

import fr.ups.dl.iaws.model.Film;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manantsoa on 23/03/15.
 */
public class SaxParserFilm extends DefaultHandler {
    private List<Film> films;

    public SaxParserFilm() {
        films = new ArrayList<Film>();
    }

    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes)
            throws SAXException {
        if (qName.equals("Movie")) {
            Film f = new Film(attributes.getValue("imdbID"),
                    attributes.getValue("Title"),
                    Integer.parseInt(attributes.getValue("Year").replaceAll("[^0-9]","")));
            films.add(f);
        }
    }

    public void startDocument() {
        System.out.println("*** START DOCUMENT");
    }

    public void endDocument() {
        System.out.println("*** END DOCUMENT");
    }

    public List<Film> getFilms() {
        return films;
    }
}
