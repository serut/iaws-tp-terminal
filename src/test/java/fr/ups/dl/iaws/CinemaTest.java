package fr.ups.dl.iaws;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by Leo on 23/03/15.
 */
public class CinemaTest extends TestCase {

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
    public void testGetCinema() {

        WebTarget target = c.target("http://localhost:8080/cinema/32089002");
        String responseMsg = target.request().get(String.class);
        assertEquals("[{\"id\":\"2\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"323242\",\"title\":\"Lol\",\"year\":2004},{\"id\":\"23\",\"title\":\"Lol\",\"year\":2004}]", responseMsg);
    }

}