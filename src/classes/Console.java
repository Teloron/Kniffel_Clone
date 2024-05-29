package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Console {


    // Method to print the gfx files in Console
    public static void printGFX(FileEnums filename) {

        String space = "                                                ";
        if (filename.getFilepath() == null) {
            System.out.println("Could not determine filepath");
            return;
        }
        // open the file and print contents to console

        try (BufferedReader in = new BufferedReader(new FileReader(filename.getFilepath()))) {
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(space + filename.getPlaceholderText());
            // return;
        } catch (IOException e) {
            System.out.println(space + filename.getPlaceholderText());
        }
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
        System.out.printf("                                       PRESS ENTER TO CONTINUE");
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to pause the program and prompt the user to press enter to do anything
    public static void promptEnterKey(String message) {
        message = message.toUpperCase();
        int difference = 8 - message.length();

        System.out.printf("                                         ");
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

    public static String getInput() {



        Charset nativeCharset = Charset.forName(System.getProperty("native.encoding", Charset.defaultCharset().name()));
        String input = System.console().readLine("", nativeCharset);
        return input;
    }

}