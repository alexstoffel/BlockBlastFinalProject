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

    // Constructor
    public Block(int[][] piece, int x, int y){
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    // Changing x and y
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    // Draw method
    public void draw(Graphics g){
        // If the piece hasnt been placed yet

        g.setColor(Color.WHITE);
        this.x = 100;
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
