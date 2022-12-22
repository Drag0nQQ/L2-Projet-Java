package test;

import GUIMeta.GUIMeta;
import javax.swing.*;
import java.awt.*;

public class TestSplash {
    
    public TestSplash() throws InterruptedException {
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
        Thread.sleep(1500);
        new GUIMeta();
        window.setVisible(false);
        window.dispose();
    }
    public static void main(String[] args) throws InterruptedException {
        new TestSplash();
    }
}
