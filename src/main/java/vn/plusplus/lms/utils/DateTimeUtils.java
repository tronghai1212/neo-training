package vn.plusplus.lms.utils;



import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTimeUtils {

    /**
     * getDateTimeNow get date time now convert to String
     *
     * @param format : format of date
     * @return String {java.lang.String}
     */
    public static String getDateTimeNow(String format) {
        if (format == null) {
            format = "dd/MM/yyyy HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dateNow = new Date(System.currentTimeMillis());
        return formatter.format(dateNow);
    }

}
