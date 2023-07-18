package date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {


  public static void main(String[] args) {
    // Display the formatted date
    System.out.println("Formatted Date: " + "SD1-CL313/" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
  }
}
