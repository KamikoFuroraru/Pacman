package pacman;

import javax.swing.*;
import java.awt.*;

public class GameLoop extends JPanel {

    private Game game;

    public GameLoop(Game game) {
        this.game = game;
        new GameThread(game, e -> {
            addMouseListener(game);
            addKeyListener(game);
            requestFocus();
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g) {
        game.draw((Graphics2D) g);
    }
}