package my.analysis.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.util.AttributeImpl;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class SynonymTokenFilter571631Tests {

    @Test
    public void testSynonymTokenFilter() throws IOException {

        JapaneseTokenizerFactory japaneseTokenizerFactory = getJapaneseTokenizerFactory();

        TokenStream tokenStream = getTokenStreamForSynonymTokenFilter(japaneseTokenizerFactory);

//        LowerCaseFilterFactory lowerCaseFilterFactory = new LowerCaseFilterFactory(new HashMap<>());
//        TokenStream tokenStream = lowerCaseFilterFactory.create(tokenizer);

        OffsetAttribute offsetAtt = tokenStream.addAttribute(OffsetAttribute.class);

        try {
            tokenStream.reset(); // Resets this stream to the beginning. (Required)

            while (tokenStream.incrementToken()) {
                // Use AttributeSource.reflectAsString(boolean)
                // for token stream debugging.

                outs(tokenStream, offsetAtt);

            }

            tokenStream.end();   // Perform end-of-stream operations, e.g. set the final offset.

        } finally {
            tokenStream.close(); // Release resources associated with this stream.
        }

    }

    private void outs(TokenStream tokenStream, OffsetAttribute offsetAtt) {

        Iterator<AttributeImpl> attributeImplsIterator = tokenStream.getAttributeImplsIterator();

        //        while (attributeImplsIterator.hasNext()) {
//            AttributeImpl next = attributeImplsIterator.next();
////            System.out.println(next.getClass().getName());
//
//            if (next.getClass().equals(org.apache.lucene.analysis.tokenattributes.PackedTokenAttributeImpl.class)){
//                System.out.println(next.toString());
//            }
//
//        }

        System.out.println("token: " + tokenStream.reflectAsString(true));
        System.out.println("token start offset: " + offsetAtt.startOffset());
        System.out.println("token end offset: " + offsetAtt.endOffset());
    }

    private JapaneseTokenizerFactory getJapaneseTokenizerFactory() throws IOException {

        Map<String, String> settingsMapForTokenizer = new HashMap<>();
        String userDictionaryKey = "userDictionary";
        settingsMapForTokenizer.put(userDictionaryKey, "571631_vos-ja_user-dict_yh_tmp.txt");

        JapaneseTokenizerFactory japaneseTokenizerFactory = new JapaneseTokenizerFactory(settingsMapForTokenizer);
        japaneseTokenizerFactory.inform(new ClasspathResourceLoader());

        return japaneseTokenizerFactory;
    }

    private TokenStream getTokenStreamForSynonymTokenFilter(JapaneseTokenizerFactory japaneseTokenizerFactory) throws IOException {

        Tokenizer tokenizer = japaneseTokenizerFactory.create();
        String myInput = "子供服";
        tokenizer.setReader(new StringReader(myInput));

        Map<String, String> synonymsSettingMap = new HashMap<>();
        synonymsSettingMap.put("synonyms", "571631_vos-ja_contract-synonym_yh_tmp.txt");
//        synonymsSettingMap.put("tokenizerFactory", "org.apache.lucene.analysis.ja.JapaneseTokenizerFactory");
        SynonymFilterFactory synonymFilterFactory = new SynonymFilterFactory(synonymsSettingMap);
        synonymFilterFactory.inform(new ClasspathResourceLoader());

        return synonymFilterFactory.create(tokenizer);
    }
}
