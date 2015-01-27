/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author timothy.pratama
 */
public class EngineTest {
    
    public EngineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Text encrypt method using standard vigenere cipher
     */
    @Test
    public void testStandard() {
        System.out.println("Test Standard Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("sony");
        instance.setPlaintext("this plaintext");
        instance.setMode(1);
        instance.setDisplay(1);
        instance.encrypt();
        
        String result = instance.getCiphertext();
        String expectedResult = "lvvq hzngfhrvl";
        assertEquals(expectedResult,result);
    }

    /**
     * Text encrypt method using extended vigenere cipher
     */
    @Test
    public void testExtended() {
        System.out.println("Test Extended Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("sony");
        instance.setPlaintext("this plaintext");
        instance.setMode(2);
        instance.setDisplay(1);
        instance.encrypt();
        
        String result = instance.getCiphertext();
        String expectedResult = "ç××ì ãÛÏâáãÓñç";
        assertEquals(expectedResult,result);
    }
    
    /**
     * Text encrypt method using extended vigenere cipher
     */
    @Test
    public void testVariant() {
        System.out.println("Test Variant Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("indo");
        instance.setPlaintext("negara penghasil minyak");
        instance.setMode(3);
        instance.setDisplay(1);
        instance.encrypt();
        
        String result = instance.getCiphertext();
        String expectedResult = "vrjoee veegwefos mavjms";
        assertEquals(expectedResult,result);
    }
    
    /**
     * Text decrypt method using extended vigenere cipher
     */
    @Test
    public void testDecryptStandard() {
        System.out.println("Test Decrypt Standard Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("sonysonysonys");
        instance.setCiphertext("lvvq hzngfhrvl");
        instance.setMode(1);
        instance.setDisplay(1);
        instance.decrypt();
        
        String result = instance.getPlaintext();
        String expectedResult = "this plaintext";
        assertEquals(expectedResult,result);
    }
}
