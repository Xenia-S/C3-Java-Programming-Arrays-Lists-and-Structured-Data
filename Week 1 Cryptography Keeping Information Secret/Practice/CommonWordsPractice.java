import edu.duke.*;

public class CommonWordsPractice {
    public String[] getCommon() {            // делаем из файла массив
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int i = 0;
        for (String s : fr.words()) {
            common[i] = s;
            i++;
        }
        return common;
    }
    
    public int[] countWords(FileResource fr, String[] common, int[] count) {   // считаем совпадения слов, записываем в массив такого же размера, как common
        for (String word : fr.words()) {                                       // для MyWay нужно убирать int[] count из параметров
            word = word.toLowerCase();
            for (int k=0; k<common.length; k++) {
                if (word.equals(common[k])) {
                    count[k]++;
                }
            }
        }
        return count;
    }
    
    public void countShakespeare() {
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] common = getCommon();
        int[] totalCount = new int[common.length];
        for (int i=0; i<plays.length; i++) {
            FileResource fr = new FileResource("data/"+plays[i]);
            totalCount = countWords(fr, common, totalCount);
            System.out.println("Done with "+ plays[i]);
        }
        System.out.println("\n TOTAL:");
        for (int k=0; k<common.length; k++){
                System.out.println(common[k] + "\t" + totalCount[k]);
        }
    }
    
    /*public void countShakespeareMyWay() {    // мой вариант стичает common отдельно в каждой play
        String[] plays = {"caesar.txt", "common.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt", "small.txt"};
        String[] common = getCommon();
        int[] totalCount = new int[common.length];
        for (int i=0; i<plays.length; i++) {
            FileResource fr = new FileResource("data/"+plays[i]);
            int[] count = countWords(fr, common);
            for (int k=0; k<common.length; k++){
                System.out.println(common[k] + "\t" + count[k]);
                totalCount[k] = totalCount[k] + count[k];
            }
            System.out.println("Done with "+ plays[i]);
        }
        System.out.println("\n TOTAL:");
        for (int k=0; k<common.length; k++){
                System.out.println(common[k] + "\t" + totalCount[k]);
        }
    }*/
    
}
