package coden.smarttranslate.controllers.reverso.data;

import coden.reverso.data.context.ReversoContext;
import coden.reverso.language.ReversoLanguage;

import java.util.List;

public class ReversoContextResponse {
    private ReversoLanguage targetLanguage;
    private ReversoLanguage sourceLanguage;

    private String url;
    private String originalPhrase;
    private List<ReversoContext> contexts;

    public ReversoContextResponse(ReversoRequest request) {
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

    public List<ReversoContext> getContexts() {
        return contexts;
    }

    public void setContexts(List<ReversoContext> contexts) {
        this.contexts = contexts;
    }
}
