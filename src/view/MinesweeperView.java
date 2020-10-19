package view;


import controller.HitMessage;
import controller.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class MinesweeperView extends JFrame {
    private JButton[][] Minefield;
    private final int ROWS = 10;
    private final int COLUMNS = 10;
    ImageIcon q = new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_question.gif");
    ImageIcon f = new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_flagged.gif");
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
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                try {
                    if (r % 2 == 0 && c % 2 == 0)
                        Minefield[r][c] = new JButton(new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_question.gif"));
                    else
                        Minefield[r][c] = new JButton(new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_flagged.gif"));
                    Minefield[r][c].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    int finalR = r;
                    int finalC = c;
                    Minefield[r][c].addActionListener(e -> hitButtonPressed(finalR, finalC));
                } catch (Exception e){
                    e.printStackTrace();
                }
                panel1.add(Minefield[r][c]);
            }
        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void repaintView(){
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                try {
                    if (r % 2 == 0 && c % 2 == 0)
                        Minefield[r][c] = new JButton(new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_question.gif"));
                    else
                        Minefield[r][c] = new JButton(new ImageIcon("C:\\Users\\Yusuf Mostafa\\IdeaProjects\\CS151\\Minesweeper\\src\\minesweeper_images\\images\\bomb_flagged.gif"));
                    Minefield[r][c].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    int finalR = r;
                    int finalC = c;
                    Minefield[r][c].addActionListener(e -> hitButtonPressed(finalR, finalC));
                } catch (Exception e){
                    e.printStackTrace();
                }
                panel1.add(Minefield[r][c]);
            }
        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    void hitButtonPressed(int r, int c){
        queue.add(new HitMessage(r, c));
    }
}