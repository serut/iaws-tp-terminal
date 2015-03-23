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
import java.util.*;
/**
 * Created by manantsoa on 18/03/15.
 */

public class FilmClient {

    public List<Film> filmClientRessourceByTitle(String title) {
        List<Film> films = new ArrayList<Film>();
        Client client = ClientBuilder.newClient();
        WebTarget res = client.target("http://www.omdbapi.com" + "?s=" + title + "&r=xml");
        //Response response = res.request().get();


        String text2;
        text2 = res.request().get(String.class);
        //Response rep =  res.request(MediaType.APPLICATION_XML).get();
        //System.out.println("TEXTE"+ text2);
        System.out.println("TEXTE"+ text2);

        try {
            films = SAXTreatment(text2);
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
                + "?s=" + title + "&y=" +  year + "&r=xml");
        //Response response = res.request().get();


        String text;
        text = res.request().get(String.class);
        //Response rep =  res.request(MediaType.APPLICATION_XML).get();
        //System.out.println("TEXTE"+ text2);
        System.out.println("TEXTE"+ text);

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

    public List<Film> SAXTreatment(String s) throws ParserConfigurationException, SAXException, IOException {
        SaxParserFilm app = new SaxParserFilm();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();

        saxParser.parse(new InputSource(new StringReader(s)), app);

        return app.getFilms();
    }



    public static void main(String[] args) {
        FilmClient f = new FilmClient();

        System.out.print(f.filmClientRessourceByTitle("Rings"));

    }
}
