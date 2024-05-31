package classes;


import java.util.Random;
import java.util.Arrays;

public class Dices {
    private int[] dices = new int[5];
    
    private boolean[] dicesToKeep = {false, false, false, false, false};

    // private int dice1 = 0;
    // private int dice2 = 0;
    // private int dice3 = 0;
    // private int dice4 = 0;
    // private int dice5 = 0;
    Random rand = new Random();

    // Method to get specific dice
    public int getDice(int diceNumber) {
        return switch(diceNumber) {
            case 1 -> dices[0];
            case 2 -> dices[1];
            case 3 -> dices[2];
            case 4 -> dices[3];
            case 5 -> dices[4];
            default -> 0;
        };
    }
    // Method to roll all dices
    public void rollAllDices() {
        for(int i = 0; i<dices.length; i++){
            dices[i] = rand.nextInt(6) + 1;
        }
    }
    // Method to roll secific Dice
    public void rollOneDice(int diceIndex) {
        dices[diceIndex] = rand.nextInt(6)+1;
    }
    // Method to reroll the chosen the dices
    public void rerollDices() {
        chooseDicesToKeep();
        for(int i = 0; i<dicesToKeep.length; i++) {
            if(!dicesToKeep[i]) {
                rollOneDice(i);
                dicesToKeep[i] = false;
            }
        }
    }
    // Method to sort the dices
    public int[] getSortedDices(){
        int[] sortedDices = new int[5];
        sortedDices = dices;
        Arrays.sort(sortedDices);
        return sortedDices;
    }

    // Method to choose which dices to reroll
    public void chooseDicesToKeep() {
        System.out.println("                                       Welche Würfel möchtest du behalten?");
        System.out.print("                                       Gewählte Würfel: ");
        
        String input = Console.getInput();
        
        String[] numbers = new String[0];
        numbers = input.split("");
        for(String number : numbers) {
            int diceIndex = 0;
            try{
                diceIndex = Integer.parseInt(number) - 1;
            }catch(NumberFormatException e){}
            try {
                dicesToKeep[diceIndex] = true;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("                    Ungültige Eingabe. Bitte gib je Würfel nur eine Zahl zwischen 1 und 5 ein.");
                chooseDicesToKeep();
            }
        }
    }

    // Method to print the dices to console in fancy way
    public void printDices(){
        String space = "                    ";
        char point = 'o', nopoint = ' ';
        // 1. Row
        System.out.print(space);
        for (int i = 0; i < dices.length; i++){
            System.out.printf("      _______ ");
        }
        System.out.println();
        // 2. Row
        System.out.print(space);
        for (int i = 0; i<dices.length;i++) {
            System.out.printf("     | %s %s %s |",
                    (dices[i] >=4 )? point:nopoint,
                    ' ',
                    (dices[i] !=1 )? point:nopoint);
        }
        System.out.println();
        // 3. Row
        System.out.print(space);
        for (int i = 0; i<dices.length;i++) {
            System.out.printf("     | %s %s %s |",
                    (dices[i] == 6 )? point:nopoint,
                    (dices[i] == 5 || dices[i] == 3 || dices[i] == 1)? point:nopoint,
                    (dices[i] == 6 )? point:nopoint);
        }

        System.out.println();
        // 4. Row
        System.out.print(space);
        for (int i = 0; i<dices.length;i++) {
            System.out.printf("     | %s %s %s |",
                    (dices[i] != 1 )? point:nopoint,
                    ' ',
                    (dices[i] >=4 )? point:nopoint);
        }
        System.out.println();
        // 5. Row
        System.out.print(space);
        for (int i = 0; i < dices.length; i++){
            System.out.printf("      ‾‾‾‾‾‾‾ ");
        }
        System.out.println();
                // Print dice Numbers
        System.out.print(space);
        for (int i = 0; i < dices.length; i++){
            System.out.printf("         %d    ",i+1);
        }
        System.out.println();
        

    }
        @Override
    public String toString() {
        return Arrays.toString(dices);
    }

}
