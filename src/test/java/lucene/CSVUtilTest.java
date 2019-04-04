package lucene;

import org.apache.lucene.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVUtilTest {

    @Test
    public void testParse() {

        String[] parse = CSVUtil.parse("\"単語1,\"\"\", 単語2");

        System.out.println("parse.length="+ parse.length);


        Arrays.asList(parse).forEach( word  -> {

            System.out.println(word.toString());

        });

    }
}
