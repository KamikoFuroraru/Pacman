package pacman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrinth {

    private ArrayList<String> lines = new ArrayList<String>();
    private int rows, columns;
    private Position pacmanPos;
    private Position ghostPos;
    private BufferedImage view;
    private ArrayList<Position> coins, superCoins;

    public Labyrinth() {
        try {
            view = ImageIO.read(getClass().getResourceAsStream("/00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            coins = new ArrayList<Position>();
            superCoins = new ArrayList<Position>();
            Scanner s = new Scanner(getClass().getResourceAsStream("/labyrinth"));
            int y = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                lines.add(line);
                if (line.contains("4")) {
                    ghostPos = new Position(y, line.indexOf('4'));
                }
                if (line.contains("5")) {
                    pacmanPos = new Position(y, line.indexOf('5'));
                }
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '2') {
                        coins.add(new Position(y, i));
                    } else if (line.charAt(i) == '3') {
                        superCoins.add(new Position(y, i));
                    }
                }
                y++;
            }
            s.close();
            rows = lines.size();
            columns = lines.get(0).length();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(view, 0, 0, null);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char charAt(int row, int column) {
        return lines.get(row).charAt(column);
    }

    public Position getPacmanPos() {
        return pacmanPos;
    }

    public Position getGhostPos() {
        return ghostPos;
    }

    public ArrayList<Position> getCoins() {
        return coins;
    }

    public ArrayList<Position> getSuperCoins() {
        return superCoins;
    }
}