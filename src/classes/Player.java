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
    private boolean finished = false;
    boolean isActive = true;

    public static List<Player> players = new ArrayList<Player>();

    // Method to add new players
    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        players.add(this);
    }

    public Player(int playerNumber, String name, boolean isActive) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.isActive = isActive;
        players.add(this);
    }

    public static void returnPlayerNames() {
        for (Player player : players) {
            System.out.println(player.name);
        }
    }

    public static String choosePlayerName(int playerNumber) {
        System.out.printf("                                   Bitte den Namen von Spieler %d eingeben: ", playerNumber);

        String playerName = Console.getInput();
        return playerName;
    }

    // Method to show players score
    public void showPlayerScore() {
        System.out.println("Name: " + name);
        System.out.println("Score: " + score);
        System.out.println("Einsen: " + einsen);
        System.out.println("Zweien: " + zweien);
        System.out.println("Dreien: " + dreien);
        System.out.println("Vieren: " + vieren);
        System.out.println("Fuenfen: " + fuenfen);
        System.out.println("Sechsen: " + sechsen);
        System.out.println("SummeOben: " + summeOben);
        System.out.println("Bonus: " + bonus);
        System.out.println("GesamtOben: " + gesamtOben);
        System.out.println("Dreierpasch: " + dreierpasch);
        System.out.println("Viererpasch: " + viererpasch);
        System.out.println("FullHouse: " + fullHouse);
        System.out.println("KleineStrasse: " + kleineStrasse);
        System.out.println("GrosseStrasse: " + grosseStrasse);
        System.out.println("Kniffel: " + kniffel);
        System.out.println("Chance: " + chance);
        System.out.println("GesamtUnten: " + gesamtUnten);
        System.out.println("Endsumme: " + endsumme);
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
        if (summe >= 0) {
            return summe;
        } else {
            return 0;
        }
    }

    public void calculateSummeOben() {
        int summe = 0;
        if (einsen > 0) {
            summe += einsen;
        }
        if (zweien > 0) {
            summe += zweien;
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
    }

    public int getCalculateBonus() {
        int summe = getCalculateSummeOben();
        if (summe >= 63) {
            return 35;
        } else {
            return 0;
        }

    }

    public void calculateBonus() {
        if (summeOben >= 63) {
            bonus = 35;
        }
    }

    public int getCalculateGesamtOben() {
        int summe = getCalculateSummeOben() + getCalculateBonus();
        if (summe > 0){
            gesamtOben = summe;
            return gesamtOben;
        }else{
            return 0;
        }
    }

    public void calculateGesamtOben() {
        gesamtOben = getCalculateSummeOben() + getCalculateBonus();
    }

    public void calculateGesamtUnten() {
        int summe = 0;
        if(dreierpasch > 0){
            summe += dreierpasch;
        } 
        if(viererpasch > 0){
            summe += viererpasch;
        }
        if(fullHouse > 0){
            summe += fullHouse;
        }
        if(kleineStrasse > 0){  
            summe += kleineStrasse;
        }
        if(grosseStrasse > 0){
            summe += grosseStrasse;
        }
        if(kniffel > 0){
            summe += kniffel;
        }
        if(chance > 0){
            summe += chance;
        }
        gesamtUnten = summe;
    }

    public int getCalculateGesamtUnten() {
        int summe = 0;
        if(dreierpasch > 0){
            summe += dreierpasch;
        } 
        if(viererpasch > 0){
            summe += viererpasch;
        }
        if(fullHouse > 0){
            summe += fullHouse;
        }
        if(kleineStrasse > 0){  
            summe += kleineStrasse;
        }
        if(grosseStrasse > 0){
            summe += grosseStrasse;
        }
        if(kniffel > 0){
            summe += kniffel;
        }
        if(chance > 0){
            summe += chance;
        }
        
        if (summe >= 0) {
            gesamtUnten = summe;
            return summe;
        } else {
            return 0;
        }
    }

    public int getCalculateEndsumme() {
        int summe = getCalculateGesamtOben() + getCalculateGesamtUnten();
        if (summe >= 0) {
            endsumme = summe;
            return summe;
        } else {
            return 0;
        }
    }

    public void calculateEndsumme() {
        endsumme = getCalculateGesamtOben() + getCalculateGesamtUnten();
    }
    // Method to let the Player Choose where to score and update Scores accordingly
    public void scoreChoice(Dices dices){
        System.out.println("                                      In welchem Feld soll das Würfelergebnis eingetragen werden?");
        System.out.print("                                      Auswahl:");
        String input =Console.getInput();
        while(input != "1" && input != "2" && input != "3" && input != "4" && input != "5" && input != "6" && input != "7" && input != "8" && input != "9" && input != "10" && input != "11" && input != "12" && input != "13"){
            switch(input){
                // TODO Cases for player Choice
                case "1":

                case "2":

                case "3":

                case "4":

                case "5":

                case "6":

                case "7":

                case "8":

                case "9":

                case "10":

                case "11":

                case "12":

                case "13":

                default:
            } 

        }
    }
    // Methods to calculate dice Scores
    public void calculateEinsen(Dices dices) {
        einsen = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 1) {
                einsen++;
            }
        }
    }

    public void calculateZweien(Dices dices) {
        zweien = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 2) {
                zweien++;
            }
        }
    }

    public void calculateDreien(Dices dices) {
        dreien = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 3) {
                dreien++;
            }
        }
    }

    public void calculateVieren(Dices dices) {
        vieren = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 4) {
                vieren++;
            }
        }
    }

    public void calculateFuenfen(Dices dices) {
        fuenfen = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 5) {
                fuenfen++;
            }
        }
    }

    public void calculateSechsen(Dices dices) {
        sechsen = 0;
        for (int i = 0; i < 5; i++) {
            if (dices.getDice(i) == 6) {
                sechsen++;
            }
        }
    }

    // Methods to calculate special dice rolls
    // Method to check if Dreierpasch is met and set score if it is
    public void calculateDreierpasch(Dices dices) {
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
        }
    }

    // Method to check if Viererpasch is met and set score if it is
    public void calculateViererpasch(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int[] sortedDices = dices.getSortedDices();
        for (int dice : sortedDices) {
            diceCounter[dice - 1]++;
        }
        boolean hasThreeOfAKind = false;
        for (int count : diceCounter) {
            if (count >= 4) {
                hasThreeOfAKind = true;
                break;
            }
        }
        if (hasThreeOfAKind) {
            viererpasch = 0;
            for (int dice : sortedDices) {
                viererpasch += dice;
            }
        }
    }

    // Method to check if Full House is met and set score if it is
    public void calculateFullHouse(Dices dices) {
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
        }
    }

    // Method to check if kleine Strasse is met and set score if it is
    public void calculateKleineStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 0;
        for (int dice = 0; dice < sortedDices.length - 1; dice++) {
            if ((sortedDices[dice] + 1) == sortedDices[dice + 1]) {
                consecutiveHits++;
            } else {
                consecutiveHits = consecutiveHits > 3 ? consecutiveHits : 0;
                if (consecutiveHits == 0 && dice <= 2)
                    consecutiveHits = 1;

                if (consecutiveHits > 3)
                    break;
            }
        }
        if (consecutiveHits > 3) {
            kleineStrasse = 30;
        }

    }

    // Method to check if grosse Strasse is met and set score if it is
    public void calculateGrosseStrasse(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        int consecutiveHits = 0;
        for (int dice = 0; dice < sortedDices.length - 1; dice++) {
            if ((sortedDices[dice] + 1) == sortedDices[dice + 1]) {
                consecutiveHits++;
            }
        }
        if (consecutiveHits >= 4) {
            grosseStrasse = 40;
        }
    }

    // Method to check if Kniffel is met and set score if it is
    public void calculateKniffel(Dices dices) {
        int[] sortedDices = dices.getSortedDices();
        boolean kniffelDetected = true;
        for (int dice : sortedDices) {
            if (sortedDices[0] != dice) {
                kniffelDetected = false;
                break;
            }
        }
        if(kniffelDetected){
            kniffel = 50;
        }  
    }

    // Method to calculate the score for chance field
    public void calculateChance(Dices dice){
        int chanceValue = 0;
        for(int point : dice.getSortedDices()){
            chanceValue += point;
        }
        chance = chanceValue;
    }

    // Getter
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

}
