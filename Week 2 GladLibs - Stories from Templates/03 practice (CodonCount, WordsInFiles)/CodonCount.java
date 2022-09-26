import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        dna = dna.trim();
        for(int i=start; i<=dna.length()-3; i+=3) {
            String codon = dna.substring(i, i+3);
            //System.out.println(codon);
            if(codonMap.keySet().contains(codon)) {
                int count = codonMap.get(codon);
                codonMap.put(codon, count+1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int count = 0;
        for(String codon : codonMap.keySet()) {
            if(codonMap.get(codon) > count) {
                mostCommonCodon = codon;
                count = codonMap.get(codon);
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        for(String codon : codonMap.keySet()) {
            if(codonMap.get(codon) >= start && codonMap.get(codon) <= end) {
                System.out.println(codon + " " + codonMap.get(codon));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(0, dna);
        printCodonCounts(7, 7);
        System.out.println("The most common codon is: " + getMostCommonCodon());
        System.out.println("The number of unique codons: " + codonMap.size());
    }
}
