package fr.ups.dl.iaws;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Leo on 23/03/15.
 */
public class FilmSalleControllerTest extends TestCase {

    private static int port = 8080;
    private Client c;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").port(port).build();

    @Before
    public void setUp() throws Exception {
        c = ClientBuilder.newClient();
    }

    /**
     */
    @Test
    public void testPutFilmSalle() {
        WebTarget target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/filmSalle?idFilm=1234567a&idSalle=1");
        String responseMsg = target.request().get(String.class);
        Assert.assertEquals("{\"error\":false}", responseMsg);


        target = c.target("http://"+BASE_URI.getHost()+":"+BASE_URI.getPort()+"/filmSalle/1234567a");
        responseMsg = target.request().get(String.class);
        Assert.assertEquals("[{\"id\":1,\"numero\":1,\"ville\":\"Toulouse\",\"imax\":true,\"3D\":false}]", responseMsg);
    }




}