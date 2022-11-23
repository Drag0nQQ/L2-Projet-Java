package test;

import extraction.ExtractMeta;
import files.*;
import java.io.File;

public class Test {

    public static void main(String[] args) {
        try {
            FileManager tmp = new FileManager();
            File file = new File("testing.odt");
            System.out.println( tmp.getTmp());
            tmp.transformation(file);
            tmp.unzip(file);

        } catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
