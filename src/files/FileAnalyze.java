package files;
import java.io.File;

public class FileAnalyze {
    //TODO: faire en sorte que le nombre de parametre est légal + préparer -title , -h ,-f -d -subject  
    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        if (args.length>0 && args[0].equalsIgnoreCase("-t"))
        {
        File dir = new File(args[1]);
        showFiles(dir.listFiles());
        }
    }

    public static void showFiles(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                showFiles(file.listFiles()); // Calls same method again.
            } else {
                System.out.println("File: " + file.getAbsolutePath());
            }
        }
    }
}

