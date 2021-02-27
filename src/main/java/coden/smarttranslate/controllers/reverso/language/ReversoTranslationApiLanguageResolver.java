package coden.smarttranslate.controllers.reverso.language;

import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("API-translate")
public class ReversoTranslationApiLanguageResolver implements ReversoLanguageResolver{
    private static final Map<Language, String> languages = Map.of(Language.EN, "eng", Language.RU, "rus", Language.DE, "ger");
    public String resolve(Language language){
        return languages.get(language);
    }
}
