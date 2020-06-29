package my.analysis.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseNumberFilterFactory;
import org.apache.lucene.analysis.ja.JapaneseTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SynonymTokenFilterTests {

    @Test
    public void testSynonymTokenFilter() throws IOException {

        Map<String, String> settigMap = new HashMap<>();
        String userDictionaryKey = "userDictionary";
        settigMap.put(userDictionaryKey, "vos-ja_user-dict_yh_tmp.txt");

        JapaneseTokenizerFactory japaneseTokenizerFactory = new JapaneseTokenizerFactory(settigMap);
        Tokenizer tokenizer = japaneseTokenizerFactory.create();

        String myInput = "スーピマコットンストレッチシャツ";
        tokenizer.setReader(new StringReader(myInput));

        Map<String, String> synonymsSettingMap = new HashMap<>();
        synonymsSettingMap.put("synonyms", "vos-ja_contract-synonym_yh_tmp.txt");

        SynonymFilterFactory synonymFilterFactory = new SynonymFilterFactory(synonymsSettingMap);

        ResourceLoader loader = new ClasspathResourceLoader();
        synonymFilterFactory.inform(loader);

        TokenStream tokenStream = synonymFilterFactory.create(tokenizer);

        OffsetAttribute offsetAtt = tokenStream.addAttribute(OffsetAttribute.class);
        try {
            tokenStream.reset(); // Resets this stream to the beginning. (Required)

            while (tokenStream.incrementToken()) {
                // Use AttributeSource.reflectAsString(boolean)
                // for token stream debugging.
                System.out.println("token: " + tokenStream.reflectAsString(true));

                System.out.println("token start offset: " + offsetAtt.startOffset());
                System.out.println("token end offset: " + offsetAtt.endOffset());
            }

            tokenStream.end();   // Perform end-of-stream operations, e.g. set the final offset.

        } finally {
            tokenStream.close(); // Release resources associated with this stream.
        }

    }

}
