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

public class MinesweeperView extends JFrame {
    private JButton[][] Minefield;
    private final int ROWS = 9;
    private final int COLUMNS = 9;
    ImageIcon f = new ImageIcon("src\\minesweeper_images\\images\\bomb_flagged.gif");
    int buttonHeight = f.getIconHeight();
    int buttonWidth = f.getIconWidth();
    JFrame frame;
    JPanel panel1;
    JPanel panel;
    private BlockingQueue<Message> queue;

    public static MinesweeperView init(BlockingQueue<Message> queue) {
        return new MinesweeperView(queue);
    }

    public MinesweeperView(BlockingQueue<Message> queue){
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
//        for (int r = 0; r < ROWS; r++){
//            for (int c = 0; c < COLUMNS; c++){
//                try {
//                    if (r % 2 == 0 && c % 2 == 0)
//                        Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\bomb_question.gif"));
//                    else
//                        Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\bomb_flagged.gif"));
//                    Minefield[r][c].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
//                    int finalR = r;
//                    int finalC = c;
//                    Minefield[r][c].addActionListener(e -> hitButtonPressed(finalR, finalC));
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                panel1.add(Minefield[r][c]);
//            }
//        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void repaintView(MinesweeperModel model){
        panel1.removeAll();
        Tile[][] grid = model.getGrid();
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                try {
                    //System.out.println("[" + r + "][" + c + "] = " + grid[r][c].getState());
                    if (grid[r][c].getState() == Tile.COVERED)
                        Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\blank.gif"));
                    else if (grid[r][c].getState() == Tile.FLAGGED)
                        Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\bomb_flagged.gif"));
                    else {
                        if (grid[r][c].getType() == Tile.BLANK)
                            Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_0.gif"));
                        else if (grid[r][c].getType() == Tile.MINE)
                            Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\bomb_wrong.gif"));
                        else if (grid[r][c].getType() == Tile.NUMBER){
                            if (grid[r][c].getNumSurroundingMines() == 1)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_1.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 2)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_2.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 3)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_3.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 4)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_4.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 5)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_5.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 6)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_6.gif"));
                            else if (grid[r][c].getNumSurroundingMines() == 7)
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_7.gif"));
                            else
                                Minefield[r][c] = new JButton(new ImageIcon("src\\minesweeper_images\\images\\num_8.gif"));
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
        panel1.repaint();
        //frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    void hitButtonPressed(MouseEvent e, int r, int c){
        if (SwingUtilities.isLeftMouseButton(e))
            queue.add(new RevealMessage(r, c));
        else if (SwingUtilities.isRightMouseButton(e))
            queue.add(new FlagMessage(r, c));
//        else if (SwingUtilities.isMiddleMouseButton(e))
//            queue.add(new QuestionMessage(r, c));
    }
}