package fr.ups.dl.iaws;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by manantsoa on 21/04/15.
 */
public class XmlHelper {
        public static Element getRootElementFromFileInClasspath(String filePathInClassPath) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.parse(new ClassPathResource(filePathInClassPath).getInputStream());
            return doc.getDocumentElement();
        }

}
