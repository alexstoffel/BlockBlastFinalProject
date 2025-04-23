// Alex Stoffel
// This class if my backend

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class BlockBlast implements MouseListener, MouseMotionListener {
    // Instance variables
    private int[][] board;
    private BlockBlastView window;
    private static final int BOARD_SIZE = 8;
    private static final int PIECE_SIZE = 5;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private ArrayList<Block> pieces;
    private boolean gameOver;
    private int stage;
    private ArrayList<Block> unplacedPieces;
    // This variable accounts for the block currently being dragged
    private Block blockBeingDragged;
    private int blockBeingDragged_InitialState;
    private static final int BOARD_TOP_X = 30;
    private static final int BOARD_TOP_Y = 130;
    private int score;
    private int numBlasts;



    // Constructor
    public BlockBlast(){
        this.score = 0; // This is important to have here because I want score printed out immediately
        window = new BlockBlastView(this);

        // Adding the mouse thing
        window.addMouseListener(this);
        window.addMouseMotionListener(this);

        // Initializing all the variables
        gameOver = false;
        stage = 1;
        unplacedPieces = new ArrayList<Block>();
        blockBeingDragged = null;

        // Creating and filling in the board
        // Note - board size is 13 for (INSERT REASON LATER!!!), but all we care about is the 8*8 board
        board = new int[13][13];
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = 0;
            }
        }

        // Creating and adding all of the pieces
        pieces = new ArrayList<Block>();
        // ASK MRS Namazivayam if there is a more efficient way to do this
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));
        pieces.add(new Block(new int[][]{
                {0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));

    }



    // Play Round function, which will play out a round
    public void playRound() {
        // Beggining of each round
        this.stage = 2;
        // Setting the three unplaced pieces
        unplacedPieces = this.getThreePieces();
        window.repaint();
    }



    // Will return three random pieces from pieces arrayList
    public ArrayList<Block> getThreePieces() {
        // ArrayList that will store them
        ArrayList<Block> p = new ArrayList<Block>(3);
        // For loop that will run through 3 times
        for (int i = 0; i < 3; i++){
            // Add a new block which creates coppy of one from main bank pieces
            p.add(new Block(this.pieces.get((int)(Math.random() * this.pieces.size())).getBlock(), 0, 0));
            // Set the state so it knows where to draw itself
            p.get(i).setState(i);
        }
        return p;
    }



    // Will return true if game is over
    public boolean checkGameOver(){
        // Create new ArrayList which will only have the non-null values in unplaced pieces
        ArrayList<Block> nonNull = new ArrayList<Block>();
        for (int i = 0; i < unplacedPieces.size(); i++){
            // If it is not null, add
            if (unplacedPieces.get(i) != null){
                nonNull.add(unplacedPieces.get(i));
            }
        }
        // Check for every block in unplaced box if there are any moves possible
        for (Block block: nonNull){
            // If the block isn't placed yet
            if (!block.isPlaced()){
                for (int i = 0; i < BOARD_SIZE; i++){
                    for (int j = 0; j < BOARD_SIZE; j++){
                        // If there is a valid move, return false to game over
                        if (isValid(block, i, j)){
                            return false;
                        }
                    }
                }
            }
        }
        // Game is over if there were no valid moves
        return true;
    }

    // This method will check if the spot is valid
    public boolean isValid(Block b, int col, int row){
        // Check for out of bounds errors
        for (int i = 0; i < PIECE_SIZE; i++){
            for (int j = 0; j < PIECE_SIZE; j++){
                if (b.getPiece(i, j) == 1){
                    // Only check for cells that have value of 1 (are prt of the shape)
                    int board_row = i + row;
                    int board_col = j + col;

                    // Check if it is out of bounds
                    if (board_row < 0 || board_row >= BOARD_SIZE || board_col < 0 || board_col >= BOARD_SIZE){
                        return false;
                    }

                    // Check for overlap
                    if (this.board[board_row][board_col] == 1){
                        return false;
                    }

                }
            }
        }
        return true;
    }

    // This will add the piece to the board
    public void addPiece(Block b, int row, int col){
        for (int i = 0; i < PIECE_SIZE; i++){
            for (int j = 0; j < PIECE_SIZE; j++){
                // Only add it if it holds a value
                if (b.getPiece(i,j) == 1) {
                    this.board[i + row][j + col] = b.getPiece(i, j);
                    // Add to the score
                    this.score += 1;
                    // Add the color to the block on the board
                    window.setColor(i + row, j + col, b.getColor());
                }
            }
        }
    }

    // This will check for a "blast" every time after a piece is placed
    public void checkBlast(){
        int rowSum = 0;
        int colSum = 0;
        // This will effect the score -- the more blasts per block the more points
        this.numBlasts = 0;

        // For loops to iterate through the board and check for a blast
        for (int i = 0; i < BOARD_SIZE; i++){
            rowSum = 0;
            colSum = 0;
            for (int j = 0; j < BOARD_SIZE; j++){
                // Add the values to colSum and rowSum
                rowSum += this.board[i][j];
                colSum += this.board[j][i];
            }
            // Check if either colSum or rowSum is equal to eight, meaning a blast must happen
            if (colSum >= 8){
                // Blast the col
                this.numBlasts += 1;
                this.blast("col", i);
            }
            if (rowSum >= 8){
                // Blast the row
                this.numBlasts += 1;
                this.blast("row", i);
            }
        }
    }



    // the Blast function
    public void blast(String str, int num){
        // If str is col, blast the column
        if (str.equals("col")){
            for (int i = 0; i < BOARD_SIZE; i++){
                // Setting each block in that col to 0
                board[i][num] = 0;
            }
        }else{
            for (int i = 0; i < BOARD_SIZE; i++){
                // Setting each block in the row to 0
                board[num][i] = 0;
            }
        }
        // Change the score
        score += 8 * numBlasts;
    }

    // Get the score
    public int getScore(){
        return this.score;
    }

    // Get the game stage
    public int getStage(){
        return this.stage;
    }

    // Get the unplaced pieces
    public ArrayList<Block> getUnplacedPieces(){
        return this.unplacedPieces;
    }

    // Methods for mouth listener
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Set the block being dragged variable
        int x = e.getX();
        int y = e.getY();

        // Loop through all the blocks
        for (Block block: unplacedPieces){
            if (block.isClicked(x, y)){
                blockBeingDragged = block;
                blockBeingDragged_InitialState = block.getState();
                block.setIsBeingDragged(true);
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // All of this only if Block being dragged isnt null
        if (blockBeingDragged != null) {
            // If the block is placed in a valid spot
            int x = e.getX();
            int y = e.getY();
            // Find what square this is a part of and click it into place and set X and Y to the top left corner of that square
            x = (x - BOARD_TOP_X) / 55;
            y = (y - BOARD_TOP_Y) / 55;

            // Check if it is a valid spot
            if (isValid(blockBeingDragged, x, y)) {
                // Add the piece to the board
                addPiece(blockBeingDragged, y, x);
                // Click the piece into place
                blockBeingDragged.setX(x * 55 + BOARD_TOP_X);
                blockBeingDragged.setY(y * 55 + BOARD_TOP_Y);
                // Update that the block has been placed
                blockBeingDragged.setPlaced(true);
                // Check for a Blast
                this.checkBlast();
            } else {
                // Reset the piece to its original place because not a valid spot
                blockBeingDragged.setState(blockBeingDragged_InitialState);
            }

            // Set the is being dragged to false
            this.blockBeingDragged.setIsBeingDragged(false);
            this.blockBeingDragged = null;
            window.repaint();

            // Check to see if all unplacedPieces have been placed, and if they have start a new round
            boolean newRound = true;
            // Iterate through and if any unplaced piece hasnt been placed, no new round
            for (int i = 0; i < 3; i++){
                if (!unplacedPieces.get(i).isPlaced()){
                    // No new round
                    newRound = false;
                    break;
                }
            }
            // If there is a newRound possible, set round to 0 and this will begin a new round and add more to unplaced pieces
            if (newRound){
                this.playRound();
            }

            // Check if the Game is Over only if Unplaced pieces isnt null
            if (this.unplacedPieces != null && this.checkGameOver()){
                this.gameOver = true;
                window.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Have pieces follow the dragging mouse
        int x = e.getX();
        int y = e.getY();

        // Check each block if it is being dragged currently
        for (Block block : this.unplacedPieces){
            if(block.isBeingDragged()){
                // This means it is no longer an "unplaced piece"
                block.setState(3);
                // Updating where the box is to where the mouse is (it will follow mouse off the window too)
                block.setX(Math.max(0, Math.min(x, WINDOW_WIDTH)));
                block.setY(Math.max(0, Math.min(y, WINDOW_HEIGHT)));

                window.repaint();
            }

        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    // Getter for the board
    public int[][] getBoard(){
        return this.board;
    }



    // The Main function
    public static void main(String[] args) {
        BlockBlast b = new BlockBlast();
        b.playRound();
    }

}


