package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ups.dl.iaws.model.Film;
import fr.ups.dl.iaws.model.FilmSalle;
import fr.ups.dl.iaws.model.Salle;
import fr.ups.dl.iaws.model.Salles;
import org.springframework.beans.factory.annotation.Autowired;
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
@Path("salle")
public class SalleController {


    @Autowired
    private Salles salles;


    @Produces("application/json")
    @GET
    @RequestMapping("/salle")
    public String getSalles(@PathParam("ville") @RequestParam(value="ville", defaultValue="") String ville,
                            @PathParam("is3D") @RequestParam(value="is3D", defaultValue="") String is3DAsString,
                            @PathParam("isImax") @RequestParam(value="isImax", defaultValue="") String isImaxAsString) {
        String result = "";
        try {
            if (ville.isEmpty() && isImaxAsString.isEmpty() && is3DAsString.isEmpty()) {
                throw new Exception("Mauvais usage de l'API de recherche de salle de cinéma. Vous devez utiliser un de ces filtres : ville, is3D, isImax");
            }
            Boolean is3D = null;
            Boolean isImax = null;
            if (! is3DAsString.isEmpty()) {
                is3D = is3DAsString.equals("true");
            }
            if (! isImaxAsString.isEmpty()) {
                isImax = isImaxAsString.equals("true");
            }
            List<Salle> listeFilms = salles.getSalles(ville, isImax, is3D);

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
