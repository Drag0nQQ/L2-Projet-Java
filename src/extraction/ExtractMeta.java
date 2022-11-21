package extraction;

import files.FileManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
public class ExtractMeta {

    // Méthodes pour récupérer morceaux par morceaux les métadonnées (les noms, les chiffres, les dates, ...)
    // Ajouter ou retirer des tags
    // --title et --subject

    private ZipFile zipFile;

    public ExtractMeta(ZipFile zipFile){
        this.zipFile = zipFile;
    }

    public ZipFile getZipFile(){
        return zipFile;
    }
    public File getMetaFile() throws IOException {
        try {
            File fileTmp = null;
            for (Enumeration<? extends ZipEntry> e = zipFile.entries();
                 e.hasMoreElements();) {
                ZipEntry ze = e.nextElement();
                String name = ze.getName();
                if (name.equalsIgnoreCase("meta.xml")) {
                    InputStream in = zipFile.getInputStream(ze);
                    fileTmp = new File(name);
                }
            }
            zipFile.close();
            return fileTmp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMetaInfo(File f){
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File("meta.xml"));
                document.getDocumentElement().normalize();
                Element root = document.getDocumentElement();
                System.out.println(root.getNodeName());

                NodeList metadataList = document.getElementsByTagName("office:meta");
                System.out.println("--------------------------");


                for (int i = 0; i < metadataList.getLength(); i++){
                    Node node = metadataList.item(i);
                    System.out.println("\n");
                    Element element = (Element)metadataList.item(i);
                    if (element.getTagName().equalsIgnoreCase("dc:title")){
                        System.out.println(element.getElementsByTagName("dc:title"));
                    } else if (element.getTagName().equalsIgnoreCase("dc:subject")){
                        System.out.println(element.getElementsByTagName("dc:subject"));
                    } else if (element.getTagName().equalsIgnoreCase("meta:creation-date")){
                        System.out.println(element.getElementsByTagName("medat:creation-date"));
                    } else if (element.getAttribute("page-count").equalsIgnoreCase("page-count")){
                        System.out.println(element.getElementsByTagName("me:document-statistic").item(i));
                    }
                }
            } catch (Exception exception){
                exception.printStackTrace();
            }
    }
}
