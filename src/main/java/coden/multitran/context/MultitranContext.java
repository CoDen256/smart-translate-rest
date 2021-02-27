package coden.multitran.context;

public class MultitranContext {

    private MultitranContextSentence source;
    private MultitranContextSentence target;

    public MultitranContext(MultitranContextSentence source, MultitranContextSentence target) {
        this.source = source;
        this.target = target;
    }

    public MultitranContextSentence getSource() {
        return source;
    }

    public void setSource(MultitranContextSentence source) {
        this.source = source;
    }

    public MultitranContextSentence getTarget() {
        return target;
    }

    public void setTarget(MultitranContextSentence target) {
        this.target = target;
    }
}
