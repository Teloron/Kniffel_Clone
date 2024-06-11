package classes;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int playerNumber = 1;
    private String name;
    private int score = 0;
    private int einsen = -1;
    private int zweien = -1;
    private int dreien = -1;
    private int vieren = -1;
    private int fuenfen = -1;
    private int sechsen = -1;
    private int summeOben = 0;
    private int bonus = 0;
    private int gesamtOben = 0;
    private int dreierpasch = -1;
    private int viererpasch = -1;
    private int fullHouse = -1;
    private int kleineStrasse = -1;
    private int grosseStrasse = -1;
    private int kniffel = -1;
    private int chance = -1;
    private int gesamtUnten = 0;
    private int endsumme = 0;
    private boolean hasNewHighscore = false;
    private boolean finished = false;
    private boolean isActive = true;
    private boolean isComputer = false;
    private String space = "                           ";


    public static List<Player> players = new ArrayList<Player>();
    public static List<Player> winners = new ArrayList<Player>();

    // Method to add new players
    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.isActive = true;
        players.add(this);
    }

    public Player(int playerNumber, String name, boolean isActive) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.isActive = isActive;
        players.add(this);
    }
    public String getSpace(){
        return space;
    }

    public static String choosePlayerName(int playerNumber) {
        System.out.printf(Console.space + "Bitte den Namen von Spieler %d eingeben: ", playerNumber);

        String playerName = Console.getInput();
        // for(String c : playerName.split("")) {
        //     if(c.equals('Ä') || c.equals('ä') || c.equals('Ö') || c.equals('ö') || c.equals('Ü') || c.equals('ü') || c.equals('ß')) {
        //         playerName = choosePlayerName(playerNumber);
        //     }
        // }
        if(playerName.length() == 0){
            playerName = choosePlayerName(playerNumber);
        }
        return playerName;
    }
    // Methods to calculate scores
    public int getCalculateSummeOben() {
        int summe = 0;
        if (einsen > 0) {
            summe += einsen;
        }
        if (zweien > 0) {
            summe += zweien;
        }
        if (dreien > 0) {
            summe += dreien;
        }
        if (vieren > 0) {
            summe += vieren;
        }
        if (fuenfen > 0) {
            summe += fuenfen;
        }
        if (sechsen > 0) {
            summe += sechsen;
        }
        summeOben = summe;
        return summeOben;
    }
    public int getCalculateBonus() {
        //int summe = getCalculateSummeOben();
        if (summeOben >= 63) {
            bonus = 35;
            return 35;
        } else {
            bonus = 0;
            return 0;
        }

    }
    public int getCalculateGesamtOben() {
        int summe = getCalculateSummeOben() + getCalculateBonus();
        gesamtOben = summe;
        return gesamtOben;
    }
    public int getCalculateGesamtUnten() {
        int summe = 0;
        if (dreierpasch > 0) {
            summe += dreierpasch;
        }
        if (viererpasch > 0) {
            summe += viererpasch;
        }
        if (fullHouse > 0) {
            summe += fullHouse;
        }
        if (kleineStrasse > 0) {
            summe += kleineStrasse;
        }
        if (grosseStrasse > 0) {
            summe += grosseStrasse;
        }
        if (kniffel > 0) {
            summe += kniffel;
        }
        if (chance > 0) {
            summe += chance;
        }
        gesamtUnten = summe;
        return gesamtUnten;

    }

    public int getCalculateEndsumme() {
        int summe = getCalculateGesamtOben() + getCalculateGesamtUnten();
        endsumme = summe;
        return summe;

    }

    public int askForAction(Dices dices) {
        
        int choice;
        System.out.println(space + "Ergebnis eintragen oder Feld streichen?");
        System.out.println(space + "(1) Eintragen | (2) Feld streichen | (B) Anderes Feld auswählen");
        System.out.print(space + "Deine Wahl: ");
        do {
            String input = Console.getInput().toUpperCase();
            switch (input) {
                case "1":
                    choice = 1;
                    break;
                case "2":
                    choice = 2;
                    break;
                case "B":
                    choice = 0;
                    break;
                default:
                    choice = -1;
                    Console.clear();
                    Console.printGFX(FileEnums.LOGO);
                    Scoreboard.printScoreboard();
                    dices.printDices(false);
                    System.out.println(space + "Ergebnis eintragen oder Feld streichen?");
                    System.out.println(space + "(1) Eintragen | (2) Feld streichen | (B) Anderes Feld auswählen");
                    System.out.println(space + "Ungültige Eingabe!!!");
                    System.out.print(space + "Deine Wahl: ");

            }
        } while (choice != 1 && choice != 2 && choice != 0);
        return choice;
    }
    // Method to let the Player Choose where to score and update Scores accordingly
    public void scoreChoice(Player player, Dices dices, int round) {
        boolean validChoice = false;
        boolean previouslyValid = true;
        int input = 0;
        boolean previouslyWrongInput = false;
        String taken = space + "Dieses Feld wurde bereits beschrieben. Bitte andere Auswahl treffen.";
        String notValid = space + "Eintragung nicht möglich!\n" + space + "Bitte ein anderes Feld auswählen oder streichen.\n";
        while (!validChoice) {
            // Console.clear();
            // Console.printGFX(FileEnums.LOGO);
            // Scoreboard.printScoreboard();
            // dices.printDices(false);
            do {
                Console.clear();
                Console.printGFX(FileEnums.LOGO);
                Scoreboard.printScoreboard();
                System.out.printf(space + "Runde %d: Ergebnis nach dem letzten Wurf von %s.\n", round, player.getName());
                dices.printDices(false);
                if(!previouslyValid){
                    System.out.println(notValid);
                }
                System.out.println( space + "In welchem Feld soll das Würfelergebnis eingetragen werden?");
                if(previouslyWrongInput){
                    System.out.println(space + "Ungültige Eingabe. Bitte gib eine Zahl zwischen 1 und 13 ein.");
                }
                previouslyWrongInput = false;
                try{
                    System.out.print(space + "Deine Wahl: ");
                    input = Integer.parseInt(Console.getInput());

                }catch(NumberFormatException e){
                    input = 99;
                }
                if(input != 1 && input != 2 && input != 3 && input != 4 && input != 5 && input != 6 && input != 7 && input != 8 && input != 9 && input != 10 && input != 11 && input != 12 && input != 13){
                    previouslyWrongInput = true;
                }else{
                    previouslyWrongInput = false;
                }
                
                
            }while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5 && input != 6 && input != 7 && input != 8 && input != 9 && input != 10 && input != 11 && input != 12 && input != 13);
            switch (input) {

                case 1:
                    if (einsen != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            einsen = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateEinsen(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 2:
                    if (zweien != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            zweien = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateZweien(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 3:
                    if (dreien != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            dreien = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateDreien(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 4:
                    if (vieren != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            vieren = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateVieren(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 5:
                    if (fuenfen != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            fuenfen = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateFuenfen(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 6:
                    if (sechsen != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            sechsen = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateSechsen(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 7:
                    if (dreierpasch != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            dreierpasch = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateDreierpasch(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 8:
                    if (viererpasch != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            viererpasch = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateViererpasch(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 9:
                    if (fullHouse != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            fullHouse = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateFullHouse(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 10:
                    if (kleineStrasse != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            kleineStrasse = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateKleineStrasse(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 11:
                    if (grosseStrasse != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            grosseStrasse = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateGrosseStrasse(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 12:
                    if (kniffel != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            kniffel = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateKniffel(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }

                    break;

                case 13:
                    if (chance != -1) {
                        System.out.println(taken);
                    } else {
                        int action = askForAction(dices);
                        if (action == 2) {
                            chance = 0;
                            validChoice = true;
                        }
                        if (action == 0) {
                            continue;
                        }
                        if (action == 1) {
                            validChoice = calculateChance(dices);
                            if(!validChoice){
                                previouslyValid = false;
                            }
                        }
                    }
                    break;
            }
        }
    }

    // Methods to calculate dice Scores
    public boolean calculateEinsen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 1) {
                counter++;
            }
        }
        if (counter > 0){
            einsen = counter * 1;
            return true;
        }else{
            return false;
        }
        
    }
    public int checkEinsen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            einsen = counter * 1;
            return einsen;
        }else{
            return 0;
        }
    }

    public boolean calculateZweien(Dices dices) {
        zweien = 0;
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 2) {
                counter++;
            }
        }
        if (counter > 0){
            zweien = counter * 2;
            return true;
        }else{
            return false;
        }
    }
    public int checkZweien(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            zweien = counter * 2;
            return zweien;
        }else{
            return 0;
        }
    }

    public boolean calculateDreien(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 3) {
                counter++;
            }
        }
        if (counter > 0){
            dreien = counter * 3;
            return true;
        }else{
            return false;
        } 
    }
    public int checkDreien(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            dreien = counter * 3;
            return dreien;
        }else{
            return 0;
        }
    }

    public boolean calculateVieren(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 4) {
                counter++;
            }
        }
        if(counter > 0){
            vieren = counter * 4;
            return true;
        }else{
            return false;
        }
    }
    public int checkVieren(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            vieren = counter * 4;
            return vieren;
        }else{
            return 0;
        }
    }

    public boolean calculateFuenfen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            fuenfen = counter * 5;
            return true;
        }else{
            return false;
        }
    }

    public int checkFuenfen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 5) {
                counter++;
            } 
        }
        if (counter > 0){
            fuenfen = counter * 5;
            return fuenfen;
        }else{
            return 0;
        }
    }

    public boolean calculateSechsen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 6) {
                counter++;
            }
        }
        if (counter > 0){
            sechsen = counter * 6;
            return true;
        }else{
            return false;
        }
    }
    public int checkSechsen(Dices dices) {
        int counter = 0;
        for (int i = 1; i < 6; i++) {
            if (dices.getDice(i) == 6) {
                counter++;
            }
        }
        if (counter > 0){
            sechsen = counter * 6;
            return sechsen;
        }else{
            return 0;
        }
    }

    // Methods to calculate special dice rolls
    // Method to check if Dreierpasch is met and set score if it is
    public boolean calculateDreierpasch(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        boolean hasThreeOfAKind = false;
        for (int count : diceCounter) {
            if (count >= 3) {
                hasThreeOfAKind = true;
                break;
            }
        }
        if (hasThreeOfAKind) {
            dreierpasch = 0;
            for (int dice : sortedDices) {
                dreierpasch += dice;
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean checkDreierpasch(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        boolean hasThreeOfAKind = false;
        for (int count : diceCounter) {
            if (count >= 3) {
                hasThreeOfAKind = true;
                break;
            }
        }
        if (hasThreeOfAKind) {
            return true;
        }else{
            return false;
        }
    }

    // Method to check if Viererpasch is met and set score if it is
    public boolean calculateViererpasch(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        boolean hasFourOfAKind = false;
        for (int count : diceCounter) {
            if (count >= 4) {
                hasFourOfAKind = true;
                break;
            }
        }
        if (hasFourOfAKind) {
            viererpasch = 0;
            for (int dice : sortedDices) {
                viererpasch += dice;
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean checkViererpasch(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        boolean hasFourOfAKind = false;
        for (int count : diceCounter) {
            if (count >= 4) {
                hasFourOfAKind = true;
                break;
            }
        }
        if (hasFourOfAKind) {
            return true;
        }else{
            return false;
        }
    }

    // Method to check if Full House is met and set score if it is
    public boolean calculateFullHouse(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        for (int count : diceCounter) {
            if (count >= 3) {
                hasThreeOfAKind = true;
            } else {
                if (count >= 2) {
                    hasTwoOfAKind = true;
                }
            }
        }
        if (hasThreeOfAKind && hasTwoOfAKind) {
            fullHouse = 25;
            return true;
        }else{
            return false;
        }
    }
    public boolean checkFullHouse(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        for (int count : diceCounter) {
            if (count >= 3) {
                hasThreeOfAKind = true;
            } else {
                if (count >= 2) {
                    hasTwoOfAKind = true;
                }
            }
        }
        if (hasThreeOfAKind && hasTwoOfAKind) {
            return true;
        }else{
            return false;
        }
    }

    // Method to check if kleine Strasse is met and set score if it is
    public boolean calculateKleineStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 1;
        for (int dice = 1; dice < sortedDices.length; dice++) {
            if (sortedDices[dice] == sortedDices[dice-1]+1) {
                consecutiveHits++;
                if(consecutiveHits >= 4){
                    kleineStrasse = 30;
                    return true;
                }
            } else {
                if (sortedDices[dice] != sortedDices[dice-1]) {
                    consecutiveHits = 1;
                }
            }
        }
            return false;
    }
    public boolean checkKleineStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 1;
        for (int dice = 1; dice < sortedDices.length; dice++) {
            if (sortedDices[dice] == sortedDices[dice-1]+1) {
                consecutiveHits++;
                if(consecutiveHits >= 4){
                    return true;
                }
            } else {
                if (sortedDices[dice] != sortedDices[dice-1]) {
                    consecutiveHits = 1;
                }
            }
        }
            return false;
    }
    public boolean checkBeinaheStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 1;
        for (int dice = 1; dice < sortedDices.length; dice++) {
            if (sortedDices[dice] == sortedDices[dice-1]+1) {
                consecutiveHits++;
                if(consecutiveHits >= 3){
                    return true;
                }
            } else {
                if (sortedDices[dice] != sortedDices[dice-1]) {
                    consecutiveHits = 1;
                }
            }
        }
            return false;
    }

    // Method to check if grosse Strasse is met and set score if it is
    public boolean calculateGrosseStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 1;
        for (int dice = 1; dice < sortedDices.length; dice++) {
            if (sortedDices[dice] == sortedDices[dice-1]+1) {
                consecutiveHits++;
            }
        }
        if (consecutiveHits >= 5) {
            grosseStrasse = 40;
            return true;
        }else{
            return false;
        }
        
    }
    public boolean checkGrosseStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 1;
        for (int dice = 1; dice < sortedDices.length; dice++) {
            if (sortedDices[dice] == sortedDices[dice-1]+1) {
                consecutiveHits++;
            }
        }
        if (consecutiveHits >= 5) {
            return true;
        }else{
            return false;
        }
        
    }

    // Method to check if Kniffel is met and set score if it is
    public boolean calculateKniffel(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            if (sortedDices[0] != dice) {
                return false;
            }
        }
        kniffel = 50;
        return true;
    }
    public boolean checkKniffel(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            if (sortedDices[0] != dice) {
                return false;
            }
        }
        return true;
    }

    // Method to calculate the score for chance field
    public boolean calculateChance(Dices dice) {
        int chanceValue = 0;
        for (int point : dice.getSortedDices()) {
            chanceValue += point;
        }
        if(chanceValue > 0){
            chance = chanceValue;
        return true;
        }else{
            return false;
        }
        
    }
    public int getDiceValues(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        return sortedDices[0] + sortedDices[1] + sortedDices[2] + sortedDices[3] + sortedDices[4];
    }

    // Getter
    public Player getNextPlayer(){
        Player nextPlayer = players.get(0);
        if (playerNumber == 4 || !players.get(playerNumber).getIsActive())
        {  
            nextPlayer = players.get(0);
        }else {
                nextPlayer = players.get(playerNumber);
        }
        return nextPlayer;
    }
    public static void getWinners(){
        int currentHighscore = 0;
        for (Player player : players){
            if (player.getEndsumme() > currentHighscore && player.getIsActive()){
                currentHighscore = player.getEndsumme();
            }
        }
        for (Player player : players){
            if (player.getEndsumme() == currentHighscore && player.getIsActive()){
                winners.add(player);
            }
        }
    }
    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getEinsen() {
        return einsen;
    }

    public int getZweien() {
        return zweien;
    }

    public int getDreien() {
        return dreien;
    }

    public int getVieren() {
        return vieren;
    }

    public int getFuenfen() {
        return fuenfen;
    }

    public int getSechsen() {
        return sechsen;
    }

    public int getSummeOben() {
        return summeOben;
    }

    public int getBonus() {
        return bonus;
    }

    public int getGesamtOben() {
        return gesamtOben;
    }

    public int getDreierpasch() {
        return dreierpasch;
    }

    public int getViererpasch() {
        return viererpasch;
    }

    public int getFullHouse() {
        return fullHouse;
    }

    public int getKleineStrasse() {
        return kleineStrasse;
    }

    public int getGrosseStrasse() {
        return grosseStrasse;
    }

    public int getKniffel() {
        return kniffel;
    }

    public int getChance() {
        return chance;
    }

    public int getGesamtUnten() {
        return gesamtUnten;
    }

    public int getEndsumme() {
        return endsumme;
    }

    public boolean getFinished() {
        return finished;
    }
 
    public boolean getIsActive() {
        return isActive;
    }
    public boolean getIsComputer(){
        return isComputer;    
    }
    public boolean getHasNewHighscore(){
        return hasNewHighscore;
    }

    // Setter
    public void setScore(int score) {
        this.score = score;
    }

    public void setEinsen(int einsen) {
        this.einsen = einsen;
    }

    public void setZweien(int zweien) {
        this.zweien = zweien;
    }

    public void setDreien(int dreien) {
        this.dreien = dreien;
    }

    public void setVieren(int vieren) {
        this.vieren = vieren;
    }

    public void setFuenfen(int fuenfen) {
        this.fuenfen = fuenfen;
    }

    public void setSechsen(int sechsen) {
        this.sechsen = sechsen;
    }

    public void setSummeOben(int summeOben) {
        this.summeOben = summeOben;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setGesamtOben(int gesamtOben) {
        this.gesamtOben = gesamtOben;
    }

    public void setDreierpasch(int dreierpasch) {
        this.dreierpasch = dreierpasch;
    }

    public void setViererpasch(int viererpasch) {
        this.viererpasch = viererpasch;
    }

    public void setFullHouse(int fullHouse) {
        this.fullHouse = fullHouse;
    }

    public void setKleineStrasse(int kleineStrasse) {
        this.kleineStrasse = kleineStrasse;
    }

    public void setGrosseStrasse(int grosseStrasse) {
        this.grosseStrasse = grosseStrasse;
    }

    public void setKniffel(int kniffel) {
        this.kniffel = kniffel;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setGesamtUnten(int gesamtUnten) {
        this.gesamtUnten = gesamtUnten;
    }

    public void setEndsumme(int endsumme) {
        this.endsumme = endsumme;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public void setIsComputer(boolean isComputer){
        this.isComputer = isComputer;
    }
    public void setHasNewHighscore(boolean hasNewHighscore){
        this.hasNewHighscore = hasNewHighscore;
    }
}
