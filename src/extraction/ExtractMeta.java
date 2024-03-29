package extraction;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



/**
* Cette classe permet de manipuler spécifiquement le fichier meta.xml.
* @author Laurent LIN
* @author Axel OLIVEIRA
*/
public class ExtractMeta{
    //Attributes
    public static final String AUTHOR_NODE="meta:initial-creator";
    public static final String SUBJECT_NODE="dc:subject";
    public static final String TITLE_NODE="dc:title";
    public static final String DATE_NODE="meta:creation-date";
    public static final String KEYWORD_NODE="meta:keyword";
    public static final String STATISTIC_NODE="meta:document-statistic";
    
    public static final String PAGE_ATTRIBUTE="meta:page-count";
    public static final String WORD_ATTRIBUTE="meta:word-count";
    public static final String CHARACTER_ATTRIBUTE="meta:character-count";
    //Constructors  
    //Methods 
    /**
    * Affiche les metadata du fichier, pour les photos voir methodes.
    * @param mainDirectory should be the path to the meta.xml.
    * @see {@link extraction.ExtractPicture ici} pour les pictures.
    * @see {@link extraction.ExtractContent ici} pour les liens.
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
    
    /**
    * Modifie le titre du meta.xml dans le dossier temporaire
    * on cherche la node "dc:title" s'il n'existe pas, on crée
    *  ce node et on le pose avant le "dc:generator" pour garder
    *  l'ordre d'apparition.
    * @param mainDirectory chemin vers le dossier temporaire
    * @param texte remplace le texte actuel par ce {@code texte}
    * @see {@link #setSubject(Path, String)} pour modifier le sujet.
    */
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
                } catch (TransformerException e) {
                    System.err.println("Problème rencontré lors de la configuration de l'exporter");
                    e.printStackTrace();
                } 
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
    }
    /**
    * Ajoute les keywords et écrase les anciens
    * @param mainDirectory Chemin vers le dossier temporaire.
    * @param texte ArrayList<String> des keywords.
    */
    public static void setKeyword(Path mainDirectory,ArrayList<String> texte){
        String toMetaFile= mainDirectory.toString()+File.separator+"meta.xml";
        File metaFile= new File(toMetaFile);
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder= builderFactory.newDocumentBuilder();
            try {
                Document doc = builder.parse(new FileInputStream(metaFile));
                NodeList offDoc= doc.getElementsByTagName(KEYWORD_NODE);
                if (offDoc.getLength()>0) {
                    while (offDoc.getLength() > 0) {
                        Node node = offDoc.item(0);
                        node.getParentNode().removeChild(node);
                    }
                    for (String string : texte) {
                        NodeList addNode=doc.getElementsByTagName("office:meta");
                        Element temp= doc.createElement(KEYWORD_NODE);
                        temp.setTextContent(string);
                        addNode.item(0).insertBefore(temp, addNode.item(0).getChildNodes().item(3));
                    }
                }else{
                    NodeList addNode=doc.getElementsByTagName("office:meta");
                    if (addNode.getLength()>0) {
                        for (String string : texte) {
                            Element temp= doc.createElement(KEYWORD_NODE);
                            temp.setTextContent(string);
                            addNode.item(0).insertBefore(temp, addNode.item(0).getChildNodes().item(3));  
                        }
                    }
                }
                try {
                    Transformer transform= TransformerFactory.newInstance().newTransformer();
                    transform.setOutputProperty(OutputKeys.METHOD, "xml");
                    transform.transform(new DOMSource(doc), new StreamResult(metaFile));
                } catch (TransformerException e) {
                    System.err.println("Problème rencontré lors de la configuration de l'exporter");
                    e.printStackTrace();
                }
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
    }
    
    /**
    * Permet de changer le sujet du fichier meta.xml, créer le noeud &#60;dc:subject> après le tag "dc:generator" s'il n'existe pas.
    * @param mainDirectory chemin vers le dossier temporaire du .odt
    * @param texte texte à mettre à la place de l'ancien texte
    * @see {@link #setTitle(Path, String)} pour changer le titre.
    */
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
                } catch (TransformerException e) {
                    System.err.println("Problème rencontré lors de la configuration de l'exporter");
                    e.printStackTrace();
                }
            } catch (SAXException | IOException e) {
                System.err.println("Problème lors de l'ouverture du fichier");
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
    }
    
    /**
    * méthode mère pour les sous-méthodes utilisant des nodes
    * @param mainDirectory chemin vers le dossier temporaire
    * @param nodeName nom de la balise (voir les constantes)
    * @return {@code null} si pas trouvé <li>{@code String} de la valeur</li>
    */
    private static String getInfo(Path mainDirectory,String nodeName){
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        String toMetaFile=mainDirectory.toString()+File.separator+"meta.xml";
        try {
            builder= builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream(new File(toMetaFile)));
            NodeList offDoc= doc.getElementsByTagName(nodeName);
            if (offDoc.getLength()>0){
                return offDoc.item(0).getTextContent();
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    /**
    * méthode mère pour les sous-méthodes utilisant des attributs d'un node
    * @param mainDirectory chemin vers le dossier temporaire
    * @param nodeName nom de la balise (voir les constantes)
    * @param attributeName nom de l'attribut (voir les constantes)
    * @return {@code null} si pas trouvé <li>{@code String} de la valeur</li>
    */
    private static String getAttributeInfo(Path mainDirectory, String nodeName, String attributeName){
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        String toMetaFile=mainDirectory.toString()+File.separator+"meta.xml";
        try {
            builder= builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream(new File(toMetaFile)));
            NodeList offDoc= doc.getElementsByTagName(nodeName);
            if (offDoc.getLength()>0){
                return offDoc.item(0).getAttributes().getNamedItem(attributeName).getTextContent();
            }
        }catch (Exception e){
            return null;
        }
        return null;   
    }
    
    //Getter Setter
    /**
    * Récupère le nombre de mots du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du nombre de mots.</li>
    */
    public static String getnbMots(Path mainDirectory) {
        return getAttributeInfo(mainDirectory, STATISTIC_NODE, WORD_ATTRIBUTE);
    }
    /**
    * Récupère le nombre de caractères du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du nombre de caractères.</li>
    */
    
    public static String getnbCaracteres(Path mainDirectory){
        return getAttributeInfo(mainDirectory, STATISTIC_NODE, CHARACTER_ATTRIBUTE);
    }
    
    /**
    * Récupère le nombre de pages du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du nombre de pages.</li>
    */
    public static String getnbPages(Path mainDirectory){
        return getAttributeInfo(mainDirectory, STATISTIC_NODE, PAGE_ATTRIBUTE);
    }
    /**
    * Récupère le titre du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du titre.</li>
    */
    public static String getTitle(Path mainDirectory){
        return getInfo(mainDirectory, TITLE_NODE);
    }
    /**
    * Récupère le sujet du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du sujet.</li>
    */
    public static String getSubject(Path mainDirectory){
        return getInfo(mainDirectory, SUBJECT_NODE);
    }
    
    /**
    * Récupère la date de création du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} de la date de création.</li>
    */
    public static String getCreation_date(Path mainDirectory){
        return getInfo(mainDirectory, DATE_NODE);
    }
    
    /**
    * Récupère l'auteur du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code String} du nombre de mots.</li>
    */
    public static String getAuthor(Path mainDirectory){
        return getInfo(mainDirectory, AUTHOR_NODE);
    }
    
    /**
    * Récupère les mots clés du fichier meta.xml.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on a pas trouvé ce tag ou bien il n'existe pas.<li> {@code ArrayList} des mots clés.</li>
    */
    public static ArrayList<String> getKeywords(Path mainDirectory){
        DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        String toMetaFile=mainDirectory.toString()+File.separator+"meta.xml";
        ArrayList<String> listKeyword= new ArrayList<String>();
        try {
            builder= builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream(new File(toMetaFile)));
            NodeList offDoc= doc.getElementsByTagName("meta:keyword");
            if (offDoc.getLength()>0){
                for (int i=0; i<offDoc.getLength();i++) {
                    listKeyword.add(offDoc.item(i).getTextContent());
                }
                return listKeyword;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    /**
     * Récupère les keywords sous la forme String.
     * @param mainDirectory chemin vers le dossier temporaire.
     * @return String 
     */
    public static String getKeywordsAsString(Path mainDirectory){
        ArrayList<String> keyword = getKeywords(mainDirectory);
        String res = "";
        if (keyword!=null){
            for (String string : keyword) {
                res+= string +", " ;
            }
            res = res.substring(0,res.lastIndexOf(","));
        }
        return res;
    }
}