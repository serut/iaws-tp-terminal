package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.model.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/15.
 */
@RestController
public class FilmController {
    @RequestMapping("/film")
    public String getFilm(@RequestParam(value="nom", defaultValue="") String nom, @RequestParam(value="annee", defaultValue="") String annee) {
        String result = "";
        try {
            if (nom.isEmpty() && annee.isEmpty()) {
                throw new Exception("Mauvais usage de l'API de recherche de film. Vous devez spécifier un de ces filtres : nom, annee");
            }
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
