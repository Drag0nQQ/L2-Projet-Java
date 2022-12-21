package GUIMeta;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class CustomMenuBar extends JMenuBar {
    private JMenu jMenu;
    private JMenuItem ouvrir,dossier ,save, sous, quitter;
    public CustomMenuBar() {
        super();
        jMenu = new JMenu("Fichier");
        ouvrir = new JMenuItem("Ouvrir", getImgFromResource(GUIMeta.toFolderString));
        save = new JMenuItem("Enregistrer",getImgFromResource(GUIMeta.toSaveString));
        sous = new JMenuItem("Enregistrer sous");
        quitter = new JMenuItem("Quitter",getImgFromResource(GUIMeta.toSmallExitString));
        dossier = new JMenuItem("Ouvrir dossier");
        //ToolTips
        ouvrir.setToolTipText("Ouvre le fichier sélectionner");
        quitter.setToolTipText("Permet de quitter la fenêtre");
        save.setToolTipText("Enregistre les changements dans le fichier actuel");
        sous.setToolTipText("Enregistre sous le fichier qui est ouvert actuellement");
        dossier.setToolTipText("Ouvre le dossier dans l'arborescence");

        jMenu.add(ouvrir);
        jMenu.add(dossier);
        jMenu.addSeparator();
        jMenu.add(save);
        jMenu.addSeparator();
        jMenu.add(sous);
        jMenu.addSeparator();
        jMenu.add(quitter);
        
        add(jMenu);

    }
    //Setter
    
    public void AddActListenerQuitter(ActionListener action){
        quitter.addActionListener(action);
    }

    public void AddActListenerOuvrir(ActionListener action){
        ouvrir.addActionListener(action);
    }
    public void AddActListenerSave(ActionListener action){
        save.addActionListener(action);
    }

    public void AddActListenerSous(ActionListener action) {
        sous.addActionListener(action);
    }

    public void AddActListenerDossier(ActionListener action) {
        dossier.addActionListener(action);
    }
    //Getter
    public JMenuItem getOuvrir() {
        return ouvrir;
    }
    public JMenuItem getSous() {
        return sous;
    }
    public JMenuItem getQuitter() {
        return quitter;
    }
    public JMenuItem getDossier() {
        return dossier;
    }
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