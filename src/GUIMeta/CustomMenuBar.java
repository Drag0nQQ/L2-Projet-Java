package GUIMeta;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
public class CustomMenuBar extends JMenuBar {
    private JMenu jMenu,enregistrer;
    private JMenuItem ouvrir, sous, quitter;
    public CustomMenuBar() {
        super();
        jMenu = new JMenu("Fichier");
        ouvrir = new JMenuItem("Ouvrir");
        enregistrer = new JMenu("Enregistrer");
        sous = new JMenuItem("Enregistrer sous");
        enregistrer.add(sous);
        quitter = new JMenuItem("Quitter");

        //ToolTips
        ouvrir.setToolTipText("Ouvre le fichier sélectionner");
        enregistrer.setToolTipText("Choisir entre enregistrer directement ou enregistrer sous");
        quitter.setToolTipText("Permet de quitter la fenêtre");
        sous.setToolTipText("Enregistre sous le fichier qui est ouvert actuellement");

        jMenu.add(ouvrir);
        jMenu.addSeparator();
        jMenu.add(enregistrer);
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