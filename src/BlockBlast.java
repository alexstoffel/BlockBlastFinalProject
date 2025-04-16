// Alex Stoffel
// This class if my backend

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Array;
import java.util.ArrayList;

public class BlockBlast implements MouseListener, MouseMotionListener {
    // Instance variables
    private int[][] board;
    private BlockBlastView window;
    private static final int SLEEP_TIME = 110;
    private static final int BOARD_SIZE = 8;
    private ArrayList<Block> pieces;
    private boolean gameOver;
    private int stage;
    private ArrayList<Block> unplacedPieces;


    // Constructor
    public BlockBlast(){
        window = new BlockBlastView(this);
        gameOver = false;
        stage = 1;
        unplacedPieces = new ArrayList<Block>();

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
            // Setting the three unplaced pieces
            unplacedPieces = this.getThreePieces();
            window.repaint();
            // PICK UP HERE
            gameOver = true;
        }
    }

    // Accessing the three blocks at the begigning of each round


    // Note - this should return an ArrayList but right now is just one block
    public ArrayList<Block> getThreePieces() {
        // ArrayList that will store them
        ArrayList<Block> p = new ArrayList<Block>(3);
        // For loop that will run through 3 times
        for (int i = 0; i < 3; i++){
            p.add(this.pieces.get((int)(Math.random() * this.pieces.size())));
        }
        return p;
    }

    public static void main(String[] args) {
        BlockBlast b = new BlockBlast();
        b.play();
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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
            if(block.isClicked(x, y)){
                block.setX(x);
                block.setY(y);
                System.out.println("dragged");
                window.repaint();
            }

        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //


}


