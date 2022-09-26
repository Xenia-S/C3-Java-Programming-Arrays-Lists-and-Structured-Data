import edu.duke.*;
import java.util.*;

public class tester {
    public void testCaesarCipher() {
        CaesarCipher cc1 = new CaesarCipher(5);
        char letter = 'a';
        System.out.println("Original letter: "+ letter);
        letter = cc1.encryptLetter(letter);
        System.out.println("Encrypted letter: "+ letter);
        letter = cc1.decryptLetter(letter);
        System.out.println("Decrypted letter: "+ letter);
        System.out.println("\n");
        
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String message = fr.asString();
        System.out.println(message);
        String encrypted = cc1.encrypt(message);
        System.out.println(encrypted);
        String decrypted = cc1.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void testCaesarCracker() {
        CaesarCracker ccr1 = new CaesarCracker();
        FileResource fr = new FileResource("VigenereTestData/titus-small_key5.txt");
        String encrypted = fr.asString();
        System.out.println(encrypted);
        String cracked = ccr1.decrypt(encrypted);
        System.out.println(cracked);
        System.out.println("\n");
        
        CaesarCracker ccr2 = new CaesarCracker('a');
        fr = new FileResource("VigenereTestData/oslusiadas_key17.txt");
        encrypted = fr.asString();
        System.out.println(encrypted);
        cracked = ccr2.decrypt(encrypted);
        System.out.println(cracked);
    }
    
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String message = fr.asString();
        System.out.println(message);
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void testToString() {
        CaesarCipher cc = new CaesarCipher(5);
        System.out.println(cc);
    }
    
    public void testSlicer() {
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString("abcdefghijklm", 0, 3); // should return "adgjm"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 1, 3); // should return "behk"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 2, 3); // should return "cfil"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 0, 4); // should return "aeim"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 1, 4); // should return "bfj"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 2, 4); // should return "cgk"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 3, 4); // should return "dhl"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 0, 5); // should return "afk"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 1, 5); // should return "bgl"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 2, 5); // should return "chm"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 3, 5); // should return "di"
        System.out.println(slice);
        slice = vb.sliceString("abcdefghijklm", 4, 5); // should return "ej"
        System.out.println(slice);
    }
    
    public void testTryKeyLength() {
        //FileResource fr = new FileResource("VigenereTestData/athens_keyflute.txt");
        FileResource fr = new FileResource();
        //String encrypted = "JPYXIJPY";
        VigenereBreaker vb = new VigenereBreaker();
        //int[] key = vb.tryKeyLength(fr.asString(), 5, 'e');
        int[] key = vb.tryKeyLength(fr.asString(), 3, 'e');
        //int[] key = vb.tryKeyLength(encrypted, 5, 'e');
        System.out.println(Arrays.toString(key));
    }
    
    public void testReadDictionary() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println(dictionary.toString());
    }
    
    public void testCountWords() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("testdictionary.txt");
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println(dictionary.toString());
        String massege = "Hello! My name is Ksenia. Glad to see you!";
        int count = vb.countWords(massege, dictionary);
        System.out.printf("There are %s real words", count);
    }
    
    public void testBreakForLanguage() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet dictionary = vb.readDictionary(fr);
        FileResource fr2 = new FileResource("messages/secretmessage2.txt");
        String massege = fr2.asString();
        // System.out.println(massege);
        String decrypted = vb.breakForLanguage(massege, dictionary);
        System.out.println(decrypted);
    }
    
    public void testReadDictionary2() {
        VigenereBreaker vb = new VigenereBreaker();
        //FileResource fr = new FileResource("dictionaries/test");
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println(dictionary);
        System.out.println(dictionary.size());
        
        /*ArrayList<String> dictionary2 = vb.readDictionary2(fr);
        System.out.println(dictionary2);
        System.out.println(dictionary2.size());*/
        
    }
    
    public void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println(dictionary.size());
        char mostCommon = vb.mostCommonCharIn(dictionary);
        System.out.println("Most common char in this dictionary is " + mostCommon);
        /*char mostCommon2 = vb.mostCommonCharIn2(dictionary);
        System.out.println("Most common char2 in this dictionary is " + mostCommon2);*/
    }
}
































































