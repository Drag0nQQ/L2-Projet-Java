package gestionfichier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * Cette classe permet de manipuler les fichiers zip et plus généralement les fichiers dans le sens brut
 */
public class ZipEtUnzip {
    /**
    * <p>Dézip un fichier et le stocke dans un dossier (crée le dossier s'il n'existe pas)</p>
    * <p><i>Problème de l'attaque Zip Slip résolu en comparant le chemin avant et après normalisation (retire les "./" et "../")</i></p>
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
                    throw new IOException("Entry with an illegal path: "+ ze.getName());
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

    public static void zip(File file, Path Dir,ZipOutputStream os) throws IOException{
        if (file.isHidden()) {
            return;
        }
        if (file.isDirectory()) {
            if (Dir.toString().endsWith("/")) {
                os.putNextEntry(new ZipEntry(Dir.toString()));
                os.closeEntry();
            } else {
                os.putNextEntry(new ZipEntry(Dir.toString() + "/"));
                os.closeEntry();
            }
            File[] children = file.listFiles();
            for (File childFile : children) {
                zip(childFile, Path.of( Dir.toString() + "/" + childFile.getName()), os);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(Dir.toString());
        os.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            os.write(bytes, 0, length);
        }
        fis.close();
        
    }
    //TODO JAVADOC afire
    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
