package parse;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class JsonReplaceTest {


    @Test
    @Ignore
    public void testExecute() throws IOException {

//        new JsonReplace().execute("C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/400.json", "C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/out-data.json");
        new JsonReplace().execute("C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/data-utf8.json", "C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/out-data-long.json");
        new JsonReplace().replaceHhtmlsourceField("C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/out-data-long.json", "C:\\Elastic\\app\\6.3.1\\elasticsearch-6.3.1\\json_new/jq-data.json");
    }
}
