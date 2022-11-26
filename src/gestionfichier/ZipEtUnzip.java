package gestionfichier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipEtUnzip {
    /**
    * <p>Dézip un fichier et le stocke dans un dossier (crée le dossier s'il n'existe pas)</p>
    * <p><i>Probkème de l'attaque Zip Slip résolu en comparant le chemin avant et après normalisation (retire les "./" et "../")</i></p>
    * @param is InputStream of the file/directory
    * @param targetDir Save all the files in this directory
    * @throws IOException tried to open a non-existant file or directory
    */
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
    public static boolean supprDossier(File dossierAsupprimer) {
        File[] allContents = dossierAsupprimer.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                supprDossier(file);
            }
        }
        return dossierAsupprimer.delete();
    }
}
