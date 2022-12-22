package GUIMeta;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MdfyList extends JDialog {
    private DefaultListModel<String> dlm;
    private JList<String> list;
    private JScrollPane scrollPane;
    public MdfyList() {
        setBounds(0,0,360,240);
        Init();
        getContentPane().add(scrollPane);
        setAlwaysOnTop(true);
        setTitle("Liste des fichiers modifié par Meta-Digger.io");
        setIconImage(getImgFromResource(GUIMeta.logoString).getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }
    
    private void Init(){
        dlm = new DefaultListModel<String>();
        list = new JList<String>(dlm);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(360,240));
    }
    /**
     * La sauvegarde dans le jar requiert que le fichier jar soit "non lock" par l'OS or lorsqu'on lance le jar, l'OS met le fichier jar en status "lock", d'où la sauvegarde dans un fichier à part.
     * @deprecated
     * @see #saveDLM()
     */
    public void saveDLMfromJar(){
        try {
            File tempSer = File.createTempFile("temp", ".ser");
            String toExport=null;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempSer,false));
            for (int i = 0; i < dlm.getSize(); i++) {
                toExport=dlm.getElementAt(i);
                oos.writeObject(toExport);
            }
            oos.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichier Jar corrompu");
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Sauvagarde tous les fichiers modifiés par notre programme pour qu'au prochain lancement on puisse lister.
     * <i>La sauvegarde dans le jar requiert que le fichier jar soit "non lock" par l'OS or lorsqu'on lance le jar, l'OS met le fichier jar en status "lock", d'où la sauvegarde dans un fichier à part.</i>
     */
    public void saveDLM(){
        try {
            ObjectOutputStream test= new ObjectOutputStream(new FileOutputStream(new File(GUIMeta.LastSaved),false));
           for(int i= 0;i<dlm.size();i++){
                test.writeObject(dlm.getElementAt(i));
              }
            test.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * <p>Charge le fichier data.ser, attention ce fichier doit se trouver dans le même dossier que le jar.</p>
     * <p><i>Sauvegarder dans le jar n'est pas possible car on ne peut pas ajouter un fichier à un Jar en cours d'exécution.
     * D'où la solution médiocre de charger depuis un fichier à part.</i></p>
     */
    public void loadDLM(){
        try {
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream(new File(GUIMeta.LastSaved)));
            String toImport;
            try{
                toImport =(String) ois.readObject();
            }catch (EOFException ex){
                toImport=null;
            }
            while (toImport != null) {
                AddElement(toImport);
                try{
                    toImport = (String) ois.readObject();
                }catch (EOFException ex){
                    toImport=null;
                }
            }
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @deprecated
     * <p>Charge correctement depuis le fichier Jar le fichier data.ser mais la sauvegarde ne fonctionne pas.</p>
     * @see #saveDLMfromJar()
     */
    public void loadDLMfromJar(){
        try {
            String toImport=null;
            try {
                JarFile jarPath=new JarFile(MdfyList.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                ObjectInputStream ois = new ObjectInputStream(jarPath.getInputStream(jarPath.getEntry("data.ser")));
                try{
                    toImport =(String) ois.readObject();
                }catch (EOFException ex){
                    toImport=null;
                }
                while (toImport != null) {
                    AddElement(toImport);
                    try{
                        toImport = (String) ois.readObject();
                    }catch (EOFException ex){
                        toImport=null;
                    }
                }
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            System.err.println("Fichier jar non trouvé dans le chemin");
        }
    }
    
    /**
     * Ajoute un élément à notre liste.
     * @param s String qu'on veut ajouter à la liste.
     */
    public void AddElement(String s){
        dlm.addElement(s);
    }
    
    
    /** 
     * @param path
     * @return ImageIcon
     */
    private ImageIcon getImgFromResource(String path){
        URL odtLien = this.getClass().getResource(path);
        try {
            assert odtLien != null;
            BufferedImage icon = ImageIO.read(odtLien);
            return new ImageIcon(icon);
        } catch (Exception e){}
        return null;
    }
}
