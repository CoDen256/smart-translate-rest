package coden.smarttranslate.controllers.reverso.language;

import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("API-context")
public class ReversoContextApiLanguageResolver implements ReversoLanguageResolver{
    private static final Map<Language, String> languages = Map.of(Language.EN, "en", Language.RU, "ru", Language.DE, "de");
    public String resolve(Language language){
        return languages.get(language);
    }
}
