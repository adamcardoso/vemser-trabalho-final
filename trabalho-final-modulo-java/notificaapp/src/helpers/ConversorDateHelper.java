package helpers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConversorDateHelper {
    public static Date LocalDateTimeToDate(LocalDateTime ldt){
        java.util.Date date = java.util.Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return new Date(date.getTime());
    }
}
