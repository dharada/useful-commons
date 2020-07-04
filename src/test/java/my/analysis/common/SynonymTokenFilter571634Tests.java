package my.analysis.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseTokenizer;
import org.apache.lucene.analysis.ja.dict.UserDictionary;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.util.AttributeImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SynonymTokenFilter571634Tests {

    @Test
    public void testSynonymTokenFilter() throws IOException {

        TokenStream tokenStream = getTokenStreamForSynonymTokenFilter(getJapaneseTokenizer());
        OffsetAttribute offsetAtt = tokenStream.addAttribute(OffsetAttribute.class);
        outsTokens(tokenStream, offsetAtt);

    }

    private void outsTokens(TokenStream tokenStream, OffsetAttribute offsetAtt) throws IOException {
        try {
            tokenStream.reset(); // Resets this stream to the beginning. (Required)
            while (tokenStream.incrementToken()) {
                outs(tokenStream, offsetAtt);
            }
            tokenStream.end();   // Perform end-of-stream operations, e.g. set the final offset.
        } finally {
            tokenStream.close(); // Release resources associated with this stream.
        }
    }

    private JapaneseTokenizer getJapaneseTokenizer() throws IOException {
        return new JapaneseTokenizer(UserDictionary.open(new FileReader(new File("/mnt/buffalo/daisuke/github/dharada/useful-commons/src/test/resources/vos-ja_user-dict_yh_tmp.txt"))), true, JapaneseTokenizer.Mode.SEARCH);
    }

    private void outs(TokenStream tokenStream, OffsetAttribute offsetAtt) {
        Iterator<AttributeImpl> attributeImplsIterator = tokenStream.getAttributeImplsIterator();
        System.out.println("token: " + tokenStream.reflectAsString(true));
        System.out.println("token start offset: " + offsetAtt.startOffset());
        System.out.println("token end offset: " + offsetAtt.endOffset() + "\n");
    }

    private TokenStream getTokenStreamForSynonymTokenFilter(Tokenizer tokenizer) throws IOException {

        String myInput = "スーピマコットンストレッチシャツ";
        tokenizer.setReader(new StringReader(myInput));

        Map<String, String> synonymsSettingMap = new HashMap<>();
        synonymsSettingMap.put("synonyms", "vos-ja_contract-synonym_yh_tmp.txt");
        SynonymFilterFactory synonymFilterFactory = new SynonymFilterFactory(synonymsSettingMap);
        synonymFilterFactory.inform(new ClasspathResourceLoader());

        return synonymFilterFactory.create(tokenizer);
    }
}
