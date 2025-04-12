// Alex Stoffel
// This Class is my front end
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class BlockBlastView extends JFrame {
    // Instance Variables
    private BlockBlast game;
    private Image background;
    private Image board;
    private Image[] pieces;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 800;

    // Constructor
    public BlockBlastView(BlockBlast b){
        this.game = b;
        board = new ImageIcon("Resources/blockBlastBackground.png").getImage();
        background = new ImageIcon("Resources/mainbackgroundColor.png").getImage();

        // Initialize everything that happens
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Block Blast");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Paint method
    public void paint(Graphics g) {
        // Drawing the main background
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Drawing the board
        g.drawImage(board, 0, 100, WINDOW_WIDTH, WINDOW_HEIGHT - 300, this);

        // Saving the three blocks at the beggining of each round and drawing them AS LONG AS NOT NULL
        if (game.getStage() == 2){
            ArrayList<Block> threePieces = game.getThreePieces();
            for(Block block: threePieces){
                block.draw(g);
            }
        }
    }
}
