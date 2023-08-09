package json;

//import com.googlecode.json-sample

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
    new JsonParsing().parse("");
  }

  public void parse(String text) throws IOException {

    File file1 = new File("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule");
    if (file1.isDirectory()) {
      Predicate<File> predicate = new Predicate<File>() {
        @Override
        public boolean test(File o) {
          return o.toString().endsWith(".txt");
        }
      };
      Iterator<File> fileIterator = Arrays.stream(file1.listFiles()).filter(predicate).iterator();

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

    String filePath = "/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/connector-rule/OpenText-WSAO-AfterGetUser.txt";
    Path file = Paths.get(filePath);
    List<String> fileStringWithLF = Files.readAllLines(file);
    for (int i = 0; i < fileStringWithLF.size(); i++) {
      checkIllegalCharacters(eachLine(fileStringWithLF, i), file);
    }
    String fileStrWithCRLF = Files.readString(file).replaceAll("\n", "\r\n");
    System.out.println(JSONObject.toString("script", fileStrWithCRLF));
  }

  private String eachLine(List<String> fileStringWithLF, int i) {
    return fileStringWithLF.get(i);
  }

  private static void checkIllegalCharacters(String eachLine, Path file) throws IOException {
    if (eachLine.contains("static")) {
      throw new RuntimeException("static is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);
    }

    if (eachLine.contains("<A") || eachLine.contains("<S") || eachLine.contains("<M") || eachLine.contains("<I") || (eachLine.contains(">") && !eachLine.contains(" > 0"))) {
      throw new RuntimeException("< or > is included in the connector-rule impl.(file path=" + file.toAbsolutePath() + ")" + eachLine);
    }

  }
}
