package test;

import extraction.ExtractMeta;
import files.*;
import java.io.File;

public class Test {

    public static void main(String[] args) {
        try {
            FileManager tmp = new FileManager();
            ExtractMeta extractMeta = new ExtractMeta();
            File file = new File("/Users/axel/Documents/GitHub/L2-Projet-Java/test.odt");
            extractMeta.getMetaFile(file);
            extractMeta.getMetaInfo(file);
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
