package coden.smarttranslate.controllers.reverso;

import coden.smarttranslate.controllers.reverso.context.ReversoContextProvider;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslation;
import coden.smarttranslate.core.Language;

import java.util.List;

public class ReversoApi implements ReversoContextProvider {
    @Override
    public List<ReversoContextTranslation> getContextTranslations(Language source, Language target, String phrase) throws Exception {
        return null;
    }

    @Override
    public String getUrl(Language source, Language target, String phrase) {
        return null;
    }
}
