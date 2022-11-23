package files;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileManager {
    //Attributes
    private ZipFile tmp;


    //Constructors
    public FileManager() {
    }


    //Methods

    /**
     * Transform a given fileName and store in his class the ZipFile of the fileName.zip
     *
     * @throws NoSuchElementException The filename was not found or don't exist
     * @see #changeExt(File, String)
     */
    public void transformation(File file) throws NoSuchElementException {
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
     *
     * @param file      can be anything with supported extensions
     * @param extension String should be {@code "XXX" for example: txt, pdf, odt}
     * @return File.extension
     * @version 0.1
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

    public ArrayList<File> unzip(File file, File destDir) {
        ArrayList<File> metaFiles = new ArrayList<>();
        try {
            byte[] buffer = new byte[1024];
            System.out.println(file.getAbsolutePath());
            ZipInputStream zis = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                File newFile = new File(destDir.getAbsolutePath(), ze.getName());
                if (ze.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Impossible de créer le répertoire " + newFile);
                    }
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Impossible de créer le répertoire " + newFile);
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int length = zis.read(buffer);
                    while (length > 0) {
                        fos.write(buffer, 0, length);
                        length = zis.read(buffer);
                    }
                    if (ze.getName().equalsIgnoreCase("meta.xml") || ze.getName().equalsIgnoreCase("content.xml")){
                        metaFiles.add(newFile);
                    }
                    fos.close();
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metaFiles;
    }
}
