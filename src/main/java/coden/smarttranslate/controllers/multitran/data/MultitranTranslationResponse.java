package coden.smarttranslate.controllers.multitran.data;

import coden.multitran.language.MultitranLanguage;
import coden.multitran.translation.MultitranTranslation;

import java.util.List;

public class MultitranTranslationResponse {
    private MultitranLanguage targetLanguage;
    private MultitranLanguage sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<MultitranTranslation> multitranTranslations;

    public MultitranTranslationResponse(MultitranRequest request) {
        setOriginalPhrase(request.getPhrase());
        setSourceLanguage(request.getSourceLanguage());
        setTargetLanguage(request.getTargetLanguage());
    }

    public MultitranTranslationResponse(String originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

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
