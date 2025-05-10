// Alex Stoffel
// This class is my backend

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BlockBlast implements MouseListener, MouseMotionListener {
    // Instance variables
    private final int[][] board;
    private final BlockBlastView window;
    public static final int BOARD_SIZE = 8;
    private static final int PIECE_SIZE = 5;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private final ArrayList<Block> pieces;
    private boolean gameOver;
    private int stage;
    private ArrayList<Block> unplacedPieces;
    // This variable accounts for the block currently being dragged
    private Block blockBeingDragged;
    private int blockBeingDragged_InitialState;
    private static final int BOARD_TOP_X = 30;
    private static final int BOARD_TOP_Y = 130;
    private int score;
    private int counter;
    private int multiplier;
    private int numBlasts;
    private static final int NUM_BLOCKS = 31;
    public ArrayList<Integer[][]> blocksOutline;


    // Constructor
    public BlockBlast() {
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
        blocksOutline = new ArrayList<Integer[][]>(NUM_BLOCKS);
        counter = 0;
        multiplier = 1;

        // Creating and filling in the board
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }

        // Creating and adding all of the pieces
        pieces = new ArrayList<Block>();
        // This will fill the pieces array
        this.loadBlocks();
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
        for (int i = 0; i < 3; i++) {
            // Add a new block which creates coppy of one from main bank pieces
            p.add(new Block(this.pieces.get((int) (Math.random() * this.pieces.size())).getBlock(), 0, 0, window));
            // Set the state so it knows where to draw itself
            p.get(i).setState(i);
        }
        return p;
    }


    // Will return true if game is over
    public boolean checkGameOver() {
        // Create new ArrayList which will only have the non-null values in unplaced pieces
        ArrayList<Block> nonNull = new ArrayList<Block>();
        for (int i = 0; i < unplacedPieces.size(); i++) {
            // If it is not null, add
            if (unplacedPieces.get(i) != null) {
                nonNull.add(unplacedPieces.get(i));
            }
        }
        // Check for every block in unplaced box if there are any moves possible
        for (Block block : nonNull) {
            // If the block isn't placed yet
            if (!block.isPlaced()) {
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        // If there is a valid move, return false to game over
                        if (isValid(block, i, j)) {
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
    public boolean isValid(Block b, int col, int row) {
        // Check for out of bounds errors
        for (int i = 0; i < PIECE_SIZE; i++) {
            for (int j = 0; j < PIECE_SIZE; j++) {
                if (b.getPiece(i, j) == 1) {
                    // Only check for cells that have value of 1 (are prt of the shape)
                    int board_row = i + row;
                    int board_col = j + col;

                    // Check if it is out of bounds
                    if (board_row < 0 || board_row >= BOARD_SIZE || board_col < 0 || board_col >= BOARD_SIZE) {
                        return false;
                    }

                    // Check for overlap
                    if (this.board[board_row][board_col] == 1) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    // This will add the piece to the board
    public void addPiece(Block b, int row, int col) {
        for (int i = 0; i < PIECE_SIZE; i++) {
            for (int j = 0; j < PIECE_SIZE; j++) {
                // Only add it if it holds a value
                if (b.getPiece(i, j) == 1) {
                    this.board[i + row][j + col] = b.getPiece(i, j);
                    // Add to the score
                    this.score += multiplier;
                    // Add the color to the block on the board
                    window.setColor(i + row, j + col, b.getColor());
                }
            }
        }
        // Add to counter that a piece is added
        counter += 1;
        if (counter == 3) {
            // Reset the multiplier
            multiplier = 1;
            // Reset the counter
            counter = 0;
        }
    }

    // This will check for a "blast" every time after a piece is placed
    public void checkBlast() {
        // This will affect the score -- the more blasts per block the more points
        this.numBlasts = 0;
        boolean rowSumLarger;

        // For loops to iterate through the board and check for a blast
        for (int i = 0; i < BOARD_SIZE; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                // Add the values to colSum and rowSum
                rowSum += this.board[i][j];
                colSum += this.board[j][i];
            }
            // Check if rowSum is greater than before the col Blast
            rowSumLarger = rowSum >= BOARD_SIZE;
            // Check if either colSum or rowSum is equal to eight, meaning a blast must happen
            if (colSum >= BOARD_SIZE) {
                // Blast the col
                this.numBlasts += 1;
                this.blast("col", i);
            }
            if (rowSumLarger) {
                // Blast the row
                this.numBlasts += 1;
                this.blast("row", i);
            }
        }
    }


    // the Blast function
    public void blast(String str, int num) {
        // If str is col, blast the column
        if (str.equals("col")) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                // Setting each block in that col to 0
                board[i][num] = 0;
            }
        } else {
            for (int i = 0; i < BOARD_SIZE; i++) {
                // Setting each block in the row to 0
                board[num][i] = 0;
            }
        }
        // If last blast was less than three ago
        if (counter < 3) {
            counter = 0;
            multiplier += 1;
        }
        // Change the score
        score += 8 * numBlasts * multiplier;
    }

    // Get the score
    public int getScore() {
        return this.score;
    }

    // Get the game stage
    public int getStage() {
        return this.stage;
    }

    // Get the unplaced pieces
    public ArrayList<Block> getUnplacedPieces() {
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
        for (Block block : unplacedPieces) {
            if (block.isClicked(x, y)) {
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
            x = (x - BOARD_TOP_X + 10) / BlockBlastView.SQUARE_SIZE;
            y = (y - BOARD_TOP_Y + 10) / BlockBlastView.SQUARE_SIZE;

            // Check if it is a valid spot
            if (isValid(blockBeingDragged, x, y)) {
                // Add the piece to the board
                addPiece(blockBeingDragged, y, x);
                // Click the piece into place
                blockBeingDragged.setX(x * BlockBlastView.SQUARE_SIZE + BOARD_TOP_X);
                blockBeingDragged.setY(y * BlockBlastView.SQUARE_SIZE + BOARD_TOP_Y);
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
            for (int i = 0; i < 3; i++) {
                if (!unplacedPieces.get(i).isPlaced()) {
                    // No new round
                    newRound = false;
                    break;
                }
            }
            // If there is a newRound possible, set round to 0 and this will begin a new round and add more to unplaced pieces
            if (newRound) {
                this.playRound();
            }

            // Check if the Game is Over only if Unplaced pieces isnt null
            if (this.unplacedPieces != null && this.checkGameOver()) {
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
        for (Block block : this.unplacedPieces) {
            if (block.isBeingDragged()) {
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
    public int[][] getBoard() {
        return this.board;
    }

    // Getter for the multiplier
    public int getMultiplier() {
        return this.multiplier;
    }

    // Load in the Blocks
    public void loadBlocks() {
        Scanner s;
        // This will be reading in from the file blocks.txt
        File blockFile = new File("Resources/blocks.txt");
        try {
            s = new Scanner(blockFile);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open dictionary file.");
            return;
        }

        // While loop which will prevent all the out of bounds errors:
        while (s.hasNextLine()) {
            int[][] block = new int[PIECE_SIZE][PIECE_SIZE];
            // Loop through and add the numbers
            for (int j = 0; j < PIECE_SIZE; j++) {
                // Make sure there is a next line
                if (!s.hasNextLine()) {
                    break;
                }
                // Create a line which stores the whole row of block
                String numbers = s.nextLine();
                for (int k = 0; k < PIECE_SIZE; k++) {
                    // Adding in the pieces
                    block[j][k] = Integer.parseInt(numbers.substring(k, k + 1));
                }
            }
            // Add the new block to pieces
            pieces.add(new Block(block, 0, 0, window));

            // Skipping the blank line
            if (s.hasNextLine()) {
                s.nextLine();
            }
        }
    }


    // The Main function
    public static void main(String[] args) {
        BlockBlast b = new BlockBlast();
        b.playRound();
    }

}


