package coden.smarttranslate.controllers.multitran.language;

import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("Website")
public class MultitranWebsiteLanguageResolver implements MultitranLanguageResolver {
    private static final Map<Language, String> languages = Map.of(Language.EN, "1", Language.RU, "2", Language.DE, "3");

    @Override
    public String resolve(Language language) {
        return languages.get(language);
    }
}
