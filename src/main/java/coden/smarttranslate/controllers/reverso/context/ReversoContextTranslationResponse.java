package coden.smarttranslate.controllers.reverso.context;

import coden.smarttranslate.core.Language;

import java.util.List;
// TODO: do we need setters?
public class ReversoContextTranslationResponse {
    private Language targetLanguage;
    private Language sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<ReversoContextTranslation> reversoContextTranslations;

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

    public List<ReversoContextTranslation> getContextTranslations() {
        return reversoContextTranslations;
    }

    public void setContextTranslations(List<ReversoContextTranslation> reversoContextTranslations) {
        this.reversoContextTranslations = reversoContextTranslations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
