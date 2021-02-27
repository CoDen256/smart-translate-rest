package coden.multitran.context;

import coden.multitran.language.MultitranLanguage;

import java.util.List;

public interface MultitranContextClient {
    List<MultitranContext> getContexts(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception;
    String getContextUrl(MultitranLanguage source, MultitranLanguage target, String phrase);
}
