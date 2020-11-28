import controller.MinesweeperController;
import controller.Message;
import model.MinesweeperModel;
import view.MinesweeperView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MinesweeperMain {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static MinesweeperView view;
    private static MinesweeperModel model;

    public static void main(String[] args) {
        view = view.init(queue);
        model = new MinesweeperModel(12, 12, 15);
        MinesweeperController controller = new MinesweeperController(view, model, queue);
        controller.run();
        //new Thread(controller).start();
        view.dispose();
        queue.clear();
    }

}

