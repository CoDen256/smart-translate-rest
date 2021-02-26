package coden.smarttranslate.controllers.reverso.context;

public class ReversoContextHighlight {
    private final int start;
    private final int end;

    public ReversoContextHighlight(int start, int end) {
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
