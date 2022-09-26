import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        CaesarCracker cc = new CaesarCracker();
        int[] countLetters = new int[26];
        for(String word : dictionary) {
            int[] countWordLetters = cc.countLetters(word);
            for(int i = 0; i < countLetters.length; i++) {
                countLetters[i] = countLetters[i] + countWordLetters[i];
            }
        }
        int maxIndex = cc.maxIndex(countLetters);
        char mostCommon = "abcdefghijklmnopqrstuvwxyz".charAt(maxIndex);
        //System.out.print(Arrays.toString(countLetters));
        return mostCommon;
    }
    
    /* Другой вариант решения для проверки:
    public char mostCommonCharIn2(HashSet<String> dictionary) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(String word : dictionary) {
            for(int k=0; k < word.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(word.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        int maxDex = 0;
        for(int k=0; k < counts.length; k++){
            if (counts[k] > counts[maxDex]){
                maxDex = k;
            }
        }
        System.out.print(Arrays.toString(counts));
        return alph.charAt(maxDex);
    }*/
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        int i = 0;
        while(i < klength) {
            String slice = sliceString(encrypted, i, klength);
            int oneKey = cc.getKey(slice);
            key[i] = oneKey;
            i++;
        }
        return key;
    }

    public void breakVigenere () {
        /* Знаем язык и знаем key:
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println(encrypted);  
        int[] key = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher vs = new VigenereCipher(key);
        String decripted = vs.decrypt(encrypted);
        System.out.println(decripted);
        */
        
        /* Знаем язык, но не знаем key:
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource fr2 = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(fr2);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
        */
        
        // Не знаем язык, не знаем key:
        System.out.println("Select encrypted file:");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        System.out.println("Select dictionary files:");
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr2 = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr2);
            languages.put(f.getName(), dictionary);
            System.out.println(f.getName() + " is done");
        }
        String decrypted = breakForAllLanguages(encrypted, languages);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : fr.lines()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    /* Другой вариант с ArrayList:
    public ArrayList<String> readDictionary2(FileResource fr) {
        ArrayList<String> dictionary = new ArrayList<String>();
        for (String word : fr.lines()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    */
    
    public int countWords(String massege, HashSet<String> dictionary) {
        int count = 0;
        for (String word : massege.split("\\W")) {
            word = word.toLowerCase();
            if(dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxRealWords = 0;
        String answer = "";
        for(int i = 1; i <= 100; i++) {
            char mostCommon = mostCommonCharIn(dictionary);
            int[] currKey = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vs = new VigenereCipher(currKey);
            String decrypted = vs.decrypt(encrypted);
            int currRealWords = countWords(decrypted, dictionary);
            if(currRealWords > maxRealWords) {
                maxRealWords = currRealWords;
                answer = decrypted;
                //System.out.println("Key is " + i);
                //System.out.println("There are " + currRealWords + " real words");
            }
        }
        return answer;
    }
    
    public String breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxRealWords = 0;
        String answer = "";
        String encryptLang = "";
        for(String currLang: languages.keySet()) {
            String decrypted = breakForLanguage(encrypted, languages.get(currLang));
            int count = countWords(decrypted, languages.get(currLang));
            if(count > maxRealWords) {
                maxRealWords = count;
                answer = decrypted;
                encryptLang = currLang;
            }
        }
        System.out.println(encryptLang);
        return answer;
    }
    
}















































