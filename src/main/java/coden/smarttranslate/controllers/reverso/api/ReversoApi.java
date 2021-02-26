package coden.smarttranslate.controllers.reverso.api;

import coden.smarttranslate.controllers.reverso.context.ReversoContextProvider;
import coden.smarttranslate.controllers.reverso.context.ReversoContextSentence;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslation;
import coden.smarttranslate.controllers.reverso.highlight.CuttableText;
import coden.smarttranslate.controllers.reverso.highlight.HighlightsExtractor;
import coden.smarttranslate.controllers.reverso.language.ReversoLanguageResolver;
import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class ReversoApi implements ReversoContextProvider {

    private static final String API = "https://context.reverso.net/bst-query-service";
    private final ReversoLanguageResolver resolver;
    private final WebClient client;
    private final HighlightsExtractor extractor;

    public ReversoApi(@Qualifier("API") ReversoLanguageResolver resolver, HighlightsExtractor extractor) {
        client = WebClient.create(API);
        this.resolver = resolver;
        this.extractor = extractor;
    }

    // TODO: may be not block, and web client via config
    @Override
    public List<ReversoContextTranslation> getContextTranslations(Language source, Language target, String phrase) throws Exception {
        return client.post()
                .bodyValue(createRequest(source, target, phrase))
                .retrieve()
                .bodyToMono(ReversoContextResponse.class)
                .block()
                .getList()
                .stream()
                .map(this::mapToContextTranslations)
                .collect(Collectors.toList());
    }

    private ReversoContextRequest createRequest(Language source, Language target, String phrase){
        ReversoContextRequest request = new ReversoContextRequest();
        request.setMode(0);
        request.setNPage(1);
        request.setSourceLang(resolver.resolve(source));
        request.setTargetLang(resolver.resolve(target));
        request.setSourceText(phrase);
        request.setTargetText("");
        return request;
    }

    private ReversoContextTranslation mapToContextTranslations(ReversoContextResponse.Context response){
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContextTranslation(sourceContext, targetContext);
    }

    private ReversoContextSentence createContextSentence(String text){
        CuttableText extracted = extractor.extract(text);
        return ReversoContextSentence.fromCutPoints(extracted.getText(), extracted.getCutPoints());
    }

}
