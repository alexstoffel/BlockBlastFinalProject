// Alex Stoffel
// This class essentially controls for each block which the player will be placing
public class Block {
    // This will be the template for each block
    private int[][] piece;
    // Top left and right corners
    private int x;
    private int y;

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
}
