package GUIMeta;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
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
    
    
    /** 
     * change l'arborescence par le nouveau file passé en paramètre.
     * @param f dossier passé.
     */
    public void changeJTree(File f){
        this.noeud.removeAllChildren();
        creatFeuille(noeud, f);
        jTree.expandRow(0);
        jTree.updateUI();
    }
    /**
    * <p>Ajoute à l'arborescence les fichiers en .odt ou dossiers contenant un .odt</p>
    * <p>Fonctionnement :<ul><li>si File est un .odt on l'ajoute au noeud</li> 
    *  <li>si File est un dossier alors on appelle à nouveau creatFeuille </li>
    *  <li>si le DMT de File est une feuille alors on a forcément un .odt
    *  sinon ca veut dire qui a un fils qui contient un .odt</li></ul></p>
    * @param noeud DefaultMutableTreeNode
    * @param f File
    */
    public void creatFeuille(DefaultMutableTreeNode noeud, File f){
        DefaultMutableTreeNode fil=new DefaultMutableTreeNode(f.getName());
        if (f.isFile()&&f.getName().endsWith(".odt")){
            try {
                if (Files.probeContentType(f.toPath()).contains("vnd.oasis.opendocument.text")){
                    noeud.add(fil);
                }
            } catch (Exception e) {}
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

    /**
     * Retourne vrai si le chemin est un fichier
     * @param path
     * @return
     */
    public static boolean checkPathIsFile(String path){
        return new File(path).isFile(); 
    } 

    
    /** 
     * Ajoute un mouselistener à jtree.
     * @param ml
     */
    //GETTER SETTER
    public void AddMouseListenerJTree(MouseListener ml){
        jTree.addMouseListener(ml);
    }
    
    /** 
     * retourne la jtree utilisé.
     * @return JTree
     */
    public JTree getjTree() {
        return jTree;
    }
}
