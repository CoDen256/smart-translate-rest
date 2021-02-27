package coden.smarttranslate.controllers.reverso.translation;

import coden.smarttranslate.core.Language;

import java.util.List;

public class ReversoTranslationResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<ReversoTranslation> reversoTranslations;

    public ReversoTranslationResponse(String originalPhrase) {
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

    public List<ReversoTranslation> getTranslations() {
        return reversoTranslations;
    }

    public void setTranslations(List<ReversoTranslation> reversoTranslations) {
        this.reversoTranslations = reversoTranslations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
