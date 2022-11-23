package test;

import java.io.File;
import java.nio.file.NoSuchFileException;

import extraction.*;
import files.*;

public class TestCLI {
    public static void main(String[] args) {
        int i = FileAnalyze.checkOption(args);
        if (i==FileAnalyze.DIRECTORY_OPTION){
            //TODO ajoute taille Ko
            File file= new File(args[1]);
            FileAnalyze.showDirectories(file.listFiles());
        }
        else{
            if (i==FileAnalyze.FILE_OPTION) {
                System.out.println("On appele showFile(File)");
                //TODO showFile meta6
                //try{
                //call FileAnalyze.showFile(args[1]);
                //}catch (NoSuchFileException e){
                //    System.err.println(e.getMessage());
                //}
            } else {
                if (i==FileAnalyze.SHOW_OPTION) {
                    FileAnalyze.showOption();
                } else {
                    FileAnalyze.showHelp();
                }
            }
        }
    }
}
