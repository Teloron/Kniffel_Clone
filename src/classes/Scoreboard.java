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

        // Variablen for SummeOben
    int playerOneSummeOben = Player.players.get(0).getCalculateSummeOben();
    int playerTwoSummeOben = Player.players.get(1).getCalculateSummeOben();
    int playerThreeSummeOben = Player.players.get(2).getCalculateSummeOben();
    int playerFourSummeOben = Player.players.get(3).getCalculateSummeOben();

    // Variables for Bonus
    int playerOneBonus = Player.players.get(0).getCalculateBonus();
    int playerTwoBonus = Player.players.get(1).getCalculateBonus();
    int playerThreeBonus = Player.players.get(2).getCalculateBonus();
    int playerFourBonus = Player.players.get(3).getCalculateBonus();

    // Variables for Gesamt Oben
    int playerOneGesamtOben = Player.players.get(0).getCalculateGesamtOben();
    int playerTwoGesamtOben = Player.players.get(1).getCalculateGesamtOben();
    int playerThreeGesamtOben = Player.players.get(2).getCalculateGesamtOben();
    int playerFourGesamtOben = Player.players.get(3).getCalculateGesamtOben();

    // Variables for Gesamt Unten
    int playerOneGesamtUnten = Player.players.get(0).getCalculateGesamtUnten();
    int playerTwoGesamtUnten = Player.players.get(1).getCalculateGesamtUnten();
    int playerThreeGesamtUnten = Player.players.get(2).getCalculateGesamtUnten();
    int playerFourGesamtUnten = Player.players.get(3).getCalculateGesamtUnten();

    // Variables for Endsumme
    int playerOneEndsumme = Player.players.get(0).getCalculateEndsumme();
    int playerTwoEndsumme = Player.players.get(1).getCalculateEndsumme();
    int playerThreeEndsumme = Player.players.get(2).getCalculateEndsumme();
    int playerFourEndsumme = Player.players.get(3).getCalculateEndsumme();

    String placeholder = "------";

        System.out.println(
                "                     _______________________ _______________________ _______________________ _______________________ ");
        System.out.println(
                "                    |                       |                       |                       |                       |");
        System.out.printf("                    |%17s      |%17s      |%17s      |%17s      | \n",
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
        System.out.printf(" | Summe            |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneSummeOben == 0 ? placeholder : playerOneSummeOben, playerTwoSummeOben == 0 ? placeholder : playerTwoSummeOben, playerThreeSummeOben == 0 ? placeholder : playerThreeSummeOben, playerFourSummeOben == 0 ? placeholder : playerFourSummeOben);
        System.out.println(" |==================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Bonus (63+ Pkt.) |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneBonus == 0 ? placeholder : playerOneBonus, playerTwoBonus == 0 ? placeholder : playerTwoBonus, playerThreeBonus == 0 ? placeholder : playerThreeBonus, playerFourBonus == 0 ? placeholder : playerFourBonus);
        System.out.println(" |==================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Gesamt oben      |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneGesamtOben == 0 ? placeholder : playerOneGesamtOben, playerTwoGesamtOben == 0 ? placeholder : playerTwoGesamtOben, playerThreeGesamtOben == 0 ? placeholder : playerThreeGesamtOben, playerFourGesamtOben == 0 ? placeholder : playerFourGesamtOben);
        System.out.println("  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        System.out.println("  __________________ _______________________ _______________________ _______________________ _______________________");
        System.out.print(" | 7 Dreierpasch    |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getDreierpasch());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 8 Viererpasch    |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getViererpasch());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 9 FullHouse      |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getFullHouse());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 10 Kleine Strasse|");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getKleineStrasse());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 11 Grosse Strasse|");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getGrosseStrasse());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 12 Kniffel       |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getKniffel());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | -----------------|-----------------------|-----------------------|-----------------------|-----------------------|");
        System.out.print(" | 13 Chance        |");
        for(Player player : Player.players) {
            printScoreOrPlaceholder(player.getChance());
            System.out.print(" |");
        }
        System.out.println();
        System.out.println(" | =================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Gesamt unten     |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneGesamtUnten == 0 ? placeholder : playerOneGesamtUnten, playerTwoGesamtUnten == 0 ? placeholder : playerTwoGesamtUnten, playerThreeGesamtUnten == 0 ? placeholder : playerThreeGesamtUnten, playerFourGesamtUnten == 0 ? placeholder : playerFourGesamtUnten);
        System.out.println(" | =================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Gesamt oben      |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneGesamtOben == 0 ? placeholder : playerOneGesamtOben, playerTwoGesamtOben == 0 ? placeholder : playerTwoGesamtOben, playerThreeGesamtOben == 0 ? placeholder : playerThreeGesamtOben, playerFourGesamtOben == 0 ? placeholder : playerFourGesamtOben);
        System.out.println(" | =================|=======================|=======================|=======================|=======================|");
        System.out.printf(" | Endsumme         |          %-12s |          %-12s |          %-12s |          %-12s | \n", playerOneEndsumme == 0 ? placeholder : playerOneEndsumme, playerTwoEndsumme == 0 ? placeholder : playerTwoEndsumme, playerThreeEndsumme == 0 ? placeholder : playerThreeEndsumme, playerFourEndsumme == 0 ? placeholder : playerFourEndsumme);
        System.out.println("  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ");
    }
}
