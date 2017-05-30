package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameThread extends Thread {

    private final Game game;
    private boolean running;
    private final ActionListener listener;

    public GameThread(Game game, ActionListener listener) {
        this.listener = listener;
        this.game = game;
        this.running = true;
    }

    public void run() {
        while (running) {
            game.update();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                return;
            }
            if (listener != null)
                listener.actionPerformed(new ActionEvent(this, 0, "Repaint/KeyListener"));
        }
    }
}