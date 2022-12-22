package test;

import javax.swing.UIManager;

import GUIMeta.GUIMeta;
/**
 * Permet de lancer le GUI sans le splash screen.
 */
public class TestGUI {
    
    /** 
     * Main
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        new GUIMeta();
    }
}
