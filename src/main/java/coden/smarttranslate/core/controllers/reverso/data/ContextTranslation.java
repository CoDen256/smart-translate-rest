package coden.smarttranslate.core.controllers.reverso.data;

public class ContextTranslation {

    private final ContextSentence sourceSentence;
    private final ContextSentence targetSentence;

    public ContextTranslation(ContextSentence sourceSentence, ContextSentence targetSentence) {
        this.sourceSentence = sourceSentence;
        this.targetSentence = targetSentence;
    }

    public ContextSentence getSourceSentence() {
        return sourceSentence;
    }

    public ContextSentence getTargetSentence() {
        return targetSentence;
    }
}
