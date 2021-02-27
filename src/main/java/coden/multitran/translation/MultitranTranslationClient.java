package coden.multitran.translation;

import coden.multitran.language.MultitranLanguage;

import java.util.List;

public interface MultitranTranslationClient {
    List<MultitranTranslation> getTranslations(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception;
    String getTranslationUrl(MultitranLanguage source, MultitranLanguage target, String phrase);
}
