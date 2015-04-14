package fr.ups.dl.iaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Leo on 14/04/15.
 */
@RestController
@Path("filmSalle")
public class FilmSalleController {


    @Autowired
    private Salles salles;

    @Produces("application/json")
    @PUT
    @RequestMapping("/filmSalle")
    public String putFilmSalle(@PathParam("idFilm") @RequestParam(value="idFilm", defaultValue="") String idFilm,
                               @PathParam("idSalle") @RequestParam(value="idSalle", defaultValue="0") int idSalle) {
        String result = "";
        try {
            if (idFilm.isEmpty() && idSalle == 0) {
                throw new Exception("Paramètres non spécifiés");
            }
            if (salles.getSalle(idSalle) == null) {
                throw new Exception("Cette salle n'existe pas !");
            }
            salles.insertFilmSalle(new FilmSalle(idSalle, idFilm));

            JsonNodeFactory factory = new JsonNodeFactory(false);
            ObjectNode response = factory.objectNode();
            response.put("error", false);
            result = response.toString();
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
    @GET
    @RequestMapping("/filmSalle/{idFilm}")
    public String putFilmSalle(@PathParam("idFilm") @PathVariable("idFilm") String idFilm) {
        String result = "";
        try {
            List<Salle> listeSalle = salles.getSalleFromFilm(idFilm);

            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(listeSalle);

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
