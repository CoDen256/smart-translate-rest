package coden.smarttranslate.controllers.reverso.website;

import coden.smarttranslate.core.Language;

public interface ReversoContextUrlProvider {
    String getContextUrl(Language source, Language target, String phrase);
}
