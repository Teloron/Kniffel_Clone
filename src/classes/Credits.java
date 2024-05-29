package classes;

public class Credits extends Console {

    private static void printCreditHeader() {
        Console.clear();
        Console.printGFX(FileEnums.LOGO);
        // Console.printGFX("podium");
        System.out.println("\t\t\t\t\t\t  Ein Spiel von:");
        Console.printGFX(FileEnums.SPREE);
    }

    public static void printCredits() {
        // printCreditHeader();

        // Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\t   Projektidee:");
        Console.printGFX(FileEnums.HENNING);
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tUse Case Designer:");
        Console.printGFX(FileEnums.DOMINIC);
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tUmsetzung in C:");
        Console.printGFX(FileEnums.DOMINIC);
        Console.printGFX(FileEnums.HENNING);
        System.out.print("      ");
        Console.promptEnterKey();
        printCreditHeader();
        System.out.println("\t\t\t\t\t\tPortierung in Java:");
        Console.printGFX(FileEnums.DOMINIC);
        System.out.print("      ");
        Console.promptEnterKey();
        Console.clear();
    }

}
