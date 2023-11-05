package com.firedeluxe.learnenglish;

import java.io.Serializable;
import java.util.Arrays;

public class Word implements Serializable {
    static int now = 0; // now = number of words
    private int _id;
    private String original;
    private String translate;
    private String knowledge; //0 - uncheck, 1 - bad, 2 - yellow, 3 - well done

    public Word() {
    }

    public Word(String original, String translate) {
        this.original = original;
        this.translate = translate;
        this.knowledge = "0";
        _id = now;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "original='" + original + '\'' +
                ", translate='" + translate + '\'' +
                ", knowledge=" + knowledge +
                '}';
    }

}
