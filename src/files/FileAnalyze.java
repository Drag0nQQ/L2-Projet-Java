package files;
import java.io.File;

public class FileAnalyze {
    public static void main(String[] args) {
        System.out.println("Hello");
        File dir = new File(args[0]);
        showFiles(dir.listFiles());
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

