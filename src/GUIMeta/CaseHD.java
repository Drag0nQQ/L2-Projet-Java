package GUIMeta;

import java.awt.Dimension;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Cette classe permet de créer et gérer la case haut droit de notre GUI
 */
public class CaseHD extends JPanel {
    
    private JLabel imageLabel;


    public CaseHD() {
        super();
        //TODO Soucis avec le jar file
        imageLabel= new JLabel(getImgFromResource(GUIMeta.toNoImgString));
        imageLabel.setPreferredSize(new Dimension(400, 350));
        imageLabel.add(Box.createRigidArea(new Dimension(100, 0)));
        add(imageLabel);
        setPreferredSize(new Dimension(450, 410));
        setBackground(Color.decode(GUIMeta.mainColor));
    }
    /**
     * Permet de modifier l'image (miniature) du JLabel de notre classe
     * @param imgIcon
     */
    public void replaceImg(ImageIcon imgIcon){
        imageLabel.setIcon(imgIcon);
        imageLabel.validate();
        imageLabel.repaint();
    }
    
    /** 
     * retourne l'image donnée par le path.
     * @param path chemin vers l'image.
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
