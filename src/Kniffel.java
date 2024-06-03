import classes.*;


public class Kniffel {


    public static void main(String[] args) {
        String choise;
        boolean wrongChoise = false;
        //Dices dice = new Dices();
        //Player player1 = new Player(1, "Player 1");
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");
        System.setProperty("input.encoding", "UTF-8");
        
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        Highscore.checkCreateHighscore();

        // Testing and Debugging
        // while(player1.getChance() == -1){
        //     dice.rollAllDices();
        //     System.out.println(Arrays.toString(dice.getSortedDices()));

        //     player1.calculateChance(dice);
        //     System.out.println(player1.getChance());
        // }
        
        Console.promptEnterKey();
        Console.clear();
        while(true){
            // Show MainMenu
            Console.clear();
            Console.printGFX(FileEnums.LOGO);
            System.out.printf("\t\t\t\t\t\tWillkommen bei Kniffel!\n\n");
            System.out.printf("\t\t\t\t\t\tOptionen:\n");
            System.out.printf("\t\t\t\t\t\tN - Neues Spiel starten\n");
            System.out.printf("\t\t\t\t\t\tH - Highscore anzeigen\n");
            System.out.printf("\t\t\t\t\t\tC - Credits anzeigen\n");
            System.out.printf("\t\t\t\t\t\tB - Beenden\n\n");
            // Get input               
            if(wrongChoise){
                System.out.printf("\t\t\t\t\t  Bitte eine gültige Auswahl treffen.\n");
            }
            System.out.printf("\t\t\t\t\t\tTriff Deine Auswahl: ");
            choise = Console.getInput().toUpperCase();
            switch(choise){
                case "N":
                    // TODO Start Game
                    Game game = new Game();
                    game.startGameWithPlayers();
                    // TODO Gameplay Loop
                    game.gameLoop();
                    //Console.getInput();
                    // TODO Game End
                    // TODO Update and Show Highscore
                    
                    break;
                case "H":
                    Highscore.showHighscore();
                    break;
                case "C":
                    Credits.printCredits();
                    break;
                case "B":
                    Console.clear();
                    Console.printGFX(FileEnums.LOGO);
                    Console.printGFX(FileEnums.LOGO);
                    System.out.print("   ");
                    Console.promptEnterKey("exit");
                    return;
                default:
                    System.out.println("\t\t\t\t\t\tUngültige Auswahl!");
                    System.out.print("         ");
                    Console.promptEnterKey();
                    break;
            }
        }
    }
}