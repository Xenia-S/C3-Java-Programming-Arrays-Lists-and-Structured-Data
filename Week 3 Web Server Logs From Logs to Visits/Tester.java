
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        //la.readFile("weblog2-short_log");
        la.printAll();
    }
    
    public void testСountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("There are " + la.countUniqueIPs() + " unique IPs");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //ArrayList<String> ips = la.uniqueIPVisitsOnDay("Sep 14");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Sep 24");
        for (String s : ips) {
            System.out.println(s);
        }
        System.out.println(ips.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int inRange = la.countUniqueIPsInRange(400, 499);
        //int inRange = la.countUniqueIPsInRange(300, 399);
        System.out.println("The number of unique IP addresses that have a status code in the range: " + inRange);
    }
    
    
}



















