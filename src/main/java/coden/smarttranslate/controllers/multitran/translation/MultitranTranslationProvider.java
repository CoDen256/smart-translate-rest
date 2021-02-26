package coden.smarttranslate.controllers.multitran.translation;

import coden.smarttranslate.core.Language;

import java.util.List;

public interface MultitranTranslationProvider {
    List<MultitranTranslation> getTranslations(Language source, Language target, String phrase) throws Exception;
    String getUrl(Language source, Language target, String phrase);
}
