package extraction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
* Cette classe permet de chercher des informations dans le content.xml
*/
public class ExtractContent {
    /**
    * Affiche tous les liens internet du fichier content.xml
    * @param mainDirectory chemin vers le dossier temporaire
    */
    public static void showLink(Path mainDirectory) {
        String toContentFile= mainDirectory.toString()+File.separator+"content.xml";
        File linkList= new File(toContentFile);
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder= builderFactory.newDocumentBuilder();
            try {
                Document doc = builder.parse(new FileInputStream(linkList));
                NodeList offDocLink= doc.getElementsByTagName("text:a");
                ArrayList<String> arrayLink= new ArrayList<String>();
                String txtcontent=null;
                for (int i=0; i < offDocLink.getLength();i++) {
                    txtcontent=offDocLink.item(i).getAttributes().getNamedItem("xlink:href").getTextContent();
                    if (!arrayLink.contains(txtcontent)&& (txtcontent.contains("http")||txtcontent.contains("www"))){
                        arrayLink.add(txtcontent);
                    }
                }
                if (arrayLink.size()>0) {
                    System.out.println("Lien internet: ");
                    for (String string : arrayLink) {
                        System.out.println("\t"+string);
                    }
                }    
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
    }
}
