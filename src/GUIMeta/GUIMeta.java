package GUIMeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FilenameFilter;

public class GUIMeta extends JFrame{

    private JTextField titre;
    private JTextField auteur;
    private JTextField sujet;
    private JTextField keyword;
    private JTextField date;
    private JTextField statistic;
    private JTextField nbCaracteres;
    private JTextField nbMots;
    private JTextField nbImages;
    private JTextField nbPages;
    private JTextField tailleImage;
    private JTextField poidImage;
    private JTextField lienHypertxt;
    private JButton jbAnnuler;
    private JButton jbModifier;
    private JButton jbAppliquer;
    private JButton jbClear;
    private JMenu jMenu;
    private JMenuItem ouvrir;
    private JMenu enregistrer;
    private JMenuItem sous;
    private JMenuItem quitter;
    private JFileChooser chooser;

    public GUIMeta(){
        this("GUI MEtadonnees");
    }

    public GUIMeta(String title){
        super(title);

        this.titre = new JTextField(20);
        this.auteur = new JTextField(20);
        this.sujet = new JTextField(20);
        this.keyword = new JTextField(20);
        this.date = new JTextField(20);
        this.statistic = new JTextField(20);
        this.nbMots = new JTextField(5);
        this.nbCaracteres = new JTextField(5);
        this.nbPages = new JTextField(5);
        this.nbImages = new JTextField(5);
        this.tailleImage = new JTextField(5);
        this.poidImage = new JTextField(5);
        this.lienHypertxt = new JTextField(60);

        titre.setEditable(false);
        auteur.setEditable(false);
        sujet.setEditable(false);
        keyword.setEditable(false);
        date.setEditable(false);
        statistic.setEditable(false);
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
        statistic.setBackground(Color.decode("#3e4e81"));
        statistic.setForeground(Color.decode("#ffffff"));
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

        ImageIcon imageIcon = new ImageIcon("/Users/axel/Dev/TestProjet/Test/media/image3.png");

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

        JLabel jlTitre = new JLabel("Titre : ");
        JLabel jlAuteur = new JLabel("Auteur : ");
        JLabel jlSujet = new JLabel("Sujet : ");
        JLabel jlKeyword = new JLabel("Keyword : ");
        JLabel jlDate = new JLabel("Date : ");
        JLabel jlStatistic = new JLabel("Statistic : ");
        JLabel jlNbMots = new JLabel("Nombre de mots : ");
        JLabel jlNbCaracteres = new JLabel("Nombre de caractères : ");
        JLabel jlNbPages = new JLabel("Nombre de pages : ");
        JLabel jlNbImages = new JLabel("Nombre d'images : ");
        JLabel jlTailleImg = new JLabel("Taille des images : ");
        JLabel jlPoidsImg = new JLabel("Poids des images : ");
        JLabel jlLienHypTxt = new JLabel("Liens hypertext : ");
        JLabel imageLabel = new JLabel(imageIcon);

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
        jpTitre.add(jlTitre);
        jpTitre.add(titre);
        titre.setText("Mon super titre");
        jpTitre.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTitre.setBackground(Color.decode("#3e4e81"));

        JPanel jpAuteur = new JPanel();
        auteur.setPreferredSize(new Dimension(75, 25));
        jlAuteur.setForeground(Color.decode("#ffffff"));
        jpAuteur.add(jlAuteur);
        jpAuteur.add(auteur);
        auteur.setText("Laurent / Axel");
        jpAuteur.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpAuteur.setBackground(Color.decode("#3e4e81"));

        JPanel jpSujet = new JPanel();
        sujet.setPreferredSize(new Dimension(75, 25));
        jlSujet.setForeground(Color.decode("#ffffff"));
        jpSujet.add(jlSujet);
        jpSujet.add(sujet);
        sujet.setText("Projet POO");
        jpSujet.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpSujet.setBackground(Color.decode("#3e4e81"));

        JPanel jpKeyword = new JPanel();
        keyword.setPreferredSize(new Dimension(75, 25));
        jlKeyword.setForeground(Color.decode("#ffffff"));
        jpKeyword.add(jlKeyword);
        jpKeyword.add(keyword);
        keyword.setText("Salut \n Coucou \n Salut \n");
        jpKeyword.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpKeyword.setBackground(Color.decode("#3e4e81"));

        JPanel jpDate = new JPanel();
        date.setPreferredSize(new Dimension(75, 25));
        jlDate.setForeground(Color.decode("#ffffff"));
        jpDate.add(jlDate);
        jpDate.add(date);
        date.setText("07/12/2022");
        jpDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpDate.setBackground(Color.decode("#3e4e81"));

        JPanel jpCaracteres = new JPanel();
        nbCaracteres.setPreferredSize(new Dimension(75, 25));
        jlNbCaracteres.setForeground(Color.decode("#ffffff"));
        jpCaracteres.add(jlNbCaracteres);
        jpCaracteres.add(nbCaracteres);
        nbCaracteres.setText("245934");
        jpCaracteres.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCaracteres.setBackground(Color.decode("#3e4e81"));

        JPanel jpMots = new JPanel();
        nbMots.setPreferredSize(new Dimension(75, 25));
        jlNbMots.setForeground(Color.decode("#ffffff"));
        jpMots.add(jlNbMots);
        jpMots.add(nbMots);
        nbMots.setText("12000");
        jpMots.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpMots.setBackground(Color.decode("#3e4e81"));

        JPanel jpPages = new JPanel();
        nbPages.setPreferredSize(new Dimension(75, 25));
        jlNbPages.setForeground(Color.decode("#ffffff"));
        jpPages.add(jlNbPages);
        jpPages.add(nbPages);
        nbPages.setText("6");
        jpPages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpPages.setBackground(Color.decode("#3e4e81"));

        JPanel jpImages = new JPanel();
        nbImages.setPreferredSize(new Dimension(75, 25));
        jlNbImages.setForeground(Color.decode("#ffffff"));
        jpImages.add(jlNbImages);
        jpImages.add(nbImages);
        nbImages.setText("6");
        jpImages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpImages.setBackground(Color.decode("#3e4e81"));

        JPanel jpTailleImage = new JPanel();
        tailleImage.setPreferredSize(new Dimension(75, 25));
        jlTailleImg.setForeground(Color.decode("#ffffff"));
        jpTailleImage.add(jlTailleImg);
        jpTailleImage.add(tailleImage);
        tailleImage.setText("60x34");
        jpTailleImage.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTailleImage.setBackground(Color.decode("#3e4e81"));

        JPanel jpPoidImg = new JPanel();
        poidImage.setPreferredSize(new Dimension(75, 25));
        jlPoidsImg.setForeground(Color.decode("#ffffff"));
        jpPoidImg.add(jlPoidsImg);
        jpPoidImg.add(poidImage);
        poidImage.setText("6" + "Ko");
        jpPoidImg.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpPoidImg.setBackground(Color.decode("#3e4e81"));

        JPanel jpLienHyptxt = new JPanel();
        lienHypertxt.setPreferredSize(new Dimension(75, 25));
        jlLienHypTxt.setForeground(Color.decode("#ffffff"));
        jpLienHyptxt.add(jlLienHypTxt);
        jpLienHyptxt.add(lienHypertxt);
        lienHypertxt.setText("6" + "Ko");
        jpLienHyptxt.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpLienHyptxt.setBackground(Color.decode("#3e4e81"));

        JPanel caseHG = new JPanel();
        caseHG.setLayout(new BoxLayout(caseHG,BoxLayout.Y_AXIS));
        caseHG.setPreferredSize(new Dimension(830, 576));
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
        caseHG.add(jpLienHyptxt);
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
        caseBD.add(new JLabel("OUI OUIU OU IU"));

        JPanel droit = new JPanel();
        droit.setPreferredSize(new Dimension(450, 700));
        droit.setLayout(new BoxLayout(droit, BoxLayout.Y_AXIS));
        droit.add(caseHD);
        droit.add(caseBD);

        jMenu.add(ouvrir);
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
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ActionWindowClosing());
    }


    private String texteTitre;

    class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            titre.setText("");
            auteur.setText("");
            sujet.setText("");
            keyword.setText("");
            date.setText("");
            statistic.setText("");
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
            jbAnnuler.setVisible(false);
            jbModifier.setVisible(true);
            jbAppliquer.setVisible(false);
            int reponse = JOptionPane.showConfirmDialog(jbAnnuler, "Voulez-vous annuler les changements effectués ?", "Annuler les changements ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            if (reponse == JOptionPane.YES_OPTION) {
                titre.setEditable(false);
                sujet.setEditable(false);
                JOptionPane.showMessageDialog(jbAnnuler, "L'annulation des changements a bien été faite.", "Annulation des changements", JOptionPane.OK_OPTION, new ImageIcon("/Users/axel/Documents/ImagePourLeProjetJava/crayon.png"));
            } else if (reponse == JOptionPane.NO_OPTION){
                titre.setEditable(false);
                sujet.setEditable(false);
                titre.setText(pourTitre);
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
