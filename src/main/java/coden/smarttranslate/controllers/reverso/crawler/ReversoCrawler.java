package coden.smarttranslate.controllers.reverso.crawler;

import coden.smarttranslate.controllers.reverso.context.ReversoContextProvider;
import coden.smarttranslate.controllers.reverso.context.ReversoContextSentence;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslation;
import coden.smarttranslate.controllers.reverso.highlight.CuttableText;
import coden.smarttranslate.controllers.reverso.highlight.HighlightsExtractor;
import coden.smarttranslate.controllers.reverso.website.ReversoContextUrlProvider;
import coden.smarttranslate.core.Language;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReversoCrawler implements ReversoContextProvider {

    private final ReversoContextUrlProvider urlProvider;
    private final HighlightsExtractor extractor;

    public ReversoCrawler(ReversoContextUrlProvider urlProvider, HighlightsExtractor extractor) {
        this.urlProvider = urlProvider;
        this.extractor = extractor;
    }

    @Override
    public List<ReversoContextTranslation> getContexts(Language source, Language target, String phrase) throws IOException {
        String url = urlProvider.getContextUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentContext(document);
    }

    private List<ReversoContextTranslation> parseDocumentContext(Document document){
        return document.getElementById("examples-content")
                .getElementsByClass("example")
                .stream()
                .map(this::extractContext)
                .collect(Collectors.toList());
    }


    private ReversoContextTranslation extractContext(Element exampleElement){
        ReversoContextSentence srcSentence = extractContextSentence(exampleElement, "src");
        ReversoContextSentence trgSentence = extractContextSentence(exampleElement, "trg");

        return new ReversoContextTranslation(srcSentence, trgSentence);
    }

    private ReversoContextSentence extractContextSentence(Element element, String className){
        return extractHighlights(element.getElementsByClass(className)
                .first()
                .getElementsByClass("text")
                .first()
                .html());
    }

    private ReversoContextSentence extractHighlights(String html) {
        CuttableText extracted = extractor.extract(html);
        return ReversoContextSentence.fromCutPoints(extracted.getText(), extracted.getCutPoints());
    }

}
