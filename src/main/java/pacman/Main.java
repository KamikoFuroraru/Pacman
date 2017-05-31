package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends Game {
    private Pacman pacman;
    private Labyrinth labyrinth;
    private GameState gameState;

    private enum STATE {
        MENU,
        GAME,
        OVER,
        WIN
    }

    private STATE State = STATE.MENU;

    public Main() {
        gameState = new GameState();
        pacman = new Pacman();
        labyrinth = new Labyrinth();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key >= 37 && key <= 40) {
            pacman.getPacman().reqDir = key - 37;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (State == STATE.MENU) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= 185 && mx <= 280) {
                if (my >= 275 && my <= 310) State = STATE.GAME;
            }
            if (mx >= 185 && mx <= 270) {
                if (my >= 340 && my <= 375) System.exit(1);
            }
        }
        if (State == STATE.OVER || State == STATE.WIN) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= 155 && mx <= 303) {
                if (my >= 280 && my <= 310) {
                    pacman.dead = false;
                    pacman.win = false;
                    pacman = new Pacman();
                    labyrinth = new Labyrinth();
                    State = STATE.GAME;
                }
            }
            if (mx >= 185 && mx <= 250) {
                if (my >= 350 && my <= 375) System.exit(1);
            }
        }
    }

    public void update() {
        if (State == STATE.GAME) {
            if (!pacman.dead && !pacman.win) {
                pacman.update();
            }
            if (pacman.dead) State = STATE.OVER;
            if (pacman.win) State = STATE.WIN;
        }
    }

    public void draw(Graphics2D g) {
        if (State == STATE.GAME) {
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            labyrinth.draw(g);
            pacman.draw(g);
        } else if (State == STATE.MENU) {
            g.setColor(Color.orange);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            gameState.drawMenu(g);
        } else if (State == STATE.OVER) {
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Minecraft", Font.BOLD, 25));
            g.drawString("Score: " + Integer.toString(pacman.getScore()), 160, 550);
            gameState.drawOver(g);
        } else if (State == STATE.WIN) {
            g.setColor(Color.orange);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.black);
            g.setFont(new Font("Minecraft", Font.BOLD, 25));
            g.drawString("Score: " + Integer.toString(pacman.getScore()), 160, 550);
            gameState.drawWin(g);
        }
    }

    public static void main(String[] args) {
        start(new Main());
    }
}