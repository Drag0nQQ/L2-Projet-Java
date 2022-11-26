package test;
import extraction.*;
import gestionfichier.ZipEtUnzip;

import java.io.*;
import java.nio.file.Path;
public class TestExtract {
    public static void main(String[] args) {
        try {
            Path dossierTravail= Path.of("C:\\Users\\Laurent\\Desktop\\VS Code\\test");
            ZipEtUnzip.unzip(new FileInputStream(new File("sujet_projet_L2-I_POO-java_2022-2023_v1.1.odt")), dossierTravail);
            ExtractMeta.showMeta(dossierTravail);
            ExtractPicture.showPicture(dossierTravail);
            ExtractContent.showLink(dossierTravail);
            ZipEtUnzip.supprDossier(new File(dossierTravail.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
}
