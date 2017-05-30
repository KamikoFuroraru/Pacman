package pacman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LabyrinthCreator {

    public static void main(String[] args) throws IOException {
        //считываем лабиринт из строки
        ArrayList<String> lines = new ArrayList<String>();
        Scanner s = new Scanner(new File("resources/labyrinth"));
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }
        s.close();
        int rows = lines.size();
        int columns = lines.get(0).length();
        int width = columns * 2;
        int height = rows * 2;
        //перерисовка лабиринта на картинку
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        for (int row = 0; row < rows; row++)
            for (int column = 0; column < columns; column++) {
                if (lines.get(row).charAt(column) != '0') {
                    g.fillRect(column * 2 - 14, row * 2 - 14, 28, 28);
                }
            }
        g.dispose();
        ImageIO.write(image, "png", new File("resources/0.png"));
    }
}
