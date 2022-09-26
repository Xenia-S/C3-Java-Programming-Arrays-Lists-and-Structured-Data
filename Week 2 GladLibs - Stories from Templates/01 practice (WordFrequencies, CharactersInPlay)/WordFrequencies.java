import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int count = myFreqs.get(index);
                myFreqs.set(index, count+1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int IndexOfMax = 0;
        for(int i : myFreqs) {
            if(i > max) {
                max = i;
                IndexOfMax = myFreqs.indexOf(i);
            }
        }
        return IndexOfMax;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of Unique Words is " + myWords.size());
        int IndexOfMax = findIndexOfMax();
        System.out.println("Most Frequent Word is " + "\"" + myWords.get(IndexOfMax) + "\"" + ", it occurs " + myFreqs.get(IndexOfMax) + " times.");
    }
}
