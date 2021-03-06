package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        String temp = "";
        if(display == 1) {
            temp = ciphertext;
        } else if(display == 2) {
            temp = ciphertext.replaceAll("\\s+","");
        } else if(display == 3) {
            temp = ciphertext.replaceAll("\\s+","");
            int interval = 5;
            int idx = 0;
            String result = "";
            while(idx + interval < temp.length()) {
                result += temp.substring(idx, idx + interval) + " ";
                idx += interval;
            }
            result += temp.substring(idx);
            temp = result;
        }
        return temp;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext.toLowerCase();
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
    
    /* save functions */
    public void saveFile(String path) {
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.print(ciphertext);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
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
        
        if(mode == 1 || mode == 3) { //standard
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
        } else if (mode == 2 || mode == 4) { //extended
            for(int i = 0; i<plaintextLength; i++) {
                c = plaintext.charAt(i);
                if(c == ' ' || c == '\n') {
                    ciphertext += c;
                } else {
                    k = key.charAt(j);
                    ciphertext += vigenereSquare256[((int) k)][((int) c)];
                    j++;
                }
            }
        } 
    }
    
    public void decrypt() {
        if(mode <= 2) {
            createKey();
        } else {
            decodeKey();
        }
        plaintext = "";
        int ciphertextLength = ciphertext.length();
        char c;
        char k;
        int j = 0;
        
        if(mode == 1 || mode == 3) { //standard
            for(int i = 0; i<ciphertextLength; i++) {
                c = ciphertext.charAt(i);
                if(c == ' ' || c == '\n') {
                    plaintext += c;
                } else {
                    k = key.charAt(j);
                    for(int l=0; l<26; l++) {
                        if(c == vigenereSquare26[((int)k)-97][l]) {
                            plaintext += (char) (l+97);
                            break;
                        }
                    }
                    j++;
                }
            }
        } else if (mode == 2 || mode == 4) { //extended
            for(int i = 0; i<ciphertextLength; i++) {
                c = ciphertext.charAt(i);
                if(c == ' ' || c == '\n') {
                    plaintext += c;
                } else {
                    k = key.charAt(j);
                    for(int l=0; l<256; l++) {
                        if(c == vigenereSquare256[(int)k][l]) {
                            plaintext += (char) (l);
                            break;
                        }
                    }
                    j++;
                }
            }
        } 
    }
    
    private void createKey() {
        int keyLength = key.length();
        int plaintextLength = plaintext.replaceAll("\\s+","").length();
        int ciphertextLength = ciphertext.replaceAll("\\s+","").length();
        int length;
        if(plaintextLength > ciphertextLength) {
            length = plaintextLength;
        } else {
            length = ciphertextLength;
        }
        int j = 0;
        
        if(mode <= 2) {
            for(int i=key.length(); i<length; i++) {
                key += key.charAt(j % keyLength);
                j++;               
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
    
    public void decodeKey() {
        String tempCiphertext = ciphertext.replaceAll("\\s+","");
        int ciphertextLength = tempCiphertext.length();
        int initialKeyLength = key.length();
        String currentKey = key;
        String decrypt = "";
        int counter = 0;
        
        if(mode == 3) {//autokey - standard
            while(currentKey.length() < ciphertextLength) {
                decrypt = "";
                for(int i=0; i<currentKey.length(); i++) {
                    char k = currentKey.charAt(i);
                    char c = tempCiphertext.charAt(i);
                    System.out.println("c= " + c);
                    for(int j=0; j<26; j++) {
                        if(c == vigenereSquare26[charToInteger(k)][j]) {
                            decrypt += (char) (j+97);
                            System.out.println("decrypt: " + decrypt);
                            break;
                        }
                    }
                }
                currentKey += decrypt.substring(counter);
                counter+= initialKeyLength;
                System.out.println("current key: " + currentKey);
                if(currentKey.length() > ciphertextLength) {
                    currentKey = currentKey.substring(0, ciphertextLength);
                }
            }
            key = currentKey;
        } else { //autokey - extended
            while(currentKey.length() < ciphertextLength) {
                decrypt = "";
                for(int i=0; i<currentKey.length(); i++) {
                    char k = currentKey.charAt(i);
                    char c = tempCiphertext.charAt(i);
                    System.out.println("c= " + c);
                    for(int j=0; j<256; j++) {
                        if(c == vigenereSquare256[(int)(k)][j]) {
                            decrypt += (char) (j);
                            System.out.println("decrypt: " + decrypt);
                            break;
                        }
                    }
                }
                currentKey += decrypt.substring(counter);
                counter+= initialKeyLength;
                System.out.println("current key: " + currentKey);
                if(currentKey.length() > ciphertextLength) {
                    currentKey = currentKey.substring(0, ciphertextLength);
                }
            }
            key = currentKey;
        }
    }
    
    private int charToInteger(char c) {
        int temp = ((int)c) - 97;
        return temp;
    }
    
    public void EncryptFile(String path_input, String path_output, String key) {
        File input = new File(path_input);
        FileInputStream in = null;
        try {
            in = new FileInputStream(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] isi = new byte[(int)input.length()];
        byte[] output = new byte[(int)input.length()];
        try {
            int read = in.read(isi);
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        int panjang_kunci = key.length();
        int j=0;
        for(int i=panjang_kunci; i<input.length(); i++) {
            key += key.charAt(j);
            j++;
        }
        j=0;
        for(byte b : isi) {
            output[j] = (byte) ((b+key.charAt(j))%256);
            j++;
        }
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path_output));
            out.write(output);
            out.close();
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("encrypt finished");
    }
    
    public void decryptFile(String path_input, String path_output, String key) {
        File input = new File(path_input);
        FileInputStream in = null;
        try {
            in = new FileInputStream(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] isi = new byte[(int)input.length()];
        byte[] output = new byte[(int)input.length()];
        try {
            int read = in.read(isi);
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        int panjang_kunci = key.length();
        int j=0;
        for(int i=panjang_kunci; i<input.length(); i++) {
            key += key.charAt(j);
            j++;
        }
        j=0;
        for(byte b : isi) {
            output[j] = (byte) ((b+256-key.charAt(j))%256);
            j++;
        }
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path_output));
            out.write(output);
            out.close();
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("decrypt finished");
    }
    
}
