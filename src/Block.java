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
    private static final int SQUARE_SIZE = 55;
    // This wil be a stage tells it where it will be placed
    private int state;
    private boolean isPlaced;
    private boolean isBeingDragged;

    // Constructor
    public Block(int[][] piece, int x, int y){
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.state = 0;
        this.isPlaced = false;
        this.isBeingDragged = false;
    }

    // Changing x and y
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    // State is important for telling the block where to draw itself
    public void setState(int state){
        this.state = state;
    }

    // Has been placed yet
    public boolean isPlaced(){
        return isPlaced;
    }

    // Getter and setter for the is being dragged variable
    public boolean isBeingDragged() {
        return isBeingDragged;
    }

    public void setIsBeingDragged(boolean dragged) {
        this.isBeingDragged = dragged;
    }

    // Is Clicked Method && placed false
    public boolean isClicked(int mouse_x, int mouse_y){
        // Must be clicked from the top left of the box
        if (this.x < mouse_x && this.x + SQUARE_SIZE > mouse_x){
            if ((this.y < mouse_y && this.y + SQUARE_SIZE > mouse_y) && !this.isPlaced){
                return true;
            }
        }
        return false;
    }


    // Draw method
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        // If the piece hasn't been placed yet
        if (this.state < 3){
            // Setting it to the correct place in the unplaced pieces board
            this.x = 50 + 155*this.state;
            this.y = 600;
            drawSquare(g, this.x, this.y);
        }else if(state == 3){ // This means that it has been placed
            drawSquare(g, this.x, this.y);
        }
    }

    // Drawing the block
    public void drawSquare(Graphics g, int x, int y){
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
