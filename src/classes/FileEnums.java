package classes;

public enum FileEnums {
    
    LOGO(("src/assets/logo.gfx"), ("KNIFFEL")),
    CREDITS(("src/assets/credits.gfx"), ("CREDITS")),
    DOMINIC(("src/assets/dominic.gfx"), ("DOMINIC")),
    EXIT(("src/assets/exit.gfx"), ("EXIT")),
    HENNING(("src/assets/henning.gfx"), ("HENNING")),
    KAI(("src/assets/kai.gfx"), ("KAI")),
    PODIUM(("src/assets/podium.gfx"), ("PODIUM MIT SIEGERN")),
    PODIUM_SMALL(("src/assets/podium_small.gfx"), ("PODIUM")),
    PODIUM_RACER(("src/assets/podium_racer.gfx"), ("PODIUM MIT RENNFAHRERN")),
    SPREE(("src/assets/spree.gfx"), ("THE SPREE CONNECTION")),
    WINNER(("src/assets/winner.gfx"), ("WINNER WINNER CHICKEN DINNER"));

    private String filepath;
    private String placeholderText;

    private FileEnums(String path, String placeholderText) {
        this.filepath = path;
        this.placeholderText = placeholderText;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public String getPlaceholderText() {
        return this.placeholderText;
    }
}
