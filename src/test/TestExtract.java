package test;
import extraction.*;
import gestionfichier.ZipEtUnzip;

import java.io.*;
import java.nio.file.Path;
public class TestExtract {
    public static void main(String[] args) {
        try {
            Path dossierTravail= Path.of("/Users/axel/Dev/TestProjet/Test");
            ZipEtUnzip.unzip(new FileInputStream(new File(args[0])), dossierTravail);
            ExtractMeta.showMeta(dossierTravail);
            ExtractPicture.showPicture(dossierTravail);
            ExtractContent.showLink(dossierTravail);
            ExtractMeta.setTitle(dossierTravail, "Je suis beau");
            ExtractMeta.setSubject(dossierTravail, "JE SAIS PAS SI CA MARCHEEE");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
