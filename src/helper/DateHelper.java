package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static final String VALID_DATE_PATTERN = "MM/dd/yyyy";

    public static Date getDate(String dateString) {
        try {
            Date date = new SimpleDateFormat(VALID_DATE_PATTERN).parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
