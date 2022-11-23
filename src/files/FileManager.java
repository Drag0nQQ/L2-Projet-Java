package files;

import java.io.*;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileManager {
    //Attributes
    private ZipFile tmp;


    //Constructors
    public FileManager(){
    }


    //Methods

    /**
     * Transform a given fileName and store in his class the ZipFile of the fileName.zip 
     * @see #changeExt(File, String)
     * @exception NoSuchElementException The filename was not found or don't exist
     */
    public void transformation(File file)  throws NoSuchElementException{
        try {
            File test = changeExt(file, "zip");
            System.out.println(test);
            ZipFile tmp = new ZipFile(test);
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
        File newFile = new File(file.getParentFile(), filename);
        file.renameTo(newFile);
        return newFile;
    }


    public void unzip(File file) throws IOException {
        String fileZip = file.getAbsolutePath();
        File directory = new File(file.getAbsolutePath());
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null){
            File newFile = new File(directory, String.valueOf(zipEntry));
            if (zipEntry.isDirectory()){
                if (!newFile.isDirectory() && !newFile.mkdirs()){
                    throw new IOException("Impossible de créer le répertoire " + newFile);
                }
            } else {
                File fileParent = newFile.getParentFile();
                if (!fileParent.isDirectory() && !fileParent.mkdirs()){
                    throw new IOException("Impossible de créer le répertoire " + fileParent);
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0){
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    //Getters Setters
    public ZipFile getTmp() {
        return tmp;
    }
}

