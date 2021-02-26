package coden.smarttranslate.controllers.reverso.data;

import java.util.List;

public class ContextSentence {

    private final String sentence;
    private final List<ContextHighlight> highlights;

    public ContextSentence(String sentence, List<ContextHighlight> highlights) {
        this.sentence = sentence;
        this.highlights = highlights;
    }

    public String getSentence() {
        return sentence;
    }

    public List<ContextHighlight> getHighlights() {
        return highlights;
    }
}
