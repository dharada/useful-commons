package parser;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.math.BigDecimal;

import java.util.Locale;

public class JodaParser {
    private final DateTimeFormatter parser;
    private final boolean hasYear;
    private final boolean hasZone;
    private final String timezone;

    private final Clock clock = defaultClock;

    public interface Clock {
        DateTime read();
    }

    public static final Clock wallClock = () -> new DateTime();
    private static Clock defaultClock = wallClock;

    public static void setDefaultClock(Clock clock) {
        JodaParser.defaultClock = clock;
    }

    public JodaParser(String pattern, Locale locale, String timezone) {
        this.timezone = timezone;

        // Does the pattern contain year information?
        hasYear = (pattern.contains("Y") || pattern.contains("y"));
        // If pattern has no timezone format, we should parse in "local" time.
        hasZone = pattern.contains("Z");

        parser = DateTimeFormat.forPattern(pattern).withDefaultYear(clock.read().getYear()).withLocale(locale).withZone(DateTimeZone.forID(timezone));
    }

    public Instant parse(String value) {

        if (hasYear) {
            return new Instant(parser.parseMillis(value));
        } else {
            return parseAndGuessYear(parser, value);
        }
    }

    public Instant parse(Long value) {
        throw new IllegalArgumentException("Expected a string value, but got a long (" + value + "). Cannot parse date.");
    }

    public Instant parse(Double value) {
        throw new IllegalArgumentException("Expected a string value, but got a double (" + value + "). Cannot parse date.");
    }

    public Instant parse(BigDecimal value) {
        throw new IllegalArgumentException("Expected a string value, but got a bigdecimal (" + value + "). Cannot parse date.");
    }

    public Instant parseWithTimeZone(String value, String timezone) {
        DateTimeZone tz = DateTimeZone.forID(timezone);
        DateTimeFormatter parserWithZone = parser.withZone(tz);
        if (hasYear) {
            return new Instant(parserWithZone.parseMillis(value));
        } else {
            return parseAndGuessYear(parserWithZone, value);
        }
    }

    private Instant parseAndGuessYear(DateTimeFormatter parser, String value) {
        // if we get here, we need to do some special handling at the time each event is handled
        // because things like the current year could be different, etc.
        DateTime dateTime;
        if (hasZone) {
            dateTime = parser.parseDateTime(value);
        } else {
            dateTime = parser.withZoneUTC().parseLocalDateTime(value).toDateTime(parser.getZone());
        }

        // The time format we have has no year listed, so we'll have to guess the year.
        int month = dateTime.getMonthOfYear();
        DateTime now = clock.read();
        int eventYear;

        if (month == 12 && now.getMonthOfYear() == 1) {
            // Now is January, event is December. Assume it's from last year.
            eventYear = now.getYear() - 1;
        } else if (month == 1 && now.getMonthOfYear() == 12) {
            // Now is December, event is January. Assume it's from next year.
            eventYear = now.getYear() + 1;
        } else {
            // Otherwise, assume it's from this year.
            eventYear = now.getYear();
        }

        return dateTime.withYear(eventYear).toInstant();
    }

}
