package test;

import extraction.ExtractMeta;
import files.*;
import java.io.File;

public class Test {

    public static void main(String[] args) {
        try {
            FileManager tmp = new FileManager();
            File file = new File("/Users/axel/Dev/TestProjet/Sans_nom_1.odt");
            System.out.println( tmp.getTmp());
            tmp.transformation(file.getAbsolutePath());
            System.out.println( tmp.getTmp());
            ExtractMeta extractMeta = new ExtractMeta(tmp.getTmp());
            extractMeta.getMetaInfo(extractMeta.getMetaFile());
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
