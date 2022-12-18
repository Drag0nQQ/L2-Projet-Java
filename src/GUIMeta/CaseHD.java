package GUIMeta;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

/**
 * Cette classe permet de créer et gérer la case haut droit de notre GUI
 */
public class CaseHD extends JPanel {
    
    private JLabel imageLabel;


    public CaseHD() {
        super();
        //TODO
        imageLabel= new JLabel(new ImageIcon(GUIMeta.toNoImgString));
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
}
