package GUIMeta;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.Random;
import java.util.zip.ZipOutputStream;

import extraction.*;
import gestionfichier.*;

public class GUIMeta extends JFrame{
    //TODO dat shit dont work
    public static final String LastSaved="data.ser";
    /**
     * Racine de nos assets.
     */
    public static final String img="/img";
    public static Path dossierTravail = null;
    /**
     * Code Hex pour la couleur utilisé pour la couleur du background.
     */
    public static final String mainColor="#3e4e81";
    /**
     * Code Hex pour la couleur utilisé pour la couleur de la font.
    */
    public static final String fontColor="#ffffff";
    /**
    * Chemin en String de l'icone 
    */
    public static final String toNoImgString=img+"/noImg.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toAnnulerString=img+"/signal.png";
    /**
     * Chemin en String de l'icone 
    */
    public static final String toModifierString=img+"/edit.png";
    /**
     * Chemin en String de l'icone 
    */
    public static final String toAppliquerString=img+"/checked.png";
    /**
    * Chemin en String de l'icone 
    */
    public static final String toClearString=img+"/rubber.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toCrayonString=img+"/crayon.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toExitString=img+"/sortie.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toFolderString= img+"/folder.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toSmallExitString=img+"/smallexit.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String toSaveString=img+"/save.png";
    /**
     * Chemin en String de l'icone 
     */
    public static final String logoString=img+"/logoMD.png";
    /**
     * Chemin en String du gif
     */
    public static final String splashscreen=img+"/loading.gif";
    
    private CaseHG caseHG;
    private CaseHD caseHD;
    private CaseBD caseBD;
    private CaseBG caseBG;
    private CustomMenuBar jMenuBar;
    private MdfyList newjlist,oldjlist;
    
    private boolean modified=false;
    private boolean opened=false;
    private boolean firstTime=true;
    
    private JFileChooser chooser,chooseDir;
    
    public GUIMeta(){
        this("Meta-Digger.io");
    }
    
    public GUIMeta(String title){
        super(title);
        //Initialisation

        this.chooser = new JFileChooser();
        this.chooseDir = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OpenDocument Text", "odt");
        chooser.addChoosableFileFilter(filter);
        
        //Initialisation des JDialog pour lister les fichiers modifiés
        try {
            newjlist = new MdfyList();
            newjlist.setVisible(false);
            oldjlist = new MdfyList();
            oldjlist.loadDLM();
        } catch (Exception e) {
            System.err.println("probleme load data");
        }

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
        
        caseHD = new CaseHD();
        
        caseBD = new CaseBD();
        caseBD.AddMouseListenerJTree(new DoubleClicArbre());
        
        JPanel droit = new JPanel();
        droit.setPreferredSize(new Dimension(450, 700));
        droit.setLayout(new BoxLayout(droit, BoxLayout.Y_AXIS));
        droit.add(caseHD);
        droit.add(caseBD);

        //MenuBar
        jMenuBar = new CustomMenuBar();
        jMenuBar.AddActListenerQuitter(new ActionQuitter());
        jMenuBar.AddActListenerOuvrir(new ActionOuvrir());
        jMenuBar.AddActListenerSave(new ActionSave());
        jMenuBar.AddActListenerSous(new ActionSous());
        jMenuBar.AddActListenerDossier(new ActionDossier());
        this.setJMenuBar(jMenuBar);
        
        //Main panel
        JPanel tout = new JPanel();
        tout.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        tout.add(gauche);
        tout.add(droit);

        Container main = super.getContentPane();
        main.add(tout);

        //Misc
        this.setResizable(false);
        this.setLocation(100, 25);
        this.pack();
        this.setIconImage(getImgFromResource(logoString).getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ActionWindowClosing());
        this.setVisible(true);
    }

    
    /** 
     * @param file
     */
    private void OpenFile(File file){
        if (opened) {
            ZipEtUnzip.supprDossier(dossierTravail.toFile());
        }
        dossierTravail=Path.of(new Random().ints(97, 122 + 1).limit(30).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
        try {
            ZipEtUnzip.unzip(new FileInputStream(file), dossierTravail);
            caseHG.loadMeta(dossierTravail);
            opened = true;
            if (ExtractPicture.getThumbnails(dossierTravail)!=null){
                caseHD.replaceImg(new ImageIcon(ExtractPicture.getThumbnails(dossierTravail)));
            }else{
                caseHD.replaceImg(getImgFromResource(GUIMeta.toNoImgString));
            }
            modified=false;
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, err.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE, getImgFromResource(GUIMeta.toAnnulerString));
        }
    }

