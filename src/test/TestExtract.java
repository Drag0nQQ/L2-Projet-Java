package test;
import extraction.*;
import gestionfichier.ZipEtUnzip;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;
public class TestExtract {
    public static void main(String[] args) {
        try {
            Path dossierTravail= Path.of("C:\\Users\\Laurent\\Desktop\\VS Code\\impossibleQueCeDossierSAppelleCommeCaVraimentSiJLeTrouveCestChaud");
            ZipEtUnzip.unzip(new FileInputStream(new File(args[0])), dossierTravail);
            ExtractMeta.showMeta(dossierTravail);
            ExtractPicture.showPicture(dossierTravail);
            ExtractContent.showLink(dossierTravail);
            ExtractMeta.setTitle(dossierTravail, "Je suis beau");
            ExtractMeta.setSubject(dossierTravail, "JE SAIS PAS SI CA MARCHEEE");
            //TODO aucune idée de comment ca marcje
            String sourceFile = "impossibleQueCeDossierSAppelleCommeCaVraimentSiJLeTrouveCestChaud//";
            FileOutputStream fos = new FileOutputStream("dirCompressed.odt");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            
            File fileToZip = new File(sourceFile);
            ZipEtUnzip.zipFile(fileToZip,fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
            ZipEtUnzip.supprDossier(dossierTravail.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
}
