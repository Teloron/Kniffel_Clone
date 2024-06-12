package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Highscore {
    private static boolean needNewScores = false;
    private static int highscoreLines = 0;
    private static String saveFile = "highscore.sav";
    private static String saveDirectory = "src/save/";
    private static String[] defaultNames = {"Joel", "Kratos", "Dominic","Henning","Joanne","Dimitios","Kai","Arthur","Brynhild","Sigrun"};

    private String name;
    private int score;

    public static List<Highscore> highscores = new ArrayList<Highscore>();

    public Highscore(int score, String name) {
        this.name = name;
        this.score = score;
        highscores.add(this);
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

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
            System.out.println(Console.menuSpace + "Highscore savefile konnte nicht angelegt werden.");
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
            System.out.println(Console.menuSpace + "Highscore datei konnte nicht angelegt werden.");
          }
    }
    // Method to create Highscore savefile
    public static void createSavefile(){
        new File(saveDirectory).mkdirs();
        try {
            new File(saveDirectory + saveFile).createNewFile();
        } catch (IOException e1) {
            System.out.println(Console.menuSpace + "Highscore konnte nicht angelegt werden.");
        }
    }

    public static void showHighscore(){
        Console.printGFX(FileEnums.LOGO);
        Console.printGFX(FileEnums.PODIUM_RACER);
        printHighscore();
        System.out.println();
        Console.promptEnterKey("EXIT");
    }

    public static void printHighscore(){
        try (BufferedReader in = new BufferedReader(new FileReader(saveDirectory + saveFile))){
            String line = in.readLine();
            while(line != null){
                System.out.println(Console.menuSpace +"      "+ line);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(Console.menuSpace + "Keine Highscores vorhanden");
        } catch (IOException e) {
            System.out.println(Console.menuSpace + "Fehler beim lesen des Highscores");
        }
    }
    public static boolean checkIfPlayerHasHighscore(Player player){
        int[] intScores = new int[11];
        try (BufferedReader in = new BufferedReader(new FileReader(saveDirectory + saveFile))){
            String line = in.readLine();
            String[] scores;
            int i = 0;
            while(line != null){
                line = line.replace(" ","");
                scores = line.split("-");
                intScores[i] = Integer.parseInt(scores[0]);
                System.out.println(intScores[i]);
                i++;
                line = in.readLine();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        for(int score : intScores){
            if(player.getEndsumme() > score){
                return true;
            }
        }
        return false;
    }
    public static void addPlayerToHighscore(Player player) {
        String[] row = new String[2];
        // Add Score of the Player to Highscores
        new Highscore(player.getEndsumme(), player.getName());
        // Read Highscores from File and append them to Highscores
        try (BufferedReader in = new BufferedReader(new FileReader(saveDirectory + saveFile))){
            String readLine = in.readLine();
            while(readLine != null){
                readLine = readLine.replace(" ","");
                row = readLine.split("-");
                new Highscore(Integer.parseInt(row[0]), row[1]);
                readLine = in.readLine();
            }
        }catch( IOException e) {
            e.printStackTrace();
        }
        // Sort Highscores by score
        Collections.sort(highscores, Comparator.comparing(Highscore::getScore).reversed());
        // Remove duplicates from Highscores List:
        // int[] marker = new int[highscores.size()];
        // for( int i = 0; i < marker.length; i++) {
        //     marker[i] = 0;
        // }
        // for(int i = 1; i < highscores.size(); i++) {
        //     if(highscores.get(i).getScore() == highscores.get(i - 1).getScore() && highscores.get(i).getName().equals(highscores.get(i - 1).getName())) {
        //         marker[i] = 1;
        //         i++;
        //     }
        // }
        // for(int i = 0; i < marker.length; i++) {
        //     if(marker[i] == 1) {
        //         highscores.remove(i);
        //     }
        // }

        // Overwrite current Highscores file with new top 10 Highscores
        try (BufferedWriter out = new BufferedWriter(new FileWriter(saveDirectory + saveFile))){
            highscoreLines = 10;
            for (int i = 0; i<10; i++) {
                Highscore score = highscores.get(i);
                String newRow = String.format("%2d - %s", score.getScore(), score.getName());
                out.write(newRow);
                if(highscoreLines > 1){
                out.newLine();
                highscoreLines--;
                }
                out.flush();
            }
          } catch (IOException e) {
            System.out.println(Console.space + "Highscore konnte nicht angelegt werden.");
          }
        // Clear Highscores list for next function call
        highscores.clear();
    }
}
