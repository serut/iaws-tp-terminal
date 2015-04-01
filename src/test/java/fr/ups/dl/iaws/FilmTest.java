package fr.ups.dl.iaws;


import fr.ups.dl.iaws.controller.CinemaController;
import fr.ups.dl.iaws.controller.FilmController;
import junit.framework.*;
import org.glassfish.grizzly.PortRange;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


/**
 * Unit test for simple App.
 */

public class FilmTest {
    private WebTarget target;
    private static int port = 8987;
    private Client c;
    private HttpServer server;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").port(port).build();

    @Before
    public void setUp() throws Exception {
        ResourceConfig rc = new ResourceConfig(FilmController.class);
        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
        server.start();
        c = ClientBuilder.newClient();
    }
    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @org.junit.Test
    public void testFilmAnnee2000() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/film?annee=1940&nom=Fantasia");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":\"tt0032455\",\"title\":\"Fantasia\",\"year\":1940},{\"id\":\"tt0027606\",\"title\":\"Fantasia sottomarina\",\"year\":1940}]", responseMsg);
    }


    @org.junit.Test
    public void testFilmErreurExceptionPasDeParametre() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/film?annee=&nom=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("{\"error\":true,\"reason\":\"Mauvais usage de l'API de recherche de film. Vous devez spécifier un de ces filtres : nom, annee\"}", responseMsg);
    }

}