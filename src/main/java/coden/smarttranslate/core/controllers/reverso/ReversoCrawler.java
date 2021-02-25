package coden.smarttranslate.core.controllers.reverso;

import static coden.smarttranslate.core.controllers.Language.DE;
import static coden.smarttranslate.core.controllers.Language.EN;
import static coden.smarttranslate.core.controllers.Language.RU;

import coden.smarttranslate.core.controllers.Language;
import coden.smarttranslate.core.controllers.reverso.data.ContextHighlight;
import coden.smarttranslate.core.controllers.reverso.data.ContextSentence;
import coden.smarttranslate.core.controllers.reverso.data.ContextTranslation;
import org.apache.commons.text.StringSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReversoCrawler {

    private static final String API = "https://context.reverso.net/translation/{source}-{target}/{phrase}";
    private static final Map<Language, String> languages = Map.of(EN, "english", RU, "russian", DE, "german");

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";


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

    private ContextSentence extractContextSentence(Element parentElement, String className){
        Element spanElement = parentElement.getElementsByClass(className)
                .get(0)
                .getElementsByClass("text")
                .get(0);

        String html = spanElement.html();
        List<ContextHighlight> highlights = extractHighlights(html);
        return new ContextSentence(html, highlights);
    }

    private List<ContextHighlight> extractHighlights(String html) {
        TruncatedText innerText = new TruncatedText(html);
        List<ContextHighlight> highlights = new LinkedList<>();

        innerText.removeAll("</?a[^>]*>");

        while (isHighlighted(innerText.getText())){
            int startIndex = innerText.truncate(HIGHLIGHT_TAG_OPEN);
            int endIndex = innerText.truncate(HIGHLIGHT_TAG_CLOSE);
            highlights.add(new ContextHighlight(startIndex, endIndex));
        }

        return highlights;
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
