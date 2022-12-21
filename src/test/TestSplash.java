package test;

import javax.imageio.ImageIO;
import javax.swing.JWindow;
import GUIMeta.GUIMeta;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class TestSplash {

    public TestSplash() throws InterruptedException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        JWindow window = new JWindow();

        window.getContentPane().add(new JLabel("", getImgFromResource(GUIMeta.splashscreen), SwingConstants.CENTER));
        window.setBounds(520,300, 480, 270);
        window.setVisible(true);
        Thread.sleep(1500);
        new GUIMeta();
        window.setVisible(false);
        window.dispose();
    }
    private ImageIcon getImgFromResource(String path){
        URL odtLien = this.getClass().getResource(path);
        try {
            assert odtLien != null;
            BufferedImage icon = ImageIO.read(odtLien);
            return new ImageIcon(icon);
        } catch (Exception e){}
        return null;
    }
    public static void main(String[] args) throws InterruptedException {
        new TestSplash();
    }
}
