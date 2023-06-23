package json;

//import com.googlecode.json-sample

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class JsonParsing {

  public static boolean startsWithIgnoreCase(String str, String prefix) {
    if (str.length() < prefix.length()) {
      return false;
    }
    String strLower = str.substring(0, prefix.length()).toLowerCase();
    String prefixLower = prefix.toLowerCase();
    return strLower.equals(prefixLower);
  }

  public static void main(String[] args) throws IOException {

//    String str = "ハロー, World!";
//    String prefix = "ハ";
//    boolean startsWith = startsWithIgnoreCase(str, prefix);
//    System.out.println(startsWith); // Output: true

    new JsonParsing().parse("");

  }

  public void parse(String text) throws IOException {

//        String s = "\"foo\" is not \"bar\". specials: \b\r\n\f\t\\/";
//    Path file = Paths.get("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/OpenText-WSBO.txt");

//    Path file = Paths.get("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserEnableAddEntitlementActiveUserRule.txt");

    //    Path file = Paths.get("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserDisableDeleteEntitlementActiveUserRule.txt");

    Path file = Paths.get("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeSuspendedRule.txt");


    String fileStringWithLF = Files.readString(file);
    checkIfcontainsIllegalCharacters(fileStringWithLF, file);
    String fileStrWithCRLF = fileStringWithLF.replaceAll("\n", "\r\n");

    System.out.println(JSONObject.toString("script", fileStrWithCRLF));

//        JSONObject jsonObject = new JSONObject();
//        String text = "Text with special character /\"\'\b\f\t\r\n.";
//        System.out.println(text);
//        System.out.println("After escaping.");
//        text = jsonObject.escape(text);
//        System.out.println(text);
  }

  private static void checkIfcontainsIllegalCharacters(String fileStringWithLF, Path file) throws IOException {
    if (fileStringWithLF.contains("<") || fileStringWithLF.contains(">")) {
      throw new RuntimeException("< or > is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")");
    }

    if (fileStringWithLF.contains("static")) {
      throw new RuntimeException("static is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")");
    }
  }
}
