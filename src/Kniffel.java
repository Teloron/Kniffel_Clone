

import classes.*;


public class Kniffel {


    public static void main(String[] args) {
        String choise;
        boolean wrongChoise = false;
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");
        System.setProperty("input.encoding", "UTF-8");
        
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        Highscore.checkCreateHighscore();
        Console.promptEnterKey();
        Console.clear();
        while(true){
            // Clear lists from previous games
            if(!Player.players.isEmpty()){
                Player.players.clear();
            }
            if(!Player.winners.isEmpty()){
                Player.winners.clear();
            }
            if(!Highscore.highscores.isEmpty()){
                Highscore.highscores.clear();
            }
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
                    game.gameLoop();
                    game.showEndScreen();
                    game.updateHighscore();
                    Highscore.showHighscore();

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