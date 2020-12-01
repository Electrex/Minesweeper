package view;


import controller.*;
import model.MinesweeperModel;
import model.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;

public class MinesweeperView {
    private final JButton[][] Minefield;
    private final int ROWS;
    private final int COLUMNS;
    ImageIcon f = new ImageIcon("src/minesweeper_images/images/bomb_flagged.gif");
    ImageIcon s = new ImageIcon("src/minesweeper_images/images/face_smile.gif");
    int buttonHeight = f.getIconHeight();
    int buttonWidth = f.getIconWidth();
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JPanel container;
    JPanel panel;
    JButton face;
    private final BlockingQueue<Message> queue;

    public static MinesweeperView init(BlockingQueue<Message> queue, int[] args) {
        return new MinesweeperView(queue, args[0], args[1]);
    }

    private MinesweeperView(BlockingQueue<Message> queue, int width, int height){
        this.queue = queue;
        this.ROWS = height;
        this.COLUMNS = width;
        face = new JButton(s);
        face.setPreferredSize(new Dimension(s.getIconWidth(), s.getIconHeight()));
        face.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                face.setIcon(s);
                queue.add(new NewGameMessage());
            }
        });
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel2 = new JPanel(new FlowLayout());
        panel2.add(face);
        panel2.setBackground(new Color(179, 179, 179));
        container = new JPanel(new BorderLayout());
        panel1.setSize(500, 500);
        panel1.setLayout(new GridLayout(ROWS, COLUMNS));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        container.add(panel2, BorderLayout.NORTH);
        container.add(panel1, BorderLayout.CENTER);
        panel.add(container);

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
                        case Tile.QUESTION:
                            Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/bomb_question.gif"));
                            break;
                        default:
                            switch (grid[r][c].getType()) {
                                case Tile.BLANK :
                                    Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/num_0.gif"));
                                    break;
                                case Tile.MINE :
                                    Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/bomb_death.gif"));
                                    break;
                                case Tile.NUMBER :
                                    Minefield[r][c] = new JButton(new ImageIcon("src/minesweeper_images/images/num_" + grid[r][c].getNumSurroundingMines() + ".gif"));
                                    break;
                            }
                    }
                    Minefield[r][c].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    int finalR = r;
                    int finalC = c;
                    Minefield[r][c].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            hitButtonPressed(e);
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            hitButtonReleased(e, finalR, finalC, grid);
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

    void hitButtonPressed(MouseEvent e){
        if (SwingUtilities.isLeftMouseButton(e))
            face.setIcon(new ImageIcon("src/minesweeper_images/images/face_ooh.gif"));
    }

    void hitButtonReleased(MouseEvent e, int r, int c, Tile[][] grid){
        if (SwingUtilities.isLeftMouseButton(e)){
            queue.add(new RevealMessage(r, c));
            if (grid[r][c].getType() == Tile.MINE){
                Minefield[r][c].setIcon(new ImageIcon("src/minesweeper_images/images/bomb_death.gif"));
                face.setIcon(new ImageIcon("src/minesweeper_images/images/face_dead.gif"));
            } else {
                face.setIcon(s);
            }
        }
        else if (SwingUtilities.isRightMouseButton(e))
            queue.add(new FlagMessage(r, c));
        else if (SwingUtilities.isMiddleMouseButton(e))
            queue.add(new QuestionMessage(r, c));
    }

    public void dispose() {
        frame.dispose();
    }

    public void gameOver() {
        for(JButton[] b : Minefield){
            for (JButton j: b){
                //j.removeMouseListener(j.getMouseListeners()[0]);
                j.removeMouseListener(j.getMouseListeners()[1]);
            }
        }
    }

    public void gameWon() {
        face.setIcon(new ImageIcon("src/minesweeper_images/images/face_win.gif"));
        for(JButton[] b : Minefield){
            for (JButton j: b){
                //j.removeMouseListener(j.getMouseListeners()[0]);
                j.removeMouseListener(j.getMouseListeners()[1]);
            }
        }
    }
}