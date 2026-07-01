import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //To store all the tiles with the mines
import java.util.Random;
import javax.swing.*;
import java.util.List;
/*
WINNING CONDITION: Player must collect a lot of un-buttons buttons without clicking the bomb
LOSING CONDITION: Player clicks the bomb
*/
public class Minesweeper {
    private class MineTile extends JButton{
        int row, col;
        public MineTile(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    private String playerName;
    int tileSize = 70; //pixels
    int numRow = 8;
    int numCol = numRow;     
    int boardWidth = numCol * tileSize; //8 * 70 = 560 pixels
    int boardHeight = numRow * tileSize; //8 * 70 = 560 pixels
    JFrame frame = new JFrame("Minesweeper by II Landicho");
    JLabel txtLabel = new JLabel();
    JPanel txtPanel = new JPanel();
    JPanel brdPanel = new JPanel();
    int mineCount = 12;
    MineTile[][] board = new MineTile[numRow][numCol];
    ArrayList<MineTile> mineList;
    Random random = new Random();
    int tilesClicked = 0;
    boolean gameOver = false;
    
    Minesweeper(String playerName){        
        //Game Window
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //Center of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Game Text
        txtLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
        txtLabel.setForeground(Color.WHITE);
        txtLabel.setBackground(Color.DARK_GRAY);
        txtLabel.setHorizontalAlignment(JLabel.CENTER);
        txtLabel.setText("Minesweeper 12  |  Score: " + tilesClicked);
        txtLabel.setOpaque(true);
        txtPanel.setLayout(new BorderLayout());
        txtPanel.add(txtLabel); //txt to txtPanel
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        txtPanel.setBackground(Color.BLACK);
        frame.add(txtPanel, BorderLayout.NORTH); //txtPanel to JFrame

        //Game Board
        brdPanel.setLayout(new GridLayout(numRow, numCol)); // 8 x 8 Grid
        brdPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        brdPanel.setBackground(Color.BLACK);
        //brdPanel.setBackground(Color.BLACK);
        frame.add(brdPanel); //brdPanel to JFrame
        for(int r = 0; r < numRow; ++r){
            for(int c = 0; c < numCol; ++c){
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;
                tile.setFocusable(false); //No keyboard access
                tile.setBackground(new Color(220, 220, 220));
                tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                tile.setMargin(new Insets(0,0, 0, 0));
                tile.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
                //tile.setText("💣");
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if(gameOver){
                            return;
                        }
                        MineTile tile = (MineTile) e.getSource();
                        if(e.getButton() == MouseEvent.BUTTON1){ //Left click
                            if(tile.getText() == ""){
                                if(mineList.contains(tile)){
                                    revealMines();
                                }
                                else{
                                    checkMine(tile.row, tile.col);
                                }
                            }
                        }
                        else if (e.getButton() == MouseEvent.BUTTON3) { //Right click
                            if(tile.getText() == "" && tile.isEnabled()){
                                tile.setText("🚩");
                                tile.setForeground(Color.RED);
                            }
                            else if(tile.getText() == "🚩") {
                                tile.setText("");
                            }
                        }
                    }
                });
                brdPanel.add(tile);
            }
        }
        frame.setVisible(true);
        setMines();

        //Player Settings
        this.playerName = playerName;
    }

    void setMines(){
        mineList = new ArrayList<MineTile>();
        int minesLeft = mineCount;
        while(minesLeft > 0){
            int ranRow = random.nextInt(numRow);
            int ranCol = random.nextInt(numCol);
            MineTile tile = board[ranRow][ranCol];
            if(!mineList.contains(tile)){ //Tile vacancy checker
                mineList.add(tile);
                --minesLeft;
            }
        }
    }
    
    void revealMines(){
        for(int i = 0; i < mineList.size(); ++i){
            MineTile tile = mineList.get(i);
            tile.setText("💣");
            tile.setForeground(Color.BLACK);
        }
        txtLabel.setText("Game Over!");
        endGame(tilesClicked * 150);
    }

    void checkMine(int r, int c){
        if(r < 0 || r >= numRow || c < 0 || c >= numCol){ //Checking if within boundaries
            return;
        }
        MineTile tile = board[r][c];
        if(!tile.isEnabled()){
            return;
        }
        tile.setEnabled(false); //Unclickable button
        ++tilesClicked;
        txtLabel.setText("Minesweeper 12  |  Score: " + (tilesClicked * 150));
        int minesFound = 0;
        minesFound += countMines(r - 1 , c - 1); //top left
        minesFound += countMines(r - 1, c); //top
        minesFound += countMines(r - 1, c + 1); //top right
        minesFound += countMines(r, c - 1); //left
        minesFound += countMines(r, c + 1); //right
        minesFound += countMines(r + 1 , c - 1); //bottom left
        minesFound += countMines(r + 1, c); //bottom
        minesFound += countMines(r + 1, c + 1); //bottom right
        if(minesFound > 0){
            tile.setText(Integer.toString(minesFound));
            tile.setBackground(new Color(255, 255, 255));
        }
        else{ //Recursion func calls
            tile.setText("");
            tile.setBackground(new Color(255, 255, 255));
            checkMine(r - 1, c - 1); //top left
            checkMine(r - 1, c); //top
            checkMine(r - 1, c + 1); //top right
            checkMine(r, c - 1); //left
            checkMine(r, c + 1); //right
            checkMine(r + 1, c - 1); //bottom left
            checkMine(r + 1, c); //bottom
            checkMine(r + 1, c + 1); //bottom right
        }
        if(tilesClicked == (numRow * numCol) - mineList.size()){
            endGame(tilesClicked + 250);
            txtLabel.setText("Mines Cleared!");
        }
    }
    int countMines(int r, int c){
        if(r < 0 || r >= numRow || c < 0 || c >= numCol){ //Checkng if within boundaries
            return 0;
        }
        if(mineList.contains(board[r][c])){
            return 1;
        }
        return 0;
    }

    private void endGame(int score){
        Leaderboard.addScore(this.playerName, score);
        List<String> topScorers = Leaderboard.getTopScores();
        StringBuilder sb = new StringBuilder("=== TOP 10 LEADERBOARD ===\n");
        int rank = 0;
        for(String record : topScorers){
            sb.append(++rank).append(". ").append(record).append("\n");
        }
        sb.append("\n-----------------------------------\n");
        sb.append("Would you like to play again?");
        int playAgain = JOptionPane.showConfirmDialog(frame, 
            sb.toString(), "Game Over - Leaderboard",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        if(playAgain == JOptionPane.YES_OPTION){
            resetGame();
        }
        else{
            System.exit(0);
        }
    }

    public void resetGame(){
        tilesClicked = 0;
        gameOver = false;
        txtLabel.setText("Minesweeper " + Integer.toString(mineCount));
        mineList.clear();
        for (int r = 0; r < numRow; r++) {
            for (int c = 0; c < numCol; c++) {
                MineTile tile = board[r][c];
                tile.setEnabled(true);     
                tile.setText("");      
                tile.setBackground(new Color(220, 220, 220));
                tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }
        txtLabel.setText("Minesweeper 12  |  Score: " + tilesClicked);
        setMines();
    }
}