package classes;

import java.util.Collections;
import java.util.Comparator;

public class Game {
    

    private int humanPlayers;
    private int currentRound = 1;

    // Method to get Player Count
    public int getPlayerCount() {
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        do {
            System.out.print(Console.space + "Bitte Anzahl der Mitspieler eingeben (1-4): ");

            try {
                humanPlayers = Integer.parseInt(Console.getInput());
            } catch (NumberFormatException e) {
            }

            if (humanPlayers < 1 || humanPlayers > 4) {
                System.out.println(
                        Console.space + "Ungültige Eingabe. Bitte eine Zahl zwischen 1 und 4 eingeben.");
                System.out.println();
            }

        } while (humanPlayers < 1 || humanPlayers > 4);

        return humanPlayers;
    }

    // Method to Start with picking Players
    public void startGameWithPlayers() {
        int playerCount = getPlayerCount();

        for (int i = 1; i < (playerCount+1); i++) {
            Console.clear();
            Console.printGFX(FileEnums.LOGO);
            String name = Player.choosePlayerName(i);
            new Player(i, name);
        }
        for (int i = 1; i < 5-playerCount; i++){
            new Player(i+playerCount, " ", false);
        }
    }

    // Method for main Gameplay
    public void gameLoop() {
        while (currentRound < 14) {
            for (Player player : Player.players) {
                if(!player.getIsActive()){
                    continue;
                }
                Console.clear();
                Console.printGFX(FileEnums.LOGO);
                playerTurn(player);
                //Console.getInput();
            }
            currentRound++;
        }
    }

    // Method for one Game Round
    public void playerTurn(Player player) {
        Dices playerDices = new Dices();
        boolean rerolled = true;
        int wurf = 1;
        String space = "                                 ";
        playerDices.rollAllDices();
        while (wurf < 4) {
            Console.clear();
            Console.printGFX(FileEnums.LOGO);
            Scoreboard.printScoreboard();
            
            if(wurf < 3){
                System.out.printf(Console.space + "Runde %d: %s ist dran mit dem %d. Wurf.\n"
                                                                                    ,(currentRound), player.getName(), wurf);
                playerDices.printDices(true);
                rerolled =playerDices.rerollDices();
                if(!rerolled){
                    wurf = 2;
                }
            }else{
                                                              
                if (wurf < 2){
                    playerDices.printDices(true);
                    
                }else
                {
                    
                    playerDices.sortDices();                   
                    player.scoreChoice(player, playerDices, currentRound);
                    Scoreboard.printScoreboard();
                    System.out.println(space + "         Die Runde von " + player.getName() + " ist beendet.");
                    System.out.println(space +"         " + player.getNextPlayer().getName() + " ist jetzt an der Reihe.");
                    Console.promptEnterKey();
                    
                }
            }
            wurf++;
        }
    }
    
    public void showEndScreen() {

        Player.getWinners();
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        Console.printGFX(FileEnums.WINNER);
        System.out.print(Console.space + "          Herzlichen Glückwunsch!\n" + Console.space);
        // Ausgabe bei einem Gewinner:
        // "Gewinner" hat das Spiel gewonnen!
        // Ausgabe bei mehreren Gewinnern:
        // "Gewinner 1" und "Gewinner 2" haben das Spiel gewonnen!
        if(Player.winners.size() > 1){
            for (Player player : Player.winners){
                if(player != Player.winners.get(Player.winners.size()-1)){
                    System.out.print(player.getName() + " und ");  
                }else{
                    System.out.print(player.getName() + " ");
                } 
            }
        System.out.print("haben das Spiel gewonnen!\n");
        }else{
            System.out.print("       "+ Player.winners.get(0).getName() + " hat das Spiel gewonnen!\n");
        }
        Collections.sort(Player.players, new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                return p1.getEndsumme() > p2.getEndsumme() ? -1 : p1.getEndsumme() == p2.getEndsumme() ? 0 : 1;
            }
        });

        Console.promptEnterKey();
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        Console.printGFX(FileEnums.PODIUM_RACER);
        System.out.println();
        
        for (Player player : Player.players) {
            if(player.getIsActive()){  
                System.out.printf("%s%17s  hat %3d Punkte.\n",Console.space, player.getName(), player.getEndsumme());
            }
        }
        Console.promptEnterKey();
        

        
        
    }
    public void updateHighscore(){
        for(Player player : Player.players){
            if(player.getIsActive()){
                player.setHasNewHighscore(Highscore.checkIfPlayerHasHighscore(player));
            }
            if(player.getHasNewHighscore() && player.getIsActive()){
                Console.printGFX(FileEnums.LOGO);
                Console.printGFX(FileEnums.PODIUM_SMALL);
                System.out.printf("%sHerlichen Glückwunsch %s!\n%s     Du hast einen Neuen Highscore erzielt!\n%s     Dein neuer Highscore beträgt %d Punkte!\n", Console.menuSpace, player.getName(), Console.space, Console.space, player.getEndsumme());
                Highscore.addPlayerToHighscore(player);
                Console.promptEnterKey();
            }
        }
    }
}