    class ActionOuvrir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int fich = chooser.showOpenDialog(jMenuBar.getOuvrir());
            if (fich == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                if (firstTime){
                    caseBG.jbClearVisible(true);
                    caseBG.jbModifierVisible(true);
                    firstTime=false;
                }
                if (modified) {
                    int reponse = JOptionPane.showConfirmDialog(null, "Attention votre travail n'a pas été sauvegarder,\nOuvrir sans sauvegarder ?", "Ouvrir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (reponse==JOptionPane.YES_OPTION){
                        OpenFile(file);
                    }else{
                        new ActionSous().actionPerformed(null);
                    }
                }else{
                    OpenFile(file);
                }
            }
        }
    }
    
    class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            caseHG.TextFieldclear();
            if (opened) {
                ZipEtUnzip.supprDossier(dossierTravail.toFile());
                opened=false;
            }
            caseHD.replaceImg(getImgFromResource(GUIMeta.toNoImgString));
            modified=false;
        }
    }
    
    class ActionModifier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            caseHG.titreEditable(true);
            caseHG.sujetEditable(true);
            caseHG.setBorderTitre(BorderFactory.createLineBorder(Color.decode("#ffffff")));
            caseHG.setBorderSujet(BorderFactory.createLineBorder(Color.decode("#ffffff")));
            caseBG.jbAnnulerVisible(true);
            caseBG.jbModifierVisible(false);
            caseBG.jbAppliquerVisible(true);
            caseBG.jbClearVisible(false);
        }
    }
    
    class ActionAnnuler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous annuler les changements effectués ?", "Annuler les changements ?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, getImgFromResource(GUIMeta.toCrayonString));
            if (reponse == JOptionPane.OK_OPTION) {
                caseBG.jbAnnulerVisible(false);
                caseBG.jbModifierVisible(true);
                caseBG.jbAppliquerVisible(false);
                caseBG.jbClearVisible(true);
                caseHG.setTitreField(ExtractMeta.getTitle(dossierTravail));
                caseHG.setSujetField(ExtractMeta.getSubject(dossierTravail));
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                caseHG.setBorderTitre(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                caseHG.setBorderSujet(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                JOptionPane.showMessageDialog(caseBG.getJbAnnuler(), "L'annulation des changements a bien été faite.", "Annulation des changements", JOptionPane.PLAIN_MESSAGE, getImgFromResource(GUIMeta.toCrayonString));
            }
        }
    }
    
    class ActionAppliquer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de bien vouloir appliquer les changements ?", "Appliquer les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, getImgFromResource(GUIMeta.toCrayonString));
            if (reponse == JOptionPane.YES_OPTION){
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                caseHG.setBorderTitre(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                caseHG.setBorderSujet(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                ExtractMeta.setTitle(dossierTravail, caseHG.getTitreField());
                ExtractMeta.setSubject(dossierTravail, caseHG.getSujetField());
                JOptionPane.showMessageDialog(null, "Les changements ont bien été effectués.", "Changements effectués", JOptionPane.OK_OPTION, getImgFromResource(GUIMeta.toAppliquerString));
                modified=true;
                caseBG.jbClearVisible(true);
                caseBG.jbAnnulerVisible(false);
                caseBG.jbModifierVisible(true);
                caseBG.jbAppliquerVisible(false);
            } else if (reponse == JOptionPane.NO_OPTION) {
                caseHG.setTitreField(ExtractMeta.getTitle(dossierTravail));
                caseHG.setSujetField(ExtractMeta.getSubject(dossierTravail));
                caseHG.titreEditable(false);
                caseHG.sujetEditable(false);
                caseHG.setBorderTitre(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                caseHG.setBorderSujet(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
                JOptionPane.showMessageDialog(null, "Vous devrez réactiver la modification si vous voulez changer quelque chose.", "Refus de l'application des changements", JOptionPane.OK_OPTION, getImgFromResource(GUIMeta.toCrayonString));
                modified=false;
                caseBG.jbClearVisible(true);
                caseBG.jbAnnulerVisible(false);
                caseBG.jbModifierVisible(true);
                caseBG.jbAppliquerVisible(false);
            }
        }
    }
    
    class ActionSave implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!firstTime){
                ZipOutputStream zos = null;
                try {
                    zos = new ZipOutputStream(new FileOutputStream(chooser.getSelectedFile()));
                    ZipEtUnzip.zip("", new File(dossierTravail.toString()), zos);
                    zos.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                modified=false;
                newjlist.AddElement(chooser.getSelectedFile().getPath());
            }
        }
    }
    
    class ActionSous implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = chooser.showOpenDialog(rootPane);
            if (reponse==JFileChooser.APPROVE_OPTION) {
                if (!firstTime) {
                    ZipOutputStream zos=null;
                    try {
                        zos= new ZipOutputStream(new FileOutputStream(chooser.getSelectedFile()));
                        ZipEtUnzip.zip("", new File(dossierTravail.toString()), zos);
                        zos.close();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    modified=false;
                    newjlist.AddElement(chooser.getSelectedFile().getPath());
                }
            }
        }
    }
    
    class ActionDossier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            chooseDir=new JFileChooser();
            chooseDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int choix = chooseDir.showDialog(jMenuBar.getDossier(),"Ouvrir");
            if (choix==JFileChooser.APPROVE_OPTION){
                caseBD.changeJTree(chooseDir.getSelectedFile());
            }
        }
    }
    
    class ActionQuitter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(jMenuBar.getQuitter(), "Voulez-vous quitter la page ?", "Sortir ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, getImgFromResource(GUIMeta.toExitString));
            if (reponse == JOptionPane.YES_OPTION){
                if (modified){
                    int rep2 = JOptionPane.showConfirmDialog(jMenuBar.getQuitter(), "Attention, vous n'avez pas sauvegardé votre travail. Voulez-vous sauvegarder ?", "Sauvegarde ?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, getImgFromResource(GUIMeta.toExitString));
                    if (rep2 == JOptionPane.YES_OPTION){
                        new ActionSous().actionPerformed(null);
                    }
                }
                if (opened){
                    ZipEtUnzip.supprDossier(dossierTravail.toFile());
                }
                newjlist.saveDLM();
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
    
    class DoubleClicArbre extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int selRow = caseBD.getjTree().getRowForLocation(e.getX(), e.getY());
            TreePath selPath = caseBD.getjTree().getPathForLocation(e.getX(), e.getY());
            if(selRow != -1) {
                if(e.getClickCount() == 2) {
                    assert selPath != null;
                    String file = selPath.getLastPathComponent().toString();
                    if (chooseDir.getSelectedFile()!=null){
                        for (int i = selPath.getPathCount();i>2;i--){
                            selPath = selPath.getParentPath();
                            file = selPath.getLastPathComponent().toString() + File.separator+file;
                        }
                        file= chooseDir.getSelectedFile().getParent()+File.separator+file;
                        chooser.setSelectedFile(new File(file));
                        if (CaseBD.checkPathIsFile(file)){
                            if (firstTime){
                                caseBG.jbClearVisible(true);
                                caseBG.jbModifierVisible(true);
                                firstTime=false;
                            }
                            if (modified) {
                                int reponse = JOptionPane.showConfirmDialog(null, "Attention votre travail n'a pas été sauvegarder,\nOuvrir sans sauvegarder ?", "Ouvrir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                if (reponse==JOptionPane.YES_OPTION){
                                    OpenFile(new File(file));
                                }else{
                                    new ActionSous().actionPerformed(null);
                                }
                            }else{
                                OpenFile(new File(file));
                            }
                        }
                    }
                }
                if (e.getClickCount()==1){
                    assert selPath != null;
                    String file = selPath.getLastPathComponent().toString();
                    if (chooseDir.getSelectedFile()!=null){
                        for (int i = selPath.getPathCount();i>2;i--){
                            selPath = selPath.getParentPath();
                            file = selPath.getLastPathComponent().toString() + File.separator+file;
                        }
                        file= chooseDir.getSelectedFile().getParent()+File.separator+file;
                        if (CaseBD.checkPathIsFile(file)){
                            caseBD.setTaille(String.valueOf((new File(file).length())/1024));
                            caseBD.setExt(new File(file).getName().substring(new File(file).getName().lastIndexOf(".")));
                        }
                    }
                }
            }   
        }
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
