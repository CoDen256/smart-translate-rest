package coden.smarttranslate.controllers.reverso.data;

public class ContextHighlight {
    private final int start;
    private final int end;

    public ContextHighlight(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
