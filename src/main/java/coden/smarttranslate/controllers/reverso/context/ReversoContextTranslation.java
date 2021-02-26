package coden.smarttranslate.controllers.reverso.context;

public class ReversoContextTranslation {

    private final ReversoContextSentence sourceSentence;
    private final ReversoContextSentence targetSentence;

    public ReversoContextTranslation(ReversoContextSentence sourceSentence, ReversoContextSentence targetSentence) {
        this.sourceSentence = sourceSentence;
        this.targetSentence = targetSentence;
    }

    public ReversoContextSentence getSourceSentence() {
        return sourceSentence;
    }

    public ReversoContextSentence getTargetSentence() {
        return targetSentence;
    }
}
