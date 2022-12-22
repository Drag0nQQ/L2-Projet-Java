package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.Random;
import java.util.zip.ZipOutputStream;

import extraction.*;
import files.*;
import gestionfichier.ZipEtUnzip;
/**
 * Permet de lancer le CLI.
 */
public class CLI {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int i = FileAnalyze.checkOption(args);
        switch (i) {
            case FileAnalyze.DIRECTORY_OPTION:
                File file= new File(args[1]);
                FileAnalyze.showDirectories(file.listFiles());
                break;
            case FileAnalyze.FILE_OPTION:
                try {
                    int j= FileAnalyze.checkModifier(args);
                    //Generer un dossier temporaire unique pour travailler dessus
                    Path dossierTravail= Path.of(new String(new Random().ints(97, 122 + 1).limit(30).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()));
                    ZipEtUnzip.unzip(new FileInputStream(new File(args[1])), dossierTravail);
                    switch (j) {
                        case FileAnalyze.SUBJECT_AND_TITLE_OPTION:
                            if (args[2].equals("--subject")) {
                                ExtractMeta.setSubject(dossierTravail, args[3]);
                                ExtractMeta.setTitle(dossierTravail, args[5]);
                            }else{
                                ExtractMeta.setTitle(dossierTravail, args[3]);
                                ExtractMeta.setSubject(dossierTravail, args[5]);
                            }
                            break;
                        case FileAnalyze.SUBJECT_OPTION:
                            ExtractMeta.setSubject(dossierTravail, args[3]);
                            break;
                        case FileAnalyze.TITLE_OPTION:
                            ExtractMeta.setTitle(dossierTravail, args[3]);
                            break;    
                        default:
                            break;
                    }
                    FileAnalyze.showFile(dossierTravail.toString());
                    try {
                        ZipOutputStream fin= new ZipOutputStream(new FileOutputStream(new File(Path.of(args[1]).getParent().toString()+"/Copie_"+Path.of(args[1]).getFileName())));
                        ZipEtUnzip.zip("",new File(dossierTravail.toString()),fin); 
                        fin.close();  
                    } catch (Exception e) {
                        System.err.println("Fichier de sortie erroné.");
                    }
                    ZipEtUnzip.supprDossier(new File(dossierTravail.toString()));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case FileAnalyze.SHOW_OPTION:
                FileAnalyze.showOption();
                break;
            default:
                FileAnalyze.showHelp();
                break;
        }
    }
}
