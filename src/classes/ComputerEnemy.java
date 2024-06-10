package classes;

public class ComputerEnemy extends Player {

    public ComputerEnemy(int playerNumber, String name, boolean isActive) {
        super(playerNumber, name, isActive);
        super.setIsComputer(true);
    }

    public static String chooseComputerName(int playerNumber) {
        System.out.printf(Console.space + "Bitte den Namen von Computerspieler %d eingeben: ", playerNumber);

        String computerName = Console.getInput();
        // for(String c : playerName.split("")) {
        // if(c.equals('Ä') || c.equals('ä') || c.equals('Ö') || c.equals('ö') ||
        // c.equals('Ü') || c.equals('ü') || c.equals('ß')) {
        // playerName = choosePlayerName(playerNumber);
        // }
        // }
        if (computerName.length() == 0) {
            computerName = choosePlayerName(playerNumber);
        }
        return computerName;
    }

    public boolean[] keepDices(Dices dices, int diceValue) {
        boolean[] dicesToKeep = { false, false, false, false, false };
        for (int i = 0; i < dices.getDices().length; i++) {
            if (dices.getDices()[i] == diceValue) {
                dicesToKeep[i] = true;
            }
        }

        return dicesToKeep;
    }

    public int[] chooseDiceIndex(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int maxValue = 0;
        int maxIndex = 0;
        for (int dice : dices.getDices()) {
            diceCounter[dice - 1]++;
        }

        for (int i = 1; i <= diceCounter.length; i++) {
            if (diceCounter[i - 1] * i > maxValue) {
                maxValue = diceCounter[i - 1] * i;
                maxIndex = i - 1;
            }
        }
        int[] ret = { maxIndex, maxValue };
        return ret;
    }

    public int[] resetDiceCounter(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };

        for (int dice : dices.getDices()) {
            diceCounter[dice -1]++;
        }
        return diceCounter;
    }
    // Method that determins what dices the computer should reroll
    // Returns true if at least one dice got rerolled, else returns false
    public boolean rerollChoiceComputer(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        for (int dice : dices.getDices()) {
            diceCounter[dice - 1]++;
        }
        // First check if we can reach a kniffel
        for (int i = 0; i < diceCounter.length; i++) {

            if (diceCounter[i] >= 4 && getKniffel() == -1) {
                dices.setDicesToKeep(keepDices(dices, i));
                return true;
            }
        }

        // Then check for a full house
        if (getFullHouse() == -1) {
            if (checkFullHouse(dices)) {
                boolean[] dicesToKeep = { true, true, true, true, true };
                dices.setDicesToKeep(dicesToKeep);
                return false;
            }
        }
        // Then check if we can reach a Strasse
        if (getKleineStrasse() == -1 || getGrosseStrasse() == -1) {
            if (checkGrosseStrasse(dices)) {
                boolean[] dicesToKeep = { true, true, true, true, true };
                dices.setDicesToKeep(dicesToKeep);
                return false;
            } else {
                if (checkKleineStrasse(dices)) {
                    boolean[] dicesToKeep = { true, true, true, true, true };
                    dices.setDicesToKeep(dicesToKeep);
                    return false;
                } else {
                    for (int i = 0; i < diceCounter.length; i++) {
                        diceCounter[i] = 0;
                    }
                    boolean[] dicesToKeep = { false, false, false, false, false };
                    for (int i = 0; i < dices.getDices().length; i++) {
                        if (diceCounter[dices.getDices()[i] - 1] == 0) {
                            dicesToKeep[i] = true;
                        }
                    }
                    dices.setDicesToKeep(dicesToKeep);
                    return true;
                }
            }

        }
        // Reset dice counter
        diceCounter = resetDiceCounter(dices);
        // Choose to keep Viererpasch if possible
        if(getViererpasch() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 4){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
                if(dices.getDices()[i] > 3){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Choose to keep Dreierpasch if possible
        if(getDreierpasch() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 3){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
                if(dices.getDices()[i] > 3){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Choose to keep one of the top choices if available
        // Sechsen
        if(getSechsen() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Fuenfen
        if(getFuenfen() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Vieren
        if(getVieren() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Dreien
        if(getDreien() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Zweien
        if(getZweien() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Einsen
        if(getEinsen() == -1 && chooseDiceIndex(dices)[1]/(chooseDiceIndex(dices)[0]+1) >= 2){
            boolean[] keepDices = {false, false, false, false, false};
            for(int i = 0; i < dices.getDices().length; i++){
                if(dices.getDices()[i] == chooseDiceIndex(dices)[0]+1 ){
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Reroll every dice if we cant find anything
        boolean[] keepDices = {false, false, false, false, false};
        dices.setDicesToKeep(keepDices);
        return true;
    }

    public void computerScoreChoice(Dices dices, int round){
        
        Console.printGFX(FileEnums.LOGO);
        Scoreboard.printScoreboard();
        System.out.printf(getSpace() + "Runde %d: Ergebnis nach dem letzten Wurf von %s.\n", round, getName());
        dices.printDices(false);

        // Eintragung ins Scoreboard nach relevanz
        if (getKniffel() == -1 && checkKniffel(dices)){
            setKniffel(50);
            System.out.println(getSpace() + getName()+" hat den Kniffel eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if (getFullHouse() == -1 && checkFullHouse(dices)){
            setFullHouse(25);
            System.out.println(getSpace() + getName()+" hat da Full House eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if (getGrosseStrasse() == -1 && checkGrosseStrasse(dices)){
            setGrosseStrasse(40);
            System.out.println(getSpace() + getName()+" hat die große Straße eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getKleineStrasse() == -1 && checkKleineStrasse(dices)){
            setKleineStrasse(30);
            System.out.println(getSpace() + getName()+" hat die kleine Straße eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getViererpasch() == -1 && checkViererpasch(dices)){
            int viererpasch = 0;
            int[] sortedDices = dices.getSortedDices();
            for (int dice : sortedDices) {
                viererpasch += dice;
            }
            setViererpasch(viererpasch);
            System.out.println(getSpace() + getName()+" hat den Viererpasch eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getDreierpasch() == -1 && checkDreierpasch(dices)){
            int dreierpasch = 0;
            int[] sortedDices = dices.getSortedDices();
            for (int dice : sortedDices) {
                dreierpasch += dice;
            }
            setDreierpasch(dreierpasch);
            System.out.println(getSpace() + getName()+" hat den Dreierpasch eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getSechsen() == -1 && checkSechsen(dices) >= 12 ){
            calculateSechsen(dices);
            System.out.println(getSpace() + getName()+" hat die Sechsen eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getFuenfen() == -1 && checkFuenfen(dices) >= 10 ){
            calculateFuenfen(dices);
            System.out.println(getSpace() + getName()+" hat die Fuenfen eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getVieren() == -1 && checkVieren(dices) >= 8 ){
            calculateVieren(dices);
            System.out.println(getSpace() + getName()+" hat die Vieren eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getDreien() == -1 && checkDreien(dices) >= 6 ){
            calculateDreien(dices);
            System.out.println(getSpace() + getName()+" hat die Dreien eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getZweien() == -1 && checkZweien(dices) >= 4 ){
            calculateZweien(dices);
            System.out.println(getSpace() + getName()+" hat die Zweien eingetragen!");
            Console.promptEnterKey();
            return;
        }
        if(getEinsen() == -1 && checkEinsen(dices) >= 2 ){
            calculateEinsen(dices);
            System.out.println(getSpace() + getName()+" hat die Einsen eingetragen!");
            Console.promptEnterKey();
            return;
        }
        // TODO eintragung oder Streichung, falls keine Eingabe möglich

    }
}
