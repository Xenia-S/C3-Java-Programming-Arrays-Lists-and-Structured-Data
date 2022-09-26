import edu.duke.*;
import java.util.*;
import java.io.*;  

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles() {
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for(String w : fr.words()) {
            
            if(!wordsMap.keySet().contains(w)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                wordsMap.put(w, list);
            } else {
                ArrayList<String> list = wordsMap.get(w);
                if (!list.contains(f.getName())) {
                    list.add(f.getName());
                    wordsMap.put(w, list);
                }
            }
        }
    }
    
    public void buildWordFileMap() {
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int max = 0;
        for(String s : wordsMap.keySet()) {
            ArrayList<String> list = wordsMap.get(s);
            if(list.size() > max) {
                max = list.size();
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for(String s : wordsMap.keySet()) {
            ArrayList<String> list = wordsMap.get(s);
            if(list.size() == number) {
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> list = wordsMap.get(word);
        for(String s : list) {
            System.out.println(s);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        for(String s : wordsMap.keySet()) {
            ArrayList<String> list = wordsMap.get(s);
            System.out.println(s + list.toString());
        }
        System.out.println("\n");
        System.out.println("The maximum number of files any word is in: " + maxNumber());
        ArrayList<String> wordsInMaxNumOfFiles = wordsInNumFiles(maxNumber());
        System.out.println("The words that are in the maximum number of files are: " + wordsInMaxNumOfFiles.size() + " " + wordsInMaxNumOfFiles.toString());
        ArrayList<String> wordsIn4Files = wordsInNumFiles(4);
        System.out.println("The words that are in 4 files are: " + wordsIn4Files.size() + " "+ wordsIn4Files.toString());
        System.out.println("laid appears in " + wordsMap.get("laid").toString()); 
        System.out.println("tree appears in " + wordsMap.get("tree").toString());
    }
}
