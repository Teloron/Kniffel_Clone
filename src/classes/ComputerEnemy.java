package classes;

public class ComputerEnemy extends Player {

    String difficulty;

    public ComputerEnemy(int playerNumber, String name, boolean isActive, Difficulty difficulty) {
        super(playerNumber, name, isActive);
        super.setIsComputer(true);
        this.difficulty = difficulty.getLevel();
    }


    public void computerTurn(){
        return;
    }
    
}
