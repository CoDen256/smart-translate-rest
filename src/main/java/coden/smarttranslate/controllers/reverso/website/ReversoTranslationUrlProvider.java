package coden.smarttranslate.controllers.reverso.website;

import coden.smarttranslate.core.Language;

public interface ReversoTranslationUrlProvider {
    String getTranslationUrl(Language source, Language target, String phrase);
}
