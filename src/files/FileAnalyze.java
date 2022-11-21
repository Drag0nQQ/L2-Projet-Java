package files;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class FileAnalyze {
    
    public static final int FILE_OPTION=1;
    public static final int DIRECTORY_OPTION=2;
    public static final int HELP_OPTION=3;
    public static final int SHOW_OPTION=0;
    /**
    * Filtre et renvoie l'option sélectionné sous forme de int
    * @param args : String[]
    * @return <ul>
    *   <li>FILE_OPTION  si c'est un fichier</li>
    *   <li>DIRECTORY_OPTION si c'est un dossier</li>
    *   <li>HELP_OPTION par défaut ou problème dans les paramètres d'arguments</li></ul>
    */
    public static int checkOption(String[] args){
        if (args.length%2==0 && args.length/2<=3 && args.length>1){
            switch (args[0]) {
                case "-f":
                return FILE_OPTION;
                
                case "-d":
                return DIRECTORY_OPTION;
                
                default:
                return SHOW_OPTION;
            }
        }
        else{return HELP_OPTION;}
    }
    
    
    
    
    //TODO: faire en sorte que le nombre de parametre est légal + préparer -title , -h ,-f -d -subject  
    public void menu(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        if (args.length>0 && args[0].equalsIgnoreCase("-d"))
        {
            File dir = new File(args[1]);
            showDirectories(dir.listFiles());
        }
    }
    
    
    /**
    * <p>Permet l'affichage d'un dossier sur la console.<br/></p>
    * <p>Affiche le nom du dossier, les sous dossiers et fichiers</p>
    * <p><i>méthode récursive</i></p>
    * @version 0.1
    * @param files : liste de tous les fichiers (dans le sens brut)
    * 
    * 
    */
    public static void showDirectories(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                //TODO reflechir a si cest utile de donner le Directory path
                try {
                System.out.println("Directory: " + file.getCanonicalPath());

                } catch (Exception e) {
                    System.err.println("Access denied, no permission to access it.");
                }
                
                showDirectories(file.listFiles());
            } else {
                if (file.getName().endsWith("odt")){
                    System.out.println("File: " + file.getName());
                }
            }
        }
    }
    
    /**
    * Affiche les métadonnées du fichier en question
    * @see #ExtractMeta
    * @throws NoSuchFileException  File n'existe pas ou non trouvé
    */
    public static void showFile(String filename) throws NoSuchFileException {
        try {
            FileManager fm= new FileManager();
            fm.transformation(filename);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

