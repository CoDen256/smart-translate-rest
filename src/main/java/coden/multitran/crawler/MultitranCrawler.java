package coden.multitran.crawler;

import coden.multitran.language.MultitranLanguage;
import coden.multitran.translation.MultitranTranslation;
import coden.multitran.translation.MultitranTranslationClient;
import org.apache.commons.text.StringSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MultitranCrawler implements MultitranTranslationClient {

    private static final String TRANSLATION_URL = "https://www.multitran.com/m.exe?s={phrase}&l1={source}&l2={target}";

    private static final Pattern queryStartPattern = Pattern.compile("^/m.exe\\?.*");
    private static final Pattern queryWordSearchPattern = Pattern.compile(".*&?s=[^&]*.*");
    private static final String queryLanguagePattern = ".*&?%s=%s.*";

    @Override
    public List<MultitranTranslation> getTranslations(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception {
        String url = getTranslationUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentTranslations(document, source, target);
    }

    private List<MultitranTranslation> parseDocumentTranslations(Document document, MultitranLanguage source, MultitranLanguage target) {
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

    private boolean matchesTranslationHref(String href, MultitranLanguage l1, MultitranLanguage l2) {
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

    private boolean matchesQueryLanguage(String href, String languageType, MultitranLanguage lang) {
        String languageRegex = String.format(queryLanguagePattern, languageType, lang.getMultitranStandard());
        return href.matches(languageRegex);
    }

    @Override
    public String getTranslationUrl(MultitranLanguage source, MultitranLanguage target, String phrase) {
        return StringSubstitutor.replace(TRANSLATION_URL, Map.of(
                "phrase", phrase,
                "source", source.getMultitranStandard(),
                "target", target.getMultitranStandard()), "{", "}");
    }
}
