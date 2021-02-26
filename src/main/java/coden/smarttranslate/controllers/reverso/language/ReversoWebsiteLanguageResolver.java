package coden.smarttranslate.controllers.reverso.language;

import coden.smarttranslate.core.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("Website")
public class ReversoWebsiteLanguageResolver implements ReversoLanguageResolver{
    private static final Map<Language, String> languages = Map.of(Language.EN, "english", Language.RU, "russian", Language.DE, "german");
    public String resolve(Language language){
        return languages.get(language);
    }
}
