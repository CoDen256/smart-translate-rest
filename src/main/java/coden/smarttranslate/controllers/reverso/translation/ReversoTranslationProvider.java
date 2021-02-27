package coden.smarttranslate.controllers.reverso.translation;

import coden.smarttranslate.core.Language;

import java.util.List;

public interface ReversoTranslationProvider {
    List<ReversoTranslation> getTranslations(Language source, Language target, String phrase) throws Exception;
}
