// Alex Stoffel
// This class essentially controls for each block which the player will be placing

import javax.swing.*;
import java.awt.*;

public class Block {
    // This will be the template for each block
    private final int[][] piece;
    // Top left and right corners
    private int x;
    private int y;
    // This will be the color of the block
    private final Color color;
    private static final int PIECE_SIZE = 5;
    private static final int SQUARE_SIZE = 55;
    private static final int RGB_VALS = 255;
    private static final int RED_STAR_SIZE = 20;
    // This wil be a stage tells it where it will be placed
    private int state;
    private boolean isPlaced;
    private boolean isBeingDragged;
    private final Image redStar;
    private final BlockBlastView window;

    // Constructor
    public Block(int[][] piece, int x, int y, BlockBlastView window) {
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.state = 0;
        this.isPlaced = false;
        this.isBeingDragged = false;
        this.window = window;
        this.color = new Color((int) (Math.random() * RGB_VALS), (int) (Math.random() * RGB_VALS), (int) (Math.random() * RGB_VALS));
        redStar = new ImageIcon("Resources/redStar.png").getImage();
    }

    // Is Clicked Method && placed false
    public boolean isClicked(int mouse_x, int mouse_y) {
        // Must be clicked from the top left of the box
        if (this.x < mouse_x && this.x + SQUARE_SIZE > mouse_x) {
            return (this.y < mouse_y && this.y + SQUARE_SIZE > mouse_y) && !this.isPlaced;
        }
        return false;
    }

    // Getters and Setters
    // Accessing the piece
    public int getPiece(int row, int col) {
        return this.piece[row][col];
    }

    // Changing x and y
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // State is important for telling the block where to draw itself
    public void setState(int state) {
        this.state = state;
    }

    // Has been placed yet
    public boolean isPlaced() {
        return isPlaced;
    }

    // Getter and setter for the is being dragged variable
    public boolean isBeingDragged() {
        return isBeingDragged;
    }

    public void setIsBeingDragged(boolean dragged) {
        this.isBeingDragged = dragged;
    }

    // Access to the state of the piece
    public int getState() {
        return this.state;
    }

    // Set is placed yet
    public void setPlaced(Boolean bool) {
        this.isPlaced = bool;
    }

    // Getting access to the 2d array and the design
    public int[][] getBlock() {
        return this.piece;
    }

    // Getting access to the color
    public Color getColor() {
        return this.color;
    }

    // Draw method
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        // If the piece hasn't been placed yet
        if (this.state < 3) {
            // Setting it to the correct place in the unplaced pieces board
            this.x = 50 + 250 * this.state;
            this.y = 600;
            drawSquare(g, this.x, this.y);
        } else if (state == 3) { // This means that it has been placed
            drawSquare(g, this.x, this.y);
        }
        g.drawImage(redStar, x, y, RED_STAR_SIZE, RED_STAR_SIZE, window);

    }

    // Drawing the block
    public void drawSquare(Graphics g, int x, int y) {
        for (int i = 0; i < PIECE_SIZE; i++) {
            for (int j = 0; j < PIECE_SIZE; j++) {
                if (this.piece[i][j] == 1) {
                    // Draw the block
                    g.setColor(this.color);
                    g.fillRect(x + SQUARE_SIZE * j, y + SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.WHITE);
                    g.drawRect(x + SQUARE_SIZE * j, y + SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }
}
