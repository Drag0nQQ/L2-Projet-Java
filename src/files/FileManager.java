package files;

import java.io.*;
import java.util.zip.ZipFile;

public class FileManager {
    private ZipFile tmp;
    public FileManager(){
    }

    public void transformation(String fileName) {
        File file = new File(fileName);
        try {
            File test=  changeExt(file, "zip");
            System.out.println(test);
            this.tmp= new ZipFile(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ZipFile getTmp() {
        return tmp;
    }

    public static File changeExt(File file, String extension) {
        String filename = file.getAbsolutePath();

        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }
        filename += "." + extension;

        file.renameTo(new File(file.getParentFile(), filename));
        return file;
    }
}

