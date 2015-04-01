package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.FilmClient;
import fr.ups.dl.iaws.model.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/15.
 */
@RestController
public class FilmController {
    @Produces("application/json")
    @RequestMapping("/film")
    public String getFilmByTitleAndYear(@RequestParam(value="nom", defaultValue="") String nom,
                          @RequestParam(value="annee", defaultValue="") String annee) {
        String result = "";
        try {
            if (nom.isEmpty() && annee.isEmpty()) {
                throw new Exception("Mauvais usage de l'API de recherche de film. Vous devez spécifier un de ces filtres : nom, annee");
            }
            FilmClient client = new FilmClient();
            List<Film> listeFilms = client.filmClientRessourceByTitleAndYear(nom, Integer.parseInt(annee));

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

    @Produces("application/json")
    @RequestMapping("/film")
    public String getFilmByTitle(@RequestParam(value="nom", defaultValue="") String nom) {
        String result = "";
        try {
            if (nom.isEmpty()) {
                throw new Exception("Mauvais usage de l'API de recherche de film. Vous devez spécifier un de ces filtres : nom, annee");
            }
            FilmClient client = new FilmClient();
            List<Film> listeFilms = client.filmClientRessourceByTitle(nom);

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
