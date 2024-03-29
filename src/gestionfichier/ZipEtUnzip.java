package gestionfichier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
* Cette classe permet de manipuler les fichiers zip et plus généralement les fichiers dans le sens brut.
*/
public class ZipEtUnzip {
    /**
    * <p>Dézip un fichier et le stocke dans un dossier (crée le dossier s'il n'existe pas).</p>
    * <p><i>Problème de l'attaque Zip Slip résolu en comparant le chemin avant et après normalisation (retire les "./" et "../").</i></p>
    * @param is InputStream du file/dossier.
    * @param targetDir Sauvegarde tous les files dans ce dossier.
    * @throws IOException si on ouvre un fichier ou dossier non-existant.
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
    * <p>Supprime le dossier (et sous dossier + fichier) passé en paramètre.</p>
    * <i>Méthode récursive</i>
    * @param dossierAsupprimer fichier (au sens brut) à supprimer
    * @return
    * True si tout s'est bien passé.
    * <li>False s'il reste des fichiers dans le dossier.</li>
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
    /**
     * Permet de mettre le contenu d'un dossier dans un fichier zip.
     * @param basePath pour garder la hiérachie dossiers, sous dossiers et fichiers.
     * @param dir chemin vers le dossier à zip.
     * @param zout sortie du fichier vers ce chemin.
     * @throws IOException apparait lors d'un soucis d'ouverture du fichier.
     */
    public static void zip(String basePath, File dir, ZipOutputStream zout) throws IOException {
        byte[] buffer = new byte[1024];
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                String path = basePath + file.getName() + "/";
                zout.putNextEntry(new ZipEntry(path));
                zip(path, file, zout);
                zout.closeEntry();
            } else {
                FileInputStream fin = new FileInputStream(file);
                zout.putNextEntry(new ZipEntry(basePath + file.getName()));
                int length;
                while ((length = fin.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }
                zout.closeEntry();
                fin.close();
            }
        }
    }
}
