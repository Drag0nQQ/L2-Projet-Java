package GUIMeta;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

import extraction.*;
import gestionfichier.*;

public class GUIMeta extends JFrame{
    public static final String dossierJtreeTest="/Users/Laurent/Desktop/VS CODE/monBordel";
    public static final String ImagePourLeProjetJava="/Users/Laurent/Documents/ImagePourLeProjetJava";
    public static Path dossierTravail = Path.of("/Users/Laurent/Documents/ProjetTest");
    /**
     * Code Hex pour la couleur utilisé
     */
    public static final String mainColor="#3e4e81";
    /**
     * Code Hex pour la couleur utilisé
     */
    public static final String fontColor="#ffffff";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toNoImgString=ImagePourLeProjetJava+"/noImg.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toAnnulerString=ImagePourLeProjetJava+"/signal.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toModifierString=ImagePourLeProjetJava+"/edit.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toAppliquerString=ImagePourLeProjetJava+"/checked.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toClearString=ImagePourLeProjetJava+"/rubber.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toCrayonString=ImagePourLeProjetJava+"/crayon.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toExitString=ImagePourLeProjetJava+"/sortie.png";
    
    CaseHG caseHG;
    CaseHD caseHD;
    CaseBD caseBD;
    CaseBG caseBG;
    CustomMenuBar jMenuBar;
    
    private File tmp = new File("/Users/Laurent/Documents/testing.odt");

    private JFileChooser chooser;
    
    public GUIMeta(){
        this("GUI Métadonnees");
    }
    
    public GUIMeta(String title){
        super(title);
        try {
            ZipEtUnzip.unzip(new FileInputStream(tmp),dossierTravail);
            
        } catch (Exception exception){
            exception.printStackTrace();
        }
        this.chooser = new JFileChooser();
        
        // lienHypertxt.setPreferredSize(new Dimension(500, 84));
        // JScrollBar scrollBarLiens = new JScrollBar(JScrollBar.VERTICAL);
        // lienHypertxt.add(scrollBarLiens);
        
        //Cote GAUCHE
        caseHG = new CaseHG();
        
        JScrollPane scrollHG = new JScrollPane(caseHG);
        scrollHG.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHG.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        caseBG = new CaseBG();
        caseBG.AddActListenerModifier(new ActionModifier());
        caseBG.AddActListenerClear(new ActionClear());
        caseBG.AddActListenerAppliquer(new ActionAppliquer());
        caseBG.AddActListenerAnnuler(new ActionAnnuler());
        
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(830, 700));
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.Y_AXIS));
        gauche.add(scrollHG);
        scrollHG.setBorder(BorderFactory.createLineBorder(Color.decode(mainColor)));
        gauche.add(caseBG);
        
        //COTE DROIT
        
        caseHD = new CaseHD(dossierTravail);
        
        caseBD = new CaseBD();
        //TODO changeJTREE ne marche pas
        caseBD.changeJTree(new File("C:/Users/Laurent/Documents"));
        
        JPanel droit = new JPanel();
        droit.setPreferredSize(new Dimension(450, 700));
        droit.setLayout(new BoxLayout(droit, BoxLayout.Y_AXIS));
        droit.add(caseHD);
        droit.add(caseBD);
        
        jMenuBar = new CustomMenuBar();
        jMenuBar.AddActListenerQuitter(new ActionQuitter());
        jMenuBar.AddActListenerOuvrir(new ActionOuvrir());
        jMenuBar.AddActListenerSous(null);
        this.setJMenuBar(jMenuBar);
        
        JPanel tout = new JPanel();
        tout.add(gauche);
        tout.setBackground(Color.decode(mainColor));
        tout.add(droit);
        
        Container main = super.getContentPane();
        main.add(tout);
        
        this.setResizable(true);
        this.setLocation(100, 25);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ActionWindowClosing());
    }
    
    class ActionOuvrir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int fich = chooser.showOpenDialog(jMenuBar.getOuvrir());
            if (fich == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                //TODO
                System.out.println(file.getAbsolutePath());
            }
        }
    }
    
    class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            caseHG.TextFieldclear();
            caseHD.replaceImg(new ImageIcon(GUIMeta.toNoImgString));
        }
    }
    
    class ActionModifier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            caseHG.titreEditable(true);
            caseHG.sujetEditable(true);
            caseBG.jbAnnulerVisible(true);
            caseBG.jbModifierVisible(false);
            caseBG.jbAppliquerVisible(true);
        }
    }
    
    class ActionAnnuler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String pourTitre = caseHG.getTitreField();
            String pourSujet = caseHG.getSujetField();
            caseBG.jbAnnulerVisible(false);
            caseBG.jbModifierVisible(true);
            caseBG.jbAppliquerVisible(false);
            int reponse = JOptionPane.showConfirmDialog(caseBG.getJbAnnuler(), "Voulez-vous annuler les changements effectués ?", "Annuler les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GUIMeta.toCrayonString));
            if (reponse == JOptionPane.YES_OPTION) {
                caseHG.setTitreField(ExtractMeta.getTitle(dossierTravail));
                caseHG.setSujetField(ExtractMeta.getSubject(dossierTravail));
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                JOptionPane.showMessageDialog(caseBG.getJbAnnuler(), "L'annulation des changements a bien été faite.", "Annulation des changements", JOptionPane.OK_OPTION, new ImageIcon(GUIMeta.toCrayonString));
            } else if (reponse == JOptionPane.NO_OPTION){
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                caseHG.setTitreField(pourTitre);
                caseHG.setSujetField(pourSujet);
                JOptionPane.showMessageDialog(caseBG.getJbAnnuler(), "Vous devrez réactiver la modification si vous voulez changer quelque chose.", "Refus de l'annulation des changements", JOptionPane.OK_OPTION, new ImageIcon(GUIMeta.toCrayonString));
            }
        }
    }
    
    class ActionAppliquer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            caseBG.jbAnnulerVisible(false);
            caseBG.jbModifierVisible(true);
            caseBG.jbAppliquerVisible(false);
            int reponse = JOptionPane.showConfirmDialog(caseBG.getJbAppliquer(), "Êtes-vous sûr de bien vouloir appliquer les changements ?", "Appliquer les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GUIMeta.toCrayonString));
            if (reponse == JOptionPane.YES_OPTION){
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                JOptionPane.showMessageDialog(caseBG.getJbAppliquer(), "Les changements ont bien été effectués.", "Changements effectués", JOptionPane.OK_OPTION, new ImageIcon(GUIMeta.toAppliquerString));
            } else if (reponse == JOptionPane.NO_OPTION) {
                caseHG.setTitreField(ExtractMeta.getTitle(dossierTravail));
                caseHG.setSujetField(ExtractMeta.getSubject(dossierTravail));
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                JOptionPane.showMessageDialog(caseBG.getJbAppliquer(), "Vous devrez réactiver la modification si vous voulez changer quelque chose.", "Refus de l'application des changements", JOptionPane.OK_OPTION, new ImageIcon(GUIMeta.toCrayonString));
            }
        }
    }
    
    class ActionQuitter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(jMenuBar.getQuitter(), "Voulez-vous quitter la page ?", "Sortir ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GUIMeta.toExitString));
            if (reponse == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
    
    class ActionWindowClosing extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            new ActionQuitter().actionPerformed(null);
        }
    }
}
