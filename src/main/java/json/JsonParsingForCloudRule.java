package json;

import org.checkerframework.checker.units.qual.A;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParsingForCloudRule {

  public static boolean startsWithIgnoreCase(String str, String prefix) {
    if (str.length() < prefix.length()) {
      return false;
    }
    String strLower = str.substring(0, prefix.length()).toLowerCase();
    String prefixLower = prefix.toLowerCase();
    return strLower.equals(prefixLower);
  }

  public static void main(String[] args) throws IOException {
    new JsonParsingForCloudRule().parse();
  }

  public void parse() throws IOException {

    Path file1 = Paths.get("/Users/daisuke.harada/github/daisuke-harada-sp/identitynow-services-config/Fujitsu/oneid-dev-sb1.identitynow.com/rules/Rule - BeforeProvisioning - Delete BillingIntegrationSystem account.xml");
    Path file2 = Paths.get("/Users/daisuke.harada/github/daisuke-harada-sp/identitynow-services-config/Fujitsu/oneid-dev-sb1.identitynow.com/rules/Rule - BeforeProvisioning - Delete DetailArrangementSystem account.xml");

    List files = new ArrayList();
    files.add(file1);
    files.add(file2);

    Iterator<Path> iterator = files.iterator();
    while (iterator.hasNext()) {
      Path next = iterator.next();
      List<String> fileStringWithLF = Files.readAllLines(next);
      for (int i = 0; i < fileStringWithLF.size(); i++) {
        checkIllegalCharacters(eachLine(fileStringWithLF, i), next);
      }
    }


  }

  private String eachLine(List<String> fileStringWithLF, int i) {
    return fileStringWithLF.get(i);
  }

  private static void checkIllegalCharacters(String eachLine, Path file) throws IOException {
    if (eachLine.contains("static")) {
      throw new RuntimeException("static is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);
    }

    if (eachLine.trim().startsWith("<?xml")) {
      //
      return;
    }

    if (eachLine.trim().startsWith("<?xml") || eachLine.trim().startsWith("<Rule") || eachLine.trim().startsWith("<Description>") || eachLine.trim().startsWith("<Source>") || eachLine.trim().startsWith("<!DOCTYPE") || eachLine.trim().startsWith("]]></Source") || eachLine.trim().startsWith("</Rule>")) {
      //
      return;
    }


    if (eachLine.contains("<A") || eachLine.contains("<S") || eachLine.contains("<M") || eachLine.contains("<I") || (eachLine.contains(">") && !eachLine.contains(" > 0"))) {
      throw new RuntimeException("< or > is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);
    }

  }
}
