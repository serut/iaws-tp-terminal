package fr.ups.dl.iaws;

import fr.ups.dl.iaws.controller.FilmController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by manantsoa on 01/04/15.
 */
public class FilmControllerTest extends TestCase {

    private FilmController f;

    @Override
    @Before
    protected void setUp() throws Exception {
        f = new FilmController();
    }

    /**
     * Test if the research with title "Onimusha" returns
     * the good movie
     */
    @Test
    public void testFilmWithTitleOnimusha() {
        String s = f.getFilm("Onimusha", "");
        assertEquals("[{\"id\":\"tt0796361\",\"title\":\"Onimusha: Dawn of Dreams\",\"year\":2006}]", s);
    }

    /**
     * Test if the research with the title Fantasia
     * and the year 1940 returns the good movies
     */
    @Test
    public void testFilmWithTitleFantasiaAndYear2010() {
        String s = f.getFilm("Fantasia", "1940");
        assertEquals("[{\"id\":\"tt0032455\",\"title\":\"Fantasia\",\"year\":1940},{\"id\":\"tt0027606\",\"title\":\"Fantasia sottomarina\",\"year\":1940}]",s);
    }
}
