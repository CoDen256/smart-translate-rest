package coden.multitran.language;

public enum MultitranLanguage {
    EN(1), RU(2), DE(3);

    private final int multitranStandard;

    MultitranLanguage(int multitranStandard){
        this.multitranStandard = multitranStandard;
    }

    public int getMultitranStandard() {
        return multitranStandard;
    }
}
