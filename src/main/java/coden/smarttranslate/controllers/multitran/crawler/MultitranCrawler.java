package coden.smarttranslate.controllers.multitran.crawler;

import coden.smarttranslate.controllers.multitran.language.MultitranLanguageResolver;
import coden.smarttranslate.controllers.multitran.translation.MultitranTranslation;
import coden.smarttranslate.controllers.multitran.translation.MultitranTranslationProvider;
import coden.smarttranslate.controllers.multitran.website.MultitranTranslationUrlProvider;
import coden.smarttranslate.core.Language;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MultitranCrawler implements MultitranTranslationProvider {

    private static final Pattern queryStartPattern = Pattern.compile("^/m.exe\\?.*");
    private static final Pattern queryWordSearchPattern = Pattern.compile(".*&?s=[^&]*.*");
    private static final String queryLanguagePattern = ".*&?%s=%s.*";

    private final MultitranTranslationUrlProvider urlProvider;
    private final MultitranLanguageResolver resolver;

    public MultitranCrawler(MultitranTranslationUrlProvider urlProvider, @Qualifier("Website") MultitranLanguageResolver resolver) {
        this.urlProvider = urlProvider;
        this.resolver = resolver;
    }

    @Override
    public List<MultitranTranslation> getTranslations(Language source, Language target, String phrase) throws Exception {
        String url = urlProvider.getTranslationUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentTranslations(document, source, target);
    }

    private List<MultitranTranslation> parseDocumentTranslations(Document document, Language source, Language target) {
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

    private boolean matchesTranslationHref(String href, Language l1, Language l2) {
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
        String languageRegex = String.format(queryLanguagePattern, languageType, resolver.resolve(lang));
        return href.matches(languageRegex);
    }

}
