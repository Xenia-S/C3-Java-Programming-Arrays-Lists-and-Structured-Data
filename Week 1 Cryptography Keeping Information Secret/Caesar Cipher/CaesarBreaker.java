import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        int[] count = new int[26];
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
    
           
    public String decrypt(String message) {
        int[] count = countLetters(message);
        int key = findKey(count);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encrypt(message, 26-key);
        return decrypted;
    }
    
    public int fingKey2(String message) {
        int[] count = countLetters(message);
        int key = findKey(count);
        return key;
    }
    
    public String decryptTwoKeys(String message) {
        StringBuilder key1sb = new StringBuilder();
        StringBuilder key2sb = new StringBuilder();
        for (int i=0; i<message.length(); i++) {
            if ((i+1)%2 != 0) {
                key1sb.append(message.charAt(i));
            }
            if ((i+1)%2 == 0) {
                key2sb.append(message.charAt(i));
            }
        }
        int key1 = fingKey2(key1sb.toString());
        int key2 = fingKey2(key2sb.toString());
        System.out.println(key1 + " " + key2);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(message, 26-key1, 26-key2);
        return decrypted;
    }
    
    public void test_decryptTwoKeys() {
        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
    
    public void test_decryptTwoKeys2() {
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        String message = fr.asString();
        System.out.println(message);
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }

    
    public void test() {
        String a = "AaABbC ffffffffffff  d";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] test = countLetters(a);
        for(int i=0; i<test.length; i++){
            System.out.println(alphabet.charAt(i) + "\t" + test[i]);
        }
        
        int key = findKey(test);
        System.out.println(key);
        
        String message = "Urna queeeeeis, diceeeetumst. Haeeeeec consectetur cras leo, et. Mollis dui ultricies. Est.eeeeeee Eget dapibus in veeeeeenenatis leo, pleeeeeeeatea lorem ultricies. Justo sed cras tortor, ipsum habitasse eleifend et. Mattis tempus sapien dictumst. In vitae lorem eget sit non sodales sed quam, quis, mollis nec in urna ornare platea nisi ex.";
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, 5);
        String decrypted = decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] test = countLetters(encrypted);
        System.out.println(findKey(test));
        for(int i=0; i<test.length; i++){
            System.out.println(alphabet.charAt(i) + "\t" + test[i]);
        }
        
        
        
     }
}
