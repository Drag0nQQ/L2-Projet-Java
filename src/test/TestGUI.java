package test;

import javax.swing.UIManager;

import GUIMeta.GUIMeta;

public class TestGUI {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // TODO: handle exception
        }
        new GUIMeta();
    }
}
