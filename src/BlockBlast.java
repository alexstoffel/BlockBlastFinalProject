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
    // This variable accounts for the block currently being dragged
    private Block blockBeingDragged;
    private static final int BOARD_SQUARE_SIZE = 55;
    private static final int BOARD_TOP_X = 30;
    private static final int BOARD_TOP_Y = 130;



    // Constructor
    public BlockBlast(){
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
            // Set the state so it knows where to draw itself
            p.get(i).setState(i);
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
        // Set the block being dragged variable
        int x = e.getX();
        int y = e.getY();

        // Loop through all the blocks
        for (Block block: unplacedPieces){
            if (block.isClicked(x, y)){
                blockBeingDragged = block;
                block.setIsBeingDragged(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // If the block is placed in a valid spot
        int x = e.getX();
        int y = e.getY();
        // Find what square this is a part of and click it into place and set X and Y to the top left corner of that square
        x = (x - BOARD_TOP_X) / 55;
        y = (y - BOARD_TOP_Y) / 55;
        // CLick the piece into place
        blockBeingDragged.setX(x);
        blockBeingDragged.setY(y);
        window.repaint();

        // Remove block being dragged from the unplaced pieces
//        for (int i = 0; i < unplacedPieces.size(); i++){
//            if(unplacedPieces.get(i).equals(blockBeingDragged)){
//                // Set the square to null
//                unplacedPieces.set(i, null);
//                break;
//            }
//        }


        // Set the is being dragged to false
        this.blockBeingDragged.setIsBeingDragged(false);
        this.blockBeingDragged = null;
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
        System.out.println("Beginning of drag");

        // Check each block if it is being dragged currently
        for (Block block : this.unplacedPieces){
            if(block.isBeingDragged()){
                // This means it is no longer an "unplaced piece"
                block.setState(3);
                // Updating where the box is to where the mouse is
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


