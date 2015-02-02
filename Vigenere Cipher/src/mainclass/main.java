/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import gui.VigenereCipher;
import javax.swing.JFrame;

/**
 *
 * @author timothy.pratama
 */
public class main {
    public static void main(String[] args) {
        VigenereCipher applet = new VigenereCipher();
        applet.init();
        applet.start();
        
        javax.swing.JFrame window = new JFrame("Vigenere's Cipher");
        window.setContentPane(applet);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
