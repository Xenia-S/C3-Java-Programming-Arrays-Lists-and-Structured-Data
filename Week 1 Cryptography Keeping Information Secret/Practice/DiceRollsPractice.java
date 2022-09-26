import edu.duke.*;
import java.util.Random;

public class DiceRollsPractice {
    public void simmulate(int rolls) {
       Random rand = new Random();
       int[] counts = new int[13];
       for(int k=0; k<rolls; k++) {
           int d1 = rand.nextInt(6)+1;
           int d2 = rand.nextInt(6)+1;
           System.out.println(d1+"+"+d2+"="+(d1+d2));
           counts[d1 + d2] ++;
        }
       
       for(int i=2; i<13; i++) {
        System.out.println(counts[i] + " times we got " + i +"(" + counts[i]*100.00/rolls + "%)");
        }
    }

}
