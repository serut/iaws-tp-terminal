package fr.ups.dl.iaws;

import fr.ups.dl.iaws.controller.CinemaController;
import fr.ups.dl.iaws.controller.FilmController;
import junit.framework.TestCase;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Leo on 23/03/15.
 */
public class CinemaTest extends TestCase {

    private static int port = 8080;
    private Client c;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").port(port).build();

    @Before
    public void setUp() throws Exception {
        c = ClientBuilder.newClient();
    }

    /**
     */
    @org.junit.Test
    public void testExceptionGetCinema() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/cinema?ville=&is3D=&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("{\"error\":true,\"reason\":\"Mauvais usage de l'API de recherche de salle de cinéma. Vous devez utiliser un de ces filtres : ville, is3D, isImax\"}", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaParVille() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/cinema?ville=Toulouse&is3D=&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":1,\"numero\":1,\"ville\":\"Toulouse\",\"3D\":false,\"imax\":true},{\"id\":2,\"numero\":2,\"ville\":\"Toulouse\",\"3D\":true,\"imax\":false}]", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaEn3D() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/cinema?ville=&is3D=true&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":2,\"numero\":2,\"ville\":\"Toulouse\",\"3D\":true,\"imax\":false},{\"id\":4,\"numero\":2,\"ville\":\"Paris\",\"3D\":true,\"imax\":true}]", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaEnImax() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/cinema?ville=&is3D=&isImax=true");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":1,\"numero\":1,\"ville\":\"Toulouse\",\"3D\":false,\"imax\":true},{\"id\":4,\"numero\":2,\"ville\":\"Paris\",\"3D\":true,\"imax\":true}]", responseMsg);
    }



}