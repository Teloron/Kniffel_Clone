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
            System.out.println(Console.menuSpace + "Willkommen bei Kniffel!\n");
            System.out.println(Console.menuSpace + "Optionen:");
            System.out.println(Console.menuSpace + "N - Neues Spiel starten");
            System.out.println(Console.menuSpace + "H - Highscore anzeigen");
            System.out.println(Console.menuSpace + "C - Credits anzeigen");
            System.out.println(Console.menuSpace + "B - Beenden\n");
            // Get input               
            if(wrongChoise){
                System.out.println(Console.menuSpace + "Falsche Eingabe.");
                System.out.print(Console.menuSpace + "Bitte eine gültige Auswahl treffen: ");
            }else{
                System.out.printf(Console.menuSpace + "Triff Deine Auswahl: ");
            }
            
            choise = Console.getInput().toUpperCase();
            switch(choise){
                case "N":
                    
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
                    wrongChoise = true;
                    break;
            }
        }
    }
}