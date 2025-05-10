// Alex Stoffel
// This Class is my front end

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlockBlastView extends JFrame {
    // Instance Variables
    private final BlockBlast game;
    private final Image background;
    private final Image board;
    // Array of colors
    private final Color[][] colors;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 900;
    private static final int BOARD_TOP_X = 30;
    private static final int BOARD_TOP_Y = 130;
    public static final int SQUARE_SIZE = 55;
    private static final int BOARD_WIDTH = 500;
    private final Color[] gameColors;

    // Constructor
    public BlockBlastView(BlockBlast b) {
        this.game = b;
        board = new ImageIcon("Resources/blockBlastBackground.png").getImage();
        background = new ImageIcon("Resources/mainbackgroundColor.png").getImage();
        this.colors = new Color[8][8];
        // THis will give me ineteresting colors for fonts
        this.gameColors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA};

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
        g.drawImage(board, 0, 100, BOARD_WIDTH, WINDOW_HEIGHT - 400, this);

        // Saving the three blocks at the beggining of each round and drawing them AS LONG AS NOT NULL
        if (game.getStage() == 2) {
            ArrayList<Block> threePieces = game.getUnplacedPieces();
            for (int i = 0; i < 3; i++) {
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

        // Print our the score
        String score = "SCORE= " + game.getScore();
        String multiplier = "MULTIPLIER = " + game.getMultiplier();
        g.setFont(new Font("roboto", Font.BOLD, 40));
        for (int i = 0; i < score.length(); i++) {
            // Get the multicolor part (this looks cool)
            g.setColor(gameColors[i % gameColors.length]);
            g.drawString(score.substring(i, i + 1), BOARD_WIDTH + 10 + i * 25, 200);
        }
        // Draw the multiplier
        g.setFont(new Font("roboto", Font.BOLD, 20));
        g.setColor(Color.white);
        g.drawString(multiplier, BOARD_WIDTH + 10, 270);

        // Print Game Over if Game is Over if it is a valid time to do so
        if (game.getUnplacedPieces() != null && game.checkGameOver()) {
            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 50, 600);
        }
    }

    // This method will paint the current board
    public void paintBoard(Graphics g) {
        int[][] board = game.getBoard();

        // Print out the board
        for (int i = 0; i < BlockBlast.BOARD_SIZE; i++) {
            for (int j = 0; j < BlockBlast.BOARD_SIZE; j++) {
                // If it is a filled square
                if (board[i][j] == 1) {
                    // Drawing the squares
                    g.setColor(colors[i][j]);
                    g.fillRect(BOARD_TOP_X + j * SQUARE_SIZE, BOARD_TOP_Y + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.WHITE);
                    g.drawRect(BOARD_TOP_X + j * SQUARE_SIZE, BOARD_TOP_Y + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    // Setter which will change colors of the blocks
    public void setColor(int row, int col, Color c) {
        this.colors[row][col] = c;
    }

}
