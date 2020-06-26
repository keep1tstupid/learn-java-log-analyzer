import java.io.FileNotFoundException;
import java.util.*;

/**
 *Test methods for LogAnalyzer Class.
 */

public class Tester {
    public void testLogEntry() {
        LogEntry visitor1 = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(visitor1);
        LogEntry visitor2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(visitor2);
    }

    public static void testLogAnalyzer() throws FileNotFoundException {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("short-test_log");
        test.printAll();
    }

    public static void testUniqIP() throws FileNotFoundException {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        int uniqueIPs = test.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
    }

    public static void testUniqDay() throws FileNotFoundException {
        String day = "Sep 27";
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashSet<String> unique = test.uniqueIPVisitsOnDay(day);
        System.out.println(unique.size() +" unique entries on day " + day + "\n" + unique);
    }

    public static void testPrintHigher() throws FileNotFoundException {
        int num = 400;
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog1_log");
        System.out.println("List of unique entries with status code higher than " + num);
        test.printAllHigherThanNum(num);
    }

    public static void testUniqueIPsInRange() throws FileNotFoundException {
        int[] range = {400, 499};
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        int uniqueIPs = test.countUniqueIPsInRange(range[0], range[1]);
        System.out.println("There are " + uniqueIPs + " unique IPs in range from " + range[0] + " to " + range[1]);
    }

    public static void testCounts() throws FileNotFoundException {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String,Integer> counts = test.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("the most number of visits by one IP was: " + test.mostNumberVisitsByIP(counts));
    }

    public static void testByDay() throws FileNotFoundException {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> days = test.iPsForDays();
        for(String key: days.keySet()){
            System.out.println(key + days.get(key));
        }
        System.out.println("the day with the most visits was: " + test.dayWithMostIPVisits(days));
    }

    public static void testHighest() throws FileNotFoundException {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String, Integer> countPerVisit = test.countVisitsPerIP();
        System.out.println("IP's most visits: " + test.iPsMostVisits(countPerVisit));

        System.out.println("Highest on day: " + test.iPsWithMostVisitsOnDay(test.iPsForDays(), "Sep 29"));
    }


    public static void main(String[] args) throws FileNotFoundException {
//        testLogAnalyzer();
//        testUniqIP();
//        testUniqDay();
//        testPrintHigher();
//        testUniqueIPsInRange();
//        testCounts();
//        testHighest();
//        testByDay();
    }
}
