package fr.ups.dl.iaws.contractfirst;

import fr.ups.dl.iaws.model.Salle;
import fr.ups.dl.iaws.model.Salles;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import javax.xml.bind.Element;
import java.util.List;

/**
 * Created by manantsoa on 13/04/15.
 */
@Endpoint
public class CinemaEndPoint {

        private List<Salle> salles;
        private Salles serviceSalle;

        private static final String NAMESPACE_URI = "http://fr/ups/dl/iaws/contractfirst/results";

        @PayloadRoot(localPart="CinemaRequest", namespace = NAMESPACE_URI)
        @ResponsePayload
        public void handleCinemaRequest(@XPathParam("/CinemaRequest/id") int id) throws Exception {

            salles =  serviceSalle.getSalleFromFilm(String.valueOf(id));

            //Element elt = XmlHelper.getRootElementFromFileInClasspath("ReleveNotes.xml") ;

            System.out.println(id);
        }


}
