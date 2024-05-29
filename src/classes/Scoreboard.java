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

        System.out.println(
                "                     _______________________ _______________________ _______________________ _______________________ ");
        System.out.println(
                "                    |                       |                       |                       |                       |");
        System.out.printf("                    |   %-19s |   %-19s |   %-19s |   %-19s | \n",
                Player.players.get(0).getName(), Player.players.get(1).getName(), Player.players.get(2).getName(),
                Player.players.get(3).getName());

        // System.out.printf(" | | | | | \n");
        // System.out.printf(" ??????????????????????? ???????????????????????
        // ??????????????????????? ??????????????????????? \n");
        System.out.println(
                "  __________________|_______________________|_______________________|_______________________|_______________________|");
        System.out.printf(" | 1 Nur Einsen     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getEinsen());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 2 Nur Zweien     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getZweien());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 3 Nur Dreien     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getDreien());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 4 Nur Vieren     |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getVieren());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 5 Nur Fuenfen    |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getFuenfen());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 6 Nur Sechsen    |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getSechsen());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | =================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Summe            |          %-12d |          %-12d |          %-12d |          %-12d | \n", Player.players.get(0).getSummeOben(), Player.players.get(1).getSummeOben(), Player.players.get(2).getSummeOben(), Player.players.get(3).getSummeOben());
        System.out.println(" |==================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Bonus (63+ Pkt.) |          %-12d |          %-12d |          %-12d |          %-12d | \n", Player.players.get(0).getCalculateBonus(), Player.players.get(1).getCalculateBonus(), Player.players.get(2).getCalculateBonus(), Player.players.get(3).getCalculateBonus());
        System.out.println(" |==================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Gesamt oben      |          %-12d |          %-12d |          %-12d |          %-12d | \n", Player.players.get(0).getCalculateGesamtOben(), Player.players.get(1).getCalculateGesamtOben(), Player.players.get(2).getCalculateGesamtOben(), Player.players.get(3).getCalculateGesamtOben());
        System.out.println("  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        System.out.println("  __________________ _______________________ _______________________ _______________________ _______________________");

    }
}
