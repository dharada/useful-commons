package json;

//import com.googlecode.json-sample

import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.*;

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
    Path file = Paths.get("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeApproveRule.txt");
    String fileStringWithLF = Files.readString(file);
    checkIllegalCharacters(fileStringWithLF, file);
    String fileStrWithCRLF = fileStringWithLF.replaceAll("\n", "\r\n");
    System.out.println(JSONObject.toString("script", fileStrWithCRLF));
  }

  private static void checkIllegalCharacters(String fileStringWithLF, Path file) throws IOException {
    if (fileStringWithLF.contains("static")) {
      throw new RuntimeException("static is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + fileStringWithLF);
    }

    if (fileStringWithLF.contains("<A") || fileStringWithLF.contains("<S") || fileStringWithLF.contains("<M") || fileStringWithLF.contains("<I") || fileStringWithLF.contains(">")) {
      throw new RuntimeException("< or > is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + fileStringWithLF);
    }
  }
}
