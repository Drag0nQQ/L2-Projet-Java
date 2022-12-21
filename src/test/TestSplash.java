package test;

import javax.imageio.ImageIO;

import GUIMeta.GUIMeta;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.awt.*;

public class TestSplash {
    
    public TestSplash() throws InterruptedException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        JWindow window = new JWindow();
        
        try {
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(GUIMeta.splashscreen)));
            System.out.println(TestSplash.class.getResource("TestSplash.class"));
            JLabel splash = new JLabel("", icon, SwingConstants.CENTER);
            System.out.println("splash chargé");
            try {
                window.getContentPane().add(splash);
                System.err.println("splash ajouté");
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
        Thread.sleep(1500);
        new GUIMeta();
        window.setVisible(false);
        window.dispose();
    }
    private ImageIcon getImgFromResource(String path){
        System.err.println(path);
        URL odtLien = TestSplash.class.getResource(path);
        try {
            assert odtLien != null;
            BufferedImage icon = ImageIO.read(odtLien);
            return new ImageIcon(icon);
        } catch (Exception e){
            System.err.println("PUTAIN ON LA PAS TROUVE");
        }
        return null;
    }
    public static void main(String[] args) throws InterruptedException {
        new TestSplash();
    }
}
