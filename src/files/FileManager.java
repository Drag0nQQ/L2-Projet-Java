package files;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.zip.ZipFile;

public class FileManager {
    //Attributes
    private ZipFile tmp;


    //Constructors
    public FileManager(){
    }


    //Methods

    /**
     * Transform a given fileName and store in his class the ZipFile of the fileName.zip 
     * @param fileName Absolute path to the file
     * @see #changeExt(File, String)
     * @exception NoSuchElementException The filename was not found or don't exist
     */
    public void transformation(String fileName)  throws NoSuchElementException{
        File file = new File(fileName);
        try {
            File test=  changeExt(file, "zip");
            System.out.println(test);
            this.tmp= new ZipFile(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rename the file by replacing the current extension with {@code .extension} given in parameter
     * <b>No save is made before so make sure to save a copy before.</b> 
     * @version 0.1
     * @param file can be anything with supported extensions
     * @param extension String should be {@code "XXX" for example: txt, pdf, odt}
     * @return File.extension
     */
    public static File changeExt(File file, String extension) {
        String filename = file.getAbsolutePath();
        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }
        filename += "." + extension;
        file.renameTo(new File(file.getParentFile(), filename));
        return file;
    }


    //Getters Setters
    public ZipFile getTmp() {
        return tmp;
    }
}

