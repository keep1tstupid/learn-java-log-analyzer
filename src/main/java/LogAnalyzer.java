import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class implements analysis of logs data in format specified in LogEntry class.
 * Allows to count unique IP addresses, visitors per day and so on.
 * Exercise to practice ArrayLists, HashMaps.
 * Educational Duke library for reading files replaced with the standard one.
 */

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner scan = new Scanner(f);

        if (!f.exists()) {
            throw new FileNotFoundException("File not found");
        }

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            LogEntry temp = WebLogParser.parseEntry(line);
            records.add(temp);
        }
    }

    public int countUniqueIPs() {
        HashSet<String> uniqueIP = new HashSet<String>();

        for (LogEntry entry: records) {
            String ipAddr = entry.getIpAddress();
            uniqueIP.add(ipAddr);
        }

        return uniqueIP.size();
    }

    public HashSet<String> uniqueIPVisitsOnDay(String day) {
        HashSet<String> uniqueIP = new HashSet<String>();

        for (LogEntry entry: records) {
            String date = entry.getMonthDay();
            if (date.equals(day)) {
                uniqueIP.add(entry.getIpAddress());
            }
        }
        return uniqueIP;
    }

    // Returns the number of unique IP addresses that have a status code in the range from low to high (inclusive)
    public int countUniqueIPsInRange(int low, int high) {
        HashSet<String> uniqueIPs = new HashSet<String>();

        for (LogEntry entry: records) {
            String ip = entry.getIpAddress();
            int code = entry.getStatusCode();
            if (code >= low && code <= high) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    // Checks all states and saves records where status code is higher than specified
    public void printAllHigherThanNum(int num) {
        HashSet<Integer> uniqueCodes = new HashSet<Integer>();

        for (LogEntry entry: records) {
            int code = entry.getStatusCode();
            if (code > num) {
                uniqueCodes.add(code);
                // System.out.println(entry);
            }
        }
        System.out.println(uniqueCodes.toString());
    }

    // This method returns a HashMap<IP, Number of visits of this IP>
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();

        for (LogEntry entry: records) {
            String ip = entry.getIpAddress();
            counts.put(ip, counts.containsKey(ip) ? counts.get(ip) + 1 : 1);
        }
        return counts;
    }

    // Returns the maximum number of visits to this website by a single IP address.
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int res = 0;

        for (int num: counts.values()) {
            if (num > res) {
                res = num;
            }
        }
        return res;
    }

    // This method returns an ArrayList of IP addresses that have the maximum number of visits.
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> IPs = new ArrayList<String>();
        int most = mostNumberVisitsByIP(map);

        for (String ip: map.keySet()) {
            if (map.get(ip) == most) {
                IPs.add(ip);
            }
        }
        return IPs;
    }


    //  Returns HashMap with IP addresses(value) that occurred on days (key)
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dateIPs = new HashMap<String, ArrayList<String>>();

        for (LogEntry entry: records) {
            String date = entry.getMonthDay();
            ArrayList<String> temp = dateIPs.containsKey(date)
                    ? dateIPs.get(date)
                    : new ArrayList<String>();
            temp.add(entry.getIpAddress());
            dateIPs.put(date, temp);
        }
        return dateIPs;
    }

    // This method returns the day that has the most IP address visits.
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days) {
        String activeDay = "";
        int biggestSize = 0;

        for (String day: days.keySet()) {
            int size = days.get(day).size();
            if (size > biggestSize) {
                biggestSize = size;
                activeDay = day;
            }
        }
        return activeDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> days, String day) {
        HashMap<String, Integer> visitsPerIp = new HashMap<String, Integer>();
        ArrayList<String> thisDay = days.get(day);

        for (String ip : thisDay) {
            visitsPerIp.put(ip, visitsPerIp.containsKey(ip) ? visitsPerIp.get(ip) + 1 : 1);
        }
        return iPsMostVisits(visitsPerIp);
    }

    public void printAll() {
        for (LogEntry entry : records) {
            System.out.println(entry);
        }
    }
}