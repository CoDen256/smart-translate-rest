package coden.smarttranslate.controllers.multitran;

import coden.smarttranslate.controllers.multitran.translation.MultitranTranslationProvider;
import coden.smarttranslate.controllers.multitran.translation.MultitranTranslation;
import coden.smarttranslate.core.Language;
import org.apache.commons.text.StringSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MultitranCrawler implements MultitranTranslationProvider {

    private static final String API = "https://www.multitran.com/m.exe?s={phrase}&l1={source}&l2={target}";
    private static final Map<Language, String> languages = Map.of(Language.EN, "1", Language.RU, "2", Language.DE, "3");

    private static final Pattern queryStartPattern = Pattern.compile("^/m.exe\\?.*");
    private static final Pattern queryWordSearchPattern = Pattern.compile(".*&?s=[^&]*.*");
    private static final String queryLanguagePattern = ".*&?%s=%s.*";

    @Override
    public List<MultitranTranslation> getTranslations(Language source, Language target, String phrase) throws IOException {
        String url = getUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentTranslations(document, source, target);
    }

    private List<MultitranTranslation> parseDocumentTranslations(Document document, Language source, Language target){
        Predicate<Element> translationHrefMatcher = el -> matchesTranslationHref(el.attr("href"), target, source);
        return document.getElementsByClass("trans")
                .stream()
                .map(el -> el.getElementsByAttribute("href"))
                .flatMap(Elements::stream)
                .filter(translationHrefMatcher)
                .map(Element::text)
                .map(MultitranTranslation::new)
                .collect(Collectors.toList());
    }


    @Override
    public String getUrl(Language source, Language target, String phrase){
        return StringSubstitutor.replace(API, Map.of(
                "phrase", phrase,
                "source", resolveLanguage(source),
                "target", resolveLanguage(target)), "{", "}");
    }

    private boolean matchesTranslationHref(String href, Language l1, Language l2){
        return matchesQueryStart(href) &&
                matchesQueryWordSearch(href) &&
                matchesQueryLanguage(href, "l1", l1) &&
                matchesQueryLanguage(href, "l2", l2);
    }

    private boolean matchesQueryStart(String href) {
        return queryStartPattern.matcher(href).matches();
    }

    private boolean matchesQueryWordSearch(String href) {
        return queryWordSearchPattern.matcher(href).matches();
    }

    private boolean matchesQueryLanguage(String href, String languageType, Language lang) {
        String languageRegex = String.format(queryLanguagePattern, languageType, resolveLanguage(lang));
        return href.matches(languageRegex);
    }

    private String resolveLanguage(Language language){
        return languages.get(language);
    }
}