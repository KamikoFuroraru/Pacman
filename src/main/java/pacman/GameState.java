package pacman;

import java.awt.*;

public class GameState {

    public void drawMenu(Graphics g) {
        Font fnt0 = new Font("PacFont", Font.BOLD, 60);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("PAcM9N", 45, 200);

        Font fnt1 = new Font("Minecraft", Font.BOLD, 45);
        g.setFont(fnt1);
        g.drawString("Play", 185, 310);

        Font fnt2 = new Font("Minecraft", Font.BOLD, 45);
        g.setFont(fnt2);
        g.drawString("Quit", 185, 375);
    }

    public void drawOver(Graphics g) {
        Font fnt0 = new Font("PacFont", Font.BOLD, 45);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("GAME OVER", 20, 200);

        Font fnt1 = new Font("Minecraft", Font.BOLD, 40);
        g.setFont(fnt1);
        g.drawString("Replay", 155, 310);

        Font fnt2 = new Font("Minecraft", Font.BOLD, 40);
        g.setFont(fnt2);
        g.drawString("Quit", 185, 375);
    }

    public void drawWin(Graphics g) {
        Font fnt0 = new Font("PacFont", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("YoU wIn!", 45, 200);

        Font fnt1 = new Font("Minecraft", Font.BOLD, 40);
        g.setFont(fnt1);
        g.drawString("Replay", 155, 310);

        Font fnt2 = new Font("Minecraft", Font.BOLD, 40);
        g.setFont(fnt2);
        g.drawString("Quit", 185, 375);
    }
}
