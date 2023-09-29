package json;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class JsonTxtCleanTool {
  public static void main(String[] args) throws IOException {


    File BPRuleJavadir = new File("/Users/daisuke.harada/github/dharada/jdbc-provision-quickstart/src/main/java/beforeProvisioningRules");
    if (BPRuleJavadir.isDirectory()) {
      Predicate<File> predicate = new Predicate<File>() {
        @Override
        public boolean test(File o) {
          return o.toString().endsWith(".txt");
        }
      };
      Iterator<File> fileIterator = Arrays.stream(Objects.requireNonNull(BPRuleJavadir.listFiles())).filter(predicate).iterator();

      while (fileIterator.hasNext()) {

        Path path = Paths.get(fileIterator.next().getAbsolutePath());
        System.out.println(path.toAbsolutePath());

        if (path.toAbsolutePath().toString().contains("JST")) {
          FileUtils.forceDeleteOnExit(new File(path.toAbsolutePath().toString()));
        }

      }
    }

  }
}
