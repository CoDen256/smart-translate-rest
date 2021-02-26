package coden.smarttranslate.controllers.reverso.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReversoContextRequest {

    @JsonProperty("source_text")
    private String sourceText;

    @JsonProperty("target_text")
    private String targetText;

    @JsonProperty("source_lang")
    private String sourceLang;

    @JsonProperty("target_lang")
    private String targetLang;

    @JsonProperty("npage")
    private int nPage;

    @JsonProperty("mode")
    private int mode;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    public int getnPage() {
        return nPage;
    }

    public void setNPage(int nPage) {
        this.nPage = nPage;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
