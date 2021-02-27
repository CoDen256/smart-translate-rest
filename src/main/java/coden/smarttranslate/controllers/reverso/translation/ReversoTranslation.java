package coden.smarttranslate.controllers.reverso.translation;

public class ReversoTranslation {

    private final String sourcePhrase;
    private final String targetPhrase;

    public ReversoTranslation(String sourcePhrase, String targetPhrase) {
        this.sourcePhrase = sourcePhrase;
        this.targetPhrase = targetPhrase;
    }

    public String getSource() {
        return sourcePhrase;
    }

    public String getTarget() {
        return targetPhrase;
    }
}
