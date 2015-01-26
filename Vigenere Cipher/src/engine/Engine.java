/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Clock;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timothy.pratama
 */
public class Engine {
    //Attributes
    private String ciphertext;
    private String plaintext;
    private String key; //maksimal 25 huruf
    private int mode; //1: standard, 2: extended, 3: autokey
    private int display; //1: apa adanya, 2: tanpa spasi, 3: dalam kelompok 5 huruf
    private char[][] vigenereSquare26;
    private char[][] vigenereSquare256;
    
    //Methods
    /* Constructors */
    public Engine() {
        ciphertext = "";
        plaintext = "";
        key = "";
        mode = 0;
        display = 0;
        createVigenereSquare();
    }

    public Engine(String ciphertext, String plaintext, String key, int mode, int display) {
        this.ciphertext = ciphertext;
        this.plaintext = plaintext;
        this.key = key;
        this.mode = mode;
        this.display = display;
    }
    
    /* Initialize Vigenere Square (26 and 256 characters) */
    private void createVigenereSquare26() {
        vigenereSquare26 = new char[26][26];
        int c;
        for (int b=0; b<26; b++) { //range 97 - 122
            for (int k=0; k<26; k++) {
                c = b + k + 97;
                if (c > 122) {
                    c -= 26;
                }
                vigenereSquare26[b][k] = (char) c;
            }
        }
    }
    
    private void createVigenereSquare256() {
        vigenereSquare256 = new char[256][256];
        int c;
        String output = "";
        for (int b=0; b<256; b++) { 
            for (int k=0; k<256; k++) {
                c = b + k;
                if (c > 255) {
                    c -= 256;
                }
                vigenereSquare256[b][k] = (char) c;
            }
        }
    }
    
    private void createVigenereSquare() {
        createVigenereSquare26();
        createVigenereSquare256();
    }

    /* Getter and Setter */
    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
        this.plaintext = this.plaintext.toLowerCase();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }
    
    public void readFile(String path) {
        plaintext = "";
        File file = new File(path);
        try {
            Scanner input = new Scanner(file);
            do {
                plaintext += input.nextLine() + "\n";
            } while (input.hasNextLine());
            plaintext = plaintext.toLowerCase();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Cryptography functions */
    public void encrypt() {
        createKey();
        ciphertext = "";
        int plaintextLength = plaintext.length();
        char c;
        char k;
        int j = 0;
        
        if(mode == 1) { //standard
            for(int i = 0; i<plaintextLength; i++) {
                c = plaintext.charAt(i);
                if(c == ' ' || c == '\n') {
                    ciphertext += c;
                } else {
                    k = key.charAt(j);
                    ciphertext += vigenereSquare26[((int) k) - 97][((int) c) - 97];
                    j++;
                }
            }
        } else if (mode == 2) { //extended
            
        } else { //autokey
            
        }
    }
    
    private void createKey() {
        int keyLength = key.length();
        int plaintextLength = plaintext.length();
        int j = 0;
        
        if(mode <= 2) {
            for(int i=key.length(); i<plaintext.length(); i++) {
                if(plaintext.charAt(i) != ' ') {
                    if(plaintext.charAt(i) != '\n') {
                        key += key.charAt(j % keyLength);
                        j++;
                    }
                    else {
                        
                    }
                } else {
                    
                }
            }
        }
        else {
            String temp = plaintext.replaceAll("\\s+","");
            plaintextLength = temp.length();
            for(int i=keyLength; i<plaintextLength; i++) {
                while(plaintext.charAt(j) == ' ' || plaintext.charAt(j) == '\n') {
                    j++;
                }
                key += plaintext.charAt(j);
                j++;
            }
        }
    }
    
    /* Main Class */
    public static void main(String[] args) {
        Engine e = new Engine();
        e.setMode(1);
        e.setPlaintext("abcdefghijklmnopqrstuvwxyz zyxwvutsrqponmlkjihgfedcba");
        e.setKey("sony");
        e.encrypt();
        System.out.println(e.getPlaintext());
        System.out.println(e.getCiphertext());
    }
}
