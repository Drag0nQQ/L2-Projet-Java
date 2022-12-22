package test;

import GUIMeta.GUIMeta;
import javax.swing.*;
import java.awt.*;
/**
 * Permet de lancer le GUI avec splash screen.
 */
public class GUI {
    
    public GUI()  {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        JWindow window = new JWindow();
        
        try {
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(GUIMeta.splashscreen)));
            JLabel splash = new JLabel("", icon, SwingConstants.CENTER);
            try {
                window.getContentPane().add(splash);
            } catch (IllegalArgumentException e) {
                System.err.println("splash refusé");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("splash null");
            e.printStackTrace();
        }
        window.setBounds(520,300, 480, 270);
        window.setVisible(true);
        try {
            Thread.sleep(300);
            new GUIMeta();
        } catch (InterruptedException e) {
            System.err.println("Soucis lors du chargement de l'appli.");
        }
        window.setVisible(false);
        window.dispose();
    }
    
    /** 
     * Main
     * @param args
     */
    public static void main(String[] args) {
        new GUI();
    }
}
