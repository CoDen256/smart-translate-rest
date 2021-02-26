package coden.smarttranslate.controllers.reverso.highlight;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class HighlightsExtractor {

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";
    public static final Pattern LINKS_PATTERN = Pattern.compile("</?a[^>]*>");
    
    public CuttableText extract(String text){
        CuttableText innerText = new CuttableText(text);

        removeLinks(innerText);
        while (isHighlighted(innerText.getText())){
            cutNextHighlight(innerText);
        }
        
        return innerText;
    }
    
    private void cutNextHighlight(CuttableText innerText) {
        innerText.cut(HIGHLIGHT_TAG_OPEN);
        innerText.cut(HIGHLIGHT_TAG_CLOSE);
    }

    private void removeLinks(CuttableText innerText) {
        innerText.cutAll(LINKS_PATTERN.pattern());
    }

    private boolean isHighlighted(String sentence){
        return sentence.contains(HIGHLIGHT_TAG_OPEN) || sentence.contains(HIGHLIGHT_TAG_CLOSE);
    }

}
