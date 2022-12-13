package extraction;
import java.io.*;
import java.nio.file.Path;


/**
* Cette classe permet de chercher les images du dossier media
*/
public class ExtractPicture {
    /**
    * Permet d'afficher le nom des images, leur taille et leur extension
    * <b>Attention les documents .odt créés par Word ont un dossier "media"
    tandis que les documents par OpenOffice ou LibreOffice ont un dossier "Pictures"
     ce dernier cas n'est pas traité.</b>
    * @param mainDirectory chemin vers le dossier temporaire
    * @see gestionfichier.ZipEtUnzip#unzip pour créer le dossier temporaire
    * @version 0.1
    */
    public static void showPicture(Path mainDirectory) {
        //TODO Pictures/ ou media/ à chercher
        String toPicFiles= mainDirectory.toString()+"\\media";
        File[] picList= new File(toPicFiles).listFiles();
        try{
            if (picList!=null){
                System.out.println("Nombre d'images: "+picList.length);
                for (File file : picList) {
                    System.out.println("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    //GETTERS SETTERS
    //TODO méthode pour le thumbnail
    /**
     * Retourne le chemin aboslu vers le thumbnail, sinon {@code null} si on le trouve pas
     * @param mainDirectory
     * @return
     */
    public static String getThumbnails(Path mainDirectory){
        String toThumb= mainDirectory.toString()+File.separator+"Thumbnails";
        File[] thumbFolder= new File(toThumb).listFiles();
        try {
            if (thumbFolder != null){
                for (File file : thumbFolder) {
                    if (file.getName().equals("thumbnail.png")){
                        return file.getAbsolutePath().toString();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Thumbnail pas trouvé.");
        }
        return null;
    }

    public static void getPicture(){

    }
}
