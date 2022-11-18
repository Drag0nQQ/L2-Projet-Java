package files;

import java.io.*;

public class FileManager {

    public void transformation(String fileName) {
        File tmp = new File(fileName);
        try {
            File test=  changeExt(tmp, "zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static File changeExt(File file, String extension) {
        String filename = file.getName();

        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }
        filename += "." + extension;

        file.renameTo(new File(file.getParentFile(), filename));
        return file;
    }
}

