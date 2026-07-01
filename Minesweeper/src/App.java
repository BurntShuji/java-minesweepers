import javax.swing.JOptionPane;

/*
Project Name: Minesweeper Game
Version: 1.0
Date: June 30, 2026
Author: II Landicho (BurntShuji)
*/
public class App {
    public static void main(String[] args) throws Exception {
        String playerName = JOptionPane.showInputDialog(null, "Enter Player Name: ", "Minesweeper", JOptionPane.PLAIN_MESSAGE);
        if(playerName == null || playerName.trim().isEmpty()){
            playerName = "Anonymous";
        }

        Minesweeper minesweeper = new Minesweeper(playerName);
        Leaderboard leaderboard = new Leaderboard(playerName);
    }
}