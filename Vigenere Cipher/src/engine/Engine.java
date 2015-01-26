/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

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
        for (int b=0; b<26; b++) { //range 65 - 90
            for (int k=0; k<26; k++) {
                c = b + k + 65;
                if (c > 90) {
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
        for (int b=0; b<256; b++) { //range 65 - 90
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
    
    /* Cryptography functions */
    public static void main(String[] args) {
        Engine e = new Engine();
    }
}
