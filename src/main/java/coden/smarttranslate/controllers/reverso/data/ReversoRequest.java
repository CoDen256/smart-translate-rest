package coden.smarttranslate.controllers.reverso.data;

import coden.reverso.language.ReversoLanguage;

public class ReversoRequest {

    private ReversoLanguage targetLanguage;
    private ReversoLanguage sourceLanguage;

    private String phrase;

    public ReversoLanguage getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(ReversoLanguage targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public ReversoLanguage getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(ReversoLanguage sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
