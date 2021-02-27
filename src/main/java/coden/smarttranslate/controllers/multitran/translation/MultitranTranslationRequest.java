package coden.smarttranslate.controllers.multitran.translation;

import coden.multitran.language.MultitranLanguage;

public class MultitranTranslationRequest {

    private MultitranLanguage targetLanguage;
    private MultitranLanguage sourceLanguage;

    private String phrase;

    public MultitranLanguage getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(MultitranLanguage targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public MultitranLanguage getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(MultitranLanguage sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
