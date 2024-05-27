package classes;

public class Dices {
    private int dice1 = 0;
    private int dice2 = 0;
    private int dice3 = 0;
    private int dice4 = 0;
    private int dice5 = 0;

    public int getDice(int i) {
        switch(i){
            case 1: return dice1;
            case 2: return dice2;
            case 3: return dice3;
            case 4: return dice4;
            case 5: return dice5;
            default: return 0;
        }
    }
    public void setDice(int i, int value) {
        switch(i){
            case 1: dice1 = value; break;
            case 2: dice2 = value; break;
            case 3: dice3 = value; break;
            case 4: dice4 = value; break;
            case 5: dice5 = value; break;
            default: break;
        }
    }

}
