package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.FilmClient;
import fr.ups.dl.iaws.model.Film;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/15.
 */
@RestController
@Path("film")
public class FilmController {

    /**
     * Utilisation de la double annotation pour correspondre
     * @param nom
     * @param annee
     * @return
     */
    @Produces("application/json")
    @GET
    @RequestMapping("/film")
    public String getFilm(@QueryParam("titre") @RequestParam(value = "titre", defaultValue = "") String titre,
                          @QueryParam("annee") @RequestParam(value = "annee", defaultValue = "0") int annee) {
        String result = "";
        try {
            if (titre.isEmpty() && annee == 0) {
                throw new Exception("Mauvais usage de l'API de recherche de film. Vous devez utiliser un de ces filtres : nom, annee");
            }
            FilmClient client = new FilmClient();
            List<Film> listeFilms = client.filmClientRessourceByTitleAndYear(titre, annee);

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