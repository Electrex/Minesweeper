import controller.MinesweeperController;
import controller.Message;
import model.MinesweeperModel;
import view.MinesweeperView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MinesweeperMain {
    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    final static int WIDTH = 15, HEIGHT = 15, NUM_MINES = 20;

    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel(WIDTH, HEIGHT, NUM_MINES);
        MinesweeperView view = MinesweeperView.init(queue, WIDTH, HEIGHT);
        MinesweeperController controller = new MinesweeperController(view, model, queue);
        controller.run();
        //new Thread(controller).start();
        view.dispose();
        queue.clear();
    }

}

