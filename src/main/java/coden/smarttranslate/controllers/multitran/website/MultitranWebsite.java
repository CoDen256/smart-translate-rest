package coden.smarttranslate.controllers.multitran.website;

import coden.smarttranslate.controllers.multitran.language.MultitranLanguageResolver;
import coden.smarttranslate.core.Language;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MultitranWebsite implements MultitranTranslationUrlProvider {
    private static final String URL = "https://www.multitran.com/m.exe?s={phrase}&l1={source}&l2={target}";
    private final MultitranLanguageResolver resolver;

    public MultitranWebsite(@Qualifier("Website") MultitranLanguageResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public String getTranslationUrl(Language source, Language target, String phrase) {
        return StringSubstitutor.replace(URL, Map.of(
                "phrase", phrase,
                "source", resolver.resolve(source),
                "target", resolver.resolve(target)), "{", "}");
    }
}
