package coden.smarttranslate.controllers.reverso.website;

import coden.smarttranslate.controllers.reverso.language.ReversoLanguageResolver;
import coden.smarttranslate.core.Language;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReversoWebsite implements ReversoContextUrlProvider {

    private static final String URL = "https://context.reverso.net/translation/{source}-{target}/{phrase}";
    private final ReversoLanguageResolver resolver;

    public ReversoWebsite(@Qualifier("Website") ReversoLanguageResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public String getContextUrl(Language source, Language target, String phrase) {
        return StringSubstitutor.replace(URL, Map.of(
                "phrase", phrase,
                "source", resolver.resolve(source),
                "target", resolver.resolve(target)), "{", "}");
    }
}
