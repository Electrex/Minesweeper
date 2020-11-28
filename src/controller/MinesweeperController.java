package controller;

import model.Tile;
import view.MinesweeperView;
import model.MinesweeperModel;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MinesweeperController implements Runnable {
    private BlockingQueue<Message> queue;
    private MinesweeperView view; // Direct reference to view
    private MinesweeperModel model; // Direct reference to model
    private GameInfo gameInfo; // Direct reference to the state of the Game/Application
    private List<Valve> valves = new LinkedList<Valve>();

    public MinesweeperController(MinesweeperView view, MinesweeperModel model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        this.gameInfo = new GameInfo();
        this.view.repaintView(model);
        valves.add(new DoNewGameValve());
        valves.add(new DoFlagValve());
        valves.add(new DoRevealValve());
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public void run() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            if (gameInfo.getState() != GameInfo.GAME_IN_PROGRESS){
                if ((gameInfo.getState() == GameInfo.GAME_WON)) {
                    view.gameWon();
                } else {
                    view.gameOver();
                }
                break;
            }
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
            model = new MinesweeperModel(9, 9, 10);
            view.repaintView(model);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoFlagValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != FlagMessage.class) {
                return ValveResponse.MISS;
            }
            int row = message.getEvent().getSecond().getFirst();
            int col = message.getEvent().getSecond().getSecond();
            if (model.getGrid()[row][col].getState() != Tile.FLAGGED) {
                model.getGrid()[row][col].setState(Tile.FLAGGED);
                model.numMinesMarked++;
                if (model.getGrid()[row][col].getType() == Tile.MINE){
                    model.numMinesLeft--;
                }
                if (model.numMinesMarked == model.getNumMines() && model.numMinesLeft == 0)
                    gameInfo.setState(GameInfo.GAME_WON);
            }
            else {
                model.getGrid()[row][col].setState(Tile.COVERED);
                model.numMinesMarked++;
                if (model.getGrid()[row][col].getType() == Tile.MINE){
                    model.numMinesLeft++;
                }
                if (model.numMinesMarked == model.getNumMines() && model.numMinesLeft == 0)
                    gameInfo.setState(GameInfo.GAME_WON);
            }
            view.repaintView(model);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoRevealValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != RevealMessage.class) {
                return ValveResponse.MISS;
            }
//            System.out.println("Event code: " + message.getEvent().getFirst() +
//                    " @ Row: " + message.getEvent().getSecond().getFirst() +
//                    " @ Col: " + message.getEvent().getSecond().getSecond());
            int row = message.getEvent().getSecond().getFirst();
            int col = message.getEvent().getSecond().getSecond();
            model.recursiveReveal(row, col);
            view.repaintView(model);
            if (model.isGameOver())
                gameInfo.setState(GameInfo.GAME_OVER);
            return ValveResponse.EXECUTED;
        }
    }
}

