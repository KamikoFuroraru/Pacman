package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Game implements KeyListener {

    public static int WIDTH = 463, HEIGHT = 600;

    public static void start(final Game game) {
        SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Pacman");
                    frame.setSize(WIDTH, HEIGHT);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setContentPane(new GameLoop(game));
                }
        );
    }

    abstract public void update();

    abstract public void draw(Graphics2D g);

    abstract public void keyPressed(KeyEvent e);

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}