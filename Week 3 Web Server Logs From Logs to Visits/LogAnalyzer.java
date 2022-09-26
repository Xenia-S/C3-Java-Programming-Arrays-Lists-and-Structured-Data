
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
        private ArrayList<LogEntry> records;
     
     
        public LogAnalyzer() {
            records = new ArrayList<LogEntry>();
        }
        
        public void readFile(String filename) {
            FileResource fr = new FileResource(filename);
            for (String s : fr.lines()) {
                LogEntry currEntry = WebLogParser.parseEntry(s);
                records.add(currEntry);
            }
        }
        
        public void printAll() {
            for (LogEntry le : records) {
                System.out.println(le);
            }
        }
     
        public int countUniqueIPs() {
            ArrayList<String> uniqueIps = new ArrayList<String>();
            for(LogEntry le : records) {
                String ip = le.getIpAddress();
                if(!uniqueIps.contains(ip)) {
                    uniqueIps.add(ip);
                }
            }
            return uniqueIps.size();
        }
     
        public void printAllHigherThanNum(int num) { // print those LogEntrys that have a status code greater than num
            for(LogEntry le : records) {
                int currStatus = le.getStatusCode();
                if (currStatus > num) {
                    System.out.println(le);
                }
            }
        }
     
        public ArrayList<String> uniqueIPVisitsOnDay(String someday) { // someday in the format “MMM DD” (“Dec 05”,“Apr 22”)
            ArrayList<String> uniqueIps = new ArrayList<String>();
            for(LogEntry le : records) {
                String d = le.getAccessTime().toString();
                if (d.contains(someday)  && !uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
            return uniqueIps;
        }
     
        public int countUniqueIPsInRange(int low, int high) { // returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive
            ArrayList<String> ips = new ArrayList<String>();
            for (LogEntry le : records) {
                int status = le.getStatusCode();
                if (status >= low && status <= high && !ips.contains(le.getIpAddress())) {
                    ips.add(le.getIpAddress());
                }
            }
            return ips.size();
        }
     
        public HashMap<String, Integer> countVisitsPerIP() {
            HashMap<String, Integer> counts = new HashMap<String,Integer>();
            for(LogEntry le : records) {
                String ip = le.getIpAddress();
                if(! counts.containsKey(ip)) {
                    counts.put(ip, 1);
                } else {
                 counts.put(ip, counts.get(ip)+1);
                }
            }
            return counts;
        }
        
        public HashMap<String, Integer> countVisitsPerIP2(ArrayList<String> IPs) {
            HashMap<String, Integer> counts = new HashMap<String,Integer>();
            for(String ip : IPs) {
                if(! counts.containsKey(ip)) {
                    counts.put(ip, 1);
                } else {
                 counts.put(ip, counts.get(ip)+1);
                }
            }
            return counts;
        }
     
        public int mostNumberVisitsByIP(HashMap<String, Integer> counts) { //returns the maximum number of visits to this website by a single IP address
            int max = 0;
            for(String ip : counts.keySet()) {
                int visits = counts.get(ip);
                if(visits > max) {
                    max = visits;
                }
            }
            return max;
        }
     
        public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) { //returns IPs that all have the maximum number of visits to this website
            ArrayList<String> mostCommonIPs = new ArrayList<String>();
            int max = mostNumberVisitsByIP(counts);
            for(String ip : counts.keySet()) {
                if(counts.get(ip) == max) {
                    mostCommonIPs.add(ip);
                }
            }
            return mostCommonIPs;
        }
        
        public HashMap<String, ArrayList<String>> iPsForDays() {
            HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String, ArrayList<String>>();
            for (LogEntry le : records) {
                String currTime = le.getAccessTime().toString();
                String currDay = currTime.substring(4, 10);
                if (! iPsForDays.containsKey(currDay)) {
                    ArrayList<String> ips = new ArrayList<String>();
                    ips.add(le.getIpAddress());
                    iPsForDays.put(currDay, ips);
                } else {
                    ArrayList<String> ips = iPsForDays.get(currDay);
                    ips.add(le.getIpAddress());
                    iPsForDays.put(currDay, ips);
                }
            }
            return iPsForDays;
        }
        
        public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
            String maxVisitsDay = "";
            int maxVisits = 0;
            for(String day : iPsForDays.keySet()) {
                ArrayList<String> currIPs = iPsForDays.get(day);
                if (currIPs.size() > maxVisits) {
                    maxVisits = currIPs.size();
                    maxVisitsDay = day;
                }
            }
            return maxVisitsDay;
        }
        
        public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day) { // a day in the format “MMM DD”
            ArrayList<String> IPsForTheDay = iPsForDays.get(day);
            HashMap<String, Integer> counts = countVisitsPerIP2(IPsForTheDay);
            ArrayList<String> iPsWithMostVisits = iPsMostVisits(counts);
            return iPsWithMostVisits;
        }
}












