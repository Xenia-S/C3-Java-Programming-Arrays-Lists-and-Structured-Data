import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> lableMap;
    private ArrayList<String> seenList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        lableMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        lableMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categotries = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String c : categotries) {
            ArrayList<String> list = readIt(source + "/" + c + ".txt");
            lableMap.put(c, list);
        }
        seenList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else {
            return randomFrom(lableMap.get(label));
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        System.out.println(w.substring(first,last+1) + " " + sub);
        int change = 0;
        while(seenList.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
            change++;
            System.out.println(w.substring(first,last+1) + " " + sub);
            if(change == seenList.size()) {
                System.out.println("There is no more unused words to substitute");
                System.exit(1);
            }
        }
        seenList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int total = 0;
        for(String s : lableMap.keySet()) {
            int size = lableMap.get(s).size();
            total += size;
        }
        return total;
    }
    
    public int totalWordsConsidered() {
        int total = 0;
        for(String c : lableMap.keySet()) {
            ArrayList<String> list = lableMap.get(c);
            for(String s : list) {
                if(seenList.contains(s)) {
                    total ++;
                }
            }
        }
        return total;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total number of words that were seen: " + seenList.size());
        System.out.println("Total number of words that were used: " + totalWordsConsidered());
        System.out.println("Total number of words that were possible to pick from: " + totalWordsInMap());
    }
    


}
