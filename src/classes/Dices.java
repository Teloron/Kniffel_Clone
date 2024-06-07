package classes;


import java.util.Random;
import java.util.Arrays;

public class Dices {
    private int[] dices = new int[5];

    private boolean[] dicesToKeep = new boolean[5];
    

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
    public boolean rerollDices() {
        // for(boolean keeper : dicesToKeep) {
        //     keeper = false;
        // }
        chooseDicesToKeep();
        boolean rerolled = false;
        for(int i = 0; i < dices.length; i++) {
            if(!dicesToKeep[i]) {
                rollOneDice(i);
                dicesToKeep[i] = false;
                rerolled = true;
            }
        }
        return rerolled;
    }
    // Method to sort the dices
    public int[] getSortedDices(){
        int[] sortedDices = new int[5];
        sortedDices = dices;
        Arrays.sort(sortedDices);
        return sortedDices;
    }

    public void sortDices(){
        Arrays.sort(dices);
    }

    // Method to choose which dices to reroll
    // TODO Still have to fix this Method
    public void chooseDicesToKeep() {
        System.out.println(Console.space + "Welche Würfel möchtest du behalten?");
        System.out.println(Console.space + "(B) um alle würfel zu behhalten");
        System.out.print(Console.space + "Gewählte Würfel: ");
        
        String input = Console.getInput().toUpperCase();
        if (input.equals("")) {
            return;
        }
        for(int i = 0; i < dicesToKeep.length; i++) {
            dicesToKeep[i] = false;
        }
        String[] numbers;
        numbers = input.split("");
        for(String number : numbers) {
            if(number.equals("B")) {
                for(int i = 0; i < dicesToKeep.length; i++) {
                    dicesToKeep[i] = true;
                    
                }
                return;
            }
            int diceIndex = 0;
            try{
                diceIndex = Integer.parseInt(number);
            }catch(NumberFormatException e){}
            try {
                dicesToKeep[diceIndex-1] = true;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println(Console.space + "Ungültige Eingabe. Bitte gib je Würfel nur eine Zahl zwischen 1 und 5 ein.");
                chooseDicesToKeep();
            }
        }
    }

    // Method to print the dices to console in fancy way
    public void printDices(boolean sub){
        String space = "                   ";
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
        if(sub){
            for (int i = 0; i < dices.length; i++){
            System.out.printf("         %d    ",i+1);
            
        }
            System.out.println();
        }else{
            System.out.println();
        }
        
        

    }
        @Override
    public String toString() {
        return Arrays.toString(dices);
    }

}
