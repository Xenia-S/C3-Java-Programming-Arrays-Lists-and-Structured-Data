import edu.duke.*;
import java.io.*;

public class WordLengths {
    public int[] WordLengths(FileResource fr, int[] counts) {
        for (String word : fr.words()) {
            System.out.println(word + " " + countLetters(word));
            int letters = countLetters(word);
            if (letters >= counts.length) {
                counts[counts.length-1]++;
            }
            else {
                counts[letters]++;
            }
        }
        return counts;
    }
    
    public int countLetters(String word) {
        int lettersTotal = 0;
            for (int i=0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(Character.isLetter(currChar) || currChar == 45 || (currChar == 39 && i!=0 && i!=word.length()-1 )) {
                    lettersTotal++;
                }
            }
        return lettersTotal;
    }
    
    public void printWordLengthArray(int[] wordLength) {
        for (int i=1; i < wordLength.length; i++) {
            System.out.println(wordLength[i] + " words of length " + i);
        }
    }
    
    public int indexOfMostCommon(int[] counts) {
        int max = 0;
        int indexOfMax = 0;
        for (int i=0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }
    
    public void test_countLetters() {
        System.out.println(countLetters("non-letter")); //10
        System.out.println(countLetters("“blue-jeans”")); //10
        System.out.println(countLetters("jjj")); //3
    }
    
    public void test_WordLengths() {
        //FileResource fr = new FileResource("1-test file.txt");
        //FileResource fr = new FileResource("smallHamlet.txt");
        FileResource fr = new FileResource("2-test file.txt");
        int[] wordLength = new int[12];
        wordLength = WordLengths(fr, wordLength);
        printWordLengthArray(wordLength);
        
        int max = indexOfMostCommon(wordLength);
        System.out.println("Most common word length is " + max);
    }
    
    public void test2() {
        FileResource fr = new FileResource("manywords.txt");
        int[] wordLength = new int[12];
        wordLength = WordLengths(fr, wordLength);
        printWordLengthArray(wordLength);
        
        int max = indexOfMostCommon(wordLength);
        System.out.println("Most common word length is " + max);
    }
}
