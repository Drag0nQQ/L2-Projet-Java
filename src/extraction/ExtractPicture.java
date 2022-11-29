package extraction;
import java.io.*;
import java.nio.file.Path;

/**
* Cette classe permet de chercher les images du dossier media
*/
public class ExtractPicture {
    /**
    * Permet d'afficher le nom des images, leur taille et leur extension
    * @param mainDirectory chemin vers le dossier temporaire
    */
    public static void showPicture(Path mainDirectory) {
        String toPicFiles= mainDirectory.toString()+"\\media";
        File[] picList= new File(toPicFiles).listFiles();
        try{
            System.out.println("Nombre d'images: "+picList.length);
            for (File file : picList) {
                System.out.println("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
