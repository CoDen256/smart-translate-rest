package coden.smarttranslate.controllers.wiktionary;

import coden.smarttranslate.core.Language;

public class WiktionaryDefinitionRequest {

    private Language targetLanguage;
    private Language sourceLanguage;

    private String phrase;

    public Language getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(Language targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public Language getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(Language sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
