package coden.smarttranslate.controllers.reverso.data;

import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.language.ReversoLanguage;

import java.util.List;

public class ReversoTranslationResponse {
    private ReversoLanguage targetLanguage;
    private ReversoLanguage sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<ReversoTranslation> contexts;

    public ReversoTranslationResponse(ReversoRequest request) {
        setOriginalPhrase(request.getPhrase());
        setSourceLanguage(request.getSourceLanguage());
        setTargetLanguage(request.getTargetLanguage());
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalPhrase() {
        return originalPhrase;
    }

    public void setOriginalPhrase(String originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

    public List<ReversoTranslation> getContexts() {
        return contexts;
    }

    public void setContexts(List<ReversoTranslation> contexts) {
        this.contexts = contexts;
    }
}
