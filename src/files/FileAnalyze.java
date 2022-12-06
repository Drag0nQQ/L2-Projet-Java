package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import extraction.ExtractContent;
import extraction.ExtractMeta;
import extraction.ExtractPicture;


public class FileAnalyze {
    
    public static final int SHOW_OPTION=0;
    public static final int FILE_OPTION=1;
    public static final int DIRECTORY_OPTION=2;
    public static final int HELP_OPTION=3;
    
    public static final int SUBJECT_OPTION=4;
    public static final int TITLE_OPTION=5;
    public static final int SUBJECT_AND_TITLE_OPTION=6;
    /**
    * Filtre et renvoie l'option sélectionné sous forme de int
    * @param args : String[]
    * @return <ul>
    *   <li>FILE_OPTION  si c'est un fichier</li>
    *   <li>DIRECTORY_OPTION si c'est un dossier</li>
    *   <li>SHOW_OPTION si on a --help</li>
    *   <li>HELP_OPTION par défaut ou problème dans les paramètres d'arguments</li></ul>
    */
    public static int checkOption(String[] args){
        if (args.length==0){
            return HELP_OPTION;
        }
        if (args.length%2==0 && args.length/2<=3 && args.length>1){
            switch (args[0]) {
                case "-f":
                return FILE_OPTION;
                case "-d":
                if (args.length==2) {
                    return DIRECTORY_OPTION;
                }
                default:
                return HELP_OPTION;
            }
        }
        else{
            if(args[0].equalsIgnoreCase("-h")&&args.length==1){
                return SHOW_OPTION;
            }
            else{
                return HELP_OPTION;}
            }
        }
        /**
        * <p>Vérifie quels sont les modifiers utilisé</p>
        * <i>invoquer cette méthode <b>seulement</b> après avoir checker avec {@code FileAnalyze.checkOption}</i>
        * @param args
        * @return <ul>
        * <li>SUBJECT_OPTION si --subject </li>
        * <li>TITLE_OPTION si --title</li>
        * <li>SUBJECT_AND_TITLE_OPTION si --subject et --title</li>
        * </ul>
        * @throws IOException
        * @see {@link #checkOption(String[])}
        */
        public static int checkModifier(String[] args)throws Exception{
            if (args.length%2==0 && args.length/2<=3 && args.length>1){
                if (args.length>4) {
                    if ((args[2].equalsIgnoreCase("--subject")&&args[4].equalsIgnoreCase("--title"))||args[4].equalsIgnoreCase("--subject")&&args[2].equalsIgnoreCase("--title")) {
                        return SUBJECT_AND_TITLE_OPTION;
                    }
                }
                if ((args[2].equalsIgnoreCase("--subject")&& args.length==4)){
                    return SUBJECT_OPTION;
                }
                if (args[2].equalsIgnoreCase("--title")&& args.length==4){
                    return TITLE_OPTION;
                }
                //Si c'est aucun des 3 options, c'est que je n'ai pas pensé a la cette particularité
                //si la personne marque "-f test.odt -huh "blabla" -jjj "raté" "
                throw new IllegalArgumentException("FileAnalyze.checkModifier erreur : Aucun des 3 options, utiliser l'option [-h] pour plus d'information.");
            }
            else{
                throw new IOException("No option found or incorrect syntax use [-h] option.");
            }
        }
        
        
        /**
        * <p>Permet l'affichage d'un dossier sur la console.<br/></p>
        * <p>Affiche le nom du dossier, les sous dossiers et fichiers (uniquement .odt)</p>
        * <p><i>méthode récursive</i></p>
        * @version 0.1
        * @param files : liste de tous les fichiers (dans le sens brut)
        */
        public static void showDirectories(File[] files) {
            for (File file : files) {
                if (file.isDirectory()) {
                    showDirectories(file.listFiles());
                } else {
                    if (file.getName().endsWith("odt")){
                        try {
                            if (Files.probeContentType(file.toPath()).contains("vnd.oasis.opendocument.text")){
                                System.out.println("Size: " +"\t"+file.length()/1024 +"kB"+"\t File: "+ file.getCanonicalPath());
                            }
                        } catch (Exception e) {
                            System.err.println("Access denied, no permission to access it.");
                        }
                    }
                }
            }
        }
        
        /**
        * Affiche les métadonnées du fichier en question
        * <ul><li>Les métadonnées principales</li><li>Les liens internet</li><li>Les images utilisées</li></ul>
        * @see #ExtractMeta
        * @throws NoSuchFileException  File n'existe pas ou non trouvé
        * @param filename chemin vers le dossier contenant les données
        */
        public static void showFile(String filename) throws NoSuchFileException {
            try {
                Path tofile= Path.of(filename);
                ExtractMeta.showMeta(tofile);
                ExtractContent.showLink(tofile);
                ExtractPicture.showPicture(tofile);
            } catch (Exception e) {
                System.err.println("Le chemin vers le fichier est incorrecte ou erronée :");
                e.printStackTrace();
            }
        }
        
        
        /**
        * Affiche ce message lorsqu'on lance avec une syntaxe incorrecte
        */
        public static void showHelp(){
            System.out.println("Vous avez une syntaxe incorrect ou manquante, utiliser l'option <-h> pour plus d'aide");
        }
        
        /**
        * Affiche le help message du programme
        */
        public static void showOption() {
            System.out.println("Usage: java -jar CLI.jar <option> [path] [args] [text]");
            System.out.println("\t <option> :");
            System.out.println("\t \t -h :show this help message");
            System.out.println("\t \t -f :specify that the [path] is a file");
            System.out.println("\t \t -d :specify that the [path] is a directory");
            System.out.println("\t [path] :should be the absolute path to the file or directory");
            System.out.println("\t [args] :only for <-f> option :specify which meta tag to modify.");
            System.out.println("\t \t --title :set title meta tag to [text]");
            System.out.println("\t \t --subject :set subject meta tag to [text]");
            System.out.println("\t [text]: replace the current text with this new text (expected one after each modifiers)");
        }
        
    }
    
    