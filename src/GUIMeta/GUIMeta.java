package GUIMeta;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Date;

import files.*;
import extraction.*;
import gestionfichier.*;

public class GUIMeta extends JFrame{

    private Path dossierTravail = Path.of("/Users/axel/Documents/ProjetTest");
    private File tmp = new File("/Users/axel/Documents/testing.odt");
    private JTextField titre;
    private JTextField auteur;
    private JTextField sujet;
    private JTextField keyword;
    private JTextField date;
    private JTextField nbCaracteres;
    private JTextField nbMots;
    private JTextField nbImages;
    private JTextField nbPages;
    private JTextField tailleImage;
    private JTextField poidImage;
    private JTextArea lienHypertxt;
    private JButton jbAnnuler;
    private JButton jbModifier;
    private JButton jbAppliquer;
    private JButton jbClear;
    private JMenu jMenu;
    private JMenuItem ouvrir;
    private JMenu enregistrer;
    private JMenuItem sous;
    private JMenuItem quitter;
    private JLabel imageLabel;
    private ImageIcon imageIcon;
    private JTree arbre;
    private JFileChooser chooser;

    public GUIMeta(){
        this("GUI MEtadonnees");
    }

    public GUIMeta(String title){
        super(title);
        try {
            ZipEtUnzip.unzip(new FileInputStream(tmp),dossierTravail);

        } catch (Exception exception){
            exception.printStackTrace();
        }

        this.titre = new JTextField(40);
        titre.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.auteur = new JTextField(20);
        auteur.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.sujet = new JTextField(20);
        sujet.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.keyword = new JTextField(20);
        keyword.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.date = new JTextField(20);
        date.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.nbMots = new JTextField(5);
        nbMots.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.nbCaracteres = new JTextField(5);
        nbCaracteres.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.nbPages = new JTextField(5);
        nbPages.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.nbImages = new JTextField(5);
        nbImages.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.tailleImage = new JTextField(5);
        tailleImage.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.poidImage = new JTextField(5);
        poidImage.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.lienHypertxt = new JTextArea();
        lienHypertxt.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        this.chooser = new JFileChooser();


        titre.setEditable(false);
        auteur.setEditable(false);
        sujet.setEditable(false);
        keyword.setEditable(false);
        date.setEditable(false);
        nbMots.setEditable(false);
        nbCaracteres.setEditable(false);
        nbPages.setEditable(false);
        nbImages.setEditable(false);
        tailleImage.setEditable(false);
        poidImage.setEditable(false);
        lienHypertxt.setEditable(false);

        titre.setBackground(Color.decode("#3e4e81"));
        titre.setForeground(Color.decode("#ffffff"));
        auteur.setBackground(Color.decode("#3e4e81"));
        auteur.setForeground(Color.decode("#ffffff"));
        sujet.setBackground(Color.decode("#3e4e81"));
        sujet.setForeground(Color.decode("#ffffff"));
        keyword.setBackground(Color.decode("#3e4e81"));
        keyword.setForeground(Color.decode("#ffffff"));
        date.setBackground(Color.decode("#3e4e81"));
        date.setForeground(Color.decode("#ffffff"));
        nbMots.setBackground(Color.decode("#3e4e81"));
        nbMots.setForeground(Color.decode("#ffffff"));
        nbCaracteres.setBackground(Color.decode("#3e4e81"));
        nbCaracteres.setForeground(Color.decode("#ffffff"));
        nbPages.setBackground(Color.decode("#3e4e81"));
        nbPages.setForeground(Color.decode("#ffffff"));
        nbImages.setBackground(Color.decode("#3e4e81"));
        nbImages.setForeground(Color.decode("#ffffff"));
        tailleImage.setBackground(Color.decode("#3e4e81"));
        tailleImage.setForeground(Color.decode("#ffffff"));
        poidImage.setBackground(Color.decode("#3e4e81"));
        poidImage.setForeground(Color.decode("#ffffff"));
        lienHypertxt.setBackground(Color.decode("#3e4e81"));
        lienHypertxt.setForeground(Color.decode("#ffffff"));

        this.imageIcon = new ImageIcon(ExtractPicture.getThumbnails(dossierTravail));

        ImageIcon imgAnnuler = new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/signal.png");
        this.jbAnnuler = new JButton("Annuler", imgAnnuler);
        jbAnnuler.setVerticalTextPosition(AbstractButton.CENTER);
        jbAnnuler.setHorizontalTextPosition(AbstractButton.LEADING);

        ImageIcon imgModifier = new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/edit.png");
        this.jbModifier = new JButton("Modifier", imgModifier);
        jbModifier.setVerticalTextPosition(AbstractButton.CENTER);
        jbModifier.setHorizontalTextPosition(AbstractButton.LEADING);

        ImageIcon imgAppliquer = new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/checked.png");
        this.jbAppliquer = new JButton("Appliquer", imgAppliquer);

        ImageIcon imgClear = new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/rubber.png");
        this.jbClear = new JButton("Clear", imgClear);
        jbClear.setVerticalTextPosition(AbstractButton.CENTER);
        jbClear.setHorizontalTextPosition(AbstractButton.LEADING);

        this.chooser = new JFileChooser();

        jbAnnuler.setToolTipText("Annule la modification du titre et/ou sujet.");
        jbModifier.setToolTipText("Permet de modifier le titre et/ou le sujet");
        jbAppliquer.setToolTipText("Applique le(s) changement(s) effectué(s)");

        JLabel jlTitre = new JLabel("<HTML><U>Titre</U></HTML>");
        JLabel jlAuteur = new JLabel("<HTML><U>Auteur</U></HTML>");
        JLabel jlSujet = new JLabel("<HTML><U>Sujet</U></HTML>");
        JLabel jlKeyword = new JLabel("<HTML><U>Mots clés</U></HTML>");
        JLabel jlDate = new JLabel("<HTML><U>Date</U></HTML>");
        JLabel jlNbMots = new JLabel("<HTML><U>Nombre de mots</U></HTML>");
        JLabel jlNbCaracteres = new JLabel("<HTML><U>Nombre de caractères</U></HTML>");
        JLabel jlNbPages = new JLabel("<HTML><U>Nombre de pages</U></HTML>");
        JLabel jlNbImages = new JLabel("<HTML><U>Nombre d'images</U></HTML>");
        JLabel jlTailleImg = new JLabel("<HTML><U>Taille des images</U></HTML>");
        JLabel jlPoidsImg = new JLabel("<HTML><U>Poids des images</U></HTML>");
        JLabel jlLienHypTxt = new JLabel("<HTML><U>Lien(s) hypertext</U></HTML>");
        this.imageLabel = new JLabel(imageIcon);

        JMenuBar jMenuBar = new JMenuBar();
        this.jMenu = new JMenu("Fichier");
        this.ouvrir = new JMenuItem("Ouvrir");
        ouvrir.setToolTipText("Ouvre le fichier sélectionner");
        this.enregistrer = new JMenu("Enregistrer");
        enregistrer.setToolTipText("Choisir entre enregistrer directement ou enregistrer sous");
        this.sous = new JMenuItem("Enregistrer sous");
        sous.setToolTipText("Enregistre sous le fichier qui est ouvert actuellement");
        enregistrer.add(sous);
        this.quitter = new JMenuItem("Quitter");
        quitter.setToolTipText("Permet de quitter la fenêtre");
        quitter.addActionListener(new ActionQuitter());

        JPanel jpTitre = new JPanel();
        titre.setPreferredSize(new Dimension(75, 25));
        jlTitre.setForeground(Color.decode("#ffffff"));
        jlTitre.setFont(new Font("Arial", Font.BOLD, 18));
        jpTitre.add(jlTitre);
        jpTitre.add(titre);
        titre.setText(ExtractMeta.getTitle(dossierTravail));
        titre.setFont(new Font("Arial", Font.PLAIN, 18));
        jpTitre.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTitre.setBackground(Color.decode("#3e4e81"));

        JPanel jpAuteur = new JPanel();
        auteur.setPreferredSize(new Dimension(75, 25));
        jlAuteur.setForeground(Color.decode("#ffffff"));
        jlAuteur.setFont(new Font("Arial", Font.BOLD, 18));
        jpAuteur.add(jlAuteur);
        jpAuteur.add(auteur);
        auteur.setText("Laurent / Axel");
        auteur.setFont(new Font("Arial", Font.PLAIN, 18));
        jpAuteur.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpAuteur.setBackground(Color.decode("#3e4e81"));

        JPanel jpSujet = new JPanel();
        sujet.setPreferredSize(new Dimension(75, 25));
        jlSujet.setForeground(Color.decode("#ffffff"));
        jlSujet.setFont(new Font("Arial", Font.BOLD, 18));
        jpSujet.add(jlSujet);
        jpSujet.add(sujet);
        sujet.setText(ExtractMeta.getSubject(dossierTravail));
        sujet.setFont(new Font("Arial", Font.PLAIN, 18));
        jpSujet.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpSujet.setBackground(Color.decode("#3e4e81"));

        JPanel jpKeyword = new JPanel();
        keyword.setPreferredSize(new Dimension(75, 25));
        jlKeyword.setForeground(Color.decode("#ffffff"));
        jlKeyword.setFont(new Font("Arial", Font.BOLD, 18));
        jpKeyword.add(jlKeyword);
        jpKeyword.add(keyword);
        keyword.setText("Salut \n Coucou \n Salut \n");
        keyword.setFont(new Font("Arial", Font.PLAIN, 18));
        jpKeyword.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpKeyword.setBackground(Color.decode("#3e4e81"));

        JPanel jpDate = new JPanel();
        date.setPreferredSize(new Dimension(75, 25));
        jlDate.setForeground(Color.decode("#ffffff"));
        jlDate.setFont(new Font("Arial", Font.BOLD, 18));
        jpDate.add(jlDate);
        jpDate.add(date);
        date.setText(ExtractMeta.getCreation_date(dossierTravail));
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        jpDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpDate.setBackground(Color.decode("#3e4e81"));

        JPanel jpCaracteres = new JPanel();
        nbCaracteres.setPreferredSize(new Dimension(75, 25));
        jlNbCaracteres.setForeground(Color.decode("#ffffff"));
        jlNbCaracteres.setFont(new Font("Arial", Font.BOLD, 18));
        jpCaracteres.add(jlNbCaracteres);
        jpCaracteres.add(nbCaracteres);
        nbCaracteres.setText("245934");
        nbCaracteres.setFont(new Font("Arial", Font.PLAIN, 18));
        jpCaracteres.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCaracteres.setBackground(Color.decode("#3e4e81"));

        JPanel jpMots = new JPanel();
        nbMots.setPreferredSize(new Dimension(75, 25));
        jlNbMots.setForeground(Color.decode("#ffffff"));
        jlNbMots.setFont(new Font("Arial", Font.BOLD, 18));
        jpMots.add(jlNbMots);
        jpMots.add(nbMots);
        nbMots.setText("12000");
        nbMots.setFont(new Font("Arial", Font.PLAIN, 18));
        jpMots.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpMots.setBackground(Color.decode("#3e4e81"));

        JPanel jpPages = new JPanel();
        nbPages.setPreferredSize(new Dimension(75, 25));
        jlNbPages.setForeground(Color.decode("#ffffff"));
        jlNbPages.setFont(new Font("Arial", Font.BOLD, 18));
        jpPages.add(jlNbPages);
        jpPages.add(nbPages);
        nbPages.setText(ExtractMeta.getNbPages(dossierTravail));
        nbPages.setFont(new Font("Arial", Font.PLAIN, 18));
        jpPages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpPages.setBackground(Color.decode("#3e4e81"));

        JPanel jpImages = new JPanel();
        nbImages.setPreferredSize(new Dimension(75, 25));
        jlNbImages.setForeground(Color.decode("#ffffff"));
        jlNbImages.setFont(new Font("Arial", Font.BOLD, 18));
        jpImages.add(jlNbImages);
        jpImages.add(nbImages);
        nbImages.setText("6");
        nbImages.setFont(new Font("Arial", Font.PLAIN, 18));
        jpImages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpImages.setBackground(Color.decode("#3e4e81"));

        JPanel jpTailleImage = new JPanel();
        tailleImage.setPreferredSize(new Dimension(75, 25));
        jlTailleImg.setForeground(Color.decode("#ffffff"));
        jlTailleImg.setFont(new Font("Arial", Font.BOLD, 18));
        jpTailleImage.add(jlTailleImg);
        jpTailleImage.add(tailleImage);
        tailleImage.setText("60x34");
        tailleImage.setFont(new Font("Arial", Font.PLAIN, 18));
        jpTailleImage.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTailleImage.setBackground(Color.decode("#3e4e81"));

        JPanel jpPoidImg = new JPanel();
        poidImage.setPreferredSize(new Dimension(75, 25));
        jlPoidsImg.setForeground(Color.decode("#ffffff"));
        jlPoidsImg.setFont(new Font("Arial", Font.BOLD, 18));
        jpPoidImg.add(jlPoidsImg);
        jpPoidImg.add(poidImage);
        poidImage.setText("6" + "Ko");
        poidImage.setFont(new Font("Arial", Font.PLAIN, 18));
        jpPoidImg.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpPoidImg.setBackground(Color.decode("#3e4e81"));

        lienHypertxt.setPreferredSize(new Dimension(500, 84));
        JScrollBar scrollBarLiens = new JScrollBar(JScrollBar.VERTICAL);
        lienHypertxt.add(scrollBarLiens);

        JPanel jpLienHypertxt = new JPanel();
        jlLienHypTxt.setForeground(Color.decode("#ffffff"));
        jlLienHypTxt.setFont(new Font("Arial", Font.BOLD, 18));
        jpLienHypertxt.add(jlLienHypTxt);
        jpLienHypertxt.add(lienHypertxt);
        lienHypertxt.setLineWrap(true);
        lienHypertxt.append("""
                https://www.cyu.fr/medias/fichier/4-institut-st-mcc-licence-2022-2023-definitivement-definitif_1664525133013-pdf\s
                https://www.ganttproject.biz/download/free
                https://doc.ubuntu-fr.org/simplescreenrecorder
                https://obsproject.com/
                https://opendocumentformat.org""");
        lienHypertxt.setFont(new Font("Arial", Font.PLAIN, 12));
        jpLienHypertxt.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpLienHypertxt.setBackground(Color.decode("#3e4e81"));

        JPanel caseHG = new JPanel();
        caseHG.setLayout(new BoxLayout(caseHG,BoxLayout.Y_AXIS));
        caseHG.setPreferredSize(new Dimension(1000, 800));
        caseHG.add(jpTitre);
        caseHG.add(jpAuteur);
        caseHG.add(jpSujet);
        caseHG.add(jpKeyword);
        caseHG.add(jpDate);
        caseHG.add(jpCaracteres);
        caseHG.add(jpMots);
        caseHG.add(jpPages);
        caseHG.add(jpImages);
        caseHG.add(jpTailleImage);
        caseHG.add(jpPoidImg);
        caseHG.add(jpLienHypertxt);
        caseHG.setBackground(Color.decode("#3e4e81"));

        JScrollPane scrollHG = new JScrollPane(caseHG);
        scrollHG.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHG.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel panelAuDessusBouton = new JPanel();
        panelAuDessusBouton.setPreferredSize(new Dimension(277, 40));
        panelAuDessusBouton.setBackground(Color.decode("#777da7"));

        JPanel panelBouton = new JPanel();
        panelBouton.add(jbAnnuler);
        jbAnnuler.setVisible(false);
        panelBouton.add(jbModifier);
        panelBouton.add(jbAppliquer);
        jbAppliquer.setVisible(false);
        jbAnnuler.addActionListener(new ActionAnnuler());
        jbModifier.addActionListener(new ActionModifier());
        jbAppliquer.addActionListener(new ActionAppliquer());
        panelBouton.setPreferredSize(new Dimension(277, 40));
        panelBouton.setBackground(Color.decode("#777da7"));

        JPanel panelEnDessousBouton = new JPanel();
        panelEnDessousBouton.add(jbClear);
        jbClear.addActionListener(new ActionClear());
        panelEnDessousBouton.setPreferredSize(new Dimension(277, 40));
        panelEnDessousBouton.setBackground(Color.decode("#777da7"));

        JPanel caseBG = new JPanel();
        caseBG.add(panelAuDessusBouton);
        caseBG.add(panelBouton);
        caseBG.add(panelEnDessousBouton);
        caseBG.setPreferredSize(new Dimension(830, 120));
        caseBG.setLayout(new BoxLayout(caseBG, BoxLayout.Y_AXIS));
        caseBG.setBackground(Color.decode("#777da7"));

        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(830, 700));
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.Y_AXIS));
        gauche.add(scrollHG);
        scrollHG.setBorder(BorderFactory.createLineBorder(Color.decode("#3e4e81")));
        gauche.add(caseBG);

        JPanel caseHD = new JPanel();
        imageLabel.setPreferredSize(new Dimension(400, 350));
        imageLabel.add(Box.createRigidArea(new Dimension(100, 0)));
        caseHD.add(imageLabel);
        caseHD.setPreferredSize(new Dimension(450, 410));
        caseHD.setBackground(Color.decode("#3e4e81"));

        JPanel caseBD = new JPanel();
        caseBD.setPreferredSize(new Dimension(450, 300));
        caseBD.setBackground(Color.CYAN);
        DefaultMutableTreeNode framework = new DefaultMutableTreeNode(dossierTravail.getFileName());
        DefaultMutableTreeNode autres = new DefaultMutableTreeNode("Salut");
        framework.add(autres);
        JTree jTree = new JTree(framework);
        caseBD.add(new JScrollPane(jTree));
        caseBD.setSize(new Dimension(300, 200));
        caseBD.setVisible(true);

        JPanel droit = new JPanel();
        droit.setPreferredSize(new Dimension(450, 700));
        droit.setLayout(new BoxLayout(droit, BoxLayout.Y_AXIS));
        droit.add(caseHD);
        droit.add(caseBD);

        jMenu.add(ouvrir);
        ouvrir.addActionListener(new ActionOuvrir());
        jMenu.addSeparator();
        jMenu.add(enregistrer);
        jMenu.addSeparator();
        jMenu.add(quitter);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);

        JPanel tout = new JPanel();
        tout.add(gauche);
        tout.setBackground(Color.decode("#232323"));
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
            int fich = chooser.showOpenDialog(ouvrir);
            if (fich == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            titre.setText("");
            auteur.setText("");
            sujet.setText("");
            keyword.setText("");
            date.setText("");
            nbMots.setText("");
            nbCaracteres.setText("");
            nbPages.setText("");
            nbImages.setText("");
            tailleImage.setText("");
            poidImage.setText("");
            lienHypertxt.setText("");
            imageLabel.setIcon(new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/noImg.png"));
        }
    }

    class ActionModifier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            titre.setEditable(true);
            sujet.setEditable(true);
            jbAnnuler.setVisible(true);
            jbAppliquer.setVisible(true);
            jbModifier.setVisible(false);
        }
    }

    class ActionAnnuler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String pourTitre = titre.getText();
            String pourSujet = sujet.getText();
            jbAnnuler.setVisible(false);
            jbModifier.setVisible(true);
            jbAppliquer.setVisible(false);
            int reponse = JOptionPane.showConfirmDialog(jbAnnuler, "Voulez-vous annuler les changements effectués ?", "Annuler les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            if (reponse == JOptionPane.YES_OPTION) {
                titre.setText(ExtractMeta.getTitle(dossierTravail));
                sujet.setText(ExtractMeta.getSubject(dossierTravail));
                titre.setEditable(false);
                sujet.setEditable(false);
                JOptionPane.showMessageDialog(jbAnnuler, "L'annulation des changements a bien été faite.", "Annulation des changements", JOptionPane.OK_OPTION, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            } else if (reponse == JOptionPane.NO_OPTION){
                titre.setEditable(false);
                sujet.setEditable(false);
                titre.setText(pourTitre);
                sujet.setText(pourSujet);
                JOptionPane.showMessageDialog(jbAnnuler, "Vous devrez réactiver la modification si vous voulez changer quelque chose.", "Refus de l'annulation des changements", JOptionPane.OK_OPTION, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            }
        }
    }

    class ActionAppliquer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            jbAnnuler.setVisible(false);
            jbModifier.setVisible(true);
            jbAppliquer.setVisible(false);
            int reponse = JOptionPane.showConfirmDialog(jbAppliquer, "Êtes-vous sûr de bien vouloir appliquer les changements ?", "Appliquer les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            if (reponse == JOptionPane.YES_OPTION){
                titre.setEditable(false);
                sujet.setEditable(false);
                JOptionPane.showMessageDialog(jbAppliquer, "Les changements ont bien été effectués.", "Changements effectués", JOptionPane.OK_OPTION, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/checked.png"));
            } else if (reponse == JOptionPane.NO_OPTION) {
                titre.setText(ExtractMeta.getTitle(dossierTravail));
                sujet.setText(ExtractMeta.getSubject(dossierTravail));
                titre.setEditable(false);
                sujet.setEditable(false);
                JOptionPane.showMessageDialog(jbAppliquer, "Vous devrez réactiver la modification si vous voulez changer quelque chose.", "Refus de l'application des changements", JOptionPane.OK_OPTION, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            }
        }
    }

    class ActionQuitter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int reponse = JOptionPane.showConfirmDialog(quitter, "Voulez-vous quitter la page ?", "Sortir ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/sortie.png"));
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