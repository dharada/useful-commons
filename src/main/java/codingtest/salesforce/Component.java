package codingtest.salesforce;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class Component {

    public String key;

    // 参照左記cmpを管理する為のMap
    private Map<String, Component> dependencies;

    // 参照元cmpを管理する為のMap
    private Map<String, Component> referenced;

    /**
     * <ul>
     * <li>何かの依存関係としてinstallされたCmpの場合は、false</li>
     * <li>RootInstallされた場合は、true</li>
     * </ul>
     *
     */
    public boolean isRootInstall;

    public void Component(String key) {
        this.key = key;
        dependencies = new LinkedHashMap<>();
        referenced = new LinkedHashMap<>();
    }

    public void addDependency(Component cmp) {
        dependencies.put(cmp.key, cmp);
    }

    public boolean isDepend(String key) {
        return dependencies.containsKey(key);
    }

    // 参照されているか
    public boolean isReferenced() {
        return !referenced.isEmpty();
    }

}
