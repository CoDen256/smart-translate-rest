package coden.smarttranslate.controllers.reverso.crawler;

import coden.smarttranslate.core.Language;
import coden.smarttranslate.controllers.reverso.data.ContextHighlight;
import coden.smarttranslate.controllers.reverso.data.ContextSentence;
import coden.smarttranslate.controllers.reverso.data.ContextTranslation;
import org.apache.commons.text.StringSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReversoCrawler {

    private static final String API = "https://context.reverso.net/translation/{source}-{target}/{phrase}";
    private static final Map<Language, String> languages = Map.of(Language.EN, "english", Language.RU, "russian", Language.DE, "german");

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";
    public static final Pattern LINKS_PATTERN = Pattern.compile("</?a[^>]*>");


    public List<ContextTranslation> parseContextTranslation(Language source, Language target, String phrase) throws IOException {
        String url = getUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentContext(document);
    }

    private List<ContextTranslation> parseDocumentContext(Document document){
        return document.getElementById("examples-content")
                .getElementsByClass("example")
                .stream()
                .map(this::extractContext)
                .collect(Collectors.toList());
    }


    private ContextTranslation extractContext(Element exampleElement){
        ContextSentence srcSentence = extractContextSentence(exampleElement, "src");
        ContextSentence trgSentence = extractContextSentence(exampleElement, "trg");

        return new ContextTranslation(srcSentence, trgSentence);
    }

    private ContextSentence extractContextSentence(Element element, String className){
        return extractHighlights(element.getElementsByClass(className)
                .first()
                .getElementsByClass("text")
                .first()
                .html());
    }

    private ContextSentence extractHighlights(String html) {
        TruncatedText innerText = new TruncatedText(html);
        List<ContextHighlight> highlights = new LinkedList<>();

        removeLinks(innerText);
        while (isHighlighted(innerText.getText())){
            highlights.add(findNextHighlight(innerText));
        }
        return new ContextSentence(innerText.getText(), highlights);
    }

    private ContextHighlight findNextHighlight(TruncatedText innerText) {
        int startIndex = innerText.truncate(HIGHLIGHT_TAG_OPEN);
        int endIndex = innerText.truncate(HIGHLIGHT_TAG_CLOSE);
        return new ContextHighlight(startIndex, endIndex);
    }

    private void removeLinks(TruncatedText innerText) {
        innerText.removeAll(LINKS_PATTERN.pattern());
    }

    private boolean isHighlighted(String sentence){
        return sentence.contains(HIGHLIGHT_TAG_OPEN) || sentence.contains(HIGHLIGHT_TAG_CLOSE);
    }

    public String getUrl(Language source, Language target, String phrase){
        return StringSubstitutor.replace(API, Map.of(
                "phrase", phrase,
                "source", resolveLanguage(source),
                "target", resolveLanguage(target)), "{", "}");
    }

    private String resolveLanguage(Language language){
        return languages.get(language);
    }

    private static class TruncatedText {
        private String text;

        public TruncatedText(String originalString) {
            this.text = originalString;
        }

        public String getText() {
            return text;
        }

        public int truncate(String substring){
            int index = text.indexOf(substring);
            text = text.replaceFirst(substring, "");
            return index;
        }

        public void removeAll(String regex){
            text = text.replaceAll(regex, "");
        }
    }
}
