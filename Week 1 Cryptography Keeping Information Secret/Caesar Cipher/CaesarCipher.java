import edu.duke.*;
import java.io.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i =0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                encrypted.setCharAt(i, shiftedAlphabet.charAt(index));
            }
        }
        return encrypted.toString();
    }
    
    public String encryptAll(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String shiftedUpper = upper.substring(key) + upper.substring(0, key);
        String shiftedLower = lower.substring(key) + lower.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int indexU = upper.indexOf(currChar);
            int indexL = lower.indexOf(currChar);
            if (indexU != -1) {
                encrypted.setCharAt(i, shiftedUpper.charAt(indexU));
            }
            if (indexL != -1) {
                encrypted.setCharAt(i, shiftedLower.charAt(indexL));
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String shiftedUpper1 = upper.substring(key1) + upper.substring(0, key1);
        String shiftedUpper2 = upper.substring(key2) + upper.substring(0, key2);
        String shiftedLower1 = lower.substring(key1) + lower.substring(0, key1);
        String shiftedLower2 = lower.substring(key2) + lower.substring(0, key2);
        for (int i = 0; i < input.length(); i++) {
            char currChar = encrypted.charAt(i);
            int indexU = upper.indexOf(currChar);
            int indexL = lower.indexOf(currChar);
            if (indexU != -1 && (i+1)%2 != 0) {
                encrypted.setCharAt(i, shiftedUpper1.charAt(indexU));
            }
            if (indexL != -1 && (i+1)%2 != 0) {
                encrypted.setCharAt(i, shiftedLower1.charAt(indexL));
            }
            if (indexU != -1 && (i+1)%2 == 0) {
                encrypted.setCharAt(i, shiftedUpper2.charAt(indexU));
            }
            if (indexL != -1 && (i+1)%2 == 0) {
                encrypted.setCharAt(i, shiftedLower2.charAt(indexL));
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypt() {
        
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23)); // CFOPQ IBDFLK XQQXZH BXPQ CIXKH!
                
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encryptedMessage = encryptAll(message, 1);
        System.out.println(encryptedMessage);
        
        System.out.println(encryptAll("First Legion", 23)); // Cfopq Ibdflk
        System.out.println(encryptAll("First Legion", 17)); // Wzijk Cvxzfe
        
        System.out.println(encryptTwoKeys("First Legion", 23, 17)); // Czojq Ivdzle
       
        System.out.println(encryptAll("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}
