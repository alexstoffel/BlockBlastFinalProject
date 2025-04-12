// Alex Stoffel
// This class if my backend

import java.util.ArrayList;

public class BlockBlast {
    // Instance variables
    private int[][] board;
    private BlockBlastView window;
    private static final int SLEEP_TIME = 110;
    private static final int BOARD_SIZE = 8;
    private ArrayList<Block> pieces;

    // Constructor
    public BlockBlast(){
        window = new BlockBlastView(this);

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

    }

    // Action performed
    public void play() {
        window.repaint();
    }

    public static void main(String[] args) {
        BlockBlast b = new BlockBlast();
        b.play();
    }

}


