package GUIMeta;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
public class CustomMenuBar extends JMenuBar {
    private JMenu jMenu,enregistrer;
    private JMenuItem ouvrir, save, sous, quitter;
    public CustomMenuBar() {
        super();
        jMenu = new JMenu("Fichier");
        ouvrir = new JMenuItem("Ouvrir");
        save = new JMenuItem("Enregistrer");
        sous = new JMenuItem("Enregistrer sous");
        quitter = new JMenuItem("Quitter");

        //ToolTips
        ouvrir.setToolTipText("Ouvre le fichier sélectionner");
        quitter.setToolTipText("Permet de quitter la fenêtre");
        save.setToolTipText("Enregistre les changements dans le fichier actuel");
        sous.setToolTipText("Enregistre sous le fichier qui est ouvert actuellement");

        jMenu.add(ouvrir);
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
}