package classes;


import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private int playerNumber = 1;
    private String name = "default";
    private int score = 0;
    private int einsen = 0;
    private int zweien = 0;
    private int dreien = 0;
    private int vieren = 0;
    private int fuenfen = 0;
    private int sechsen = 0;
    private int summeOben = 0;
    private int bonus = 0;
    private int gesamtOben = 0;
    private int dreierpasch = 0;
    private int viererpasch = 0;
    private int fullHouse = 0;
    private int kleineStrasse = 0;
    private int grosseStrasse = 0;
    private int kniffel = 0;
    private int chance = 0;
    private int gesamtUnten = 0;
    private int endsumme = 0;
    private boolean finished = false;
    boolean isActive = true;

    public static List<Player> players = new ArrayList<Player>();
    // Method to add new players
    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        players.add(this);
    }
    public Player(int playerNumber, String name, boolean isActive) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.isActive = isActive;
        players.add(this);
    }
    public static void returnPlayerNames(){
        for( Player player : players){
            System.out.println(player.name);
        }
    }
    public static String choosePlayerName(int playerNumber){
        System.out.printf("                                   Bitte den Namen von Spieler %d eingeben: ", playerNumber);
        
        String playerName = Console.getInput();
        return playerName;
    }
    // Method to show players score
    public void showPlayerScore(){
        System.out.println("Name: " + name);
        System.out.println("Score: " + score);
        System.out.println("Einsen: " + einsen);
        System.out.println("Zweien: " + zweien);
        System.out.println("Dreien: " + dreien);
        System.out.println("Vieren: " + vieren);
        System.out.println("Fuenfen: " + fuenfen);
        System.out.println("Sechsen: " + sechsen);
        System.out.println("SummeOben: " + summeOben);
        System.out.println("Bonus: " + bonus);
        System.out.println("GesamtOben: " + gesamtOben);
        System.out.println("Dreierpasch: " + dreierpasch);
        System.out.println("Viererpasch: " + viererpasch);
        System.out.println("FullHouse: " + fullHouse);
        System.out.println("KleineStrasse: " + kleineStrasse);
        System.out.println("GroßeStrasse: " + grosseStrasse);
        System.out.println("Kniffel: " + kniffel);
        System.out.println("Chance: " + chance);
        System.out.println("GesamtUnten: " + gesamtUnten);
        System.out.println("Endsumme: " + endsumme);
    }
    public int getCalculateSummeOben(){
        return (einsen + zweien + dreien + vieren + fuenfen + sechsen);
    }
    public void calculateSummeOben(){
        summeOben = einsen + zweien + dreien + vieren + fuenfen + sechsen;
    }
    
    public int getCalculateBonus(){
        if (summeOben >= 63){
            return 35;
        }else{
            return 0;
        }
        
    }
    public void calculateBonus(){
        if (summeOben >= 63){
            bonus = 35;
        }
    }
    public int getCalculateGesamtOben(){
        return (getCalculateSummeOben() + getCalculateBonus());
    
    }
    public void calculateGesamtOben(){
        gesamtOben = getCalculateSummeOben() + getCalculateBonus();
    }
    public void calculateGesamtUnten(){
        gesamtUnten = (dreierpasch + viererpasch + fullHouse + kleineStrasse + grosseStrasse + kniffel + chance);
    }
    public int getCalculateGesamtUnten(){
        return (dreierpasch + viererpasch + fullHouse + kleineStrasse + grosseStrasse + kniffel + chance);
        
    }
    
    public void calculateEinsen(Dices dices){
        for(int i = 0; i < 5; i++){
            if(dices.getDice(i) == 1){
                einsen++;
            }        
        }
    }
    
    // Getter 
    public int getPlayerNumber(){
        return playerNumber;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public int getEinsen(){
        return einsen;
    }
    public int getZweien(){
        return zweien;
    }
    public int getDreien(){
        return dreien;
    }
    public int getVieren(){
        return vieren;
    }
    public int getFuenfen(){
        return fuenfen;
    }
    public int getSechsen(){
        return sechsen;
    }
    public int getSummeOben(){
        return summeOben;
    }
    public int getBonus(){
        return bonus;
    }
    public int getGesamtOben(){
        return gesamtOben;
    }
    public int getDreierpasch(){
        return dreierpasch;
    }
    public int getViererpasch(){
        return viererpasch;
    }
    public int getFullHouse(){
        return fullHouse;
    }
    public int getKleineStrasse(){
        return kleineStrasse;
    }
    public int getGroßeStrasse(){
        return grosseStrasse;
    }
    public int getKniffel(){
        return kniffel;
    }
    public int getChance(){
        return chance;
    }
    public int getGesamtUnten(){
        return gesamtUnten;
    }
    public int getEndsumme(){
        return endsumme;
    }
    public boolean getFinished(){
        return finished;
    }

    // Setter
    public void setScore(int score){
        this.score = score;
    }
    public void setEinsen(int einsen){
        this.einsen = einsen;
    }
    public void setZweien(int zweien){
        this.zweien = zweien;
    }
    public void setDreien(int dreien){
        this.dreien = dreien;
    }
    public void setVieren(int vieren){
        this.vieren = vieren;
    }
    public void setFuenfen(int fuenfen){
        this.fuenfen = fuenfen;
    }
    public void setSechsen(int sechsen){
        this.sechsen = sechsen;
    }
    public void setSummeOben(int summeOben){
        this.summeOben = summeOben;
    }
    public void setBonus(int bonus){
        this.bonus = bonus;
    }
    public void setGesamtOben(int gesamtOben){
        this.gesamtOben = gesamtOben;
    }
    public void setDreierpasch(int dreierpasch){
        this.dreierpasch = dreierpasch;
    }
    public void setViererpasch(int viererpasch){
        this.viererpasch = viererpasch;
    }
    public void setFullHouse(int fullHouse){
        this.fullHouse = fullHouse;
    }
    public void setKleineStrasse(int kleineStrasse){
        this.kleineStrasse = kleineStrasse;
    }
    public void setGroßeStrasse(int grosseStrasse){
        this.grosseStrasse = grosseStrasse;
    }
    public void setKniffel(int kniffel){
        this.kniffel = kniffel;
    }
    public void setChance(int chance){
        this.chance = chance;
    }
    public void setGesamtUnten(int gesamtUnten){
        this.gesamtUnten = gesamtUnten;
    }
    public void setEndsumme(int endsumme){
        this.endsumme = endsumme;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }

}
