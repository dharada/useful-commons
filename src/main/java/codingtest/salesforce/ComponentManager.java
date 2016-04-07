package codingtest.salesforce;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class ComponentManager {

    public void ComponentManager() {
    }

    private static Map<String, Component> dependencyMap = new LinkedHashMap<>();

    private static Map<String, Component> installMap = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {

        for (String line : lines()) {

            final String[] words = line.split("\\s");

            switch (words[0]) {

                case "DEPEND":

                    System.out.println(line);

                    final Component cmp = new Component(words[1]);

                    if (words.length > 1) {
                        for (int i = 2; i < words.length; i++) {
                            cmp.addDependency(new Component(words[i]));
                        }
                    }

                    dependencyMap.put(cmp.key, cmp);

                    break;

                case "INSTALL":

                    System.out.println(line);

                    Component rootInstallCmp = new Component(words[1]);
                    rootInstallCmp.isRootInstall = true;

                    if (dependencyMap.containsKey(rootInstallCmp.key)) {

                        rootInstallCmp = dependencyMap.get(rootInstallCmp.key);
                        rootInstallCmp.isRootInstall = true;

                        rootInstallCmp.setDependencyInstalled();
                    }

                    installMap.put(rootInstallCmp.key, rootInstallCmp);

                    break;

                case "REMOVE":

                    break;

                case "LIST":

                    break;

                case "END":

                    break;

                default:

                    break;

            }

        }

    }

    private static List<String> lines() throws IOException, URISyntaxException {
        return FileUtils.readLines(new File(
                URLClassLoader.class.cast(ComponentManager.class.getClassLoader()).findResource("prog.dat").toURI()));
    }

}
