import java.io.*;
import java.util.*;

public class Leaderboard {
    private String currentPlayer;
    private static final String FILE_NAME = "minesweepLeadB.txt";
    
    Leaderboard(String player){
        this.currentPlayer = player;
    }

    public String getCurrentPlayer(){
        return this.currentPlayer;
    }

    public static void addScore(String player, int score){
        try(FileWriter writer = new FileWriter(FILE_NAME, true)){
            writer.write(player + ", " + score + "\n");
        } catch(IOException e){
            System.out.println("Error saving score.");
        }
    }
    
    public static List<String> getTopScores(){
        List<String> scores = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null){
                scores.add(line);
            }
        } catch (IOException e){
            return scores;
        }
        scores.sort((a,b) -> {
            int scoreA = Integer.parseInt(a.split(",")[1].trim());
            int scoreB = Integer.parseInt(b.split(",")[1].trim());
            return scoreB - scoreA;
        });
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }
}
