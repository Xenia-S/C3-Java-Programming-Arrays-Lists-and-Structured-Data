import edu.duke.*;
import java.io.*;
public class testCaesarCipherTwo {
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
    
    public String breakCaesarCipherTwo(String input) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0; i<input.length(); i+=2) {
            //System.out.println(i);
            sb1.append(input.charAt(i));
        }
        for(int i=1; i<input.length(); i+=2) {
            sb2.append(input.charAt(i));
            //System.out.println(i);
        }
        int[] countLetters1 = countLetters(sb1.toString());
        int key1 = findKey(countLetters1);
        int[] countLetters2 = countLetters(sb2.toString());
        int key2 = findKey(countLetters2);
        System.out.println(key1+","+key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource("1-test file.txt");
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(2,5);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(breakCaesarCipherTwo(encrypted));
    } 

    public void simpleTests2() {
        /*String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(breakCaesarCipherTwo(encrypted));*/
        
        /*String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        String decrypted = cc.decrypt(message);
        System.out.println(decrypted);*/
        
        /*String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(breakCaesarCipherTwo(message));*/
        
        
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String message = fr.asString();
        System.out.println(breakCaesarCipherTwo(message));
    } 
}
