package test;

import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;

public class EncoreuneTree extends JFrame{
    JTree jtree;
    JPanel pane1;
    JLabel label1;
    DefaultMutableTreeNode noeud;
    JScrollPane scrollpane;
    public EncoreuneTree() {
        super("ENCORE DES ARBRES N-Aires");
        noeud=new DefaultMutableTreeNode("Root du noeud");
        creatFeuille(noeud, new File("C:/Users/Laurent/Desktop"));
        jtree=new JTree(noeud);
        scrollpane=new JScrollPane(jtree);

        Container main= super.getContentPane();
        main.add(scrollpane);
        main.setPreferredSize(new Dimension(480,240));
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
    }
    /**
     * <p>Ajoute à l'arborescence les fichiers en .odt ou dossiers contenant un .odt</p>
     * <p>Fonctionnement :<ul><li>si File est un .odt on l'ajoute au noeud</li> 
     *  <li>si File est un dossier alors on appelle à nouveau creatFeuille </li>
     *  <li>si le DTM de File est une feuille alors on a forcément un .odt
     *  sinon ca veut dire qui a un fils qui contient un .odt</li></ul></p>
     * @param noeud DefaultMutableTreeNode
     * @param f File
     */
    public void creatFeuille(DefaultMutableTreeNode noeud, File f){
        DefaultMutableTreeNode fil=new DefaultMutableTreeNode(f.getName());
        if (f.isFile()&&f.getName().endsWith(".odt")){
            //TODO checker via mimeType
            noeud.add(fil);
        }
        if (f.isDirectory()) {
            File[] liste=f.listFiles();
            DefaultMutableTreeNode node=new DefaultMutableTreeNode(f.getName());
            for (File iterateurFile : liste) {
                creatNoeud(fil, iterateurFile);
                creatFeuille( node, iterateurFile);
            }
            if (!node.isLeaf()) {
                noeud.add(node);
            }
           
        }
        
    }
    /**
     * Permet d'ajouter au DTM racine, le DTM File.
     * @param racine
     * @param file
     */
    public void creatNoeud(DefaultMutableTreeNode racine,File file){
        DefaultMutableTreeNode fils=new DefaultMutableTreeNode(file.getName());
        racine.add(fils);
    }
    public static void main(String[] args) {
        new EncoreuneTree();
    }
}
