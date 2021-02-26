package coden.smarttranslate.controllers.reverso.context;

import coden.smarttranslate.controllers.reverso.highlight.CuttableText;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public static ReversoContextSentence fromCutPoints(String sentence, List<Integer> cutPoints){
        List<ReversoContextHighlight> highlightList = IntStream.range(0, cutPoints.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(n -> new ReversoContextHighlight(cutPoints.get(n), cutPoints.get(n + 1)))
                .collect(Collectors.toList());
        return new ReversoContextSentence(sentence, highlightList);
    }
}
