package coden.smarttranslate.controllers.reverso.highlight;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class HighlightsExtractor {

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";
    public static final Pattern LINKS_PATTERN = Pattern.compile("</?a[^>]*>");
    
    public HighlightedText extract(String text){
        CuttableText innerText = new CuttableText(text);
        List<Pair<Integer, Integer>> highlights = new LinkedList<>();

        removeLinks(innerText);
        while (isHighlighted(innerText.getText())){
            highlights.add(findNextHighlight(innerText));
        }
        
        return new HighlightedText(innerText.getText(), highlights);
    }
    
    private Pair<Integer, Integer> findNextHighlight(CuttableText innerText) {
        int startIndex = innerText.cut(HIGHLIGHT_TAG_OPEN);
        int endIndex = innerText.cut(HIGHLIGHT_TAG_CLOSE);
        return Pair.of(startIndex, endIndex);
    }

    private void removeLinks(CuttableText innerText) {
        innerText.removeAll(LINKS_PATTERN.pattern());
    }

    private boolean isHighlighted(String sentence){
        return sentence.contains(HIGHLIGHT_TAG_OPEN) || sentence.contains(HIGHLIGHT_TAG_CLOSE);
    }

}
