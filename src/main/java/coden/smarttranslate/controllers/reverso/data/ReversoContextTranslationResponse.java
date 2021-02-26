package coden.smarttranslate.controllers.reverso.data;

import coden.smarttranslate.core.Language;

import java.util.List;

public class ReversoContextTranslationResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<ContextTranslation> contextTranslations;

    public ReversoContextTranslationResponse(String originalPhrase) {
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

    public List<ContextTranslation> getContextTranslations() {
        return contextTranslations;
    }

    public void setContextTranslations(List<ContextTranslation> contextTranslations) {
        this.contextTranslations = contextTranslations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
