package fr.ups.dl.iaws;

import com.sun.net.httpserver.HttpServer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import sun.applet.Main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


/**
 * Unit test for simple App.
 */

public class FilmTest extends TestCase {

    private Client c;
    @Before
    public void setUp() throws Exception {
        c = ClientBuilder.newClient();
    }

    @After
    public void tearDown() throws Exception {
        c.close();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @org.junit.Test
    public void testFilmAnnee2000() {

        WebTarget target = c.target("http://localhost:8080/film?annee=2014");
        String responseMsg = target.request().get(String.class);
        assertEquals("[{\"id\":\"2\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"323242\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"23\",\"title\":\"Lol\",\"year\":2004}]", responseMsg);
    }

    @org.junit.Test
    public void testFilmErreurExceptionPasDeParametre() {

        WebTarget target = c.target("http://localhost:8080/film");
        String responseMsg = target.request().get(String.class);
        assertEquals("{\"error\":true,\"reason\":\"Mauvais usage de l'API de recherche de film. Vous devez spécifier un de ces filtres : nom, annee\"}", responseMsg);
    }

}