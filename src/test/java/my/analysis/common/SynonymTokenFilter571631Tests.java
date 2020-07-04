package my.analysis.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseTokenizer;
import org.apache.lucene.analysis.ja.dict.UserDictionary;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
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

public class SynonymTokenFilter571631Tests {

    @Test
    public void testSynonymTokenFilter() throws IOException {

        JapaneseTokenizer tokenizer =
                new JapaneseTokenizer(
                        UserDictionary.open(
                                new FileReader(new File("/mnt/buffalo/daisuke/github/dharada/useful-commons/src/test/resources/571631_vos-ja_user-dict_yh_tmp.txt"))), true, JapaneseTokenizer.DEFAULT_MODE);

        TokenStream tokenStream = getTokenStreamForSynonymTokenFilter(tokenizer);

        OffsetAttribute offsetAtt = tokenStream.addAttribute(OffsetAttribute.class);
        CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);


        try {
            tokenStream.reset(); // Resets this stream to the beginning. (Required)

            while (tokenStream.incrementToken()) {
                // Use AttributeSource.reflectAsString(boolean)
                // for token stream debugging.

                outs(tokenStream, offsetAtt, charTermAttr, positionIncrementAttribute);

            }

            tokenStream.end();   // Perform end-of-stream operations, e.g. set the final offset.

        } finally {
            tokenStream.close(); // Release resources associated with this stream.
        }

    }

    private void outs(TokenStream tokenStream, OffsetAttribute offsetAtt, CharTermAttribute charTermAttr, PositionIncrementAttribute positionIncrementAttribute) {

        String token = charTermAttr.toString();
//        String positionIncrementAttr = positionIncrementAttribute.toString();


//        System.out.println("positionIncrementAttribute: " + positionIncrementAttr);

        Iterator<AttributeImpl> attributeImplsIterator = tokenStream.getAttributeImplsIterator();
//        System.out.println("token: " + tokenStream.reflectAsString(true));
        System.out.println("token: " + token);
        System.out.println("token start offset: " + offsetAtt.startOffset());
        System.out.println("token end offset: " + offsetAtt.endOffset() + "\n");

    }

    private TokenStream getTokenStreamForSynonymTokenFilter(Tokenizer tokenizer) throws IOException {

        String myInput = "子供服";
        tokenizer.setReader(new StringReader(myInput));

        Map<String, String> synonymsSettingMap = new HashMap<>();
        synonymsSettingMap.put("synonyms", "571631_vos-ja_contract-synonym_yh_tmp.txt");
        SynonymFilterFactory synonymFilterFactory = new SynonymFilterFactory(synonymsSettingMap);
        synonymFilterFactory.inform(new ClasspathResourceLoader());

        return synonymFilterFactory.create(tokenizer);
    }
}
