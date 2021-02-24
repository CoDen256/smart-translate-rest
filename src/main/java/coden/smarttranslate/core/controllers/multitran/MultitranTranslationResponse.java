package coden.smarttranslate.core.controllers.multitran;

import coden.smarttranslate.core.controllers.Language;

import java.util.List;

public class MultitranTranslationResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<String> translation;

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

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslations(List<String> translations) {
        this.translation = translations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
