package coden.smarttranslate.controllers.multitran.website;

import coden.smarttranslate.core.Language;

public interface MultitranUrlProvider {
    String getUrl(Language source, Language target, String phrase);
}
