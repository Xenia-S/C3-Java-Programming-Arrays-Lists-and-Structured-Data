import edu.duke.*;
import java.io.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for(int i=0; i<sb.length(); i++) {
            char c = sb.charAt(i);
            char upperC = Character.toUpperCase(c);
            int index = alphabet.indexOf(upperC);
            if (index != -1) {
                char newC = shiftedAlphabet.charAt(index);
                sb.setCharAt(i,newC);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(i,Character.toLowerCase(newC));
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
