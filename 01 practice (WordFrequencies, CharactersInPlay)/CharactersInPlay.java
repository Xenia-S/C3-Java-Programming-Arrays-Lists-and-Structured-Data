import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> count;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for(String s : fr.lines()) {
            int end = s.indexOf(".");
            if(end != -1) {
                String character = s.substring(0, end);
                update(character);
            }
        }
    }
    
    public void update(String character) {
        character = character.toUpperCase();
        int index = characters.indexOf(character);
        if (index == -1) {
            characters.add(character);
            count.add(1);
        } else {
            int amount = count.get(index);
            count.set(index, amount+1);
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        if (num1 < num2) {
            for(int i = 0; i < characters.size(); i++) {
                if(count.get(i) >= num1 && count.get(i) <= num2) {
                    System.out.println(count.get(i) + " - " + characters.get(i));
                }
            }
        } else {
            System.out.println("Set another numbers. First number should be smaller, than second one.");
        }
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(count.get(i) + " - " + characters.get(i));
        }
    }
    
    public void tester2() {
        findAllCharacters();
        charactersWithNumParts(10, 15);
    }
}
