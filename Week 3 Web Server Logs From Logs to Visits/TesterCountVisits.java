import java.util.*;
public class TesterCountVisits {
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("The maximum number of visits to this website by a single IP address: " + maxVisits);
        ArrayList<String> mostCommonIPs = la.iPsMostVisits(counts);
        System.out.println(mostCommonIPs);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(iPsForDays);
        for(String day : iPsForDays.keySet()) {
            System.out.println(day + ": " + iPsForDays.get(day) + "(" + iPsForDays.get(day).size() + " total IPs)");
        }
        String maxVisitsDay = la.dayWithMostIPVisits(iPsForDays);
        System.out.println("The day that has the most IP address visits is: " + maxVisitsDay);
        System.out.println("\n");
        ArrayList<String> iPsWithMostVisitsOnDay = la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30");  // если забудешь поменять день, может вылететь null exception!
        System.out.println("IP addresses that had the most accesses on the given day: " + iPsWithMostVisitsOnDay);
    }
}
