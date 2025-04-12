import java.awt.*;

// Alex Stoffel
// This class essentially controls for each block which the player will be placing
public class Block {
    // This will be the template for each block
    private int[][] piece;
    // Top left and right corners
    private int x;
    private int y;
    // Has it been placed yet? 0-2 means not placed yet, 3 means placed)
    int placed;

    // Constructor
    public Block(int[][] piece, int x, int y){
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.placed = 0;
    }

    // Changing x and y
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    // Changing placed
    public void setPlaced(int placed) {
        this.placed = placed;
    }

    // Draw method
    public void draw(Graphics g){
        // If the piece hasnt been placed yet
        if (this.placed < 3){
            // Need to make this draw image
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.PLAIN, 20));
            // NOTE_ THIS IS JUST FOR TESTING
            g.drawString("hello"+placed, 600, 100 + 50*placed);
            System.out.println("hello");

        }

    }
}
