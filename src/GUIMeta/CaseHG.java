package GUIMeta;

import javax.swing.JPanel;

import extraction.ExtractContent;
import extraction.ExtractMeta;
import extraction.ExtractPicture;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
public class CaseHG extends JPanel {
    private JTextField titre,auteur,sujet,keyword,date,nbCaracteres,nbMots,nbPages;
    private JTextArea images,lienHypertxt;
    private JLabel jlTitre,jlAuteur,jlSujet,jlKeyword,jlDate,jlNbMots,jlNbCaracteres,jlNbPages,jlImages,jlLienHypTxt;
    private JPanel jpTitre,jpAuteur,jpSujet,jpKeyword,jpDate,jpMots,jpCaracteres,jpPages,jpImages,jpLienHypTxt;
    
    public CaseHG() {
        super();
        formatTextField();
        formatLabel();
        
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 800));
        InitPanelTitre();
        add(jpTitre);
        InitPanelAuteur();
        add(jpAuteur);
        InitPanelSujet();
        add(jpSujet);
        InitPanelKeyword();
        add(jpKeyword);
        InitPanelDate();
        add(jpDate);
        InitPanelCaracteres();
        add(jpCaracteres);
        InitPanelMots();
        add(jpMots);
        InitPanelPages();
        add(jpPages);
        InitPanelImages();
        add(jpImages);
        InitPanelLienHypTxt();
        add(jpLienHypTxt);
        setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    //Methods
    public void loadMeta(Path mainDirectory) {
        setTitreField(ExtractMeta.getTitle(mainDirectory));
        setAuteurField(ExtractMeta.getAuthor(mainDirectory));
        setSujetField(ExtractMeta.getSubject(mainDirectory));
        ArrayList<String> al=ExtractMeta.getKeywords(mainDirectory);
        StringBuilder kw= new StringBuilder();
        if (al!=null){
            for (String tmp : al) {
                kw.append(tmp).append(", ");
            }
        }
        
        setKeywordField(kw.toString());
        setDateField(ExtractMeta.getCreation_date(mainDirectory));
        setnbMotsField(ExtractMeta.getnbMots(mainDirectory));
        setnbPagesField(ExtractMeta.getnbPages(mainDirectory));
        setNbCaracteresField(ExtractMeta.getnbCaracteres(mainDirectory));
        kw = new StringBuilder();
        al=ExtractContent.getLink(mainDirectory);
        if (al!=null){
            for (String tmp: al){
                kw.append(tmp).append("\n");
            }
        }
        setLienField(kw.toString());
        al=ExtractPicture.getPictures(mainDirectory);
        kw = new StringBuilder();
        if (al != null){
            kw = new StringBuilder("Nombre d'images:" + al.size() + "\n");
            for (String string : al) {
                kw.append(string).append("\n");
            }
        }

        setImageField(kw.toString());
    }
    
    //Setup
    
    public void formatTextField(){
        //TAILLE
        titre = new JTextField(40);
        auteur = new JTextField(20);
        sujet = new JTextField(40);
        keyword = new JTextField(50);
        date = new JTextField(20);
        nbMots = new JTextField(5);
        nbCaracteres = new JTextField(5);
        nbPages = new JTextField(5);
        images = new JTextArea(6,60);
        lienHypertxt = new JTextArea(6,70);
        
        //EDITION
        titre.setEditable(false);
        auteur.setEditable(false);
        sujet.setEditable(false);
        keyword.setEditable(false);
        date.setEditable(false);
        nbMots.setEditable(false);
        nbCaracteres.setEditable(false);
        nbPages.setEditable(false);
        images.setEditable(false);
        lienHypertxt.setEditable(false);
        
        //COULEUR
        titre.setBackground(Color.decode(GUIMeta.mainColor));
        titre.setForeground(Color.decode(GUIMeta.fontColor));
        titre.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        auteur.setBackground(Color.decode(GUIMeta.mainColor));
        auteur.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        auteur.setForeground(Color.decode(GUIMeta.fontColor));
        sujet.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        sujet.setBackground(Color.decode(GUIMeta.mainColor));
        keyword.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        sujet.setForeground(Color.decode(GUIMeta.fontColor));
        date.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        keyword.setBackground(Color.decode(GUIMeta.mainColor));
        nbMots.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        keyword.setForeground(Color.decode(GUIMeta.fontColor));
        nbCaracteres.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        date.setBackground(Color.decode(GUIMeta.mainColor));
        nbPages.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        date.setForeground(Color.decode(GUIMeta.fontColor));
        images.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor)));
        nbMots.setBackground(Color.decode(GUIMeta.mainColor));
        lienHypertxt.setBorder(BorderFactory.createLineBorder(Color.decode(GUIMeta.mainColor))); 
        nbMots.setForeground(Color.decode(GUIMeta.fontColor));
        nbCaracteres.setBackground(Color.decode(GUIMeta.mainColor));
        nbCaracteres.setForeground(Color.decode(GUIMeta.fontColor));
        nbPages.setBackground(Color.decode(GUIMeta.mainColor));
        nbPages.setForeground(Color.decode(GUIMeta.fontColor));
        images.setBackground(Color.decode(GUIMeta.mainColor));
        images.setForeground(Color.decode(GUIMeta.fontColor));
        lienHypertxt.setBackground(Color.decode(GUIMeta.mainColor));
        lienHypertxt.setForeground(Color.decode(GUIMeta.fontColor));
    }
    
    public void formatLabel(){
        jlTitre = new JLabel("<HTML><U>Titre:</U></HTML>");
        jlAuteur = new JLabel("<HTML><U>Auteur:</U></HTML>");
        jlSujet = new JLabel("<HTML><U>Sujet:</U></HTML>");
        jlKeyword = new JLabel("<HTML><U>Mots clés:</U></HTML>");
        jlDate = new JLabel("<HTML><U>Date:</U></HTML>");
        jlNbMots = new JLabel("<HTML><U>Nombre de mots:</U></HTML>");
        jlNbCaracteres = new JLabel("<HTML><U>Nombre de caractères:</U></HTML>");
        jlNbPages = new JLabel("<HTML><U>Nombre de pages:</U></HTML>");
        jlImages = new JLabel("<HTML><U>Images:</U></HTML>");
        jlLienHypTxt = new JLabel("<HTML><U>Lien(s) hypertexte:</U></HTML>");
    }
    
    public void InitPanelTitre(){
        jpTitre=new JPanel();
        titre.setPreferredSize(new Dimension(75, 25));
        jlTitre.setForeground(Color.decode(GUIMeta.fontColor));
        jlTitre.setFont(new Font("Arial", Font.BOLD, 18));
        jpTitre.add(jlTitre);
        jpTitre.add(titre);
        //TODO
        titre.setText("");
        titre.setFont(new Font("Arial", Font.PLAIN, 18));
        jpTitre.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTitre.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelAuteur(){
        jpAuteur=new JPanel();
        auteur.setPreferredSize(new Dimension(75, 25));
        jlAuteur.setForeground(Color.decode(GUIMeta.fontColor));
        jlAuteur.setFont(new Font("Arial", Font.BOLD, 18));
        jpAuteur.add(jlAuteur);
        jpAuteur.add(auteur);
        //TODO 
        auteur.setText("");
        auteur.setFont(new Font("Arial", Font.PLAIN, 18));
        jpAuteur.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpAuteur.setBackground(Color.decode(GUIMeta.mainColor));
        
    }
    public void InitPanelSujet(){
        jpSujet=new JPanel();
        sujet.setPreferredSize(new Dimension(75, 25));
        jlSujet.setForeground(Color.decode(GUIMeta.fontColor));
        jlSujet.setFont(new Font("Arial", Font.BOLD, 18));
        jpSujet.add(jlSujet);
        jpSujet.add(sujet);
        //TODO
        sujet.setText("");
        sujet.setFont(new Font("Arial", Font.PLAIN, 18));
        jpSujet.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpSujet.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelKeyword(){
        jpKeyword=new JPanel();
        //TODO
        keyword.setPreferredSize(new Dimension(75, 25));
        jlKeyword.setForeground(Color.decode(GUIMeta.fontColor));
        jlKeyword.setFont(new Font("Arial", Font.BOLD, 18));
        jpKeyword.add(jlKeyword);
        jpKeyword.add(keyword);
        keyword.setText("");
        keyword.setFont(new Font("Arial", Font.PLAIN, 18));
        jpKeyword.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpKeyword.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelDate(){
        jpDate=new JPanel();
        date.setPreferredSize(new Dimension(75, 25));
        jlDate.setForeground(Color.decode(GUIMeta.fontColor));
        jlDate.setFont(new Font("Arial", Font.BOLD, 18));
        jpDate.add(jlDate);
        jpDate.add(date);
        //TODO
        date.setText("");
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        jpDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpDate.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelCaracteres(){
        jpCaracteres=new JPanel();
        nbCaracteres.setPreferredSize(new Dimension(75, 25));
        jlNbCaracteres.setForeground(Color.decode(GUIMeta.fontColor));
        jlNbCaracteres.setFont(new Font("Arial", Font.BOLD, 18));
        jpCaracteres.add(jlNbCaracteres);
        jpCaracteres.add(nbCaracteres);
        //TODO
        nbCaracteres.setText("");
        nbCaracteres.setFont(new Font("Arial", Font.PLAIN, 18));
        jpCaracteres.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCaracteres.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelMots(){
        jpMots=new JPanel();
        nbMots.setPreferredSize(new Dimension(75, 25));
        jlNbMots.setForeground(Color.decode(GUIMeta.fontColor));
        jlNbMots.setFont(new Font("Arial", Font.BOLD, 18));
        jpMots.add(jlNbMots);
        jpMots.add(nbMots);
        //TODO
        nbMots.setText("");
        nbMots.setFont(new Font("Arial", Font.PLAIN, 18));
        jpMots.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpMots.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelPages(){
        jpPages=new JPanel();
        nbPages.setPreferredSize(new Dimension(75, 25));
        jlNbPages.setForeground(Color.decode(GUIMeta.fontColor));
        jlNbPages.setFont(new Font("Arial", Font.BOLD, 18));
        jpPages.add(jlNbPages);
        jpPages.add(nbPages);
        //TODO
        nbPages.setText("");
        nbPages.setFont(new Font("Arial", Font.PLAIN, 18));
        jpPages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpPages.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelImages(){
        jpImages=new JPanel();
        images.setPreferredSize(new Dimension(400, 60));
        jlImages.setForeground(Color.decode(GUIMeta.fontColor));
        jlImages.setFont(new Font("Arial", Font.BOLD, 18));
        jpImages.add(jlImages);
        jpImages.add(images);
        images.setLineWrap(true);
        //TODO et en dessous aussi
        images.setText("");
        images.setFont(new Font("Arial", Font.PLAIN, 12));
        jpImages.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpImages.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    public void InitPanelLienHypTxt() {
        jpLienHypTxt=new JPanel();
        jlLienHypTxt.setForeground(Color.decode(GUIMeta.fontColor));
        jlLienHypTxt.setFont(new Font("Arial", Font.BOLD, 18));
        jpLienHypTxt.add(jlLienHypTxt);
        jpLienHypTxt.add(lienHypertxt);
        lienHypertxt.setLineWrap(true);
        //TODO
        lienHypertxt.append("");
        lienHypertxt.setFont(new Font("Arial", Font.PLAIN, 12));
        jpLienHypTxt.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpLienHypTxt.setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    //Clear
    public void TextFieldclear(){
        titre.setText("");
        auteur.setText("");
        sujet.setText("");
        keyword.setText("");
        date.setText("");
        nbMots.setText("");
        nbCaracteres.setText("");
        nbPages.setText("");
        images.setText("");
        lienHypertxt.setText("");
    }
    
    //Getter Setter and Misc
    public String getTitreField(){
        return titre.getText();    
    }
    public String getSujetField(){
        return sujet.getText();    
    }
    public String getAuteurField(){
        return auteur.getText();    
    }
    public String getDateField(){
        return date.getText();    
    }
    public String getKeywordField(){
        return keyword.getText();    
    }
    public String getNbCaracteresField(){
        return nbCaracteres.getText();    
    }
    public String getnbMotsField(){
        return nbMots.getText();    
    }
    public String getnbPagesField(){
        return nbPages.getText();    
    }
    public String getLienField(){
        return lienHypertxt.getText();
    }
    public String getImageField(){
        return images.getText();
    }
    
    public void setTitreField(String txt){
        titre.setText(txt);    
    }
    public void setSujetField(String txt){
        sujet.setText(txt);    
    }
    public void setAuteurField(String txt){
        auteur.setText(txt);    
    }
    public void setDateField(String txt){
        date.setText(txt);    
    }
    public void setKeywordField(String txt){
        keyword.setText(txt);    
    }
    public void setNbCaracteresField(String txt){
        nbCaracteres.setText(txt);    
    }
    public void setnbMotsField(String txt){
        nbMots.setText(txt);    
    }
    public void setnbPagesField(String txt){
        nbPages.setText(txt);    
    }
    public void setLienField(String txt){
        lienHypertxt.setText(txt);
    }
    public void setImageField(String txt){
        images.setText(txt);
    }

    public void titreEditable(boolean b) {
        titre.setEditable(b);
    }
    public void sujetEditable(boolean b) {
        sujet.setEditable(b);
    }
}
