package pacman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pacman {

    private BufferedImage pacmanView;
    private BufferedImage[] ghostsView = new BufferedImage[5];
    private BufferedImage healthView;
    private int pacmanFrame;
    private int ghostsFrame;
    private Move pacman;
    private Move[] ghosts = new Move[4];
    private Labyrinth labyrinth = new Labyrinth();
    private int edible;
    private int health;
    private int score;
    boolean dead = false;
    boolean win = false;

    public Pacman() {
        score = 0;
        health = 3;
        edible = 0;
        pacmanFrame = 0;
        ghostsFrame = 0;

        pacman = new Move(labyrinth.getPacmanPos());
        try {
            pacmanView = ImageIO.read(getClass().getResourceAsStream("/pacman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int m = 1;
        for (int g = 0; g < 4; g++) {
            try {
                ghostsView[g] = ImageIO.read(getClass().getResourceAsStream("/ghost" + m + ".png"));
                ghostsView[4] = ImageIO.read(getClass().getResourceAsStream("/ghost5.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ghosts[g] = new Move(labyrinth.getGhostPos());
            m++;
        }

        try {
            healthView = ImageIO.read(getClass().getResourceAsStream("/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        ghostsFrame++;
        if (ghostsFrame > 2) {
            ghostsFrame = 0;
        }
        moveGhosts(Move.decide(ghosts));

        pacmanFrame++;
        if (pacmanFrame > 5) {
            pacmanFrame = 0;
        }
        movePacman(pacman.reqDir);

        if (labyrinth.getCoins().contains(pacman.pos)) {
            labyrinth.getCoins().remove(pacman.pos);
            score += 5;
        } else if (labyrinth.getSuperCoins().contains(pacman.pos)) {
            labyrinth.getSuperCoins().remove(pacman.pos);
            score += 50;
            edible = 500;
        }

        for (Move ghost: ghosts) {
            if (edible > 0) {
                if (ghost.touching(ghost.pos, pacman.pos)) {
                    score += 100;
                    ghost.pos.y = labyrinth.getGhostPos().y;
                    ghost.pos.x = labyrinth.getGhostPos().x;
                }
                edible--;
            } else {
                if (ghost.touching(ghost.pos, pacman.pos)) {
                    health--;
                    if (health == 0) dead = true;
                    pacman.pos.y = labyrinth.getPacmanPos().y;
                    pacman.pos.x = labyrinth.getPacmanPos().x;
                }
            }
        }
        if (labyrinth.getCoins().isEmpty() && labyrinth.getSuperCoins().isEmpty()) {
            win = true;
        }
    }

    public void moveGhosts(int[] reqDirs) {
        for (int i = 0; i < 4; i++) {
            Move ghost = ghosts[i];
            ghost.reqDir = reqDirs[i];
            if (ghost.move(ghost.reqDir, ghost)) {
                ghost.curDir = ghost.reqDir;
            } else {
                ghost.move(ghost.curDir, ghost);
            }
        }
    }

    public void movePacman(int reqDir) {
        if (pacman.move(reqDir, pacman)) {
            pacman.curDir = reqDir;
        } else {
            pacman.move(pacman.curDir, pacman);
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        for (Position coin : labyrinth.getCoins()) {
            g.fillOval(coin.x * 2 - 3, coin.y * 2 - 3, 6, 6);
        }
        for (Position superCoin : labyrinth.getSuperCoins()) {
            g.fillOval(superCoin.x * 2 - 6, superCoin.y * 2 - 6, 12, 12);
        }
        //пак
        g.drawImage(pacmanView.getSubimage((pacmanFrame / 2) * 30, pacman.curDir * 30, 28, 28), pacman.pos.x * 2 - 14, pacman.pos.y * 2 - 14, null);
        //
        for (int i = 0; i < 4; i++) {
            // призраки
            Move ghost = ghosts[i];
            if (edible == 0) {
                g.drawImage(ghostsView[i].getSubimage((ghostsFrame / 2) * 28, ghost.curDir * 28, 28, 28), ghost.pos.x * 2 - 14, ghost.pos.y * 2 - 14, null);
            } else {
                g.drawImage(ghostsView[4].getSubimage((ghostsFrame / 2) * 28, ghost.curDir * 28, 28, 28), ghost.pos.x * 2 - 14, ghost.pos.y * 2 - 14, null);
            }
        }
        //счет
        g.setColor(Color.white);
        g.setFont(new Font("Minecraft", Font.PLAIN, 20));
        g.drawString("Score: " + Integer.toString(score), 10, 550);
        //жизы
        switch (health) {
            case 3:
                g.drawImage(healthView, 360, 530, null);
                g.drawImage(healthView, 380, 530, null);
                g.drawImage(healthView, 400, 530, null);
                break;
            case 2:
                g.drawImage(healthView, 360, 530, null);
                g.drawImage(healthView, 380, 530, null);
                break;
            case 1:
                g.drawImage(healthView, 360, 530, null);
                break;
        }
    }

    public Move getPacman() {
        return pacman;
    }

    public int getScore() { return score; }
}