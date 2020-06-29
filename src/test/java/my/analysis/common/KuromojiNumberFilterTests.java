package my.analysis.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseNumberFilterFactory;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class KuromojiNumberFilterTests {

    @Test
    public void testNumberFilter() throws IOException {

        String myInput = "三十一";
        myInput = "百二十";
        myInput = "五十三十一";

        Tokenizer tokenizer = new NGramTokenizer(2, 2);
        tokenizer.setReader(new StringReader(myInput));

        JapaneseNumberFilterFactory japaneseNumberFilterFactory = new JapaneseNumberFilterFactory(new HashMap<>());
        TokenStream tokenStream = japaneseNumberFilterFactory.create(tokenizer);

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
