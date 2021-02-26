package coden.smarttranslate.controllers.multitran.website;

import coden.smarttranslate.core.Language;

public interface MultitranTranslationUrlProvider {
    String getTranslationUrl(Language source, Language target, String phrase);
}
