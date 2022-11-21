package extraction;

import java.util.zip.*;
import java.io.*;



public class DeZip {
    public static void main(String[] args) {
        if (args.length>0){}
        File tmp=new File(args[0]);
        try {
            File test=  changeExt(tmp, "zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
    public static File changeExt(File file, String extension) {
        String filename = file.getName();
        
        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }
        filename += "." + extension;
        
        file.renameTo(new File(file.getParentFile(), filename));
        return file;
    }
    
    
    
    
}

