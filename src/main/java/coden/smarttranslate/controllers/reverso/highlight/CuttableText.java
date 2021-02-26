package coden.smarttranslate.controllers.reverso.highlight;

import java.util.LinkedList;
import java.util.List;

public class CuttableText {
    private String text;
    private final List<Integer> cuts = new LinkedList<>();

    public CuttableText(String originalString) {
        this.text = originalString;

    }
    public String getText() {
        return text;
    }

    public void cut(String substring) {
        cuts.add(text.indexOf(substring));
        text = text.replaceFirst(substring, "");
    }

    public void cutAll(String regex) {
        text = text.replaceAll(regex, "");
    }

    public List<Integer> getCutPoints() {
        return cuts;
    }
}
