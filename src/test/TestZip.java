package test;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

import gestionfichier.ZipEtUnzip;


public class TestZip {
    public static void main(String[] args) {
        Path test = Path.of("C:/Users/Laurent/Desktop/test3.odt");
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(test.toFile()));
            File tmp =new File("C:/Users/Laurent/Desktop/VS Code/impossibleQueCeDossierSAppelleCommeCaVraimentSiJLeTrouveCestChaud");
            ZipEtUnzip.zip("",tmp , zout);
            zout.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
