package coden.smarttranslate.controllers.multitran.translation;

import coden.smarttranslate.core.Language;

import java.util.List;

public class MultitranTranslationResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<MultitranTranslation> multitranTranslations;

    public MultitranTranslationResponse(String originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

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

    public String getOriginalPhrase() {
        return originalPhrase;
    }

    public void setOriginalPhrase(String originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

    public List<MultitranTranslation> getTranslations() {
        return multitranTranslations;
    }

    public void setTranslations(List<MultitranTranslation> multitranTranslations) {
        this.multitranTranslations = multitranTranslations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
