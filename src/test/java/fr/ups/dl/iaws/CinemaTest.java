package fr.ups.dl.iaws;

import fr.ups.dl.iaws.controller.CinemaController;
import fr.ups.dl.iaws.controller.FilmController;
import junit.framework.TestCase;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Leo on 23/03/15.
 */
public class CinemaTest extends TestCase {

    private WebTarget target;
    private static int port = 8987;
    private Client c;
    private HttpServer server;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").port(port).build();

    @Before
    public void setUp() throws Exception {
        ResourceConfig rc = new ResourceConfig(CinemaController.class);
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
    public void testGetCinema() {
        while(!server.isStarted()) {

        }
        WebTarget target = c.target("http://localhost:8987/cinema/1");
        String responseMsg = target.request().get(String.class);
        assertEquals("[{\"id\":\"2\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"323242\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"23\",\"title\":\"Lol\",\"year\":2004}]", responseMsg);
    }

}