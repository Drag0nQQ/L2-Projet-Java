package extraction;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



/**
* Cette classe permet de manipuler spécifiquement le fichier meta.xml
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
                        System.out.println("Nombre de caractères: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:character-count").getTextContent());
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
    
    public static void setTitle(Path mainDirectory,String texte){
        String toMetaFile= mainDirectory.toString()+File.separator+"meta.xml";
        File metaFile= new File(toMetaFile);
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder= builderFactory.newDocumentBuilder();
            try {
                Document doc = builder.parse(new FileInputStream(metaFile));
                NodeList offDoc= doc.getElementsByTagName("dc:title");
                if (offDoc.getLength()>0) {
                    offDoc.item(0).setTextContent(texte);
                }else{
                    NodeList addNode=doc.getElementsByTagName("office:meta");
                    if (addNode.getLength()>0) {
                        Element temp= doc.createElement("dc:title");
                        temp.setTextContent(texte);
                        addNode.item(0).insertBefore(temp, addNode.item(0).getChildNodes().item(0));  
                    }
                }
                try {
                    Transformer transform= TransformerFactory.newInstance().newTransformer();
                    transform.setOutputProperty(OutputKeys.METHOD, "xml");
                    transform.transform(new DOMSource(doc), new StreamResult(metaFile));
                } catch (TransformerConfigurationException e) {
                    System.err.println("Problème rencontré lors de l'instanciation de l'exporteur.");
                    e.printStackTrace();
                } catch (TransformerException e) {
                    System.err.println("Problème rencontré lors de l'exportation du document");
                    e.printStackTrace();
                }
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
    }
    
    public static void setSubject(Path mainDirectory,String texte){
        String toMetaFile= mainDirectory.toString()+File.separator+"meta.xml";
        File metaFile= new File(toMetaFile);
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder= builderFactory.newDocumentBuilder();
            try {
                Document doc = builder.parse(new FileInputStream(metaFile));
                NodeList offDoc= doc.getElementsByTagName("dc:subject");
                if (offDoc.getLength()>0) {
                    offDoc.item(0).setTextContent(texte);
                }else{
                    NodeList addNode=doc.getElementsByTagName("office:meta");
                    if (addNode.getLength()>0) {
                        NodeList subNodeList= doc.getElementsByTagName("meta:generator");
                        Node tmp= subNodeList.item(0);
                        Element newElement=doc.createElement("tmp");
                        String newString= newElement.getTextContent();
                        addNode.item(0).insertBefore(newElement, tmp);
                        newElement.setNodeValue("meta:generator");
                        newElement.setTextContent(newString);
                        tmp.setTextContent(texte);
                        tmp.setNodeValue("dc:subject"); 
                    }
                }
                try {
                    Transformer transform= TransformerFactory.newInstance().newTransformer();
                    transform.setOutputProperty(OutputKeys.METHOD, "xml");
                    transform.transform(new DOMSource(doc), new StreamResult(metaFile));
                } catch (TransformerConfigurationException e) {
                    System.err.println("Problème lors de l'instanciation de l'exporteur");
                    e.printStackTrace();
                } catch (TransformerException e) {
                    System.err.println("Problème rencontré lors de l'exportation du document");
                    e.printStackTrace();
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