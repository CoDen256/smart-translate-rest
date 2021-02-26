package coden.smarttranslate.controllers.reverso.context;

import coden.smarttranslate.core.Language;

import java.util.List;

public interface ReversoContextProvider {
    List<ReversoContextTranslation> getContextTranslations(Language source, Language target, String phrase) throws Exception;
}
