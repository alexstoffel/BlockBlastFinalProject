// Alex Stoffel
// This class if my backend

import java.sql.Array;
import java.util.ArrayList;

public class BlockBlast {
    // Instance variables
    private int[][] board;
    private BlockBlastView window;
    private static final int SLEEP_TIME = 110;
    private static final int BOARD_SIZE = 8;
    private ArrayList<Block> pieces;
    private boolean gameOver;
    // ArrayList of the three pieces each round
    private ArrayList<Block> threePieces;
    private int stage;


    // Constructor
    public BlockBlast(){
        window = new BlockBlastView(this);
        gameOver = false;
        threePieces = new ArrayList<Block>(3);
        stage = 1;

        // Creating and filling in the board
        board = new int[BOARD_SIZE][BOARD_SIZE];
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
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
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
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
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
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
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
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
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
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, 0,0));

    }

    // Action performed
    public void play() {
        // Beggining of each round
        window.repaint();
        this.stage = 2;

        while (gameOver == false){
            // Set the three blocks for the beggining of each round
            Block block = null;
            for (int i = 0; i < 3; i++){
                // Get a random piece and assign it's placed and put it in the three pieces of each Round
                block = pieces.get((int) (Math.random() * pieces.size()));
                block.setPlaced(i);
                threePieces.set(i, block);
            }
            window.repaint();
            // PICK UP HERE
            gameOver = true;
        }
    }

    // Accessing the three blocks at the begigning of each round


    public ArrayList<Block> getThreePieces() {
        return threePieces;
    }

    public static void main(String[] args) {
        BlockBlast b = new BlockBlast();
        b.play();
    }
    // Get the game stage
    public int getStage(){
        return this.stage;
    }


}


