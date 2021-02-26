package coden.smarttranslate.controllers.reverso.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReversoContextResponse {
    @JsonProperty("list")
    private List<Context> list;

    public ReversoContextResponse() {
    }

    public List<Context> getList() {
        return list;
    }

    public void setList(List<Context> list) {
        this.list = list;
    }

    public static class Context {
        @JsonProperty("s_text")
        private String sourceSentence;
        @JsonProperty("t_text")
        private String targetSentence;

        public String getSourceSentence() {
            return sourceSentence;
        }

        public String getTargetSentence() {
            return targetSentence;
        }

        public void setSourceSentence(String sourceSentence) {
            this.sourceSentence = sourceSentence;
        }

        public void setTargetSentence(String targetSentence) {
            this.targetSentence = targetSentence;
        }
    }
}
