import edu.duke.*;
import java.io.*;
public class testCaesarCipher {
    public int[] countLetters(String message) {
        int[] count = new int[26];
        //                 01234567890123456789012345
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i<message.length(); i++) {
            char letter = message.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(letter));
            if(index != -1) {
                count[index] += 1;
            }
        }
        return count;
    }
    
    public int findKey(int[] count) {
        int max = 0;
        int key = 0;
        for(int i=0; i<count.length; i++) {
            if(count[i] > max) {
                max = count[i];
                key = i-4;
                if(key < 0) {
                    key = 26+i-4;
                }
            }
        }
        return key;
    }
    
    public String breakCaesarCipher(String input) {
        int[] countLetters = countLetters(input);
        int key = findKey(countLetters);
        CaesarCipher cc = new CaesarCipher(26-key);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource("1-test file.txt");
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encripted = cc.encrypt(message);
        /*String decripted = cc.decrypt(encripted);
        System.out.println(decripted);*/
        System.out.println(message);
        System.out.println(encripted);
        System.out.println(breakCaesarCipher(encripted));
    } 
    
    public void simpleTests2() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket? eeeeeeeeeee";
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(breakCaesarCipher(encrypted));
    } 
}
