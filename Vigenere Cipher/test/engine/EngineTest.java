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
    
    /**
     * Text decrypt method using extended vigenere cipher
     */
    @Test
    public void testDecryptExtended() {
        System.out.println("Test Decrypt Extended Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("sonysonysonys");
        instance.setCiphertext("ç××ì ãÛÏâáãÓñç");
        instance.setMode(2);
        instance.setDisplay(1);
        instance.decrypt();
        
        String result = instance.getPlaintext();
        String expectedResult = "this plaintext";
        assertEquals(expectedResult,result);
    }
    
    /**
     * Text decrypt method using variant autokey vigenere cipher
     */
    @Test
    public void testDecryptVariantAutoKey() {
        System.out.println("Test Decrypt Variant Autokey Vigenere Cipher");
        Engine instance = new Engine();
        
        instance.setKey("indonegarapenghasilmi");
        instance.setCiphertext("vrjoee veegwefos mavjms");
        instance.setMode(3);
        instance.setDisplay(1);
        instance.decrypt();
        
        String result = instance.getPlaintext();
        String expectedResult = "negara penghasil minyak";
        assertEquals(expectedResult,result);
    }
    
    /**
     * Test ciphertext output using the same format as plaintext
    */
    @Test
    public void testOutput1() {
        System.out.println("Test Output 1");
        Engine e = new Engine();
        
        e.setCiphertext("ciphertext formatted output test");
        e.setDisplay(1);
        String result = e.getCiphertext();
        assertEquals("ciphertext formatted output test", result);
    }
    
    /**
     * Test ciphertext output without whitespace
    */
    @Test
    public void testOutput2() {
        System.out.println("Test Output 2");
        Engine e = new Engine();
        
        e.setCiphertext("ciphertext formatted output test");
        e.setDisplay(2);
        String result = e.getCiphertext();
        assertEquals("ciphertextformattedoutputtest", result);
    }
    
    /**
     * Test ciphertext output in group of 5 characters
    */
    @Test
    public void testOutput3() {
        System.out.println("Test Output 3");
        Engine e = new Engine();
        
        e.setCiphertext("ciphertext formatted output test");
        e.setDisplay(3);
        String result = e.getCiphertext();
        assertEquals("ciphe rtext forma ttedo utput test", result);
    }
    
    /**
     * Test encrypt long plaintext using standard vigenere cipher
     */
    @Test
    public void testLongPlaintext1() {
        Engine e = new Engine();
        
        e.setPlaintext("The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog");
        e.setMode(1);
        e.setDisplay(2);
        e.setKey("abcdefghijklmnopqrstuvwxyz");
        e.encrypt();
        
        String result = e.getCiphertext();
        System.out.println(result);
        String expected = "tigtynirjayhzscmzleimjrbpshfndddjvocrpchwrasjhqiblviunrvsakybqowmmmsexlaylqfajbsqzrkuerdwaebjthkzxfvvvbngujhuzojskbziatdnamfjnkscqtigoeeekwp";
        assertEquals(expected, result);
    }
    
    @Test
    public void testLongPlaintext2() {
        Engine e = new Engine();
        
        e.setPlaintext("The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog");
        e.setMode(2);
        e.setDisplay(2);
        e.setKey("abcdefghijklmnopqrstuvwxyz");
        e.encrypt();
        
        String result = e.getCiphertext();
        String expected = "ÕÊÈÕÚÏÊÓËÜÚãÛÔÞèÛçàäèåíÝëîÉÇÏÅßßË×ÐÞÓÑÞãØÓÜÔåãìäÝçñäÖÏÓ×ÔÜÌÚÝÒÐØÎèèÔàÙçÜÚçìáÜåÃÔÒÛÓÌÖàÓßØÜàÝåÕãæÛÙá×ññÝéÈÖËÉÖÛÐËÔÌÝÛäÜÕßéÜèáåéæîÞìÕÊÈÐÆààÌØÑ";
        assertEquals(expected, result);
    }
    
    @Test
    public void testLongPlaintext3() {
        Engine e = new Engine();
        
        e.setPlaintext("The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog");
        e.setMode(3);
        e.setDisplay(2);
        e.setKey("abcdefghijklmnopqrstuvwxyz");
        e.encrypt();
        
        String result = e.getCiphertext();
        String expected = "tigtynirjayhzscmzleimjrbpsalpqtgfyhkvadzwztvddobasocbqasnthfzalpqtgfyhkvadzwztvddobasocbqasnthfzalpqtgfyhkvadzwztvddobasocbqasnthfzalpqtgfyh";
        assertEquals(expected, result);
    }
}
