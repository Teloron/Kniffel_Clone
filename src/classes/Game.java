package classes;


public class Game {
    // private List<Player> players = new ArrayList<Player>();

    private int humanPlayers;
    int currentRound = 0;

    // Method to get Player Count
    public int getPlayerCount() {
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        do {
            System.out.print("                                   Bitte Anzahl der Mitspieler eingeben (1-4): ");

            try {
                humanPlayers = Integer.parseInt(Console.getInput());
            } catch (NumberFormatException e) {
            }

            if (humanPlayers < 1 || humanPlayers > 4) {
                System.out.println(
                        "                                   UngÃ¼ltige Eingabe. Bitte eine Zahl zwischen 1 und 4 eingeben.");
                System.out.println();
            }

        } while (humanPlayers < 1 || humanPlayers > 4);

        return humanPlayers;
    }

    // Method to Start with picking Players
    public void startGameWithPlayers() {
        int playerCount = getPlayerCount();

        for (int i = 0; i < playerCount; i++) {
            Console.clear();
            Console.printGFX(FileEnums.LOGO);
            String name = Player.choosePlayerName(i + 1);
            new Player(i, name);
        }
        for (int i = 0; i < 4-playerCount; i++){
            new Player(i, " ", false);
        }
        Scoreboard.printScoreboard();
        Console.getInput();

    }

    // Method for main Gameplay
    public void gameLoop() {
        while (currentRound < 13) {
            for (Player player : Player.players) {
                if(!player.isActive){
                    continue;
                }
                Console.clear();
                Console.printGFX(FileEnums.LOGO);
                playerTurn(player);
                Console.getInput();
            }
            currentRound++;
        }
    }

    // Method for one Game Round
    public void playerTurn(Player player) {
        Dices playerDices = new Dices();
        int wurf = 1;
        playerDices.rollAllDices();
        while (wurf < 4) {
            Console.clear();
            Console.printGFX(FileEnums.LOGO);
            Scoreboard.printScoreboard();
            if(wurf < 3){
                System.out.printf("                                      Runde %d: %s ist dran mit dem %d. Wurf.\n"
                                                                                    ,(currentRound+1), player.getName(), wurf);
            playerDices.printDices();
            playerDices.rerollDices();
            }else{
                System.out.printf("                                      Runde %d: Ergebnis nach dem letzten Wurf von %s.\n"
                                                                                    ,(currentRound+1), player.getName());
                playerDices.printDices();
                player.scoreChoice(playerDices);
                Scoreboard.printScoreboard();
            }
            wurf++;
        }

    }

}
