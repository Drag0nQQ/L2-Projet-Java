package test;

import java.io.File;
import java.nio.file.NoSuchFileException;

import extraction.*;
import files.*;

public class TestCLI {
    public static void main(String[] args) {
        int i = FileAnalyze.checkOption(args);
        if (i==FileAnalyze.DIRECTORY_OPTION){
            File file= new File(args[1]);
            FileAnalyze.showDirectories(file.listFiles());
        }
        else{
            if (i==FileAnalyze.FILE_OPTION) {
                //TODO showFile meta
                //try{
                //call FileAnalyze.showFile(args[0]);
                //}catch (NoSuchFileException e){
                //    System.err.println(e.getMessage());
                //}
            } else {
                if (i==FileAnalyze.SHOW_OPTION) {
                    //TODO call FileAnalyze.showOption();
                } else {
                    //TODO call FileAnalyze.showHelp();
                }
            }
        }
    }
}