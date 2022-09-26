import edu.duke.*;
import java.io.*;
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    
    public CaesarCipherTwo(int key1, int key2) {
        //          01234567890123456789012345
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for(int i=0; i<sb.length(); i++) {
            char c = sb.charAt(i);
            char upperC = Character.toUpperCase(c);
            int index = alphabet.indexOf(upperC);
            if (index != -1 && (i+1)%2 != 0 ) {
                char newC = shiftedAlphabet1.charAt(index);
                sb.setCharAt(i,newC);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(i,Character.toLowerCase(newC));
                }
            }
            if (index != -1 && (i+1)%2 == 0 ) {
                char newC = shiftedAlphabet2.charAt(index);
                sb.setCharAt(i,newC);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(i,Character.toLowerCase(newC));
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
