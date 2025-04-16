import java.awt.*;

// Alex Stoffel
// This class essentially controls for each block which the player will be placing
public class Block {
    // This will be the template for each block
    private int[][] piece;
    // Top left and right corners
    private int x;
    private int y;
    private static final int size = 5;
    private static final int SQUARE_SIZE = 30;
    // This wil be a stage tells it where it will be placed
    private int state;

    // Constructor
    public Block(int[][] piece, int x, int y){
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.state = 0;
    }

    // Changing x and y
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void setState(int state){
        this.state = state;
    }

    // Draw method
    public void draw(Graphics g){
        // If the piece hasn't been placed yet

        g.setColor(Color.WHITE);
        this.x = 50 + 155*this.state;
        this.y = 600;
        // Drawing the block
        for (int i = 0; i < size; i++){
            for( int j = 0; j < size; j++){
                if (this.piece[i][j] == 1){
                    // Draw the block
                    g.drawRect(x + SQUARE_SIZE * j, y + SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }
}
