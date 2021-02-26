package coden.smarttranslate.controllers.multitran.translation;

import coden.smarttranslate.core.Language;

import java.io.IOException;
import java.util.List;

public interface MultitranTranslationProvider {
    List<MultitranTranslation> getTranslations(Language source, Language target, String phrase) throws IOException;
    String getUrl(Language source, Language target, String phrase);
}
