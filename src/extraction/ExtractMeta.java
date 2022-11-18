package extraction;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtractMeta {

    // Reçoit un fichier zip et ressort tous les fichiers XML de ce zip.
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

    public File openMeta(){
        ZipEntry zipEntry = zipFile.getEntry("meta.xml");
        zipEntry.
        File fileXML = new File(zipEntry);
    }

}
