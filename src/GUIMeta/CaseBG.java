package GUIMeta;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;

/**
 * Cette classe permet de créer et gérer la case bas gauche de notre GUI
 */
public class CaseBG extends JPanel {
    private JPanel panelAuDessusBouton, panelEnDessousBouton,panelBouton;
    private ImageIcon imgAnnuler,imgModifier, imgAppliquer, imgClear;
    private JButton jbAnnuler,jbModifier, jbAppliquer, jbClear;
    private JLabel Boutons;
    
    public CaseBG() {
        super();
        InitIcon();
        InitAnnuler();
        InitModifier();
        InitClear();
        InitAppliquer();
        InitTooltips();
        InitInvisible();
        //Panel au dessus
        panelAuDessusBouton=new JPanel();
        Boutons = new JLabel("<HTML><U>Boutons pour la modification et pour effacer toutes les données de l'écran</U></HTML>");
        Boutons.setForeground(Color.decode("#1f2740"));
        Boutons.setFont(new Font("Arial", Font.PLAIN, 15));
        panelAuDessusBouton.add(Boutons);
        panelAuDessusBouton.setPreferredSize(new Dimension(277, 40));
        panelAuDessusBouton.setBackground(Color.decode("#c5c9d9"));
        panelAuDessusBouton.setBorder(BorderFactory.createLineBorder(Color.decode("#ffffff")));
        
        //Panel des buttons
        panelBouton=new JPanel();
        panelBouton.add(jbAnnuler);
        panelBouton.add(jbModifier);
        panelBouton.add(jbAppliquer);
        jbAnnuler.setVisible(false);
        jbAppliquer.setVisible(false);
        panelBouton.setPreferredSize(new Dimension(277, 40));
        panelBouton.setBackground(Color.decode(GUIMeta.mainColor));
        //Panel en dessous
        panelEnDessousBouton = new JPanel();
        panelEnDessousBouton.add(jbClear);
        panelEnDessousBouton.setPreferredSize(new Dimension(277, 40));
        panelEnDessousBouton.setBackground(Color.decode(GUIMeta.mainColor));
        
        add(panelAuDessusBouton);
        add(panelBouton);
        add(panelEnDessousBouton);
        setPreferredSize(new Dimension(830, 120));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(GUIMeta.mainColor));
    }
    
    //Setup
    private void InitInvisible(){
        jbAnnuler.setVisible(false);
        jbAppliquer.setVisible(false);
        jbClear.setVisible(false);
        jbModifier.setVisible(false);
    }
    /**
     * Initialise les tooltips des boutons
     */
    private void InitTooltips() {
        jbAnnuler.setToolTipText("Annule la modification du titre et/ou sujet.");
        jbModifier.setToolTipText("Permet de modifier le titre et/ou le sujet");
        jbAppliquer.setToolTipText("Applique le(s) changement(s) effectué(s)");
    }
    /**
     * Initialise les icones de notre boutons
     */
    private void InitIcon() {
        //TODO On aura un soucis avec le jar file.
        imgAnnuler = getImgFromResource(GUIMeta.toAnnulerString);
        imgModifier = getImgFromResource(GUIMeta.toModifierString);
        imgAppliquer = getImgFromResource(GUIMeta.toAppliquerString);
        imgClear = getImgFromResource(GUIMeta.toClearString);
    }
    
    private void InitAnnuler(){
        jbAnnuler = new JButton("Annuler", imgAnnuler);
        jbAnnuler.setVerticalTextPosition(AbstractButton.CENTER);
        jbAnnuler.setHorizontalTextPosition(AbstractButton.LEADING);
    }
    
    private void InitModifier() {
        jbModifier = new JButton("Modifier", imgModifier);
        jbModifier.setVerticalTextPosition(AbstractButton.CENTER);
        jbModifier.setHorizontalTextPosition(AbstractButton.LEADING);
    }
    
    private void InitAppliquer() {
        jbAppliquer = new JButton("Appliquer", imgAppliquer);
    }
    
    private void InitClear() {
        jbClear = new JButton("Clear", imgClear);
        jbClear.setVerticalTextPosition(AbstractButton.CENTER);
        jbClear.setHorizontalTextPosition(AbstractButton.LEADING);
    }
    
    /** 
     * Ajoute un actionlistener au bouton annuler.
     * @param action
     */
    //ADD ACTION LISTENER
    public void AddActListenerAnnuler(ActionListener action) {
        jbAnnuler.addActionListener(action);
    }
    
    /** 
     * Ajoute un actionlistener au bouton Clear.
     * @param action
     */
    public void AddActListenerClear(ActionListener action) {
        jbClear.addActionListener(action);
    }
    
    /**
     * Ajoute un actionlistener au bouton appliquer. 
     * @param action
     */
    public void AddActListenerAppliquer(ActionListener action) {
        jbAppliquer.addActionListener(action);
    }
    
    /**
     * Ajoute un actionlistener au bouton modifier. 
     * @param action
     */
    public void AddActListenerModifier(ActionListener action) {
        jbModifier.addActionListener(action);
    }
    
    
    //Getters Setters and Misc
    /**
     * Retourne le JButton Annuler
     * @return JButton
     */
    public JButton getJbAnnuler() {
        return jbAnnuler;
    }
    /**
     * Retourne le JButton Appliquer
     * @return JButton
     */
    public JButton getJbAppliquer() {
        return jbAppliquer;
    }
    /**
     * Retourne le JButton Clear
     * @return JButton
     */
    public JButton getJbClear() {
        return jbClear;
    }
    /**
     * Retourne le JButton Modifier
     * @return JButton
     */
    public JButton getJbModifier() {
        return jbModifier;
    }

    /**
     * Rend le button visible (true) ou invisible (false)
     * @param b
     */
    public void jbAnnulerVisible(boolean b){
        jbAnnuler.setVisible(b);
    }
    /**
     * Rend le button visible (true) ou invisible (false)
     * @param b
     */
    public void jbModifierVisible(boolean b){
        jbModifier.setVisible(b);
    }
    /**
     * Rend le button visible (true) ou invisible (false)
     * @param b
     */
    public void jbAppliquerVisible(boolean b){
        jbAppliquer.setVisible(b);
    }
    /**
     * Rend le button visible (true) ou invisible (false)
     * @param b
     */
    public void jbClearVisible(boolean b){ jbClear.setVisible(b);}
    
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
