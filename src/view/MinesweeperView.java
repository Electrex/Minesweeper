package view;


import controller.FlagMessage;
import controller.Message;
import controller.RevealMessage;
import model.MinesweeperModel;
import model.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;

public class MinesweeperView {
    private JButton[][] Minefield;
    private final int ROWS = 12;
    private final int COLUMNS = 12;
    ImageIcon f = new ImageIcon("src/minesweeper_images/images/bomb_flagged.gif");
    int buttonHeight = f.getIconHeight();
    int buttonWidth = f.getIconWidth();
    JFrame frame;
    JPanel panel1;
    JPanel panel;
    private BlockingQueue<Message> queue;

    public static MinesweeperView init(BlockingQueue<Message> queue) {
        return new MinesweeperView(queue);
    }

    private MinesweeperView(BlockingQueue<Message> queue){
        this.queue = queue;
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setSize(500, 500);
        panel1.setLayout(new GridLayout(ROWS, COLUMNS));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(panel1);

        Minefield = new JButton[ROWS][COLUMNS];
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void repaintView(MinesweeperModel model){
        panel1.removeAll();
        Tile[][] grid = model.getGrid();
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                try {
                    switch (grid[r][c].getState()) {
                        case Tile.COVERED:
                            Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/blank.gif"));
                            break;
                        case Tile.FLAGGED:
                            Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/bomb_flagged.gif"));
                            break;
                        default:
                            switch (grid[r][c].getType()) {
                                case Tile.BLANK : Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/num_0.gif"));
                                case Tile.MINE : Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/bomb_death.gif"));
                                case Tile.NUMBER : Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/num_" + grid[r][c].getNumSurroundingMines() + ".gif"));
                            }
                    }
                    Minefield[r][c].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    int finalR = r;
                    int finalC = c;
                    Minefield[r][c].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            hitButtonPressed(e, finalR, finalC);
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
                panel1.add(Minefield[r][c]);
            }
        }
        //panel1.repaint();
        frame.repaint();
        //frame.add(panel);
        frame.pack();
//        frame.setVisible(true);
    }

    void hitButtonPressed(MouseEvent e, int r, int c){
        if (SwingUtilities.isLeftMouseButton(e))
            queue.add(new RevealMessage(r, c));
        else if (SwingUtilities.isRightMouseButton(e))
            queue.add(new FlagMessage(r, c));
//        else if (SwingUtilities.isMiddleMouseButton(e))
//            queue.add(new QuestionMessage(r, c));
    }

    public void dispose() {
        frame.dispose();
    }
}