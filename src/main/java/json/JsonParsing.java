package json;

//import com.googlecode.json-sample

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    new JsonParsing().parse();
  }

  public void parse() throws IOException {

    File connRuleDir = new File("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule");
    if (connRuleDir.isDirectory()) {
      Predicate<File> predicate = new Predicate<File>() {
        @Override
        public boolean test(File o) {
          return o.toString().endsWith(".txt");
        }
      };
      Iterator<File> fileIterator = Arrays.stream(Objects.requireNonNull(connRuleDir.listFiles())).filter(predicate).iterator();

      while (fileIterator.hasNext()) {
        Path path = Paths.get(fileIterator.next().getAbsolutePath());
        Iterator<String> lineIte = Files.readAllLines(path).iterator();
        while (lineIte.hasNext()) {
          checkIllegalCharacters(lineIte.next(), path);
        }

        if (false) {
          System.out.println(path.toAbsolutePath() + " :check DONE.OK");
        }

      }
    }


    List<String> fileList = new ArrayList();

    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeApproveRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeModifyUserRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeSuspendedRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserDisableDeleteEntitlementActiveUserRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserDisableRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserEnableAddEntitlementActiveUserRule.txt");
    fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/BlackLine-BeforeUserEnableRule.txt");

    //fileList.add("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/AD-connector-rule/AD-BeforeCreateRule.txt");

    Iterator<String> iterator = fileList.iterator();
    while (iterator.hasNext()) {
      String filePath = iterator.next();

      Path file = Paths.get(filePath);
      List<String> fileStringWithLF = Files.readAllLines(file);
      for (int i = 0; i < fileStringWithLF.size(); i++) {
        checkIllegalCharacters(eachLine(fileStringWithLF, i), file);
      }

      System.out.println(file.getFileName().toString() + "\n");

      String fileString = Files.readString(file);
      fileString = fileString.replace("Map<String, Object>", "Map");
      fileString = fileString.replace("List<AttributeRequest>", "List");
      fileString = fileString.replace("List<AccountRequest>", "List");
      fileString = fileString.replace("List<Map>", "List");

      String fileStrWithCRLF = fileString.replaceAll("\n", "\r\n");
      System.out.println(JSONObject.toString("script", fileStrWithCRLF));
      System.out.println();
    }

  }

  private String eachLine(List<String> fileStringWithLF, int i) {
    return fileStringWithLF.get(i);
  }

  private static void checkIllegalCharacters(String eachLine, Path file) throws IOException {
    if (eachLine.contains("static")) {
      throw new RuntimeException("static is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);
    }

    eachLine = eachLine.replace("Map<String, Object>", "Map");
    eachLine = eachLine.replace("List<AttributeRequest>", "List");
    eachLine = eachLine.replace("List<AccountRequest>", "List");
    eachLine = eachLine.replace("List<Map>", "List");

    if (eachLine.contains("<A") ||
            eachLine.contains("<S") || eachLine.contains("<M") ||
            eachLine.contains("<I") || (eachLine.contains(">")
            && !eachLine.contains(" > 0")) && !eachLine.contains("$ErrorItem -> Message")) {
      throw new RuntimeException("< or > is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);

    }

  }
}
