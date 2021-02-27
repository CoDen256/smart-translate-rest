package coden.smarttranslate.controllers.reverso.api;

import coden.smarttranslate.controllers.reverso.context.ReversoContextProvider;
import coden.smarttranslate.controllers.reverso.context.ReversoContextSentence;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslation;
import coden.smarttranslate.controllers.reverso.highlight.CuttableText;
import coden.smarttranslate.controllers.reverso.highlight.HighlightsExtractor;
import coden.smarttranslate.controllers.reverso.language.ReversoLanguageResolver;
import coden.smarttranslate.controllers.reverso.translation.ReversoTranslationProvider;
import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class ReversoApi implements ReversoContextProvider, ReversoTranslationProvider {

    private static final String CONTEXT_API = "https://context.reverso.net/bst-query-service";
    private static final String TRANSLATE_API = "https://api.reverso.net/translate/v1/translation";
    private final ReversoLanguageResolver ctxResolver;
    private final ReversoLanguageResolver transResolver;
    private final WebClient contextClient;
    private final WebClient transClient;
    private final HighlightsExtractor extractor;

    public ReversoApi(@Qualifier("API-context") ReversoLanguageResolver ctxResolver,
                      @Qualifier("API-translate") ReversoLanguageResolver transResolver,
                      HighlightsExtractor extractor) {
        contextClient = WebClient.create(CONTEXT_API);
        transClient = WebClient.create(TRANSLATE_API);
        this.ctxResolver = ctxResolver;
        this.transResolver = transResolver;
        this.extractor = extractor;
    }

    // TODO: may be not block, and web client via config
    @Override
    public List<ReversoContextTranslation> getTranslations(Language source, Language target, String phrase) throws Exception {
        return contextClient.post()
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
        request.setSourceLang(ctxResolver.resolve(source));
        request.setTargetLang(ctxResolver.resolve(target));
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

    @Override
    public List<ReversoContextTranslation> getContexts(Language source, Language target, String phrase) throws Exception {
        return null;
    }

    private ReversoTranslationRequest createRequest(Language source, Language target, String phrase){
        ReversoTranslationRequest request = new ReversoTranslationRequest();
        request.setMode(0);
        request.setNPage(1);
        request.setSourceLang(ctxResolver.resolve(source));
        request.setTargetLang(ctxResolver.resolve(target));
        request.setSourceText(phrase);
        request.setTargetText("");
        return request;
    }

    private ReversoContextTranslation mapToTranslations(ReversoTranslationResponse.Context response){
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContextTranslation(sourceContext, targetContext);
    }
}
