package coden.smarttranslate.core.controllers.multitran;

import static coden.smarttranslate.core.controllers.Language.*;

import coden.smarttranslate.core.controllers.Language;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;

import java.util.List;
import java.util.Map;

public class MultitranCrawler {

    private static final String API = "https://www.multitran.com/m.exe?s={phrase}&l1={source}&l2={target}";
    private static final Map<Language, String> languages = Map.of(EN, "1", RU, "2", DE, "3");

    public List<String> parseTranslations(Language source, Language target, String phrase){
        return List.of("trans1", "trans2");
    }

    public String getUrl(Language source, Language target, String phrase){
        return StringSubstitutor.replace(API, Map.of(
                "phrase", phrase,
                "source", resolveLanguage(source),
                "target", resolveLanguage(target)), "{", "}");
    }

    private String resolveLanguage(Language language){
        return languages.get(language);
    }

}
