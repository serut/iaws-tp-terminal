package fr.ups.dl.iaws;

import junit.framework.TestCase;
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
public class SalleTest extends TestCase {

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
    public void testExceptionPasDeParametre() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/salle?ville=&is3D=&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("{\"error\":true,\"reason\":\"Mauvais usage de l'API de recherche de salle de cinéma. Vous devez utiliser un de ces filtres : ville, is3D, isImax\"}", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaParVille() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/salle?ville=Toulouse&is3D=&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":1,\"numero\":1,\"ville\":\"Toulouse\",\"imax\":true,\"3D\":false},{\"id\":2,\"numero\":2,\"ville\":\"Toulouse\",\"imax\":false,\"3D\":true}]", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaEn3D() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/salle?ville=&is3D=true&isImax=");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":2,\"numero\":2,\"ville\":\"Toulouse\",\"imax\":false,\"3D\":true},{\"id\":4,\"numero\":2,\"ville\":\"Paris\",\"imax\":true,\"3D\":true}]", responseMsg);
    }

    /**
     */
    @org.junit.Test
    public void testGetCinemaEnImax() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/salle?ville=&is3D=&isImax=true");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":1,\"numero\":1,\"ville\":\"Toulouse\",\"imax\":true,\"3D\":false},{\"id\":4,\"numero\":2,\"ville\":\"Paris\",\"imax\":true,\"3D\":true}]", responseMsg);
    }



}