package date_example;

import org.junit.Test;

import java.text.ParseException;

public class DateTimeConvertTest {

    @Test
    public void testconvertFromUTCToJST() throws ParseException {

        System.out.println(new DateTimeConvert().convertFromUTCToJST("2022-08-30T02:10:16.559Z"));
        //System.out.println(new DateTimeConvert().convert("2022-08-30T02:10:16.559Z"));
        System.out.println(new DateTimeConvert().convert("2022-08-30T02:10:16.559Z"));


    }
}
