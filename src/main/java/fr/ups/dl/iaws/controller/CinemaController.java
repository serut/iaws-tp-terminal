package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.model.Film;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/15.
 */
@RestController
@Path("cinema")
public class CinemaController {

    @Produces("application/json")
    @GET
    @RequestMapping("/cinema/{id}")
    @Path("{id}")
    public String getFilm(@PathParam("id") @RequestParam(value="id", defaultValue="") String id) {
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
