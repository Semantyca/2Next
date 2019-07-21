package com.toonext.localization;

import com.toonext.domain.AppEnv;

import com.toonext.localization.constants.LanguageCode;
import com.toonext.log.Lg;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class Vocabulary {

    public HashMap<String, Sentence> words;

    public Vocabulary() {


    }

    public Vocabulary(AppEnv env) {

    }

    @Deprecated
    public Vocabulary(String appName) {
        this.words = new HashMap<String, Sentence>();
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    public Vocabulary(Document doc, String appName, HashMap<String, Sentence> w) {
        this.words = (HashMap<String, Sentence>) w.clone();
        org.w3c.dom.Element root = doc.getDocumentElement();

        NodeList nodename = root.getElementsByTagName("sentence");
        for (int i = 0; i < nodename.getLength(); i++) {
            Node keyword = nodename.item(i);
            Sentence sentence = new Sentence(keyword);
            words.put(sentence.keyword, sentence);
        }
    }

    public String getWord(String keyword, LanguageCode lang) {
        try {
            Sentence sent = words.get(keyword);
            if (sent == null) {
                Lg.warning("Translation of word \"" + keyword + "\" to " + lang + ", has not found in vocabulary");
                return keyword;
            } else {
                return sent.words.get(lang);
            }
        } catch (Exception e) {
            return keyword;
        }
    }

}
