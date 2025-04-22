// Alex Stoffel
// This Class is my front end
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlockBlastView extends JFrame {
    // Instance Variables
    private BlockBlast game;
    private Image background;
    private Image board;
    private Image[] pieces;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final int BOARD_TOP_X = 30;
    private static final int BOARD_TOP_Y = 130;
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 55;
    private static final int BOARD_WIDTH = 500;


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
        g.drawImage(board, 0, 100, BOARD_WIDTH, WINDOW_HEIGHT - 300, this);

        // Saving the three blocks at the beggining of each round and drawing them AS LONG AS NOT NULL
        if (game.getStage() == 2){
            ArrayList<Block> threePieces = game.getUnplacedPieces();
            for(int i = 0; i < 3; i++){
                // Draw the block only if it is unplaced
                if (threePieces.get(i) != null && !threePieces.get(i).isPlaced()) {
                    threePieces.get(i).draw(g);
                }
            }
        }

        // Paint the board if it is not null
        if (game.getBoard() != null) {
            paintBoard(g);
        }

        // Print Game Over if Game is Over if it is a valid time to do so
        if (game.getUnplacedPieces() != null && game.checkGameOver()){
            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 50, 600);
        }
    }

    // This method will paint the current board
    public void paintBoard(Graphics g){
        int[][] board = game.getBoard();

        // Print out the board
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                // If it is a filled square
                if (board[i][j] == 1) {
                    // Drawing the squares
                    g.setColor(Color.RED);
                    g.fillRect(BOARD_TOP_X + j * SQUARE_SIZE, BOARD_TOP_Y + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.WHITE);
                    g.drawRect(BOARD_TOP_X + j * SQUARE_SIZE, BOARD_TOP_Y + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }
}
