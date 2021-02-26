package coden.smarttranslate.controllers.reverso.highlight;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class HighlightedText {
    private final String text;
    private final List<Pair<Integer, Integer>> highlights;

    public HighlightedText(String text, List<Pair<Integer, Integer>> highlights) {
        this.text = text;
        this.highlights = highlights;
    }

    public String getText() {
        return text;
    }

    public List<Pair<Integer, Integer>> getHighlights() {
        return highlights;
    }
}
