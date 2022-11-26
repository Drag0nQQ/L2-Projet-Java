package extraction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



/**
* Classe permettant de récupérer spécifiquement le fichier meta.xml et affiche le contenu utile
* @author Laurent LIN
* @author Axel OLIVEIRA
*/
public class ExtractMeta{
    //Attributes
    
    //Constructors
    
    
    //Methods 
    /**
    * Affiche les metadata du fichier, pour les photos voir methodes
    * @param mainDirectory should be the path to the meta.xml
    * @see 
    */
    public static void showMeta(Path mainDirectory){
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        ArrayList<String> keyword=new ArrayList<String>();
        String toMetaFile=mainDirectory.toString()+File.separator+"meta.xml";
        try {
            builder= builderFactory.newDocumentBuilder();
            
            
            try {
                Document doc = builder.parse(new FileInputStream(new File(toMetaFile)));
                Element offDocMeta= doc.getDocumentElement();
                NodeList nodeMeta = offDocMeta.getChildNodes();
                NodeList subNodeMeta= nodeMeta.item(0).getChildNodes();
                for (int i=0 ; i < subNodeMeta.getLength();i++) {
                    switch (subNodeMeta.item(i).getNodeName()) {
                        case "dc:title":
                            System.out.println("Titre: "+subNodeMeta.item(i).getTextContent());
                        break;
                        case "dc:subject":
                            System.out.println("Sujet: "+subNodeMeta.item(i).getTextContent());
                        break;
                        case "meta:creation-date":
                            System.out.println("Date de création: "+subNodeMeta.item(i).getTextContent());
                        break;
                        case "meta:document-statistic":
                            System.out.println("Statistiques:");
                            System.out.println("Nombre de pages: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:page-count").getTextContent());
                            System.out.println("Nombre de mots: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:word-count").getTextContent());
                            System.err.println("Nombre de caractères: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:character-count").getTextContent());
                        break;
                        case "meta:keyword":
                            keyword.add(subNodeMeta.item(i).getTextContent());
                        break;
                        default:
                        break;
                    }
                }
                if (keyword.size()>0){
                    System.out.print("Keywords: ");
                    for (String string : keyword) {
                        System.out.print(string+ " ");
                    }
                    System.out.println();
                }
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
        
    }
    
    
    //Getter Setter
    
}