package json;

import org.checkerframework.checker.units.qual.A;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

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


    File BPRuleJavadir = new File("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/src/main/java/beforeProvisioningRules");
    if (BPRuleJavadir.isDirectory()) {
      Predicate<File> predicate = new Predicate<File>() {
        @Override
        public boolean test(File o) {
          return o.toString().endsWith(".java");
        }
      };
      Iterator<File> fileIterator = Arrays.stream(Objects.requireNonNull(BPRuleJavadir.listFiles())).filter(predicate).iterator();

      while (fileIterator.hasNext()) {
        Path path = Paths.get(fileIterator.next().getAbsolutePath());

        StringBuilder newfileContents = new StringBuilder();

        System.out.println(path.toAbsolutePath());

        Iterator<String> lineIte = Files.readAllLines(path).iterator();
        while (lineIte.hasNext()) {
          String eachLine = lineIte.next();
          if (eachLine.contains("List<AccountRequest>")) {
            eachLine = eachLine.replace("List<AccountRequest>", "List");
          } else if (eachLine.contains("List<ProvisioningPlan.AttributeRequest>")) {
            eachLine = eachLine.replace("List<ProvisioningPlan.AttributeRequest>", "List");
          } else if (eachLine.contains("List<String>")) {
            eachLine = eachLine.replace("List<String>", "List");
          }

          checkIllegalCharacters(eachLine, path);

          newfileContents.append(eachLine + "\n");

        }

        try {
          String creatingFile = path.toAbsolutePath() + new Date().toString() + ".txt";
          FileWriter writer = new FileWriter(creatingFile);
          writer.write(newfileContents.toString());
          writer.close();
          System.out.println("Successfully wrote to the file." + creatingFile);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }


        if (false) {
          System.out.println(path.toAbsolutePath() + " :check DONE.OK");
        }

      }
    }


//    Path file1 = Paths.get("/Users/daisuke.harada/github/daisuke-harada-sp/identitynow-services-config/Fujitsu/oneid-dev-sb1.identitynow.com/rules/Rule - BeforeProvisioning - Delete BillingIntegrationSystem account.xml");
//    Path file2 = Paths.get("/Users/daisuke.harada/github/daisuke-harada-sp/identitynow-services-config/Fujitsu/oneid-dev-sb1.identitynow.com/rules/Rule - BeforeProvisioning - Delete DetailArrangementSystem account.xml");
//
//    List files = new ArrayList();
//    files.add(file1);
//    files.add(file2);
//
//    Iterator<Path> iterator = files.iterator();
//    while (iterator.hasNext()) {
//      Path next = iterator.next();
//      List<String> fileStringWithLF = Files.readAllLines(next);
//      for (int i = 0; i < fileStringWithLF.size(); i++) {
//        checkIllegalCharacters(eachLine(fileStringWithLF, i), next);
//      }
//    }


  }


  private String eachLine(List<String> fileStringWithLF, int i) {
    return fileStringWithLF.get(i);
  }

  private static void checkIllegalCharacters(String eachLine, Path file) throws IOException {

    List ignoreList = new ArrayList<String>();
    ignoreList.add("static ProvisioningPlan plan");
    ignoreList.add("static Logger log");
    ignoreList.add("static Application application");
    ignoreList.add("public static void main");
    ignoreList.add("static sailpoint.object.ProvisioningPlan plan");

    boolean isIgnore = false;
    Iterator<String> iterator = ignoreList.stream().iterator();
    while (iterator.hasNext()) {
      String ignorePhrase = iterator.next();
      if (eachLine.contains(ignorePhrase)) {
        isIgnore = true;
      }
    }


    if (eachLine.contains("static") && !isIgnore) {
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
