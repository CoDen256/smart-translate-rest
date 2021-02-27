package coden.smarttranslate.controllers.reverso.website;

import coden.smarttranslate.controllers.reverso.language.ReversoLanguageResolver;
import coden.smarttranslate.core.Language;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReversoWebsite implements ReversoContextUrlProvider, ReversoTranslationUrlProvider {

    private static final String CONTEXT_URL = "https://context.reverso.net/translation/{source}-{target}/{phrase}";
    private static final String TRANSLATION_URL = "https://www.reverso.net/translationresults.aspx?lang=EN&direction={source}-{target}";
    private final ReversoLanguageResolver resolver;

    public ReversoWebsite(@Qualifier("Website") ReversoLanguageResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public String getContextUrl(Language source, Language target, String phrase) {
        return StringSubstitutor.replace(CONTEXT_URL, Map.of(
                "phrase", phrase,
                "source", resolver.resolve(source),
                "target", resolver.resolve(target)), "{", "}");
    }

    @Override
    public String getTranslationUrl(Language source, Language target, String phrase) {
        return StringSubstitutor.replace(TRANSLATION_URL, Map.of(
                "source", resolver.resolve(source),
                "target", resolver.resolve(target)), "{", "}");
    }
}
