package GUIMeta;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.io.File;
/**
 * Cette classe permet de créer et gérer la case bas droit de notre GUI
 */
public class CaseBD extends JPanel {
    private JPanel auDessusBD;
    private JLabel jlAuDessusBD;
    private JScrollPane panelJTree;
    private JPanel enDessousBD;
    private DefaultMutableTreeNode noeud;
    private JTree jTree;
    
    public CaseBD() {
        super();
        
        //JPanel du dessus
        auDessusBD = new JPanel();
        jlAuDessusBD = new JLabel("<HTML><U>Arborescence du dossier</U></HTML>");
        jlAuDessusBD.setFont(new Font("Arial", Font.PLAIN, 15));
        auDessusBD.add(jlAuDessusBD);
        jlAuDessusBD.setForeground(Color.decode("#1f2740"));
        auDessusBD.setPreferredSize(new Dimension(277, 40));
        auDessusBD.setBackground(Color.decode("#c5c9d9"));
        auDessusBD.setBorder(BorderFactory.createLineBorder(Color.decode("#ffffff")));
        
        //JPanel du dessous
        enDessousBD = new JPanel();
        enDessousBD.setPreferredSize(new Dimension(277, 40));
        enDessousBD.setBackground(Color.decode(GUIMeta.mainColor));
        
        //JTree
        noeud = new DefaultMutableTreeNode("Mon Dossier Sélectionné");
        //TODO devra commencer a vide
        creatFeuille(noeud, new File(GUIMeta.dossierJtreeTest));
        jTree = new JTree(noeud);
        jTree.setBackground(Color.decode("#ffffff"));
        panelJTree = new JScrollPane(jTree);
        panelJTree.setBackground(Color.decode(GUIMeta.mainColor));
        panelJTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        add(auDessusBD);
        add(panelJTree);
        add(enDessousBD);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void changeJTree(File f){
        this.noeud.removeAllChildren();
        creatFeuille(noeud, f);
        jTree.updateUI();
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
            assert liste != null;
            if (liste!=null){
                if (liste.length>0){
                    for (File iterateurFile : liste) {
                        creatFeuille( node, iterateurFile);
                    }
                }
                if (!node.isLeaf()) {
                    noeud.add(node);
                }
            }
        }
    }
}
