package coden.smarttranslate.controllers.reverso.context;

import java.util.List;

public class ReversoContextSentence {

    private final String sentence;
    private final List<ReversoContextHighlight> highlights;

    public ReversoContextSentence(String sentence, List<ReversoContextHighlight> highlights) {
        this.sentence = sentence;
        this.highlights = highlights;
    }

    public String getSentence() {
        return sentence;
    }

    public List<ReversoContextHighlight> getHighlights() {
        return highlights;
    }
}
