package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends Game {
    private Pacman pacman;
    private Labyrinth labyrinth;

    public Main() {
        pacman = new Pacman();
        labyrinth = new Labyrinth();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key >= 37 && key <= 40) {
            pacman.getPacman().reqDir = key - 37;
        }
    }

    public void update() {
        if (!pacman.dead && !pacman.win) {
            pacman.update();
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        labyrinth.draw(g);
        pacman.draw(g);

        if (pacman.dead) {
            g.setColor(new Color(100, 100, 100, 100));
            g.fillRect(0,0, WIDTH, HEIGHT);
            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.drawString("Game Over", 100, 300);
        }

        if (pacman.win) {
            g.setColor(new Color(100, 100, 100, 100));
            g.fillRect(0,0, WIDTH, HEIGHT);
            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.drawString("You win!", 140, 255);
        }

    }

    public static void main(String[] args) {
        start(new Main());
    }
}