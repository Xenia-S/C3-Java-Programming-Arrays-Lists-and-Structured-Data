import edu.duke.*;
import java.io.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "aeiouEUIOA";
        int i = vowel.indexOf(ch);
        if (i != -1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('a')); // true
        System.out.println(isVowel('A')); // true
        System.out.println(isVowel('b')); // false
        System.out.println(isVowel('R')); // false
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            if(isVowel(sb.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels() {
        String phrase = "Let me speak from my heart";
        System.out.println(replaceVowels(phrase, '*'));
        
        phrase = "insert(int offset, char c) Inserts the string representation of the char argument into this sequence.";
        System.out.println(replaceVowels(phrase, '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i <sb.length(); i++) {
            if(sb.charAt(i) == Character.toUpperCase(ch) || sb.charAt(i) == Character.toLowerCase(ch)) {
                if((i + 1) % 2 == 0) {
                    sb.setCharAt(i, '+');
                }
                else {
                    sb.setCharAt(i, '*');
                }
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize() {
        String phrase = "Let me speak from my heart";
        System.out.println(emphasize(phrase, 'e'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
        System.out.println(emphasize("MAry Bella AbracadAbra", 'a'));
        
    }
}
