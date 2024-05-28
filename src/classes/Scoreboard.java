package classes;

public class Scoreboard {
    public static void printScoreOrPlaceholder(int score){
        
    }



    public static void printScoreboard(){

        
        

        System.out.printf("                     _______________________ _______________________ _______________________ _______________________  \n");
        System.out.printf("                    |                       |                       |                       |                       | \n");
        System.out.printf("                    |   %-19s |   %-19s |   %-19s |   %-19s | \n"
        , Player.players.get(0).getName(), Player.players.get(1).getName(), Player.players.get(2).getName(), Player.players.get(3).getName());

        //System.out.printf("                    |                       |                       |                       |                       | \n");
        //System.out.printf("                     ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾  \n");
        System.out.printf("  __________________|_______________________|_______________________|_______________________|_______________________| \n");
        System.out.printf(" | 1 Nur Einsen     |");

        

    }
}
