package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.model.Film;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/15.
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Produces("application/json")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getFilm(@PathVariable String id) {
        String result = "";
        try {
            List<Film> listeFilms = new ArrayList<>();
            listeFilms.add(new Film("2", "Lol", 2004));
            listeFilms.add(new Film("323242", "Lol", 2004));
            listeFilms.add(new Film("23", "Lol", 2004));

            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(listeFilms);
        } catch (Exception e) {
            JsonNodeFactory factory = new JsonNodeFactory(false);
            ObjectNode response = factory.objectNode();
            response.put("error", true);
            response.put("reason", e.getMessage());
            result = response.toString();
        }
        return result;
    }
}
