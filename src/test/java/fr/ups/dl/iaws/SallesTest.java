package fr.ups.dl.iaws;

import fr.ups.dl.iaws.model.Salle;
import fr.ups.dl.iaws.model.Salles;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cédric Rohaut (@Oxynos) on 01/04/2015 10:25.
 */
public class SallesTest {
    //private Salles salles;

    @Before
    public void setUp() throws Exception {
        /*Salle s1 = new Salle(1, 1, "Toulouse");
        Salle s2 = new Salle(2, 2, "Toulouse");
        Salle s3 = new Salle(3, 1, "Paris");
        Salle s4 = new Salle(4, 2, "Paris");
        List<Salle> ls = new ArrayList<>();
        ls.add(s1);
        ls.add(s2);
        ls.add(s3);
        ls.add(s4);*/
        //salles = new Salles();

    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testGetSalles() {
        /*List<Salle> ls = Salles.getSalles();
        for (Salle s : ls) {
            System.out.println(s);
        }*/
        //assertEquals();
    }

    @org.junit.Test
    public void testGetSallesVille() {
        String ville = "Toulouse";
        //assertEquals();
    }

    @org.junit.Test
    public void testGetSallesFilm() {
        String id = "";
        //assertEquals();
    }
}
