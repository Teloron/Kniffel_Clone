package classes;

public class Credits extends Console {

    private static void printCreditHeader() {
        Console.clear();
        Console.printGFX("logo");
        // Console.printGFX("podium");
        System.out.println("\t\t\t\t\t\t  Ein Spiel von:");
        Console.printGFX("spree");
    }

    public static void printCredits() {
        // printCreditHeader();

        // Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\t   Projektidee:");
        Console.printGFX("henning");
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tUse Case Designer:");
        Console.printGFX("kai");
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tUmsetzung in C:");
        Console.printGFX("dominic");
        Console.printGFX("henning");
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tPortierung in Java:");
        Console.printGFX("dominic");
        System.out.print("      ");
        Console.promptEnterKey();
        Console.clear();
    }

}
