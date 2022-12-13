package test;
import extraction.*;
import gestionfichier.ZipEtUnzip;

import java.io.*;
import java.nio.file.Path;
public class TestExtract {
    public static void main(String[] args) {
        try {
            Path dossierTravail= Path.of("C:/Users/Laurent/Desktop/VS Code/zoijoiehgoierz");
            ZipEtUnzip.unzip(new FileInputStream(new File("C:/Users/Laurent/Desktop/VS Code/Copie_sujet_projet_L2-I_POO-java_2022-2023_v1.1.odt")), dossierTravail);
            System.out.println(ExtractMeta.getTitle(dossierTravail));
            System.out.println(ExtractMeta.getCreation_date(dossierTravail));
            System.out.println(ExtractMeta.getSubject(dossierTravail));
            System.out.println(ExtractMeta.getKeywords(dossierTravail)); 
            System.out.println(ExtractMeta.getAuthor(dossierTravail));
            System.out.println(ExtractPicture.getPictures(dossierTravail));
            ZipEtUnzip.supprDossier(new File(dossierTravail.toString()));   
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
