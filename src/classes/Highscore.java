package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Highscore {
    private static boolean needNewScores = false;
    private static int highscoreLines = 0;
    private static String saveFile = "highscore.sav";
    private static String saveDirectory = "src/save/";
    private static String[] defaultNames = {"Joel", "Kratos", "Dominic","Henning","Joanne","Dimitios","Kai","Arthur","Brynhild","Sigrun"};

    // Method to create Highscore savefile and or folder if it does not exist
    public static void checkCreateHighscore() {
        // Check if savefile exists
        try (BufferedReader in = new BufferedReader(new FileReader(saveDirectory + saveFile))){
            String line = in.readLine();
            while (line != null) {
                highscoreLines++;
                line = in.readLine();
            }
            // Create new safe folder and or file if it is corrupted
            if(highscoreLines < 10 || highscoreLines > 10){
                createSavefile();
                needNewScores = true;
            }
        } catch (FileNotFoundException e) {
            // Create new safe folder and file if it does not exist
            createSavefile();
            needNewScores = true;
        } catch (IOException e) {
            System.out.println("Could not create Savefile");
        }
        if(needNewScores){
            writeNewHighscores();
        }
    }
    // Methode zum anlegen eines neuen default Highscores
    public static void writeNewHighscores(){
        try (BufferedWriter out = new BufferedWriter(new FileWriter(saveDirectory + saveFile))){
            highscoreLines = 10;
            for(int i = 0; i < 10; i++){
                String row = String.format("%2d - %s", 10-i, defaultNames[i]);
                out.write(row);
                if(highscoreLines > 1){
                out.newLine();
                highscoreLines--;
                }
                out.flush();
            }
          } catch (IOException e) {
            System.out.println("Highscore datei konnte nicht angelegt werden.");
          }
    }
    // Method to create Highscore savefile
    public static void createSavefile(){
        new File(saveDirectory).mkdirs();
        try {
            new File(saveDirectory + saveFile).createNewFile();
        } catch (IOException e1) {
            System.out.println("Highscore konnte nicht angelegt werden.");
        }
    }

    public static void showHighscore(){
        Console.printGFX(classes.FileEnums.LOGO);
        Console.printGFX(classes.FileEnums.PODIUM_RACER);
        printHighscore();
        System.out.println();
        System.out.print("       ");
        Console.promptEnterKey("EXIT");
    }

    public static void printHighscore(){
        try (BufferedReader in = new BufferedReader(new FileReader(saveDirectory + saveFile))){
            String line = in.readLine();
            while(line != null){
                System.out.printf("\t\t\t\t\t\t       %s%n",line);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Keine Highscores vorhanden");
        } catch (IOException e) {
            System.out.println("Fehler beim lesen des Highscores");
        }
    }

}
