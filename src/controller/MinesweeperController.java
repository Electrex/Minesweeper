package controller;

import view.MinesweeperView;
import model.MinesweeperModel;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MinesweeperController {
    private BlockingQueue<Message> queue;
    private MinesweeperView view; // Direct reference to view
    private MinesweeperModel model; // Direct reference to model
    private GameInfo gameInfo; // Direct reference to the state of the Game/Application

    private List<Valve> valves = new LinkedList<Valve>();

    public MinesweeperController(MinesweeperView view, MinesweeperModel model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        valves.add(new DoNewGameValve());
        valves.add(new DoHitValve());
    }

    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take(); // <--- take next message from the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Look for a Valve that can process a message
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    private void updateGameInfo() {
        gameInfo.setState(GameInfo.GAME_IN_PROGRESS);
    }

    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        ValveResponse execute(Message message);
    }

    private class DoNewGameValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise it means that it is a NewGameMessage message
            // actions in Model
            // actions in View
            view.repaintView();
            return ValveResponse.EXECUTED;
        }
    }

    private class DoHitValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != HitMessage.class) {
                return ValveResponse.MISS;
            }
            System.out.println("Event code: " + message.getEvent().getFirst() +
                    " @ Row: " + message.getEvent().getSecond().getFirst() +
                    " @ Col: " + message.getEvent().getSecond().getSecond());
            // otherwise message is of HitMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }
}

