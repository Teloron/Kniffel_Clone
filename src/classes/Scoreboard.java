package classes;

public class Scoreboard {
    public static void printScoreOrPlaceholder(int score) {
        if (score == -1) { // If score is default value (-1) it means that the field is empty
            System.out.printf("                      ");
        } else { // If score is not default value, it gets printed
            System.out.printf("          %-12d", score);
        }
    }

    public static void printScoreboard() {

        System.out.printf(
                "                     _______________________ _______________________ _______________________ _______________________  \n");
        System.out.printf(
                "                    |                       |                       |                       |                       | \n");
        System.out.printf("                    |   %-19s |   %-19s |   %-19s |   %-19s | \n",
                Player.players.get(0).getName(), Player.players.get(1).getName(), Player.players.get(2).getName(),
                Player.players.get(3).getName());

        // System.out.printf(" | | | | | \n");
        // System.out.printf(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
        // ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ \n");
        System.out.printf(
                "  __________________|_______________________|_______________________|_______________________|_______________________| \n");
        System.out.printf(" | 1 Nur Einsen     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getEinsen());
            System.out.print(" |");
        }
        System.out.println();
        System.out.print(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------| \n");
        System.out.print(" | 2 Nur Zweien     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getZweien());
            System.out.print(" |");
        }
        System.out.println();
        System.out.print(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------| \n");

    }
}
