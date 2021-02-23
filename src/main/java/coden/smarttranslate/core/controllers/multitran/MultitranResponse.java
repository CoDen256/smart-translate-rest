package coden.smarttranslate.core.controllers.multitran;

import coden.smarttranslate.core.controllers.Language;

import java.util.List;

public class MultitranResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<String> translatedPhrase;

    public MultitranResponse(String originalPhrase) {
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

    public List<String> getTranslatedPhrase() {
        return translatedPhrase;
    }

    public void setTranslatedPhrase(List<String> translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
