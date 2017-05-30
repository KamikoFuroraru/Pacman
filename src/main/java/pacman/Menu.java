package pacman;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(200, 150, 100, 50 );
    public Rectangle quitButton = new Rectangle(200, 250, 100, 50 );

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Pacman", 150, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 35);
        g2d.draw(playButton);
        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
        g2d.draw(quitButton);
    }
}
