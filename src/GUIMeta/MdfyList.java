package GUIMeta;

import java.awt.Dimension;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        setIconImage(new ImageIcon(GUIMeta.logoString).getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }
    
    private void Init(){
        dlm = new DefaultListModel<String>();
        list = new JList<String>(dlm);
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(360,240));
    }
    
    public void saveDLM(){
        try {
            String toExport=null;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GUIMeta.LastSaved,false));
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
    
    public void loadDLM(){
        try {
            String toImport=null;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GUIMeta.LastSaved));
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
    }
    
    public void AddElement(String s){
        dlm.addElement(s);
    }
}
