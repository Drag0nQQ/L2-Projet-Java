package extraction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;




public class ExtractMeta{
    //Attributes
    
    //Constructors
    
    
    //Methods 
    public static void unzip(InputStream is, Path targetDir) throws IOException {
        targetDir = targetDir.toAbsolutePath();
        try (ZipInputStream zipIn = new ZipInputStream(is)) {
            for (ZipEntry ze; (ze = zipIn.getNextEntry()) != null; ) {
                Path resolvedPath = targetDir.resolve(ze.getName()).normalize();
                if (!resolvedPath.startsWith(targetDir)) {
                    throw new RuntimeException("Entry with an illegal path: "+ ze.getName());
                }
                if (ze.isDirectory()) {
                    Files.createDirectories(resolvedPath);
                } else {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(zipIn, resolvedPath);
                }
            }
        }
    }
    /**
     * <p>Supprime le dossier (et sous dossier + fichier) passé en paramètre</p>
     * <i>Méthode récursive</i> 
     * @param dossierAsupprimer fichier (au sens brut) à supprimer
     * @return <ul>
     * <li>True si tout s'est bien passé</li>
     * <li>False s'il reste des fichiers dans le dossier</li>
     * </ul>
     */
    public static boolean deleteDirectory(File dossierAsupprimer) {
        File[] allContents = dossierAsupprimer.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return dossierAsupprimer.delete();
    }
    
    /**
    * Affiche les metadata du fichier, pour les tags et photos voir methodes
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
        } catch (ParserConfigurationException e) {
            System.err.println("Problème lors de la création d'un Document");
        }
        
        try {
            Document doc = builder.parse(new FileInputStream(new File(toMetaFile)));
            Element offDocMeta= doc.getDocumentElement();
            NodeList nodeMeta = offDocMeta.getChildNodes();
            NodeList subNodeMeta= nodeMeta.item(0).getChildNodes();
            for (int i=0 ; i < subNodeMeta.getLength();i++) {
                if (subNodeMeta.item(i).getNodeName().equals("dc:title")){
                    System.out.println("Titre: "+subNodeMeta.item(i).getTextContent());
                }else{
                    if (subNodeMeta.item(i).getNodeName().equals("dc:subject")){
                        System.out.println("Sujet: "+subNodeMeta.item(i).getTextContent());
                    }else{
                        if (subNodeMeta.item(i).getNodeName().equals("meta:creation-date")){
                            System.out.println("Date de création: "+subNodeMeta.item(i).getTextContent());
                        }else{
                            if (subNodeMeta.item(i).getNodeName().equals("meta:document-statistic")){
                                System.out.println("Statistiques:");
                                System.out.println("Nombre de pages: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:page-count").getTextContent());
                                System.out.println("Nombre de mots: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:word-count").getTextContent());
                                System.err.println("Nombre de caractères: "+subNodeMeta.item(i).getAttributes().getNamedItem("meta:character-count").getTextContent());
                            }else{
                                if (subNodeMeta.item(i).getNodeName().equals("meta:keyword")) {
                                    keyword.add(subNodeMeta.item(i).getTextContent());
                                }
                            }
                        }
                    }
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
        
    }
    
    
    //Getter Setter
    
}