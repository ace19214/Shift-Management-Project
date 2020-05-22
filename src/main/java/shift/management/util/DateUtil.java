package shift.management.util;



import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static DateTime getCurrentDateTime(){
        Instant instant = new Instant();
        Instant.now();
        return new DateTime(instant.toDateTime());
    }

    public static Date getCurrentDate(){
        LocalDateTime currentDateTimeFromJavaDate = new LocalDateTime(new Date());
        Date currentJavaDate = currentDateTimeFromJavaDate.toDate();
        return currentJavaDate;
    }

    public static DateTime plusDate(int plusDay){
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime nextDayDateTime = currentLocalDateTime.plusDays(plusDay);
        return new DateTime(nextDayDateTime.toDateTime());
    }

    public static Date plusMonth(Date startDate, int plusMonth){
        LocalDateTime currentDateTimeFromJavaDate = new LocalDateTime(startDate);
        LocalDateTime plusMonthResult = currentDateTimeFromJavaDate.plusMonths(plusMonth);
        Date result = plusMonthResult.toDate();
        return result;
    }

    public static boolean isBeforeDate(Date beforeDate, Date afterDate){
        boolean result = beforeDate.before(afterDate);
        return result;
    }

    public static boolean isAfterDate(Date beforeDate, Date afterDate){
        boolean result = beforeDate.after(afterDate);
        return result;
    }

    public static String DateFormatter(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String DateFormatterTime(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

//    public static java.sql.Date parseDate(java.sql.Date date){
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//            String dateString = format.format(date);
//            return new java.sql.Date(format.parse(dateString));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
