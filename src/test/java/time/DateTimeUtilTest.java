package time;

import org.hamcrest.Matchers;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.junit.Test;
import parser.JodaParser;

import java.time.Clock;
import java.util.Locale;

import static org.junit.Assert.assertThat;

/**
 * Created by ep001656 on 2015/12/07.
 */
public class DateTimeUtilTest {

    private final Clock clock = defaultClock;

    public interface Clock {
        DateTime read();
    }

    public static final Clock wallClock = () -> new DateTime();
    private static Clock defaultClock = wallClock;

    @Test
    public void testYyyyMMdd() throws Exception {
        assertThat(DateTimeUtil.yyyyMMdd(), Matchers.containsString("/"));
    }


    @Test
    public void testDateTimeFormat() {

        Locale locale = Locale.getDefault();
        String zoneId = DateTimeZone.getDefault().getID();

//        String pattern = "EEE MMM dd HH:mm:ss zzz YYYY";
        String pattern = "EEE MMM dd HH:mm:ss 'JST' YYYY";
        String dateValue = "Sat Mar 17 17:02:43 JST 2018";
//        String dateValue = "Sat Mar 17 17:02:43 'JST' 2018";

        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withDefaultYear(clock.read().getYear()).withLocale(locale).withZone(DateTimeZone.forID(zoneId));

        long millisecondsSinceEpoch = formatter.parseMillis(dateValue);


    }


    @Test
    public void testLogstash() {

//        String pattern = "EEE MMM dd HH:mm:ss 'JST' YYYY";
        String pattern = "EEE MMM dd HH:mm:ss zzz YYYY";
        String dateValue = "Sat Mar 17 17:02:43 JST 2018";
//        String dateValue = "Sat Mar 17 17:02:43 'JST' 2018";

        JodaParser jodaParser = new JodaParser(pattern, Locale.getDefault(), DateTimeZone.getDefault().getID());
        Instant parse = jodaParser.parse(dateValue);
    }

    @Test
    public void testDateFormatBuild() {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendDayOfWeekShortText()
                .appendLiteral(' ')
                .appendMonthOfYearShortText()
                .appendLiteral(' ')
                .appendDayOfMonth(2)
                .appendLiteral(' ')
                .appendHourOfDay(2)
                .appendLiteral(':')
                .appendMinuteOfHour(2)
                .appendLiteral(':')
                .appendSecondOfMinute(2)
                .appendLiteral(' ')
                .appendTimeZoneShortName()
                .appendLiteral(' ')
                .appendYear(4, 4)
                .toFormatter();

        // 金 10 19 01:34:19 JST 2018
        System.out.println(DateTime.now().toString(formatter));
    }


}