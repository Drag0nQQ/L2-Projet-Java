package test;

import javax.swing.JWindow;
import GUIMeta.GUIMeta;
import javax.swing.*;
public class TestSplash {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        JWindow window = new JWindow();
       
        window.getContentPane().add(new JLabel("", new ImageIcon(GUIMeta.splashscreen), SwingConstants.CENTER));
        window.setBounds(520,300, 480, 270);
        window.setVisible(true);
        new GUIMeta();
        window.setVisible(false);
        window.dispose();
    }
}
