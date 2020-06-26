import java.text.SimpleDateFormat;
import  java.util.*;

/**
 * Class provided by course.
 * Added getMonthDay() method to access date in appropriate format and
 * not to convert it to string every time I need it.
 */

public class LogEntry {
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     
   public LogEntry(String ip, Date time, String req, int status, int bytes) {
       ipAddress = ip;
       accessTime = time;
       request = req;
       statusCode = status;
       bytesReturned = bytes;
   }
   
   public String getIpAddress() {
         return ipAddress;
   }

   public Date getAccessTime() {
         return accessTime;
   }

   public String getMonthDay() {
       SimpleDateFormat formatDay = new SimpleDateFormat("dd");
       SimpleDateFormat formatMonth = new SimpleDateFormat("MMM");

       String currentDay = formatDay.format(accessTime);
       String currentMonth = formatMonth.format(accessTime);

       return currentMonth + " " + currentDay;
   }

   public String getRequest() {
         return request;
   }

   public int getStatusCode() {
         return statusCode;
   }

   public int getBytesReturned() {
         return bytesReturned;
   }
   
   public String toString() {
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
   }
}
