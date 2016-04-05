package time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;

/**
 * utility of Date time
 *
 */
public class DateTimeUtil {

    /**
     * Get this year
     * 
     * @return this year
     */
    public static int nowYear() {
        return new DateTime().getYear();
    }

    /**
     * Get this month of year
     *
     * @return this month of year
     */
    public static int nowMonth() {
        return new DateTime().getMonthOfYear();
    }

    /**
     * Get this day of Month
     *
     * @return the day of month
     */
    public static int nowDay() {
        return new DateTime().getDayOfMonth();
    }

    /**
     * Gets the current time in milliseconds.
     *
     * @return the current time in milliseconds from 1970-01-01T00:00:00Z
     */
    public static long currentTimeMillis() {
        return DateTimeUtils.currentTimeMillis();
    }

    /**
     * Gets the current date with yyyyMMdd format
     * 
     * @return
     */
    public static String yyyyMMdd() {
        return new DateTime().toString(DateTimeFormat.longDate());
    }


}
