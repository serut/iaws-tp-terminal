package fr.ups.dl.iaws;

import fr.ups.dl.iaws.model.Film;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by manantsoa on 18/03/15.
 */

public class FilmClient {

    public List<Film> filmClientRessourceByTitle(String title) {
        List<Film> films = new ArrayList<Film>();
        Client client = ClientBuilder.newClient();
        WebTarget res = client.target("http://www.omdbapi.com" + "?s=" + title + "&r=xml");

        String text;
        text = res.request().get(String.class);

        try {
            films = SAXTreatment(text);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.close();

        return films;
    }

    public List<Film> filmClientRessourceByTitleAndYear(String title, int year) {
        List<Film> films = new ArrayList<Film>();
        Client client = ClientBuilder.newClient();
        WebTarget res = client.target("http://www.omdbapi.com"
                + "?s=" + title + "&y=" +  String.valueOf(year) + "&r=xml");

        String text;
        text = res.request().get(String.class);

        try {
            films = SAXTreatment(text);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(films.get(0));

        client.close();

        return films;
    }

    public List<Film> SAXTreatment(String s) throws ParserConfigurationException, SAXException, IOException {
        SaxParserFilm app = new SaxParserFilm();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();

        saxParser.parse(new InputSource(new StringReader(s)), app);

        return app.getFilms();
    }
}
