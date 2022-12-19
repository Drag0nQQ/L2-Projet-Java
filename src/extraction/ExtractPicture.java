package extraction;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Cette classe permet de chercher les images du dossier media ou Pictures.
 * @author Laurent LIN
 * @author Axel OLIVEIRA
 */
public class ExtractPicture {
    /**
    * Permet d'afficher le nom des images, leur taille et leur extension
    * <b>Attention les documents .odt créés par Word ont un dossier "media"
    tandis que les documents par OpenOffice ou LibreOffice ont un dossier "Pictures".</b>
    <p>Traite maintenant les 2 cas depuis la {@code version 0.2}</p>
    * @param mainDirectory chemin vers le dossier temporaire.
    * @see gestionfichier.ZipEtUnzip#unzip pour créer le dossier temporaire.
    * @version 0.2
    */
    public static void showPicture(Path mainDirectory) {
        String toPicFiles= mainDirectory.toString()+File.separator+"media";
        String toPicFilesBis= mainDirectory.toString()+File.separator+"Pictures";
        File[] picList= new File(toPicFiles).listFiles();
        File[] picListBis= new File(toPicFilesBis).listFiles();
        try{
            if (picList!=null){
                System.out.println("Nombre d'images: "+picList.length);
                for (File file : picList) {
                    System.out.println("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
                }
            }
            if (picListBis!=null){
                System.out.println("Nombre d'images: "+picList.length);
                for (File file : picListBis) {
                    System.out.println("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //GETTERS SETTERS
    /**
    * Retourne le chemin aboslu vers le thumbnail, sinon {@code null} si on le trouve pas.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} si on ne le trouve pas.<li>{@code String file} si on l'a trouvé</li>
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
    
    
    /**
    * Retourne une ArrayList de chaque image avec leur extension et taille.
    * @param mainDirectory chemin vers le dossier temporaire.
    * @return {@code null} s'il n'y a pas d'image.
    * <li>{@code arrayLink} ArrayList de chaque image.</li>
    *
    */
    public static ArrayList<String> getPictures(Path mainDirectory){
        ArrayList<String> picList = null;
        String toMedia= mainDirectory.toString()+File.separator+"media";
        String toPictures= mainDirectory.toString()+File.separator+"Pictures";
        File[] FileList=new File(toMedia).listFiles();
        File[] FileListBis=new File(toPictures).listFiles();
        if (FileList!=null){
            picList = new ArrayList<String>();
            for (File file : FileList) {
                picList.add("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
            }
        }else{
            if (FileListBis!=null){
                picList = new ArrayList<String>();
                for (File file : FileListBis) {
                    picList.add("Nom: "+file.getName().substring(0,file.getName().lastIndexOf("."))+"\t\tTaille:"+file.length()/1024+"kB"+"\tExt: "+file.getName().substring(file.getName().lastIndexOf(".")+1));
                }
            }
        }
        return picList; 
    }
}
