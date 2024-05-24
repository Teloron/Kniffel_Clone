package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Console {

    private static String filepath;

    // Method to print the gfx files in Console
    public static void printGFX(String filename) {
        // get the filepath of the gfx file
        filepath = getFilepath(filename.toUpperCase());
        if (filepath == null) {
            System.out.println("File has no path");
            return;
        }
        // open the file and print contents to console
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
            return;
        }
        try {
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filepath);
        }
    }

    // Method to get the path to gfx file through the enum
    private static String getFilepath(String filename) {
        Files file = Files.valueOf(filename.toUpperCase());
        // get the filepath of the gfx file and return it
        return switch (file) {
            case LOGO -> "src/assets/logo.gfx";
            case CREDITS -> "src/assets/credits.gfx";
            case DOMINIC -> "src/assets/dominic.gfx";
            case EXIT -> "src/assets/exit.gfx";
            case HENNING -> "src/assets/henning.gfx";
            case KAI -> "src/assets/kai.gfx";
            case PODIUM -> "src/assets/podium.gfx";
            case PODIUM_SMALL -> "src/assets/podium_small.gfx";
            case PODIUM_RACER -> "src/assets/podium_racer.gfx";
            case SPREE -> "src/assets/spree.gfx";
            case WINNER -> "src/assets/winner.gfx";
            default -> null;
        };
    }

    // Method to clear the console Window
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (final Exception e) {
            // Handle any exceptions.
        }
    }

    // Method to pause the program and prompt the user to press enter to continue
    public static void promptEnterKey() {
        System.out.printf("\t\t\t\t\t    PRESS ENTER TO CONTINUE");
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getInput() {
        return System.console().readLine();
    }

    // Method to pause the program and prompt the user to press enter to do anything
    public static void promptEnterKey(String message) {
        message = message.toUpperCase();
        int difference = 8 - message.length();

        System.out.printf("\t\t\t\t\t    ");
        for (int i = 0; i < difference; i++) {
            System.out.printf(" ");
        }
        System.out.printf("PRESS ENTER TO %s", message);
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}