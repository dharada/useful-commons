package org.elasticsearch.analysis.common;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ja.JapaneseNumberFilterFactory;
import org.apache.lucene.analysis.ja.JapaneseTokenizerFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class SynonymTokenFilterTests {

    @Test
    public void testSynonymTokenFilter() {

        Map<String, String> settigMap = new HashMap<>();
        String userDictionaryKey = "userDictionary";
        settigMap.put(userDictionaryKey, "/home/daisuke/d/7.3.2/elasticsearch/config/dict-00571634/vos-ja_user-dict_yh_tmp.txt");

        JapaneseTokenizerFactory japaneseTokenizerFactory = new JapaneseTokenizerFactory(settigMap);
        Tokenizer tokenizer = japaneseTokenizerFactory.create();

        String myInput = "スーピマコットンストレッチシャツ";
        tokenizer.setReader(new StringReader(myInput));





    }

}
