package classes;

public class ComputerEnemy extends Player {

    public ComputerEnemy(int playerNumber, String name, boolean isActive) {
        super(playerNumber, name, isActive);
        super.setIsComputer(true);
    }

    public static String chooseComputerName(int playerNumber) {
        // TODO Random names for Computer Enemies
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

    /**
     * Chooses the index of the dice with the highest number of hits in the given
     * Dices object.
     *
     * @param dices the Dices to analyze
     * @return an int array containing:
     *         - [0]: The index of the dice with the highest number of hits.
     * 
     *         - [1]: The number of hits.
     * 
     *         - [2]: The sum of the points achived with this number of hits.
     */
    public int[] chooseDiceIndex(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };
        int maxValue = 0;
        int maxIndex = 0;
        int maxHits = 0;
        for (int dice : dices.getDices()) {
            diceCounter[dice - 1]++;
        }
        for (int i = 0; i < diceCounter.length; i++) {
            if (diceCounter[i] >= maxHits) {
                maxHits = diceCounter[i];
                maxIndex = i;
                maxValue = (i + 1) * maxHits;
            }
        }
        int[] ret = new int[3];
        ret[0] = maxIndex;
        ret[1] = maxHits;
        ret[2] = maxValue;
        return ret;
    }

    public int[] resetDiceCounter(Dices dices) {
        int[] diceCounter = { 0, 0, 0, 0, 0, 0 };

        for (int dice : dices.getDices()) {
            diceCounter[dice - 1]++;
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
        // DEBUG
        // int[] dIdx = chooseDiceIndex(dices);
        // System.out.print("Max Index: " + dIdx[0] + " Max Hits: " + dIdx[1] + " Max Value: " + dIdx[2] + "\n");
        // dices.sortDices();

        // First check if we can reach a kniffel
        for (int i = 0; i < diceCounter.length; i++) {
            if (diceCounter[i] > 4 && getKniffel() == -1) {
                dices.setDicesToKeep(keepDices(dices, i));
                // System.out.println("Kniffel wurde ausgelöst");
                return false;
            }
        }

        // Then check for a full house
        if (getFullHouse() == -1) {
            if (checkFullHouse(dices)) {
                boolean[] dicesToKeep = { true, true, true, true, true };
                dices.setDicesToKeep(dicesToKeep);
                // System.out.println("Full House wurde ausgelöst");
                return false;
            }
        }
        // Then check if we can reach a Strasse
        if (getKleineStrasse() == -1 || getGrosseStrasse() == -1) {
            if (checkGrosseStrasse(dices)) {
                boolean[] dicesToKeep = { true, true, true, true, true };
                dices.setDicesToKeep(dicesToKeep);
                // System.out.println("Grosse Strasse wurde ausgelöst");
                return false;
            } else {
                // if (checkKleineStrasse(dices)) {
                //     boolean[] dicesToKeep = { true, true, true, true, true };
                //     dices.setDicesToKeep(dicesToKeep);
                //     // System.out.println("Kleine Strasse wurde ausgelöst");
                //     return false;
                // } else {
                    if (checkBeinaheStrasse(dices)) {
                        // System.out.println("Beinahe Strasse wurde ausgelöst");
                                                //  0      1      2      3      4
                        boolean[] dicesToKeep = { false, false, false, false, false };

                        int[] diceVal = dices.getDices(); // { 0, 1, 2, 3, 4, 5 }
                        //                       0      1      2      3      4      5
                        boolean[] valTaken = { false, false, false, false, false, false };
                        int consecutive = 1;
                        boolean consecutiveBreak = false;
                        for (int i = 1; i < diceVal.length; i++) {
                            if (diceVal[i] == diceVal[i - 1] + 1 && !consecutiveBreak) {
                                if(!valTaken[diceVal[i]-1]){
                                    dicesToKeep[i] = true;
                                    valTaken[diceVal[i]-1] = true;
                                }
                                if(!valTaken[diceVal[i - 1] - 1]){
                                    dicesToKeep[i - 1] = true;
                                    valTaken[diceVal[i - 1]-1] = true;
                                }
                                consecutive++;
                                if (consecutive >= 3) {
                                    dices.setDicesToKeep(dicesToKeep);
                                    return true;
                                }
                            } else {
                                consecutiveBreak = true;
                                for(int j = 0; j < dicesToKeep.length; j++) {
                                    dicesToKeep[j] = false;
                                }
                                for (int j = 0; j < valTaken.length; j++) {
                                    valTaken[j] = false;
                                }
                                consecutive = 1;
                            }
                        }
                        for (int i = 2; i < diceVal.length; i++) {
                            if (diceVal[i] == diceVal[i - 1] + 1 && consecutiveBreak) {
                                if(!valTaken[diceVal[i] -1 ]){
                                    dicesToKeep[i] = true;
                                    valTaken[diceVal[i] - 1] = true;
                                }
                                if(!valTaken[diceVal[i - 1] - 1]){
                                    dicesToKeep[i - 1] = true;
                                    valTaken[diceVal[i - 1] - 1] = true;
                                }

                                consecutive++;
                            }
                        }
                        dices.setDicesToKeep(dicesToKeep);
                        return true;
                    //}
                }

            }
        }

        // Reset dice counter
        diceCounter = resetDiceCounter(dices);
        // TODO Viererpasch and Dreierpasch need some optimisation so they wont trigger on early rounds if it consists only of low numbers.
        // Choose to keep Viererpasch if possible
        if (getViererpasch() == -1 && chooseDiceIndex(dices)[1] >= 4) {
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                } else {
                    if (dices.getDices()[i] > 3) {
                        keepDices[i] = true;
                    }
                }

            }
            dices.setDicesToKeep(keepDices);
            // System.out.println("Viererpasch wurde ausgelöst");
            return true;
        }
        // Choose to keep Dreierpasch if possible
        if (getDreierpasch() == -1 && chooseDiceIndex(dices)[1] >= 3) {
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                } else {
                    if (dices.getDices()[i] > 3) {
                        keepDices[i] = true;
                    }
                }

            }
            dices.setDicesToKeep(keepDices);
            // System.out.println("Dreierpasch wurde ausgelöst");
            return true;
        }
        // Choose to keep one of the top choices if available
        // Sechsen
        if (getSechsen() == -1 && chooseDiceIndex(dices)[0] == 5 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Sechsen wurde ausgelöst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Fuenfen
        if (getFuenfen() == -1 && chooseDiceIndex(dices)[0] == 4 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Fuenfen wurde ausgelöst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Vieren
        if (getVieren() == -1 && chooseDiceIndex(dices)[0] == 3 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Vieren wurde ausgelöst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Dreien
        if (getDreien() == -1 && chooseDiceIndex(dices)[0] == 2 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Dreien wurde ausgelöst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Zweien
        if (getZweien() == -1 && chooseDiceIndex(dices)[0] == 1 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Zweien wurde ausgelöst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Einsen
        if (getEinsen() == -1 && chooseDiceIndex(dices)[0] == 0 && chooseDiceIndex(dices)[1] >= 2) {
            // System.out.println("Einsen wurde ausgelösst");
            boolean[] keepDices = { false, false, false, false, false };
            for (int i = 0; i < dices.getDices().length; i++) {
                if (dices.getDices()[i] == chooseDiceIndex(dices)[0] + 1) {
                    keepDices[i] = true;
                }
            }
            dices.setDicesToKeep(keepDices);
            return true;
        }
        // Reroll every dice if we cant find anything
        // System.out.println("Reroll every dice wurde ausgelöst");
        boolean[] keepDices = { false, false, false, false, false };
        dices.setDicesToKeep(keepDices);
        return true;
    }

    public void computerScoreChoice(Dices dices, int round) {
        boolean alreadyChosen = false;       
        StringBuilder sbChoice = new StringBuilder(getSpace() + "           " + getName() + " ");

        // Eintragung ins Scoreboard nach Relevanz
        // Zuerst die versuchen den Unteren Teil des Scorebocks abzuschließen
        if (getKniffel() == -1 && checkKniffel(dices) && !alreadyChosen) {
            setKniffel(50);
            sbChoice.append("hat den Kniffel eingetragen!");
            alreadyChosen = true;
        }
        if (getFullHouse() == -1 && checkFullHouse(dices) && !alreadyChosen) {
            setFullHouse(25);
            sbChoice.append("hat das Full House eingetragen!");
            alreadyChosen = true;
        }
        if (getGrosseStrasse() == -1 && checkGrosseStrasse(dices) && !alreadyChosen) {
            setGrosseStrasse(40);
            sbChoice.append("hat die große Straße eingetragen!");
            alreadyChosen = true;
        }
        if (getKleineStrasse() == -1 && checkKleineStrasse(dices) && !alreadyChosen) {
            setKleineStrasse(30);
            sbChoice.append("hat die kleine Straße eingetragen!");
            alreadyChosen = true;
        }
        if (getViererpasch() == -1 && checkViererpasch(dices) && !alreadyChosen) {
            int viererpasch = 0;
            int[] sortedDices = dices.getSortedDices();
            for (int dice : sortedDices) {
                viererpasch += dice;
            }
            setViererpasch(viererpasch);
            sbChoice.append("hat den Viererpasch eingetragen!");
            alreadyChosen = true;
        }
        if (getDreierpasch() == -1 && checkDreierpasch(dices) && !alreadyChosen) {
            int dreierpasch = 0;
            int[] sortedDices = dices.getSortedDices();
            for (int dice : sortedDices) {
                dreierpasch += dice;
            }
            setDreierpasch(dreierpasch);
            sbChoice.append("hat den Dreierpasch eingetragen!");
            alreadyChosen = true;
        }

        // High value Rolls (mindestens 4 gleiche Würfel) bevorzugen!!
        if (getSechsen() == -1 && checkSechsen(dices) > 18 && !alreadyChosen) {
            calculateSechsen(dices);
            sbChoice.append("hat die Sechsen eingetragen!");
            alreadyChosen = true;
        }
        if (getFuenfen() == -1 && checkFuenfen(dices) > 15 && !alreadyChosen) {
            calculateFuenfen(dices);
            sbChoice.append("hat die Fünfen eingetragen!");
            alreadyChosen = true;
        }
        if (getVieren() == -1 && checkVieren(dices) > 12 && !alreadyChosen) {
            calculateVieren(dices);
            sbChoice.append("hat die Vieren eingetragen!");
            alreadyChosen = true;
        }
        if (getDreien() == -1 && checkDreien(dices) > 9 && !alreadyChosen) {
            calculateDreien(dices);
            sbChoice.append("hat die Dreien eingetragen!");
            alreadyChosen = true;
        }
        if (getZweien() == -1 && checkZweien(dices) > 6 && !alreadyChosen) {
            calculateZweien(dices);
            sbChoice.append("hat die Zweien eingetragen!");
            alreadyChosen = true;
        }
        if (getEinsen() == -1 && checkEinsen(dices) > 3 && !alreadyChosen) {
            calculateEinsen(dices);
            sbChoice.append("hat die Einsen eingetragen!");
            alreadyChosen = true;
        }

        

        // Low Value Rolls als Notlösung
        if (getEinsen() == -1 && checkEinsen(dices) >= 1 && !alreadyChosen) {
            calculateEinsen(dices);
            sbChoice.append("hat die Einsen eingetragen!");
            alreadyChosen = true;
        }
        if (getZweien() == -1 && checkZweien(dices) >= 2 && !alreadyChosen) {
            calculateZweien(dices);
            sbChoice.append("hat die Zweien eingetragen!");
            alreadyChosen = true;
        }
        if (getDreien() == -1 && checkDreien(dices) >= 3 && !alreadyChosen) {
            calculateDreien(dices);
            sbChoice.append("hat die Dreien eingetragen!");
            alreadyChosen = true;
        }
        if (getVieren() == -1 && checkVieren(dices) >= 8 && !alreadyChosen) {
            calculateVieren(dices);
            sbChoice.append("hat die Vieren eingetragen!");
            alreadyChosen = true;
        }
        if (getFuenfen() == -1 && checkFuenfen(dices) >= 10 && !alreadyChosen) {
            calculateFuenfen(dices);
            sbChoice.append("hat die Fuenfen eingetragen!");
            alreadyChosen = true;
        }
        if (getSechsen() == -1 && checkSechsen(dices) >= 12 && !alreadyChosen) {
            calculateSechsen(dices);
            sbChoice.append("hat die Sechsen eingetragen!");
            alreadyChosen = true;
        }
        if(getChance() == -1 && getDiceValues(dices) > 15 && !alreadyChosen){
            calculateChance(dices);
            sbChoice.append("hat die Chance eingetragen!");
            alreadyChosen = true;
        }


        // Streichen falls keine Eintragung möglich
        if(getKniffel() == -1 && !alreadyChosen){
            setKniffel(0);
            sbChoice.append("hat den Kniffel gestrichen!");
            alreadyChosen = true;
        }
        if(getGrosseStrasse() == -1 && !alreadyChosen){
            setGrosseStrasse(0);
            sbChoice.append("hat die große Straße gestrichen!");
            alreadyChosen = true;
        }
        if(getFullHouse() == -1 && !alreadyChosen){
            setFullHouse(0);
            sbChoice.append("hat das Full House gestrichen!");
            alreadyChosen = true;
        }
        if(getKleineStrasse() == -1 && !alreadyChosen){
            setKleineStrasse(0);
            sbChoice.append("hat die kleine Straße gestrichen!");
            alreadyChosen = true;
        }
        if(getViererpasch() == -1 && !alreadyChosen){
            setViererpasch(0);
            sbChoice.append("hat den Viererpasch gestrichen!");
            alreadyChosen = true;
        }
        if(getDreierpasch() == -1 && !alreadyChosen){
            setDreierpasch(0);
            sbChoice.append("hat den Dreierpasch gestrichen!");
            alreadyChosen = true;
        }
        if(getEinsen() == -1 && !alreadyChosen){
            setEinsen(0);
            sbChoice.append("hat die Einsen gestrichen!");
            alreadyChosen = true;
        }
        if(getZweien() == -1 && !alreadyChosen){
            setZweien(0);
            sbChoice.append("hat die Zweien gestrichen!");
            alreadyChosen = true;
        }
        if(getDreien() == -1 && !alreadyChosen){
            setDreien(0);
            sbChoice.append("hat die Dreien gestrichen!");
            alreadyChosen = true;
        }
        if(getVieren() == -1 && !alreadyChosen){
            setVieren(0);
            sbChoice.append("hat die Vieren gestrichen!");
            alreadyChosen = true;
        }
        if(getFuenfen() == -1 && !alreadyChosen){
            setFuenfen(0);
            sbChoice.append("hat die Fuenfen gestrichen!");
            alreadyChosen = true;
        }
        if(getSechsen() == -1 && !alreadyChosen){
            setSechsen(0);
            sbChoice.append("hat die Sechsen gestrichen!");
            alreadyChosen = true;
        }
        Console.printGFX(FileEnums.LOGO);
        Scoreboard.printScoreboard();
        System.out.printf(Console.space + "Runde %d: Ergebnis nach dem letzten Wurf von %s.\n", round, getName());
        dices.printDices(false);
        System.out.println(sbChoice.toString());
        

    }
}
