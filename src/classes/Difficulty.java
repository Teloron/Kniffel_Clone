package classes;

public enum Difficulty {

    EASY("Easy"),
    MEDIUM("Normal"),
    HARD("Hard");

    private String level;

    private Difficulty(String level) {
        this.level = level;
    }

    public String getLevel(){
        return this.level;
    }

}



