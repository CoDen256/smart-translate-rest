package coden.smarttranslate.controllers.reverso.highlight;

public class CuttableText {
    private String text;

    public CuttableText(String originalString) {
        this.text = originalString;
    }

    public String getText() {
        return text;
    }

    public int cut(String substring) {
        int index = text.indexOf(substring);
        text = text.replaceFirst(substring, "");
        return index;
    }

    public void removeAll(String regex) {
        text = text.replaceAll(regex, "");
    }
}
