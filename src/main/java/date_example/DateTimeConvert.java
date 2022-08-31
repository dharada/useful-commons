package date_example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeConvert {


    public String convertFromUTCToJST(String gmtString) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat jpsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jpsdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

        String pstString = jpsdf.format(sdf.parse(gmtString));


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPAN);
//        String pstString = output.format(sdf.parse(gmtString));

//        //"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        DateTimeFormatter ldtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        ZonedDateTime gmtZdt = SimpleDateFormat.parse(gmtString, dtf);
//        ZonedDateTime pstZdt =
//                gmtZdt.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
//        String pstString = pstZdt.format(ldtf);

        return pstString;

    }

    public String convert(String gmtString) {

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("Asia/Tokyo"));

        return ZonedDateTime.parse(gmtString, dtf).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

}
