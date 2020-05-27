package org.elasticsearch.analysis.common;

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


//        final Index index = new Index("kuromoji_sample", "_na_");
//        final String name = "ngram_tokenizer";
//        final Settings indexSettings = newAnalysisSettingsBuilder().build();
//        Settings settings = newAnalysisSettingsBuilder().put("min_gram", 2).put("max_gram", 3).put("token_chars", "letter,digit,whitespace,punctuation,symbol").build();
//        NGramTokenizerFactory nGramTokenizerFactory = new NGramTokenizerFactory(KuromojiNumberFilterTests.newIndexSettings(index, indexSettings, Settings.EMPTY), null, name, settings);
//        CustomAnalyzer customAnalyzer = new CustomAnalyzer(nGramTokenizerFactory, null, new ArrayList<TokenFilterFactory>().add(japaneseNumberFilterFactory));

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

//    public static IndexSettings newIndexSettings(Index index, Settings indexSetting, Settings nodeSettings, Setting<?>... setting) {
//        Settings build = Settings.builder().put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
//                .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, 1)
//                .put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, 1)
//                .put(indexSetting)
//                .build();
//        IndexMetaData metaData = IndexMetaData.builder(index.getName()).settings(build).build();
//        Set<Setting<?>> settingSet = new HashSet<>(IndexScopedSettings.BUILT_IN_INDEX_SETTINGS);
//        if (setting.length > 0) {
//            settingSet.addAll(Arrays.asList(setting));
//        }
//        return new IndexSettings(metaData, nodeSettings, new IndexScopedSettings(Settings.EMPTY, settingSet));
//    }
//
//    public Settings.Builder newAnalysisSettingsBuilder() {
//        return Settings.builder().put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT);
//    }

}
